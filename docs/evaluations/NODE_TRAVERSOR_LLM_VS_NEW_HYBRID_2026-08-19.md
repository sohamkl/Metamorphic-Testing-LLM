# NodeTraversor.filter: LLM vs NEW_HYBRID - 2026-08-19

## Metamorphic relation tested

- SUT: `NodeTraversor.filter(NodeFilter, Node)`
- MR provider: `DEV`
- Transformation: deep-clone a non-null detached DOM root and use a fresh, behaviorally equivalent deterministic `NodeFilter`.
- Expected relation: both executions return the same terminal `FilterResult` and leave the original and cloned roots with identical `outerHtml`.
- Count upper limit: 50
- Java release: 17, using JDK 25 to run Maven
- PIT target: `org.jsoup.select.NodeTraversor`

The YAML also describes equal callback sequences and requires a configured non-`CONTINUE` action to occur. The current developer MR implementation does not record or assert callback sequences and does not directly enforce action occurrence; it compares only terminal results and final DOMs.

## Results

| Measure | LLM | NEW_HYBRID |
|---|---:|---:|
| Passing generated tests | 44 | 42 |
| Failing generated tests | 0 | 0 |
| Concrete Randoop seeds | N/A | 50 |
| Synthesized callback policies | N/A | 17 |
| Mutated line coverage | 64/93 (69%) | 66/93 (71%) |
| Generated mutants | 58 | 58 |
| Killed | 11 | 11 |
| Survived | 27 | 30 |
| No coverage | 20 | 17 |
| PIT mutation score | 19% | 19% |
| PIT test strength | 29% | 27% |
| Mutation-test executions | 941 | 926 |

## Source-input quality

### LLM

- Generated distinct explicit HTML strings for all 44 tests.
- Covered single nodes, linear trees, branching and wide trees, mixed-depth trees, and nesting up to depth five.
- Covered `CONTINUE`, `SKIP_CHILDREN`, `SKIP_ENTIRELY`, `REMOVE`, and `STOP` from head and tail callbacks, including non-`CONTINUE` actions at the root.
- Used simple attribute-targeted head and tail policies. These are readable, but mostly select one `data-act` node and therefore explore limited callback state.
- One test contains a duplicated call to `assertRelation`; it is harmless but redundant.

### NEW_HYBRID

- Generated 50 Randoop seeds using 17 automatically synthesized `NodeFilter` callback policies.
- The seeds provide real jsoup and callback construction sequences, although cyclic DOM serialization appears as `"<cycle>"` in JSON and the primitive values are not themselves meaningful DOM scenarios.
- The final 42 tests use reusable programmatic builders for progressively deeper and wider trees and a path-based filter policy that targets exact traversal positions.
- Covered every `FilterResult`, internal and root skip/remove paths, removal before a sibling and as the final child, and head/tail stopping with remaining work.
- It omitted the explicit root-`STOP` case present in the LLM suite.
- The final tests do not directly replay the Randoop policies. The LLM uses the seeds as API-grounded examples and constructs cleaner final `PathPolicy` cases.

## Mutation differences

NEW_HYBRID covered three mutants that LLM did not:

- Line 134: negated the condition controlling an upward `tail` callback.
- Line 137: replaced a `STOP` return with `null` in the upward traversal path.
- Line 143: removed `Node.remove()` after moving to the parent.

All three survived. Consequently, NEW_HYBRID improved line and mutant coverage but did not improve mutation detection. Its displayed test strength is lower because it covered three additional surviving mutants, increasing the covered-mutant denominator.

## Interpretation

NEW_HYBRID produced the better structural traversal coverage, helped by synthesized callbacks and path-targeted policies. LLM produced two more tests, more explicit root-action cases, and a slightly higher test-strength percentage. Both killed exactly the same 11 mutants, so neither is clearly stronger at fault detection for this MR.

The main limit is the oracle, not the test count. A mutant can alter traversal identically for the source DOM and its clone and still satisfy clone equivalence. Strengthening the developer MR to compare ordered head/tail traces and explicitly validate that the intended action occurred would make these inputs more discriminating. Additional independent MRs are still needed for mutations that preserve clone equivalence.

As with the Ta4j comparison, each mode independently inferred its structured input domain. Persisting one inferred domain and reusing it across modes would provide a stricter controlled experiment.

## Commands

Generation:

```bash
mvn -Pjsoup -DskipTests compile exec:java \
  -Dexec.mainClass=mtllm.OpenaiRunner \
  -Dexec.args=examples/jsoup/mt-testing/prompt1.yaml
```

Focused PIT run:

```bash
mvn -Pjsoup,pitest-jsoup \
  process-classes test-compile \
  org.pitest:pitest-maven:mutationCoverage
```

