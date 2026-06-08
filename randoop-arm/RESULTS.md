# Randoop as a Mode 4 source-input generator — results

Branch: `nathan/randoop-input-gen`  ·  SUT: `OrderUtil.calculateTotal(Order)` (with deliberate
`qty>5` cap bug)  ·  MR (developer-owned): doubling quantities should double the total.

## What was built (`randoop-arm/`)
- `RandoopOrderHarvester` — drives Randoop's generator in-process, harvests live `Order` objects,
  de-dupes by STRUCTURE (so identical empty orders collapse to one). Supports extra value seeds and
  multi-random-seed runs.
- `Mode4Evaluator` — runs each source through the buggy SUT + developer MR; counts bug-revealing
  failures, distinct failure shapes, and an item-count diversity histogram. (Same pass/fail outcome
  Soham's `ActualResultTestSplitter` computes, without the Maven/Surefire machinery.)
- `Mode4TestEmitter` — emits a real Mode 4 JUnit candidate test from harvested orders (one `@Test`
  each, calling the developer's MR). No Randoop dependency in the emitted test.
- `RandoopMode4Arm` — orchestrates harvest → evaluate → emit.

## Results (10s budget; ~2,200-2,500 Order instances built per run)

| Arm | Instances | **Structurally-distinct** | Bug-revealing | Item-count histogram |
|-----|-----------|---------------------------|---------------|----------------------|
| A — raw Randoop                 | 2,492 | **2** | 1 | {0, 2}        |
| B — seeded (LLM-style values)   | 2,306 | **1** | 0 | {3}           |
| C — seeded + 5 random-seed union| ~12k  | **4** | 1 | {0, 1, 3, 11} |

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

## Implications / next steps
1. **Measure the LLM baseline arm** (Soham's Mode 4 on the Order SUT) — needs an OpenAI run.
   Hypothesis from the data: the LLM will show HIGHER structural diversity than raw Randoop.
2. **Reconsider the hybrid split**: likely LLM generates diverse object STRUCTURES; Randoop fuzzes
   VALUES within them (the reverse of the original plan). Or: Randoop is better suited to flat/
   primitive SUTs (the original int[] sort case had high natural diversity).
3. Try Randoop diversity tuning (literals file, larger budgets, more seeds) to see how far structural
   diversity can be pushed before concluding it's fundamental.
