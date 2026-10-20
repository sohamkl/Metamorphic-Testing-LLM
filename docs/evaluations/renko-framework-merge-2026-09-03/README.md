# Renko LLM Model Comparison After Framework Merge

## Fixed configuration

- SUT: `RenkoBarAggregator.aggregate(List<Bar>)`
- MR: positive scaling of prices, monetary amounts, and box size by `2`
- Input generator: `LLM`
- MR provider: `DEV`
- Count: `50`
- Prompt: `examples/ta4j/prompt1.yaml`

## Results

| Measure | GPT-4.1 | GPT-5.6 Sol |
|---|---:|---:|
| Passing generated tests | 29 | 47 |
| Failing generated tests | 0 | 0 |
| Repair attempts | 0 in successful rerun | 1 |
| Mutated line coverage | 137/143 (96%) | 140/143 (98%) |
| Generated mutants | 48 | 48 |
| Explicit `KILLED` status | 7 | 9 |
| `TIMED_OUT` status | 3 | 3 |
| PIT detected total | 10 (21%) | 12 (25%) |
| Survived | 37 | 36 |
| No coverage | 1 | 0 |
| PIT test strength | 21% | 25% |
| Mutation-test executions | 505 | 796 |
| Prompt tokens | 14,391 | 42,283 |
| Completion tokens | 15,477 | 20,943 |
| Total tokens | 29,868 | 63,226 |
| Framework elapsed time | 78.4 s | 233.4 s |

PIT counts timed-out mutants as detected in its console total. Explicit killed and timed-out
statuses are therefore reported separately.

## Interpretation

GPT-5.6 Sol produced the stronger suite. Its 47 named scenarios covered exact and overshot
reversal thresholds, established-direction continuation, sub-reversal movement, pending metadata,
null and zero metadata, timestamp scheduling, fractional and extreme scales, and oscillation before
emission. It covered every mutated line and explicitly killed two more mutants than GPT-4.1.

The improvement cost approximately 2.1 times as many tokens and 3.0 times the elapsed generation
time. GPT-5.6 Sol still required one repair after its initial generated Java failed validation. The
framework did not persist the pre-repair diagnostic, so the exact initial defect cannot be audited
after the successful repair. It did not repeat GPT-4.1's invented `BigDecimalNum` class or the
over-budget inferred scenario total.

The `.env` configuration already names `gpt-5.6-sol`; the GPT-4.1 experiment used a command-level
override, so no persistent model-file change was required for this rerun.
