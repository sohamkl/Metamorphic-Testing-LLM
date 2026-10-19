# Exploratory MRs: spatial4j `CircleImpl.relate(Rectangle)`

Metamorphic relations written during development that are **not part of the final experiment**. Kept as
reference only; the Maven profiles these prompts name (`spatial4j-mirror-nh` and the mirror PIT profiles)
were removed, so they are not runnable as-is. The experiment's circle-relate MR is
`../mr/CircleRelateDevMetamorphicSpec.java`.

| File | Relation |
|------|----------|
| `CircleRelateMetamorphicSpec.java` | Translation invariance: sliding the circle and rectangle by the same offset leaves the relation unchanged. Superseded by `CircleRelateDevMetamorphicSpec`. |
| `CircleRelateMirrorMetamorphicSpec.java` | Mirror invariance: reflecting both shapes about the y axis leaves the relation unchanged. Exercises the opposite comparison branches to translation. |
| `prompt-circle-relate-mirror*.yaml` | Prompts for the mirror MR with the `LLM`, `NEW_HYBRID` (`-nh`) and `RANDOOP` input generators. |
