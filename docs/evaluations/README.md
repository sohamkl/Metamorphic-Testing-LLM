# Exploratory Evaluations (pre-experiment)

Small comparisons run in August and early September 2026, while the framework and the NEW_HYBRID input
generator were still being developed. They informed the final experiment's design but are **not** part of
its results: they use earlier framework versions, earlier models and prompts, and prompt files that were
later replaced by `final-experiment/final-experiment-sut-prompts/` (names such as `prompt1.yaml` no longer
exist). For the reported results see `final-experiment/` and the README's Final Experiment section.

| File | What it compared |
|------|------------------|
| `NODE_TRAVERSOR_LLM_VS_NEW_HYBRID_2026-08-19.md` | LLM vs NEW_HYBRID input generation on jsoup `NodeTraversor.filter` |
| `RENKO_LLM_VS_NEW_HYBRID_2026-08-19.md` | LLM vs NEW_HYBRID on ta4j `RenkoBarAggregator.aggregate` |
| `SLOPE_CHANGE_LLM_VS_NEW_HYBRID_2026-08-19.md` | LLM vs NEW_HYBRID on ta4j `SlopeChangeSwingDetector.detect` |
| `renko-framework-merge-2026-09-03/` | Renko re-run after the framework merge, comparing models; includes the generated suites, inferred domains, metrics and PIT `mutations.xml` |
