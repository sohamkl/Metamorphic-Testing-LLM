# Renko LLM vs NEW_HYBRID - 2026-08-19

## Fixed configuration

- SUT: `org.ta4j.core.aggregator.RenkoBarAggregator.aggregate(List<Bar>)`
- MR provider: `DEV`
- MR: scale source prices, monetary amounts, and box size by 2 while preserving Renko structure
- Count upper limit: 50
- Java: 25
- PIT target: `RenkoBarAggregator`
- PIT tests: `GeneratedRenkoBarAggregatorMetamorphicPassingTest`

## Results

| Measure | LLM | NEW_HYBRID |
|---|---:|---:|
| Passing generated tests | 42 | 44 |
| Failing generated tests | 0 | 0 |
| Randoop seeds | N/A | 2 |
| Mutated line coverage | 140/143 (98%) | 125/143 (87%) |
| Generated mutants | 48 | 48 |
| `KILLED` status | 8 | 9 |
| `TIMED_OUT` status | 3 | 3 |
| PIT detected total | 11 (23%) | 12 (25%) |
| Survived | 37 | 32 |
| No coverage | 0 | 4 |
| PIT test strength | 23% | 27% |
| Test executions | 727 | 788 |

PIT's console summary counts timed-out mutants as detected in its displayed `Killed` total. The explicit XML statuses are therefore reported separately.

## Mutant differences

- NEW_HYBRID killed one mutant that survived LLM: negating the null-volume guard at line 123. Its `SUB_BOX_MOVEMENT_variation3_threeBarsNullVolume` fixture deliberately contains a null volume.
- Four conditional mutants at lines 204-211 were covered but survived under LLM, while NEW_HYBRID did not cover them. These lines handle metadata assignment and reset during continued downward brick emission.
- LLM included an explicit downward same-direction continuation path. NEW_HYBRID covered initial downward jumps and reversals but did not exercise that particular continuation state.

## Source-input quality

- LLM generated longer paths: bar-list sizes reached 6, with several 4-6 bar cases. Its 41 non-empty fixtures were distributed across sizes 1-6.
- NEW_HYBRID concentrated on shorter paths: 39 of its 43 non-empty fixtures used 2 or 3 bars, and its maximum was 4.
- NEW_HYBRID varied box sizes more broadly, including fractional values from 1.5 upward, and deliberately varied null volume.
- LLM used larger price and box magnitudes and covered deeper temporal/directional sequences, giving it materially better line coverage.
- The two Randoop seeds were API-grounding examples, not strong market scenarios. They demonstrated receiver and `BaseBar` construction but contained odd primitive values and several null fields.

## Interpretation

NEW_HYBRID showed a small mutation-strength improvement, driven by null-volume diversity, but it was not an overall quality improvement because mutated-line coverage fell by 11 percentage points. LLM produced the stronger broad-coverage suite; NEW_HYBRID contributed a useful metadata edge case.

The comparison is not fully causal because each run independently inferred a different structured `InputDomain`. The LLM domain requested 42 cases and included same-direction continuation; the NEW_HYBRID domain requested 44 cases and included null-volume scenarios but omitted that continuation scenario. A strict generator comparison should persist one inferred domain in `prompt1.yaml` and reuse it unchanged for both modes.

## Commands

Generation:

```bash
mvn -Pta4j -DskipTests compile exec:java \
  -Dexec.mainClass=mtllm.OpenaiRunner \
  -Dexec.args=examples/ta4j/prompt1.yaml
```

Focused PIT run:

```bash
mvn -Pta4j,pitest-ta4j-renko \
  process-classes test-compile \
  org.pitest:pitest-maven:mutationCoverage
```
