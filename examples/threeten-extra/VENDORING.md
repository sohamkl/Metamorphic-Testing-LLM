# Vendored threeten-extra source

This folder holds a partial, vendored copy of threeten-extra, used as the real-world SUT for two
datasets: `prompt-days-between.yaml` and `prompt-range-length.yaml`.

It follows the same layout as the other vendored-library examples (`spatial4j/`, `ta4j/`,
`jsoup/`): the library tree, one `mr/` folder holding every metamorphic relation written against
it, and one `prompt-*.yaml` per relation.

## Why source rather than a Maven project

`spatial4j/` and `ta4j/` are complete Maven projects, so their configs set `AutomaticDiscovery:
true` and the framework resolves the real classpath by running
`mvn dependency:build-classpath` against the library's own pom.

threeten-extra is vendored here as source only, with no pom, so both configs set
`AutomaticDiscovery: false` and list the compile closure in `SUTSupportFiles`. Note that
`AutomaticDiscovery` defaults to **true**, and its project search walks up from the SUT source
file until it finds any `pom.xml`. Leaving it on here would find the *framework's* own pom and
resolve the wrong classpath, so the `false` is load-bearing, not decorative.

`SUTClassFile` must point at a source file either way, so vendoring source is required regardless.
What a pom would replace is the hand-listed dependency closure, not the SUT source.

**Possible follow-up:** adding a real `pom.xml` here would promote this folder to the same
mechanism as `spatial4j/`, and would also make the four annotation stubs below unnecessary, since
joda-convert and jspecify resolve from Maven Central. That needs network access on first build, so
it is deliberately left as a separate decision.

## Provenance

| Path | Project | Upstream |
| --- | --- | --- |
| `src/main/java/org/threeten/extra/` | threeten-extra | https://github.com/ThreeTen/threeten-extra |
| `src/main/java/org/joda/convert/` | *not upstream*, see below | - |
| `src/main/java/org/jspecify/annotations/` | *not upstream*, see below | - |

threeten-extra is a benchmark subject from the SBFT'23 Java test-generation competition
(Jahangirova & Terragni, 2023), which also supplied the class-selection criteria used to pick
these SUTs.

Nine threeten-extra files are vendored: the combined compile closure of `Days` and
`LocalDateRange`. Both configs list the whole closure rather than a per-target subset, because the
two overlap almost entirely and one list is easier to keep correct.

## Modifications to upstream source

The threeten-extra files are copied verbatim. The only additions are four stubs:
`@FromString` and `@ToString` (joda-convert) and `@Nullable` and `@NullMarked` (jspecify) are
re-declared as empty marker annotations. threeten-extra's sources reference them, but they carry no
runtime behaviour and both libraries are compile-scope metadata only. These four files are written
for this repository and are **not** upstream code.

## No adapters

Neither dataset wraps its target method. The framework calls the library method directly:
`InvocationWrapperGenerator` synthesizes the typed input boundary Randoop needs, and under
`InputGenerator: LLM` the model constructs the call itself from the target signature. The
developer-owned relations therefore transform the receiver and the declared arguments and return
`Object[]`, rather than transforming a hand-written input holder.

## Refreshing

The vendored tree is a snapshot. To update, re-clone threeten-extra, recompute the compile closure
for `Days` and `LocalDateRange`, re-copy, and re-run both datasets' compile checks.
