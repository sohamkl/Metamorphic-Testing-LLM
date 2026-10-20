# MT4J: Metamorphic Testing for Java with LLMs

MT4J generates developer-reviewable **JUnit 5 metamorphic tests** for a Java system under test (SUT). You
give it a target method and a metamorphic relation (MR), for example "transposing a matrix does not change
its rank". It generates source inputs, applies the follow-up transformation, checks the relation, runs the
suite, and splits the tests into passing tests and failing tests (candidate MR violations).

Instead of asking the LLM for raw test data, MT4J asks it for a complete JUnit class with explicit helper
methods for input construction, the follow-up transformation and the relation assertion, so the generated
logic stays readable and debuggable. Source inputs can come from the LLM, from Randoop, or from both
combined, and the relation can be written by the developer, by the LLM, or split between them.

## Compendium Guide

This repository is the research compendium (online technical appendix) for the 2026 Part IV project on
metamorphic testing with LLMs (Nathan Turley and Soham Kulkarni, University of Auckland). It holds the
tool, the subjects and their metamorphic relations, the experiment configuration, every raw result, and
the scripts that turn those results into the report's tables.

| Report content | Where it is |
|----------------|-------------|
| How MT4J works: pipeline, configuration, input generators, MR ownership | [docs/FRAMEWORK.md](docs/FRAMEWORK.md), code in `src/main/java/mtllm/` |
| Experiment design, subjects and environment | [docs/EXPERIMENT.md](docs/EXPERIMENT.md) |
| Subjects and the developer-written MRs | `examples/<sut>/` ([Subjects](docs/EXPERIMENT.md#subjects)) |
| Manifest, runner and the 40 experiment prompts | `final-experiment/` |
| Raw results for all 480 runs | `final-experiment/final-experiment-run-metadata/`, `final-experiment/final-experiment-pit-reports/` |
| Oracle stripping of the AUTO (`nl`) runs | [Oracle stripping](docs/EXPERIMENT.md#oracle-stripping-auto-runs), `final-experiment/final-experiment-stripped-oracles/` |
| Every results table (RQ1, RQ2, cost) | `final-experiment/analysis/analyse.py`, output in `final-experiment/analysis/results/` |
| Work not in the report | `examples/*/exploratory/`, `examples/*/mt-testing/exploratory/`, `docs/evaluations/` |
| Extending the work with a new subject | [docs/ADDING_A_SUT.md](docs/ADDING_A_SUT.md) |

## Requirements

| What | Why |
|------|-----|
| JDK 25 | The core targets Java 17, but some subject profiles (e.g. ta4j) compile with `release 25`. All experiment runs used JDK 25 |
| Maven | Build and run everything |
| Python 3 | Experiment runner and analysis scripts (standard library only; PyYAML is used if installed) |
| OpenAI API key | `LLM` and `NEW_HYBRID` generation. `RANDOOP` and the analysis scripts make no API calls |
| Randoop | Bundled as `lib/randoop-all-4.3.4.jar` and wired into `pom.xml`; nothing to install |

## Setup

**1. Build the subjects.** The subjects under `examples/` are upstream Maven projects. Build them once with
JDK 25, because the pom and the experiment prompts use their compiled classes and jars:

```bash
mvn -f examples/the-algorithms-java/pom.xml -DskipTests install
mvn -f examples/jsoup/pom.xml -DskipTests install
(cd examples/ta4j && ./mvnw -pl ta4j-core -am -DskipTests install)   # mvnw.cmd on Windows
mvn -f examples/spatial4j/pom.xml -DskipTests package
mvn -f examples/threeten-extra/pom.xml -DskipTests package
```

the-algorithms-java, jsoup and ta4j-core are normal Maven dependencies (hence `install`); spatial4j and
threeten-extra are system-scope dependencies on their `target/*.jar` (hence `package`). ta4j uses its own
Maven wrapper, and `-pl ta4j-core -am` skips the unused `ta4j-examples` module.

**2. Configure the LLM.** Create `.env` in the repository root:

```text
OPENAI_API_KEY_OPENAI=sk-...
OPENAI_MODEL=gpt-5.6-luna
OPENAI_BASE_URL=https://api.openai.com/v1
OPENAI_REASONING_EFFORT=medium
```

`OPENAI_API_KEY_OPENAI` is the only key variable read (`OPENAI_API_KEY` is ignored). Leave
`OPENAI_REASONING_EFFORT` unset for models without reasoning support. Optional: `MAVEN_CMD` if Maven is
not on `PATH`, and `JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR` to run generated tests with the JUnit console
instead of Maven. Never commit `.env`.

**3. Build and test MT4J:**

```bash
mvn test
```

The Randoop integration tests are skipped unless `MTLLM_RUN_RANDOOP_INTEGRATION=true` is set.

## Quick Start

**Generate tests for one MR.** `prompt.yaml` is a copy of the experiment's jsoup cleaner-idempotence `dev`
prompt:

```bash
mvn -Pjsoup -DskipTests compile exec:java -Dexec.mainClass=mtllm.OpenaiRunner
```

Output lands in the prompt's `OutputRoot`; see [Outputs](docs/FRAMEWORK.md#outputs). Any other prompt runs
with `-Dexec.args="path/to/prompt.yaml"` and the profiles in its `MavenProfiles`.

**Reproduce the report's tables** from the archived results (no API calls):

```bash
python final-experiment/analysis/analyse.py
python final-experiment/analysis/verify_oracle_stripping.py
```

**Rerun the experiment**, or the offline Randoop regression check: see
[Rerunning](docs/EXPERIMENT.md#rerunning). Point the runner at a copy of the manifest so the committed
results are not overwritten.

## Repository Layout

| Path | Contents |
|------|----------|
| `src/main/java/mtllm/` | MT4J ([Source layout](docs/FRAMEWORK.md#source-layout)) |
| `src/test/java/mtllm/` | Unit and integration tests |
| `pom.xml` | Build, plus the Maven profiles for each subject and each experiment PIT target |
| `lib/` | Vendored Randoop jar |
| `prompt.yaml` | Default prompt (jsoup cleaner-idempotence, `dev`) |
| `examples/<sut>/` | The five subjects: upstream code plus `mr/` or `mt-testing/mr/` (developer MRs) and `exploratory/` (MRs not used in the experiment) |
| `final-experiment/` | Manifest, runner, prompts, raw results and `analysis/` ([EXPERIMENT.md](docs/EXPERIMENT.md#results-layout)) |
| `docs/` | Documentation (below) |

## Documentation

| Document | Covers |
|----------|--------|
| [docs/FRAMEWORK.md](docs/FRAMEWORK.md) | How MT4J works: pipeline, prompt fields, MR ownership, input generators, outputs, running, PIT, limitations |
| [docs/EXPERIMENT.md](docs/EXPERIMENT.md) | The final experiment: design, subjects, environment, rerunning, results layout, analysis, oracle stripping |
| [docs/ADDING_A_SUT.md](docs/ADDING_A_SUT.md) | Step-by-step guide to adding and mutation-testing a new Java subject |
| [docs/evaluations/](docs/evaluations/README.md) | Exploratory comparisons from before the final experiment |
