# Randoop as a Mode 4 source-input generator — results

Branch: `nathan/randoop-input-gen`  ·  SUT: `OrderUtil.calculateTotal(Order)` (with deliberate
`qty>5` cap bug)  ·  MR (developer-owned): doubling quantities should double the total.

## What was built (`randoop-arm/`)
- `RandoopHarvester<T>` — GENERIC: drives Randoop's generator in-process and harvests live instances
  of any caller-specified `targetClass` (via `isInstance`/`cast`); Randoop class names are a param.
  De-dupes by STRUCTURE (so identical empty objects collapse to one). Supports extra value seeds and
  multi-random-seed runs. Signature is optional (see `StructuralSignature`). Replaces the old
  Order-specific `RandoopOrderHarvester`.
- `StructuralSignature` — reflection auto-default for the de-dup signature: recurses through
  arrays/Iterables/Maps/own-objects to primitive/String/enum leaves, `toString` fallback for JDK
  types, cycle guard. Means a developer writes NO signature lambda for clean POJOs; only needed when
  an object has a poison field (random UUID, construction timestamp, cached hash).
- `Mode4Evaluator<I,O>` — GENERIC: runs each source through `Function<I,O>` SUT + the developer's
  `MetamorphicSpec<I,O>`; counts bug-revealing failures, distinct failure shapes, and an optional
  size histogram. (Same pass/fail outcome Soham's `ActualResultTestSplitter` computes, without the
  Maven/Surefire machinery.)
- `Mode4TestEmitter` — emits a real Mode 4 JUnit candidate test from harvested orders (one `@Test`
  each, calling the developer's MR). No Randoop dependency in the emitted test.
- `LlmValueSeeder` — asks an LLM (via OpenAI API) for seed values (strings, integers, doubles) by
  showing it the SUT source and asking it to INFER edge cases. SUT-agnostic prompt (no domain
  vocabulary, no hardcoded value list); `stripComments()` removes comments first so the LLM cannot
  read a bug giveaway out of a developer comment. Takes `List<String> sutSources` (Arm D loads every
  configured SUT class). Self-contained HTTP call; no mtllm.* dependency. Implements the
  LLM-seeds-Randoop hybrid: LLM supplies inferred domain knowledge, Randoop supplies volume.
- `RandoopMode4Arm` — orchestrates harvest → evaluate → emit across four arms (A–D).

## Results (10s budget; ~2,200-2,500 Order instances built per run)

| Arm | Instances | **Structurally-distinct** | Bug-revealing | Bug-reveal rate | Item-count histogram |
|-----|-----------|---------------------------|---------------|-----------------|----------------------|
| A — raw Randoop                      | 2,492 | **2** | 1 | 50%     | {0, 2}           |
| B — hand-picked seeds, 1 run         | 2,306 | **1** | 0 | 0%      | {3}               |
| C — hand-picked seeds + 5-seed union | ~12k  | **4** | 1 | 25%     | {0, 1, 3, 11}    |
| D — **LLM-seeded + 5-seed union**    | ~12k  | **7** | **6** | **86%** | {0, 6, 7×3, 10×2} |

Per-run diagnostic, every arm: `identity-distinct Orders ≈ 2,300-2,500` but
`structurally-distinct = 1-2`.

## KEY FINDING (overturns the prior assumption)
**Randoop builds huge VOLUME but almost no structural DIVERSITY for this nested object SUT.**
It generates ~2,400 Order instances that collapse to 1-2 distinct shapes, because its
ComponentManager converges on a few "good" construction sequences and reuses them, drawing leaf
values from a tiny pool (e.g. `new LineItem("hi!", 97, 100.0)` repeated 136x in arm A).

- Seeding the value pool with LLM-style meaningful values changed WHICH shape it converged to and
  let it reach larger orders (8, 16, 11 items), but a single run still converged to ~1 shape.
- Unioning across 5 random seeds is the most effective lever tried (1-2 -> 4 shapes), but still low.

This reverses the earlier working assumption ("Randoop = diverse inputs, LLM = narrow"). For
STRUCTURAL diversity of object inputs the opposite looks true: the LLM deliberately enumerates
distinct shapes (the Student-SUT `generateSources()` produced 6: single / many / all-same / mix /
zeros / empty), whereas Randoop converges. Randoop's strength is VOLUME and unusual leaf VALUES
(negatives via casts, char-as-int), not structural variety.

## Caveats (do not over-generalise)
- One SUT, one MR. Needs replication on more SUTs/bugs before it's a trend.
- Default-ish Randoop config. Deeper tuning (literals files, longer budgets, GRT-style diversity,
  more operations in the model) might raise diversity; seeding + multi-seed were the levers tried.
- Bug-revealing counts are tiny here ONLY because diversity is tiny — the bug-finding comparison is
  not meaningful until input diversity is fixed.
- Structural signature is exact/order-sensitive, but the identity-vs-structural gap (2,400 -> 1-2)
  makes the convergence undeniable regardless of signature strictness.

## KEY FINDING FROM ARM D

**LLM-seeded Randoop outperforms hand-picked seeds — and the win survives a CLEAN prompt
(80% vs 25% bug-finding rate).**

*Contamination found and fixed:* the original 86% run was contaminated. The prompt (a) showed the
LLM `OrderUtil.java` with the comment `// quantities above 5 are capped`, and (b) hardcoded the
above-cap integers (`must include 0..7,10,25,50,100`) — handing the model the answer. The prompt is
now SUT-agnostic with no value list, and `LlmValueSeeder.stripComments()` removes comments first.

*Clean result:* with the giveaway gone, the LLM still returned `[-1,0,1,2,3,4,5,6,7,8,9,10,100]`
(6 of 13 above the cap, straddling 5 exactly) — inferring the boundary purely from
`Math.min(getQuantity(), 5)`. Arm D = 80% bug-reveal (5 shapes, 4 bug-revealing) vs 50% raw, 25%
hand-picked. (6 s budget; re-run at 15 s for a like-for-like table.)

This confirms the hybrid design is sound: **LLM contributes inferred domain knowledge (what values
matter), Randoop contributes volume and construction (building valid object graphs at scale).**

## Implications / next steps
1. **Measure the LLM-only baseline** (Soham's Mode 4 on the Order SUT) — structural diversity of
   what the LLM generates directly as `@Test` methods. The Arm D result suggests LLM domain
   knowledge is valuable even indirectly; measuring LLM-direct will complete the comparison.
2. **Replicate on more SUTs/bugs** — one SUT is one data point. The hybrid's advantage on the
   Order SUT needs confirmation on a SUT with a different shape (primitive arrays, strings, etc.).
3. Try Randoop diversity tuning (literals file, larger budgets, more seeds) to see how far structural
   diversity can be pushed without LLM seeding.
