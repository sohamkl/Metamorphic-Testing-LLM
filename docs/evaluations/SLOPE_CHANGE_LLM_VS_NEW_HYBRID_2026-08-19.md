# Slope Change Swing Detector: LLM vs NEW_HYBRID - 2026-08-19

## Fixed configuration

- SUT: `SlopeChangeSwingDetector.detect(BarSeries, int, ElliottDegree)`
- MR provider: `DEV`
- MR: adding `100.0` to every source OHLC price preserves pivot/swing structure and shifts output prices by `100.0`
- Count upper limit: 50
- Java: 25
- PIT target: `SlopeChangeSwingDetector`
- PIT tests: `GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest`

## Results

| Measure | LLM (late repaired suite) | NEW_HYBRID |
|---|---:|---:|
| Passing generated tests | 43 | 33 |
| Failing generated tests | 0 | 0 |
| Initial runner outcome | Failed after 3 repairs | Passed after 1 repair |
| Concrete Randoop seeds | N/A | 0 |
| Mutated line coverage | 83/91 (91%) | 84/91 (92%) |
| Generated mutants | 75 | 75 |
| Killed | 18 | 17 |
| Survived | 55 | 53 |
| No coverage | 2 | 5 |
| PIT mutation score | 24% | 23% |
| PIT test strength | 25% | 24% |
| Mutation-test executions | 1567 | 1325 |

The initial LLM command reported failure. A repaired 43-test split suite appeared later and was verified independently with focused JUnit and PIT runs. Its scenario names correspond to the LLM-inferred domain rather than the NEW_HYBRID domain, so it is reported as the late LLM artifact. This delayed write should still be investigated as a pipeline lifecycle/race issue.

## LLM failure

The LLM repeatedly generated this constructor call:

```java
new SlopeChangeConfig(
        window,
        confirmationBars,
        minimumSlopeChange,
        atrPeriod,
        minimumAtrReversal)
```

The real parameter order is:

```java
SlopeChangeConfig(
        int window,
        int confirmationBars,
        int atrPeriod,
        double minSlopeChange,
        double minAtrReversal)
```

It therefore passed a `double` into the third `int` parameter and an `int` into the fourth parameter. Compilation failed with `possible lossy conversion from double to int`, and all three initially reported repair attempts retained the same mistake. The late suite avoided positional construction by reading record-component names reflectively.

## LLM source-input quality

- The 43 valid cases vary all five `SlopeChangeConfig` fields rather than fixing one detector configuration.
- They cover empty and insufficient history, indices below and above the available range, positive begin indices, monotonic paths, confirmed HIGH/LOW pivots, alternating pivots, weak ATR reversals, and same-type replacement.
- Price offsets, Elliott degrees, series lengths, window sizes, confirmation periods, ATR periods, slope thresholds, and ATR multipliers all vary.
- Reflective configuration construction solved the parameter-order problem, but makes the generated test code substantially more complicated than direct construction.
- The suite killed one additional math mutant and left fewer mutants uncovered than NEW_HYBRID.

## NEW_HYBRID source-input quality

- The 33 valid cases cover empty and short series, flat and monotonic prices, confirmed HIGH and LOW pivots, alternating pivots, persistence rejection, ATR rejection, same-type replacement, and tied extremes.
- Inputs vary evaluation indices across negative, interior, end, and `Integer.MAX_VALUE` values, and use both `MINOR` and `PRIMARY` Elliott degrees.
- Series lengths and price paths are meaningfully varied, including multi-cycle paths with more than 20 bars.
- ATR ranges vary from narrow fractional ranges to very wide ranges.
- All tests use `new SlopeChangeSwingDetector(3)`, so detector-configuration diversity is absent.
- Scenario names are not backed by explicit source-behavior assertions. A named ATR-rejection or alternating-pivot case may satisfy the MR while not actually exhibiting the claimed source behavior.
- The Randoop seed file is an empty array. The improvement therefore came from the NEW_HYBRID pipeline's inferred structured domain and API-grounded generation path, not from concrete Randoop values.

## Interpretation

The late LLM suite is slightly stronger overall: it killed one additional mutant, achieved 25% rather than 24% test strength, and varied detector configuration substantially. NEW_HYBRID covered one additional source line and generated a cleaner, smaller suite, but fixed the detector at `new SlopeChangeSwingDetector(3)`.

Both mutation scores remain low because translation invariance checks one property. Many mutations affect the original and translated execution in the same way and therefore preserve the MR. More inputs alone will not solve that oracle limitation; additional independent MRs and source-scenario validation are needed.

The result is not a perfectly controlled generator comparison because each mode independently inferred a different input domain. For a stricter experiment, persist one inferred domain and reuse it unchanged across modes. Source-scenario validation should also be enabled before treating scenario labels as confirmed behavioral coverage.

## Commands

Generation:

```bash
mvn -Pta4j -DskipTests compile exec:java \
  -Dexec.mainClass=mtllm.OpenaiRunner \
  -Dexec.args=examples/ta4j/prompt3.yaml
```

Focused PIT run:

```bash
mvn -Pta4j,pitest \
  process-classes test-compile \
  org.pitest:pitest-maven:mutationCoverage
```
