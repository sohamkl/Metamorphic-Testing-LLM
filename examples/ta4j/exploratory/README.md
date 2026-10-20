# Exploratory MRs: ta4j `RenkoBarAggregator.aggregate`

Metamorphic relations written during development that are **not part of the final experiment**. Kept as
reference only; the `ta4j-renko-stutter-randoop` profile was removed, so these are not runnable as-is. The
experiment's Renko MR is `../mr/mtllm/examples/ta4j/RenkoBarAggregatorMetamorphicSpec.java`.

| File | Relation |
|------|----------|
| `RenkoBarAggregatorScaledStutteringMetamorphicSpec.java` | Scaled stuttering: multiply prices, amounts and the box size by k = 2, and follow every bar with a zero-volume no-change bar at the same scaled close. The follow-up must give the same brick count and directions, with brick prices and amounts scaled by k. Still declares package `mtllm.examples.ta4j`. |
| `prompt-renko-scaled-stuttering.yaml` | Natural-language prompt for that MR. |
| `prompt-renko-scaled-stuttering-formal.yaml` | Formal (set-notation) prompt for that MR. |
| `prompt-renko-stutter-randoop.yaml` | `RANDOOP` input-generator prompt for that MR. |
