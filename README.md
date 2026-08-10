# Metamorphic Testing with LLMs

Backend prototype for generating developer-reviewable **JUnit 5 metamorphic tests** from a Java system under test (SUT), a target method, an input domain, and a metamorphic relation.

The current design treats the LLM as a code-generation assistant. Instead of asking for raw JSON test pairs, the tool asks the model to generate a complete JUnit 5 test class with explicit helper methods for:

- source input generation
- follow-up input generation/transformation
- metamorphic relation assertions

This keeps the generated logic readable and debuggable by developers.

The backend supports configurable JSON/JUnit outputs and developer- or LLM-provided MR logic. See [CONFIGURATION.md](docs/CONFIGURATION.md) for the detailed explanation.

## Requirements

| What | Why |
|------|-----|
| JDK 17+ | Compile and run the backend (the Randoop input-generation mode requires Java 17) |
| Maven | Build the project and resolve JUnit 5 |
| OpenAI API key | `LLM`, `HYBRID`, and `NEW_HYBRID` generation (raw `RANDOOP` makes no API calls) |
| JUnit Platform Console Standalone jar | Optional alternative to Maven for compiling/running generated JUnit tests |
| Randoop | Bundled — `lib/randoop-all-4.3.4.jar` is vendored and wired into `pom.xml` (system scope), so no separate install is needed for the Randoop-backed input modes |

## Configuration

Create a `.env` file in the project root:

```text
OPENAI_API_KEY=sk-...
OPENAI_MODEL=gpt-4o-mini
OPENAI_BASE_URL=https://api.openai.com/v1
MAVEN_CMD=mvn
JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR=/absolute/path/to/junit-platform-console-standalone.jar
```

`JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR` is optional. If it is missing, the tool uses Maven (`mvn test`) to compile and run generated JUnit tests. `MAVEN_CMD` is optional too; set it only if Maven is not on your normal PATH.

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
| `examples/pricing/` | Example shopping-cart SUT, MR helper, and prompt |
| `examples/pricing/generated/data-generator-code/` | Generated Java code that creates and evaluates pricing JSON data |
| `examples/pricing/generated/json-data/` | Generated pricing JSON data and pass/fail splits |
| `examples/pricing/generated/junit-tests/` | Generated pricing JUnit tests and pass/fail splits |
| `examples/pricing/generated/junit-support/` | Copied pricing SUT/support/MR sources used only to compile generated JUnit tests |
| `examples/pricing/generated/reports/` | Generated pricing HTML reports |
| `src/main/resources/reports/` | FreeMarker templates for generated HTML reports |
| `prompt.yaml` | Active generation config |
| `prompt.class-level.example.yaml` | Template config |
| `docs/CONFIGURATION.md` | Explanation of output/MR-provider configuration combinations |
| `docs/PITEST.md` | PIT mutation testing profile, command, target scope, and report paths |

## Prompt Config

`prompt.yaml` uses top-level YAML fields. `SUTSupportFiles` can be an empty list or a YAML list of paths.
The SUT may be identified with `SUTClassFile`, or with `ProjectRoot` plus a fully qualified `SUTClass`.
When `SUTClasspath` is omitted, the framework locates the nearest Maven project and resolves its
compiled outputs and dependency classpath. Set `AutomaticDiscovery: false` to require explicit paths.
The backend parses this file with SnakeYAML into `PromptConfig`; the LLM receives the generated prompt built from those Java config values, not the raw YAML file.

```yaml
SUTClassFile: examples/sorting/src/SortUtil.java
TargetFunction: public static int[] sortArray(int[] arr)
SUTSupportFiles: []

MRInput: the follow-up input is a permutation of the source input array
MROutput: the sorted source output and sorted follow-up output are equal arrays
MR:

Count: 8
InputDomain: non-null int arrays; include empty arrays, duplicates, negatives, already sorted arrays, reverse sorted arrays
GeneratedClassName: GeneratedSortUtilMetamorphicTest
OutputRoot: examples/sorting/generated
JsonRequired: false
TestSuiteRequired: true
MRProvider: LLM
MaxRepairAttempts: 1
```

A minimal project-oriented form is:

```yaml
ProjectRoot: examples/jsoup
SUTClass: org.jsoup.safety.Cleaner
TargetFunction: public org.jsoup.nodes.Document clean(org.jsoup.nodes.Document dirtyDocument)
MR: Cleaning an already-cleaned document must not change the cleaned result.
Count: 20
```

`InputDomain`, `SUTSupportFiles`, and `SUTClasspath` are optional in this form. The framework inspects
the resolved method's generic parameter and return types and sends discovered public constructors and
static factory methods to the LLM. Randoop execution currently requires a one-argument target method;
other signatures produce a clear message that a generated invocation wrapper is required.

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
| `LLM` (default) | the LLM (existing behavior) | yes |
| `RANDOOP` | Randoop, building objects by calling the SUT's own constructors/methods | none (fully offline) |
| `HYBRID` | the LLM suggests seed values, then Randoop builds objects from them at scale | one small seeding call |
| `NEW_HYBRID` | Randoop first discovers API-valid source examples; the LLM then generalizes them into the final constrained input set | final generation call plus any repair calls |

Key points:

- **`RANDOOP` and `HYBRID` require `MRProvider: DEV`.** Randoop produces the final source inputs on these paths, so the metamorphic relation must be developer-owned. `NEW_HYBRID` feeds Randoop examples into the normal LLM generation/repair path and therefore supports both `DEV` and `LLM` MR providers.
- **Works for any object or array.** Randoop constructs inputs from the SUT's visible API, so object SUTs (e.g. an `Order` with a `List<LineItem>`), array SUTs (`double[][]`), and nested object graphs (`Cart` → `List<CartItem>`) are all supported with no per-SUT serialization code.
- **All modes honor the same output flags.** With `JsonRequired: true` you get JSON data, pass/fail splits, and an HTML report. `RANDOOP`/`HYBRID` emit their object JUnit suites pre-split because they know verdicts in-process; `LLM`/`NEW_HYBRID` use the existing generated-test execution and actual-result splitter.
- **Seeding source (HYBRID):** the LLM is asked for seed values from the `InputDomain` description when present, otherwise from the SUT source. `InputDomain` values are coarser (domain-level), while code-derived values can straddle exact boundaries.
- **Seeding source (NEW_HYBRID):** Randoop runs first without an API call, retains source objects accepted by the target method, and emits each runtime value plus its minimal Java construction trace. The LLM receives those examples together with `InputDomain`; the examples ground it in the real API, while `InputDomain` remains authoritative for semantic constraints and diversity.
- **Optional `RandoopTargetClasses`:** use a YAML list of fully qualified class names to limit Randoop to construction-relevant APIs. If omitted, the SUT and all `SUTSupportFiles` are explored as before.
- The Randoop time budget is fixed at 15s; Randoop runs in a subprocess so the SUT classes are genuinely on its classpath.

`NEW_HYBRID` is useful for constrained object domains. Pure `LLM` generation understands prose constraints but can hallucinate constructors or factories. Existing `HYBRID` provides domain values before exploration, but Randoop must still combine them into semantically valid objects and may produce few usable cases. `NEW_HYBRID` reverses the order: Randoop demonstrates real construction paths first, then the LLM applies the domain rules and expands those grounded examples into diverse final inputs. It does not guarantee better results for every SUT, so it remains a separate evaluation mode rather than replacing either existing strategy.

Example:

```yaml
InputGenerator: NEW_HYBRID
RandoopTargetClasses:
  - com.example.ConversionCase
  - com.example.Money
  - java.math.BigDecimal
```

Example (Randoop builds the carts, developer owns the MR):

```yaml
SUTClassFile: examples/pricing/src/PricingEngine.java
SUTSupportFiles:
  - examples/pricing/src/Cart.java
  - examples/pricing/src/CartItem.java
  - examples/pricing/src/Customer.java
  - examples/pricing/src/CustomerTier.java
  - examples/pricing/src/DiscountCode.java
TargetFunction: public static BigDecimal calculateDiscountedSubtotal(Cart cart)
InputGenerator: HYBRID
JsonRequired: true
TestSuiteRequired: true
MRProvider: DEV
DeveloperMrFile: examples/pricing/mr/PricingMetamorphicSpec.java
DeveloperFollowUpMethod: PricingMetamorphicSpec.generateFollowUp
DeveloperAssertMethod: PricingMetamorphicSpec.assertRelation
```

When both `JsonRequired` and `TestSuiteRequired` are true, the backend performs two generation passes. If the configured class name ends in `Data`, the JUnit class name is derived by replacing that suffix with `Test`.

`OutputRoot` controls where generated files are written. If it is omitted and the SUT is under
`examples/<name>/src`, the backend defaults to `examples/<name>/generated`. Otherwise it falls back
to the root-level `generated` folder.

Generated data code is compiled with plain `javac`, so it must use only the Java standard library and the SUT/support classes. The LLM is instructed to build JSON manually rather than importing Jackson, Gson, or other JSON libraries.

If `MRProvider: DEV`, add:

```yaml
DeveloperMrFile: examples/pricing/mr/PricingMetamorphicSpec.java
DeveloperFollowUpMethod: PricingMetamorphicSpec.generateFollowUp
DeveloperAssertMethod: PricingMetamorphicSpec.assertRelation
```

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

The profile follows the PIT Maven quickstart flow and pins explicit plugin versions. It mutates the compiled example SUT classes currently registered as main sources:

- `DijkstraAlgorithm*`
- `MatrixRank`
- `PricingEngine*`

It runs only generated `*PassingTest` classes and excludes generated `*FailingTest` classes. This is intentional: the framework stores failing tests as bug-revealing artifacts, while PIT requires the selected test suite to be green before mutation analysis starts.

Reports are written to:

```text
target/pit-reports/index.html
target/pit-reports/mutations.xml
```

The current mutation threshold is `0`, so PIT produces reports without failing the build while generated metamorphic suites are still being evaluated. See [PITEST.md](docs/PITEST.md) for details.

## Current Scope

The backend is designed to be generic at the JUnit integration level: it can target any Java SUT that can be called from generated or developer-written JUnit tests. Practical quality still depends on the context supplied to the LLM, deterministic SUT behavior, valid object construction, and clear input-domain constraints.

Generated metamorphic relations should be treated as developer-reviewable candidates. This prototype does not evaluate MR quality.

## Security

Never commit `.env` or API keys. Review generated tests before trusting them in a real project.
