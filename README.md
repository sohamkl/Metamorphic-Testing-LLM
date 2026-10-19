# Metamorphic Testing with LLMs

Backend prototype for generating developer-reviewable **JUnit 5 metamorphic tests** from a Java system under test (SUT), a target method, an input domain, and a metamorphic relation.

The current design treats the LLM as a code-generation assistant. Instead of asking for raw JSON test pairs, the tool asks the model to generate a complete JUnit 5 test class with explicit helper methods for:

- source input generation
- follow-up input generation/transformation
- metamorphic relation assertions

This keeps the generated logic readable and debuggable by developers.

The backend supports configurable JSON/JUnit outputs and developer- or LLM-provided MR logic. See [CONFIGURATION.md](docs/CONFIGURATION.md) for the detailed explanation.

In the Part IV project reports this framework is called **MT4J**.

## Compendium Guide

This repository is the research compendium (online technical appendix) for the 2026 Part IV project on
metamorphic testing with LLMs (Nathan Turley and Soham Kulkarni, University of Auckland). It holds the
tool, the subjects and their metamorphic relations, the experiment configuration, every raw result, and
the scripts that turn those results into the report's tables.

| Report content | Where it is in this repository |
|----------------|--------------------------------|
| MT4J design and implementation | `src/main/java/mtllm/` (see [Project Layout](#project-layout)) |
| Configuration: input generators and MR ownership | [Prompt Config](#prompt-config), `docs/CONFIGURATION.md`, `docs/AUTOMATIC_INPUT_PIPELINE.md` |
| Subjects, their provenance, and the developer-written MRs | `examples/<sut>/` (see [Subjects](#subjects)) |
| Experiment design, manifest, runner, and the 40 prompts | `final-experiment/` (see [Final Experiment](#final-experiment)) |
| Raw results for all 480 runs | `final-experiment/final-experiment-run-metadata/`, `final-experiment/final-experiment-pit-reports/` |
| Oracle stripping of the AUTO (`nl`) runs | `final-experiment/final-experiment-stripped-oracles/`, `final-experiment/analysis/OracleStripper.java` |
| Every results table (RQ1, RQ2, cost) | `final-experiment/analysis/analyse.py`, output in `final-experiment/analysis/results/` |
| Work not in the report | `examples/*/exploratory/`, `examples/*/mt-testing/exploratory/`, `docs/evaluations/` |
| Extending the work with a new subject | `docs/ADDING_A_SUT.md` |

To reproduce from a fresh clone: install the [Requirements](#requirements), build the subjects
([Setup](#setup)), run `mvn test`, then use the commands under [Final Experiment](#final-experiment).

## Requirements

| What | Why |
|------|-----|
| JDK 25 | The core backend targets Java 17, but some SUT profiles (e.g. ta4j) compile with `release 25`. All final-experiment runs used JDK 25 |
| Maven | Build the project and resolve JUnit 5 |
| Python 3 | Only for `final-experiment/run-final-experiment.py`. PyYAML is used if installed but is not required |
| OpenAI API key | `LLM` and `NEW_HYBRID` generation (raw `RANDOOP` makes no API calls) |
| JUnit Platform Console Standalone jar | Optional alternative to Maven for compiling/running generated JUnit tests |
| Randoop | Bundled: `lib/randoop-all-4.3.4.jar` is vendored and wired into `pom.xml` (system scope), so no separate install is needed for the Randoop-backed input modes |

## Configuration

Create a `.env` file in the project root:

```text
OPENAI_API_KEY_OPENAI=sk-...
OPENAI_MODEL=gpt-4o-mini
OPENAI_BASE_URL=https://api.openai.com/v1
MAVEN_CMD=mvn
JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR=/absolute/path/to/junit-platform-console-standalone.jar
```

`OPENAI_API_KEY_OPENAI` is the only API-key variable read by the framework. The legacy
`OPENAI_API_KEY` name is ignored in both `.env` and the shell environment.

`JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR` is optional. If it is missing, the tool uses Maven (`mvn test`) to compile and run generated JUnit tests. `MAVEN_CMD` is optional too; set it only if Maven is not on your normal PATH.

`OPENAI_REASONING_EFFORT` is optional. When set (for example `medium`), it is sent to the API as `reasoning_effort`; leave it unset for models without reasoning support, such as `gpt-4o-mini`, which reject the field. The final-experiment runner sets it from `reasoningEffort` in `final-experiment/experiment-manifest.yaml`.

## Setup

The subjects under `examples/` are upstream Maven projects. Build them once (with JDK 25) before running
the framework, because the pom and the experiment prompts use their compiled classes and jars:

```bash
mvn -f examples/the-algorithms-java/pom.xml -DskipTests install
mvn -f examples/jsoup/pom.xml -DskipTests install
(cd examples/ta4j && ./mvnw -pl ta4j-core -am -DskipTests install)   # mvnw.cmd on Windows
mvn -f examples/spatial4j/pom.xml -DskipTests package
mvn -f examples/threeten-extra/pom.xml -DskipTests package
mvn test
```

the-algorithms-java, jsoup and ta4j-core are consumed as normal Maven dependencies (hence `install`).
spatial4j and threeten-extra are system-scope dependencies on their `target/*.jar` (hence `package`).
ta4j uses its own Maven wrapper; `-pl ta4j-core -am` skips the unused `ta4j-examples` module.

## Project Layout

| Path | Purpose |
|------|---------|
| `pom.xml` | Maven build file with JUnit 5 configured |
| `src/main/java/mtllm/OpenaiRunner.java` | Compatibility entry point that delegates to `mtllm.App` |
| `src/main/java/mtllm/App.java` | Main backend orchestration |
| `src/main/java/mtllm/config/` | Reads and stores `prompt.yaml` settings |
| `src/main/java/mtllm/sut/` | Loads SUT source and first-level dependencies |
| `src/main/java/mtllm/prompt/` | Builds initial and repair prompts |
| `src/main/java/mtllm/llm/` | LLM provider interface and OpenAI client |
| `src/main/java/mtllm/generation/` | Writes generated JUnit code |
| `src/main/java/mtllm/randoop/` | Randoop input-generation mode: harvests objects, serializes them, and emits the object JUnit suite |
| `src/main/java/mtllm/runner/` | Compiles, runs, and repairs generated tests |
| `src/main/java/mtllm/util/` | Small helpers for `.env`, JSON, and code fences |
| `lib/randoop-all-4.3.4.jar` | Vendored Randoop jar (system-scoped dependency in `pom.xml`) |
| `examples/the-algorithms-java/`, `examples/jsoup/`, `examples/ta4j/`, `examples/spatial4j/`, `examples/threeten-extra/` | The five live SUTs. The 10 metamorphic relations used in the final experiment are listed in `final-experiment/experiment-manifest.yaml`; some example folders also hold extra MR specs that were not part of the experiment |
| `final-experiment/` | Final experiment manifest, runner, prompts, and all raw results (see [Final Experiment](#final-experiment)) |
| `src/main/resources/reports/` | FreeMarker templates for generated HTML reports |
| `prompt.yaml` | Default generation config: a copy of the jsoup cleaner-idempotence `dev` prompt from the final experiment |
| `examples/<sut>/exploratory/`, `examples/<sut>/mt-testing/exploratory/` | MRs explored during development but not used in the final experiment (reference only; see each folder's README) |
| `docs/CONFIGURATION.md` | Explanation of output/MR-provider configuration combinations |
| `docs/AUTOMATIC_INPUT_PIPELINE.md` | Automatic domain inference, scenario planning, quality gates, and current boundary |
| `docs/PITEST.md` | PIT mutation testing profile, command, target scope, and report paths |
| `docs/ADDING_A_SUT.md` | Step-by-step guide to adding and mutation-testing a new Java SUT |
| `docs/evaluations/` | Exploratory pre-experiment comparisons (not part of the reported results) |

## Prompt Config

`prompt.yaml` uses top-level YAML fields. `SUTSupportFiles` can be an empty list or a YAML list of paths.
The SUT may be identified with `SUTClassFile`, or with `ProjectRoot` plus a fully qualified `SUTClass`.
When `SUTClasspath` is omitted, the framework locates the nearest Maven project and resolves its
compiled outputs and dependency classpath. Set `AutomaticDiscovery: false` to require explicit paths.
The backend parses this file with SnakeYAML into `PromptConfig`; the LLM receives the generated prompt built from those Java config values, not the raw YAML file.

The final experiment's matrix-transpose `nl` prompt
(`final-experiment/final-experiment-sut-prompts/the-algorithms-java/MatrixRank/prompt-matrix-transpose-nl.yaml`):

```yaml
ProjectRoot: examples/the-algorithms-java
AutomaticDiscovery: true
SUTClassFile: examples/the-algorithms-java/src/main/java/com/thealgorithms/matrix/MatrixRank.java
SUTClasspath:
  - examples/the-algorithms-java/target/classes
TargetFunction: computeRank
SUTDescription: >-
  Computes the rank of a non-empty rectangular double matrix using pivot
  selection, row normalization, and elimination.

MRInput: Transpose the source matrix.
MROutput: The source and transposed matrices must have the same rank.

Count: 50

GeneratedClassName: GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicTest
OutputRoot: examples/the-algorithms-java/mt-testing/generated/matrix-rank-transpose
MavenProfiles: [the-algorithms-java]
InputGenerator: LLM
JsonRequired: false
TestSuiteRequired: true
MRProvider: LLM
MaxRepairAttempts: 3
```

A minimal project-oriented form is:

```yaml
ProjectRoot: examples/jsoup
SUTClass: org.jsoup.safety.Cleaner
TargetFunction: public org.jsoup.nodes.Document clean(org.jsoup.nodes.Document dirtyDocument)
MR: Cleaning an already-cleaned document must not change the cleaned result.
Count: 20
```

`InputDomain`, `SUTSupportFiles`, and `SUTClasspath` are optional in this form. When `InputDomain` is
omitted in an LLM-backed mode, the framework first asks the LLM to infer a structured domain grounded
in the resolved target method, SUT source, MR, and discovered construction API. The inferred domain is
validated and written to `<OutputRoot>/input-domain/inferred-input-domain.yaml` before source-input
generation begins. An explicitly supplied scalar or structured `InputDomain` always takes precedence.
The framework inspects
the resolved method's generic parameter and return types and sends discovered public constructors and
static factory methods to the LLM. It also builds a bounded construction graph: ClassGraph discovers
concrete implementations of interface and abstract parameters, reflection follows public constructors,
factory methods, arrays, and generic arguments, and JavaParser finds source-level factories/builders.
The graph is capped at depth 5 and 96 classes so dependency-heavy projects remain tractable. For zero-
For instance, zero-argument, or multi-argument methods, the framework automatically generates a typed
one-input invocation wrapper for Randoop and stores its source under `generated-support` and
`junit-support`. Instance wrappers include the receiver before the method arguments, allowing Randoop
to construct and vary the complete invocation scenario.

Automatic discovery and LLM-backed generation follow this workflow:

1. Resolve the project, SUT source, exact target method, compiled outputs, and Maven dependencies.
2. Inspect the target signature and generate an invocation wrapper for instance methods or when the
   method's arity is not one.
3. Discover the bounded construction graph from reflection, ClassGraph implementations, and
   JavaParser factory/builder evidence.
4. If `InputDomain` is absent, infer and validate structured constraints, diversity dimensions, and
   measurable scenarios. Allocate deterministic source-case slots without exceeding `Count`.
5. Give the resulting construction classes to Randoop where the selected input mode uses it, and
   retain only distinct inputs accepted by the target method.
6. In `HYBRID` or `NEW_HYBRID` only, if Randoop finds no executable seed, try deterministic Instancio
   seeds and validate each one by executing the SUT. Pure `RANDOOP` deliberately has no fallback.
7. Generate candidate tests from the validated domain and scenario plan. Reject duplicate test bodies,
   missing scenario IDs, missing assertion paths, malformed Java, and suites above `Count`; repair LLM
   output when the configured attempt budget permits.
8. When missing scenarios are the only defect, request and merge only those additional tests while
   retaining the original candidate methods. Other defects continue through whole-class repair.
9. Apply or generate the MR, execute source and follow-up cases, split actual passing/failing tests,
   compile-gate generated JUnit, and write JSON/HTML artifacts requested by the prompt.

For `MRProvider: DEV` with a wrapped target, the developer follow-up method accepts the original typed
invocation components and returns an `Object[]` in the same order. For an instance method, the receiver
is the first component followed by the method arguments; for a static multi-argument method, only the
arguments are present. The framework-generated wrapper validates and converts that array back into the
typed source case. This contract is not needed for LLM-provided MRs.

Prefer `MRInput` plus `MROutput` because it matches the metamorphic-testing form “input relation implies output relation.” `MR` remains as a fallback for relations that are easier to express in one field.

`DataType` is no longer required. The LLM is instructed to infer Java types from the target method signature and SUT source.

`InputDomain` also accepts a structured mapping. Existing scalar descriptions remain supported.

```yaml
Count: 8
InputDomain:
  summary: Generate valid inputs around the target threshold.
  globalConstraints:
    - Values must be finite and positive.
  diversity:
    sizes: [small, medium, large]
  scenarios:
    - id: ABOVE_THRESHOLD
      category: BOUNDARY
      description: Cross the threshold from below.
      preconditions:
        - source value is greater than the threshold
      expectedSourceBehavior:
        - source execution activates the above-threshold branch
      targetCases: 3
      emptyOutputAllowed: false
```

Scenario categories are `NORMAL`, `BOUNDARY`, `EDGE`, and `INVALID`. Scenario IDs must be unique,
and the sum of all `targetCases` values cannot exceed `Count`. For LLM-backed generation, each
scenario is rendered as an explicit checklist item and its ID must appear in generated test method
names. `RANDOOP` does not interpret scenario prose; meaningful relational constraints may still
require scenario-aware factories or runtime filtering.

Output and MR ownership are controlled by three fields:

```yaml
JsonRequired: true
TestSuiteRequired: false
MRProvider: DEV
```

`MRProvider` accepts:

```text
DEV
LLM
```

The current supported combinations are:

| JsonRequired | TestSuiteRequired | MRProvider | Output |
|---|---|---|---|
| true | false | DEV | JSON data using developer MR helpers |
| false | true | DEV | JUnit tests using developer MR helpers |
| true | true | DEV | JSON/report output and JUnit tests using developer MR helpers |
| true | false | LLM | JSON data using LLM-generated follow-up/assertion logic |
| false | true | LLM | JUnit tests using LLM-generated follow-up/assertion logic |
| true | true | LLM | JSON/report output and JUnit tests using LLM-generated MR logic |

Mode-style numbers are no longer needed in `prompt.yaml`. Internally, the backend still maps these fields to generation strategies.

### Input generation modes

By default the **LLM** generates the source inputs. An optional `InputGenerator` field lets you generate them with [Randoop](https://randoop.github.io/randoop/) (feedback-directed random test generation) instead, or with a hybrid of both:

```yaml
InputGenerator: LLM
```

`InputGenerator` accepts:

| Value | Source inputs come from | API calls |
|---|---|---|
| `LLM` (default) | the LLM (existing behavior) | generation, repair, and one domain-inference call when `InputDomain` is absent |
| `RANDOOP` | Randoop, building objects by calling the SUT's own constructors/methods | none (fully offline) |
| `NEW_HYBRID` | Randoop first discovers API-valid source examples; the LLM then generalizes them into the final constrained input set. The Part IV reports call this generator **HYBRID** | final generation and repair calls, plus one domain-inference call when `InputDomain` is absent |

An earlier `HYBRID` value (the LLM suggested seed values and Randoop built the final inputs) was replaced by `NEW_HYBRID` before the final experiment and has been removed; configs that still say `HYBRID` fail with a message pointing to `NEW_HYBRID`.

Key points:

- **`RANDOOP` requires `MRProvider: DEV`.** Randoop produces the final source inputs, so the metamorphic relation must be developer-owned. `NEW_HYBRID` feeds Randoop examples into the normal LLM generation/repair path and therefore supports both `DEV` and `LLM` MR providers.
- **Works for any object or array.** Randoop constructs inputs from the SUT's visible API, so object SUTs (e.g. an `Order` with a `List<LineItem>`), array SUTs (`double[][]`), and nested object graphs (`Cart` → `List<CartItem>`) are all supported with no per-SUT serialization code.
- **All modes honor the same output flags.** With `JsonRequired: true` you get JSON data, pass/fail splits, and an HTML report. `RANDOOP` emits its object JUnit suites pre-split because they know verdicts in-process; `LLM`/`NEW_HYBRID` use the existing generated-test execution and actual-result splitter.
- **Seeding source (NEW_HYBRID):** Randoop runs first without an API call, retains source objects accepted by the target method, and emits each runtime value plus its minimal Java construction trace. The LLM receives those examples together with `InputDomain`; the examples ground it in the real API, while `InputDomain` remains authoritative for semantic constraints and diversity.
- **Optional `RandoopTargetClasses`:** use a YAML list of fully qualified class names as additional construction evidence. Automatic construction-graph discovery still runs, so this field is a hint rather than a requirement.
- **Empty-harvest fallback:** `NEW_HYBRID` first uses deterministic Instancio seeds after Randoop produces no executable input. Each fallback object is run through the SUT before the LLM sees it. If the complete `NEW_HYBRID` seed stage still returns no seeds or fails, the run continues in `LLM` mode and prints an `[INPUT_GENERATOR_FALLBACK]` warning. `<OutputRoot>/metrics.json` preserves `requestedInputGenerator: NEW_HYBRID`, records `effectiveInputGenerator: LLM`, and tags the reason with `NEW_HYBRID_FALLBACK_LLM_NO_SEEDS` or `NEW_HYBRID_FALLBACK_LLM_SEED_ERROR`. Raw `RANDOOP` remains unchanged for fair generator comparisons.
- **Callback interfaces:** when a target argument is an interface whose callback methods return enums, booleans, characters, or primitive numbers, the framework generates deterministic concrete policy classes automatically. Policies include constant returns and numeric-threshold returns, are compiled into the Randoop workspace, and are copied as replayable JUnit support. Callback-based seeds must contain non-null reference arguments, preventing vacuous inputs such as `(null, null)`. Existing project implementations are still discovered normally. Callbacks with unsupported object return values or domain-specific state transitions remain outside this bounded automatic synthesis.
- The Randoop time budget is fixed at 15s; Randoop runs in a subprocess so the SUT classes are genuinely on its classpath.

`NEW_HYBRID` is useful for constrained object domains. Pure `LLM` generation understands prose constraints but can hallucinate constructors or factories. `NEW_HYBRID` lets Randoop demonstrate real construction paths first, then the LLM applies the domain rules and expands those grounded examples into diverse final inputs. It does not guarantee better results for every SUT, so it remains a separate evaluation mode rather than replacing `LLM`.

Example:

```yaml
InputGenerator: NEW_HYBRID
RandoopTargetClasses:
  - com.example.ConversionCase
  - com.example.Money
  - java.math.BigDecimal
```

Example (Randoop builds the inputs, developer owns the MR; from the final experiment's days-between `dev` prompt):

```yaml
ProjectRoot: examples/threeten-extra
SUTClassFile: examples/threeten-extra/src/main/java/org/threeten/extra/Days.java
TargetFunction: public static Days between(Temporal startDateInclusive, Temporal endDateExclusive)
MavenProfiles:
  - threeten
InputGenerator: RANDOOP
JsonRequired: false
TestSuiteRequired: true
MRProvider: DEV
DeveloperMrFile: examples/threeten-extra/mr/DaysBetweenMetamorphicSpec.java
DeveloperFollowUpMethod: DaysBetweenMetamorphicSpec.generateFollowUp
DeveloperAssertMethod: DaysBetweenMetamorphicSpec.assertRelation
```

When both `JsonRequired` and `TestSuiteRequired` are true, the backend performs two generation passes. If the configured class name ends in `Data`, the JUnit class name is derived by replacing that suffix with `Test`.

`OutputRoot` controls where generated files are written. If it is omitted and the SUT is under
`examples/<name>/src`, the backend defaults to `examples/<name>/generated`. Otherwise it falls back
to the root-level `generated` folder.

Generated data code is compiled with plain `javac`, so it must use only the Java standard library and the SUT/support classes. The LLM is instructed to build JSON manually rather than importing Jackson, Gson, or other JSON libraries.

If `MRProvider: DEV`, add:

```yaml
DeveloperMrFile: examples/the-algorithms-java/mt-testing/mr/MatrixRankMetamorphicSpec.java
DeveloperFollowUpMethod: thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp
DeveloperAssertMethod: thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation
```

Method names are qualified with the spec's package when it declares one (here `thealgorithmsmt`).

The LLM then generates source inputs that call those developer-owned methods instead of inventing the follow-up transformation or assertion itself.

With `JsonRequired: true`, JSON is written to `<OutputRoot>/json-data/<GeneratedClassName>.json`. The generated Java data program also computes `followUp`, `sourceOutput`, `followUpOutput`, and `passed`.

The backend also splits the full JSON into:

```text
<OutputRoot>/json-data/<GeneratedClassNameWithoutData>Passing.json
<OutputRoot>/json-data/<GeneratedClassNameWithoutData>Failing.json
```

The backend also writes a FreeMarker-rendered HTML report for executed JSON data:

```text
<OutputRoot>/reports/<GeneratedClassNameWithoutData>Report.html
```

The report summarizes the SUT, target method, MR, configuration, passing count, failing count, and expandable pass/fail case details.

## Run

Build the backend with Maven:

```bash
mvn test
```

Run the generator:

```bash
mvn exec:java -Dexec.mainClass=mtllm.OpenaiRunner
```

Use Maven exec for normal runs because it includes runtime dependencies such as FreeMarker automatically.

Generated JUnit candidate classes are first written to:

```text
<OutputRoot>/junit-tests/<GeneratedClassName>.java
```

After Maven/JUnit executes it, the backend rewrites the actual outcomes into:

```text
<OutputRoot>/junit-tests/<GeneratedClassNameWithoutTest>PassingTest.java
<OutputRoot>/junit-tests/<GeneratedClassNameWithoutTest>FailingTest.java
```

Generated Java data-generator source is written to:

```text
<OutputRoot>/data-generator-code/<GeneratedClassName>.java
```

and the JSON data output is written to:

```text
<OutputRoot>/json-data/<GeneratedClassName>.json
```

JSON data is also split by actual `passed` value:

```text
<OutputRoot>/json-data/<GeneratedClassNameWithoutData>Passing.json
<OutputRoot>/json-data/<GeneratedClassNameWithoutData>Failing.json
```

Executed JSON runs also produce:

```text
<OutputRoot>/reports/<GeneratedClassNameWithoutData>Report.html
```

Generated artifacts are written under `OutputRoot`, usually inside the relevant example folder. During Maven test execution, the current generated test/support files are staged into `target/mtllm-test-sources` so old examples do not clash with the active run.

If `JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR` is configured, the tool compiles/runs generated tests through the JUnit Platform Console. Otherwise, it uses `mvn test -Dtest=<GeneratedClassName>`. With Maven, JUnit output uses execution-based classification: JUnit assertion failures are treated as discovered failing cases, and the backend splits actual passing and failing `@Test` methods into separate files. Compilation errors, invalid generated code, and infrastructure failures are still sent back to the LLM for up to `MaxRepairAttempts` repair attempts.

## Mutation Testing with PIT

PIT is available through the Maven `pitest` profile. Normal builds do not run mutation testing.

Run PIT with:

```bash
mvn clean -Ppitest test-compile org.pitest:pitest-maven:mutationCoverage
```

The profile follows the PIT Maven quickstart flow and pins explicit plugin versions. The default `pitest` profile mutates:

- `org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector`

It runs only generated `*PassingTest` classes and excludes generated `*FailingTest` classes. This is intentional: the framework stores failing tests as bug-revealing artifacts, while PIT requires the selected test suite to be green before mutation analysis starts.

Reports are written to:

```text
target/pit-reports/index.html
target/pit-reports/mutations.xml
```

The current mutation threshold is `0`, so PIT produces reports without failing the build while generated metamorphic suites are still being evaluated. See [PITEST.md](docs/PITEST.md) for details.

## Final Experiment

`final-experiment/` holds everything needed to rerun or audit the final experiment reported in the Part IV project reports.

### Design

- **Subjects and MRs:** 10 metamorphic relations across five SUTs (`the-algorithms-java` MatrixRank, jsoup, ta4j, spatial4j, threeten-extra). Each relation is one `runs` entry in `experiment-manifest.yaml`, which also records its prompts, generated class names, and PIT profile.
- **MR ownership modes:** `dev` (developer writes the follow-up transformation and the assertion in Java), `nl` (the LLM implements both from a natural-language MR), `iauto` (developer input transformation, LLM-written output relation), and `oauto` (LLM-written input transformation, developer output relation).
- **Input generators:** `LLM`, `NEW_HYBRID`, and `RANDOOP`. `RANDOOP` makes no API calls and runs only in `dev` mode.
- **Models:** `gpt-5.6-luna`, `gpt-5.6-terra`, and `gpt-5.6-sol`, with `reasoningEffort: medium`.
- **Repetitions:** 3 per configuration, 480 runs in total:
  - `dev`: 7 configurations (LLM and NEW_HYBRID for each of the 3 models, plus RANDOOP) x 10 MRs x 3 reps = 210 runs
  - `nl`, `iauto`, `oauto`: NEW_HYBRID x 3 models x 10 MRs x 3 reps = 90 runs each
- **Effectiveness metric:** killed PIT mutants of the target class, compared within an MR (mutant totals differ between classes).

### Subjects

Four of the five subjects (jsoup, ta4j, spatial4j, threeten-extra) come from the benchmark of the SBFT
2023 Java tool competition. Each folder is a copy of the upstream project, with the MT-specific files
beside the library code (`mr/` or `mt-testing/mr/`).

| Folder | Upstream | Version in this repo | Licence | MRs in the experiment |
|--------|----------|----------------------|---------|-----------------------|
| `examples/the-algorithms-java/` | https://github.com/TheAlgorithms/Java | `1.0-SNAPSHOT` | MIT | matrix-transpose |
| `examples/jsoup/` | https://github.com/jhy/jsoup | `1.23.2-SNAPSHOT` | MIT | node-filter-clone, cleaner-idempotence |
| `examples/ta4j/` | https://github.com/ta4j/ta4j | `0.23.1-SNAPSHOT` | MIT | slope-change, renko-scaling |
| `examples/spatial4j/` | https://github.com/locationtech/spatial4j | `0.9-SNAPSHOT` | Apache 2.0 | circle-relate, longitude-normalize, rectangle-contains |
| `examples/threeten-extra/` | https://github.com/ThreeTen/threeten-extra | `1.10.1-SNAPSHOT` (upstream commit `5ec7ef4`) | BSD 3-Clause | days-between, range-length |

### Environment

All 480 runs used Windows, JDK 25.0.4.1 and Maven 3.9.11, between 14 and 20 September 2026 (NZ time). On
Windows the runner does not switch JDKs per subject (`javaVersion` in the manifest is recorded but only
honoured where `/usr/libexec/java_home` exists), so every run used the inherited JDK 25; each run's
`resolvedJavaHome` is in its metadata. The Randoop runs are deterministic: rerunning the 10 Randoop
configurations on a second machine (Windows 11 with the same JDK and Maven versions and Python 3.12.3) reproduced
every archived kill count and covered-line count exactly.

### Analysis

`final-experiment/analysis/analyse.py` reads only the archived data and prints every results table:

```bash
python final-experiment/analysis/analyse.py                                  # Markdown tables
python final-experiment/analysis/analyse.py --csv-dir final-experiment/analysis/results
```

The committed output is in `final-experiment/analysis/results/` (`tables.md` plus one CSV per table).
The selection rules, which match the reports, are:

- `all-runs.jsonl` also holds pilot and retried attempts; the last record per run ID is the run, which
  leaves exactly 480 runs.
- A run is successful when generation reported `PASSED` and PIT exited 0 (445 runs). Failed runs (32) are
  counted but never averaged.
- An `iauto`/`oauto` run whose passing suite calls the developer half the LLM was meant to write is a
  leak and is excluded (3 runs, all `oauto` node-filter-clone).
- Kills are PIT `KILLED` only. Relative scores divide, per MR, a configuration's mean by the best RQ1
  configuration's mean (RQ1) or by `dev` with the same models (RQ2), then average over MRs; "pooled"
  rows pool runs across models before dividing.

### Oracle stripping (AUTO runs)

With `MRProvider: LLM` the model sometimes added conventional expected-value assertions (for example a
hard-coded expected rank) beside the metamorphic check. To measure only the relation, those assertions
were removed with `final-experiment/analysis/OracleStripper.java` and the affected suites were re-run
through the test and PIT steps:

- In a `@Test` method, a JUnit assertion is kept if it references a follow-up value or calls a method
  declared in the test class; otherwise it is removed.
- In a helper method, an assertion comparing against an `expectedSource*` value is removed.

22 of the 85 successful `nl` runs had something removed. `final-experiment-stripped-oracles/` holds, per
run, the stripped suite, the list of removed and kept statements (`.removed.txt`), and the test and PIT
logs; `stripped-runs.jsonl` holds the re-measured results, which `analyse.py` uses for those runs. The
extra `..._control` entry is matrix-transpose terra run001 replayed **unmodified** through the same path,
which reproduced its original 32 kills and validates the replay. Check the tool against the archive with:

```bash
python final-experiment/analysis/verify_oracle_stripping.py
```

It reproduces all 22 stripped suites and removed-statement lists byte for byte, and confirms that exactly
those 22 of the 85 passing `nl` suites have anything to strip.

### Rerunning

The runner executes one combination at a time: it writes the prompt, runs generation, runs the generated passing tests, then runs PIT, and archives everything. List the planned runs first:

```bash
python final-experiment/run-final-experiment.py --list --runs-per-combination 3
```

Examples matching the experiment:

```bash
# dev mode, all three generators
python final-experiment/run-final-experiment.py --mode dev --runs-per-combination 3 --continue-on-error

# nl / iauto / oauto were run with NEW_HYBRID only
python final-experiment/run-final-experiment.py --mode nl --mode iauto --mode oauto \
  --input-generator new-hybrid --runs-per-combination 3 --continue-on-error
```

Use `--run <id>`, `--model <id>`, and `--start-run <n>` to restrict or resume, and `--dry-run` to print the Maven commands without executing them. The runner temporarily sets `OPENAI_MODEL` in `.env` per run (restoring the original file afterwards) and passes `OPENAI_REASONING_EFFORT` to the generation process from the manifest. LLM outputs are non-deterministic, so reruns will not reproduce the archived numbers exactly; the archived results below are the data the reports use.

### Results layout

| Path | Contents |
|------|----------|
| `final-experiment/experiment-manifest.yaml` | Models, modes, input generators, Maven command templates, and the 10 run definitions |
| `final-experiment/run-final-experiment.py` | Serial experiment runner |
| `final-experiment/final-experiment-sut-prompts/<sut>/<target>/` | The `dev`, `nl`, `iauto`, and `oauto` prompt YAML for each MR |
| `final-experiment/final-experiment-run-metadata/all-runs.jsonl` | One JSON line per attempted run (status, timings, token usage, kills, coverage). It also contains pilot and retried attempts, so it has more lines than the 480 reported runs |
| `final-experiment/final-experiment-run-metadata/<run-id>.*` | Per run: the exact prompt used, metadata JSON, generation/test/PIT logs, and the archived generated sources (`.generated/`) |
| `final-experiment/final-experiment-pit-reports/<run-id>/` | PIT reports for every run |
| `final-experiment/final-experiment-stripped-oracles/` | The 22 oracle-stripped `nl` runs plus one unmodified control replay (see [Oracle stripping](#oracle-stripping-auto-runs)) |
| `final-experiment/final-experiment-pit-reports-stripped/` | PIT reports for the stripped re-runs |
| `final-experiment/analysis/` | `analyse.py` (results tables), `OracleStripper.java` and `verify_oracle_stripping.py`, and the committed `results/` |

Run IDs follow `<sut>__<mr>__<mode>__<generator>__<model>__runNNN`. Randoop runs use `no-model` as the model component.

## Current Scope

The backend is designed to be generic at the JUnit integration level: it can target any Java SUT that can be called from generated or developer-written JUnit tests. Practical quality still depends on the context supplied to the LLM, deterministic SUT behavior, valid object construction, and clear input-domain constraints.

Generated metamorphic relations should be treated as developer-reviewable candidates. This prototype does not evaluate MR quality.

## Security

Never commit `.env` or API keys. Review generated tests before trusting them in a real project.
