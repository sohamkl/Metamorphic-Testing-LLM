# Exploratory MRs: jsoup `NodeTraversor.filter`

A metamorphic relation written during development that is **not part of the final experiment**. Kept as
reference only. It is natural-language only (`MRProvider: LLM`, no developer spec). The experiment's
NodeTraversor MR is filter-clone (`../mr/NodeTraversorFilterCloneMetamorphicSpec.java`).

| File | Relation |
|------|----------|
| `prompt-node-filter-stop-suffix.yaml` | Stop suffix: with a deterministic filter that returns STOP at a target node, appending a uniquely marked subtree after that target must not change the result: both calls return STOP with the same callback events, and the appended subtree is never visited. |
| `prompt-node-filter-stop-suffix-formal.yaml` | Formal (set-notation) prompt for the same MR. |
