# MT4J: How the Framework Works and How to Configure It

MT4J generates developer-reviewable JUnit 5 metamorphic tests for a Java system under test (SUT). One run
reads a YAML prompt config, builds an LLM prompt from it, and produces a JUnit suite split into passing and
failing tests (and, optionally, executed JSON data with an HTML report).

Setup and quick-start commands are in the [README](../README.md). The final experiment is described in
[EXPERIMENT.md](EXPERIMENT.md).

Contents: [Pipeline](#pipeline) · [Prompt configuration](#prompt-configuration) ·
[MR ownership](#mr-ownership) · [Input generators](#input-generators) · [Outputs](#outputs) ·
[Running](#running) · [Mutation testing with PIT](#mutation-testing-with-pit) ·
[Source layout](#source-layout) · [Limitations](#limitations)

## Pipeline

1. **Resolve the SUT.** Find the project, SUT source, exact target method overload, compiled outputs and
   Maven dependency classpath (`ProjectDiscovery`, `TargetMethodResolver`).
2. **Inspect the API.** Report the target's parameter and return types and the public constructors and
   static factories of the input types. Build a bounded construction graph: ClassGraph finds concrete
   implementations of interface and abstract parameters, reflection follows constructors, factories,
   arrays and generic arguments, and JavaParser finds source-level factories and builders. The graph is
   capped at depth 5 and 96 classes.
3. **Wrap awkward targets.** For instance methods and methods whose arity is not one, generate a typed
   one-input invocation wrapper (receiver first, then the arguments) so Randoop can construct and vary
   the whole invocation. Its source is kept under `generated-support/` and `junit-support/`.
4. **Infer the input domain** (LLM-backed generators, only when `InputDomain` is absent). Ask the LLM for
   structured YAML with grounded constraints, diversity dimensions and measurable scenarios; validate it
   with the same typed model used for developer-written domains; repair malformed YAML once, then fail
   clearly. The result is saved to `<OutputRoot>/input-domain/inferred-input-domain.yaml`. Scenario slots
   are allocated deterministically within `Count`. An explicit `InputDomain` is never replaced.
5. **Generate source inputs** with the configured [input generator](#input-generators). Randoop-backed
   generators keep only distinct inputs that the target method accepts.
6. **Generate the test class.** The LLM writes a JUnit 5 class with explicit helpers for source-input
   construction, the follow-up transformation and the relation assertion (or calls the developer's
   helpers, see [MR ownership](#mr-ownership)).
7. **Quality gate.** Parse the generated JUnit with JavaParser before compiling. Reject malformed Java,
   empty required suites, suites above `Count`, duplicate test bodies, missing scenario IDs and test
   methods that never reach an assertion.
8. **Repair.** If missing scenario coverage is the only defect, request just the missing `@Test` methods
   and merge them into the class. Otherwise send the whole class back with the error (compilation,
   runtime, or quality gate) for up to `MaxRepairAttempts` attempts.
9. **Execute and split.** Compile and run the suite; JUnit assertion failures are treated as discovered
   violations, and actual passing and failing `@Test` methods are written to separate classes. Write the
   JSON data and HTML report when requested.

## Prompt configuration

A run is described by one YAML file (default `prompt.yaml` in the repository root). The backend parses it
with SnakeYAML into `PromptConfig`; the LLM sees a prompt built from those values, not the raw YAML.

Example: the final experiment's matrix-transpose `nl` prompt
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

A minimal project-oriented form, relying on automatic discovery:

```yaml
ProjectRoot: examples/jsoup
SUTClass: org.jsoup.safety.Cleaner
TargetFunction: public org.jsoup.nodes.Document clean(org.jsoup.nodes.Document dirtyDocument)
MR: Cleaning an already-cleaned document must not change the cleaned result.
Count: 20
```

### Fields

| Field | Default | Meaning |
|-------|---------|---------|
| `ProjectRoot` | | Maven project containing the SUT; used for discovery |
| `AutomaticDiscovery` | `true` | Locate the nearest Maven project and resolve compiled outputs and dependencies when `SUTClasspath` is omitted. `false` requires explicit paths |
| `SUTClassFile` or `SUTClass` | | The class under test, as a source path or a fully qualified name under `ProjectRoot` |
| `TargetFunction` | | The method under test: a full signature or just the name if it is unambiguous |
| `SUTSupportFiles` | `[]` | Extra source files to show the LLM |
| `SUTClasspath` | discovered | Compiled classes and jars the SUT needs |
| `SUTDescription` (alias `SUT`) | | Short prose description of the SUT |
| `MRInput`, `MROutput` | | The relation as "input transformation implies output relation" (preferred) |
| `MR` | | One-field fallback for relations easier to state in one sentence |
| `Count` | `5` | Maximum number of source inputs / tests |
| `InputDomain` (legacy alias `Constraints`) | inferred | Prose or a structured mapping (below). When absent, LLM-backed generators infer it |
| `GeneratedClassName` | `GeneratedMetamorphicTest` | Name of the generated test class |
| `OutputRoot` | `examples/<name>/generated` | Where generated files go (see [Outputs](#outputs)) |
| `MavenProfiles` | `[]` | Maven profiles that put the SUT and generated tests on the build path |
| `InputGenerator` | `LLM` | `LLM`, `RANDOOP` or `NEW_HYBRID` (see [Input generators](#input-generators)) |
| `RandoopTargetClasses` | `[]` | Extra classes for Randoop to use as construction evidence (a hint; discovery still runs) |
| `MRProvider` | `LLM` | Who implements the relation: `LLM` or `DEV` (see [MR ownership](#mr-ownership)) |
| `DeveloperMrFile`, `DeveloperFollowUpMethod`, `DeveloperAssertMethod` | | The developer's MR helpers, for `MRProvider: DEV` |
| `JsonRequired` | `false` | Also produce executed JSON data and an HTML report |
| `TestSuiteRequired` | `true` | Produce the JUnit suite |
| `MaxRepairAttempts` | `1` | LLM repair attempts after a failed generation |

`DataType` is no longer used; the LLM infers types from the target signature and SUT source.

### Structured input domain

`InputDomain` accepts prose or a mapping:

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

Scenario categories are `NORMAL`, `BOUNDARY`, `EDGE` and `INVALID`. Scenario IDs must be unique and the
`targetCases` values must not sum to more than `Count`. For LLM-backed generation each scenario becomes an
explicit checklist item and its ID must appear in the generated test method names. `RANDOOP` does not
interpret scenario prose.

## MR ownership

`MRProvider` decides who implements the metamorphic relation:

| `MRProvider` | Developer supplies | LLM writes | Experiment name |
|---|---|---|---|
| `DEV`, both methods | `generateFollowUp` and `assertRelation` in Java | source inputs only | `dev` (human) |
| `DEV`, follow-up only | input transformation | output relation, from `MROutput` | `iauto` |
| `DEV`, assertion only | output relation | input transformation, from `MRInput` | `oauto` |
| `LLM` | nothing | transformation and assertion, from `MRInput`/`MROutput` | `nl` (AUTO) |

With `MRProvider: DEV`, name the helper file and methods:

```yaml
MRProvider: DEV
DeveloperMrFile: examples/the-algorithms-java/mt-testing/mr/MatrixRankMetamorphicSpec.java
DeveloperFollowUpMethod: thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp
DeveloperAssertMethod: thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation
```

Method names are qualified with the spec's package when it declares one (here `thealgorithmsmt`; the
threeten-extra specs have no package, so `DaysBetweenMetamorphicSpec.generateFollowUp`). The developer MR
file is compiled separately from the framework, so keep it self-contained: static methods and no imports
from `mtllm`.

For split ownership, name only the developer-owned method and describe the other half in prose:
`DeveloperFollowUpMethod` + `MROutput` (i-AUTO) or `DeveloperAssertMethod` + `MRInput` (o-AUTO).
`PromptConfigLoader` requires `DeveloperMrFile`, at least one method, and the prose for the LLM-owned half.

For a wrapped target (instance or multi-argument method), the developer follow-up method takes the
original typed invocation components and returns an `Object[]` in the same order: the receiver first for
an instance method, then the arguments. The generated wrapper converts that array back into a typed case.

`MRProvider: LLM` is the highest-trust option: the LLM decides how the relation is checked, so review its
assertions. A wrong assertion can pass silently.

## Input generators

| `InputGenerator` | Source inputs come from | API calls |
|---|---|---|
| `LLM` (default) | The LLM, guided by the inferred or supplied domain | generation, repair, and one domain-inference call when `InputDomain` is absent |
| `RANDOOP` | Randoop, calling the SUT's own constructors and methods | none (offline) |
| `NEW_HYBRID` | Randoop first finds API-valid examples; the LLM then generalises them into the final, domain-constrained inputs. The Part IV reports call this generator **HYBRID** | as `LLM` |

- **`RANDOOP` requires `MRProvider: DEV` with both methods.** Randoop produces the final inputs and applies
  the developer's relation in-process, so it emits its suites already split into passing and failing.
  `LLM` and `NEW_HYBRID` work with every MR ownership mode.
- **Works for objects and arrays.** Randoop builds inputs from the SUT's visible API, so object graphs,
  arrays and nested collections need no per-SUT serialisation code.
- **NEW_HYBRID seeding.** Randoop runs first, keeps source objects the target accepts, and emits each value
  with its minimal Java construction code. The LLM receives these with the input domain: the examples
  ground it in the real API, and the domain stays authoritative for semantic constraints.
- **Empty-harvest fallback.** If Randoop finds no executable input, NEW_HYBRID tries deterministic
  Instancio seeds, each validated by running the SUT. If the seed stage still yields nothing or fails, the
  run continues as `LLM` and prints `[INPUT_GENERATOR_FALLBACK]`; `<OutputRoot>/metrics.json` records
  `requestedInputGenerator: NEW_HYBRID`, `effectiveInputGenerator: LLM` and the reason
  (`NEW_HYBRID_FALLBACK_LLM_NO_SEEDS` or `NEW_HYBRID_FALLBACK_LLM_SEED_ERROR`). Raw `RANDOOP` has no
  fallback, to keep generator comparisons fair.
- **Callback interfaces.** When an argument is an interface whose methods return enums, booleans,
  characters or primitive numbers, deterministic policy classes (constant and numeric-threshold returns)
  are generated, compiled into the Randoop workspace, and copied as JUnit support. Seeds must have
  non-null reference arguments.
- **Budget.** Randoop runs for 15 s in a subprocess, so the SUT classes are genuinely on its classpath.
- **Removed: `HYBRID`.** An earlier generator in which the LLM suggested seed values and Randoop built the
  final inputs was replaced by `NEW_HYBRID` before the final experiment. A config that still says `HYBRID`
  fails with a message pointing to `NEW_HYBRID`.

Example: Randoop builds the inputs, the developer owns the relation (the experiment's days-between
`dev` prompt with the generator switched):

```yaml
ProjectRoot: examples/threeten-extra
SUTClassFile: examples/threeten-extra/src/main/java/org/threeten/extra/Days.java
TargetFunction: public static Days between(Temporal startDateInclusive, Temporal endDateExclusive)
MavenProfiles:
  - threeten
InputGenerator: RANDOOP
MRProvider: DEV
DeveloperMrFile: examples/threeten-extra/mr/DaysBetweenMetamorphicSpec.java
DeveloperFollowUpMethod: DaysBetweenMetamorphicSpec.generateFollowUp
DeveloperAssertMethod: DaysBetweenMetamorphicSpec.assertRelation
```

Example of construction hints, from the jsoup cleaner-idempotence prompt:

```yaml
InputGenerator: NEW_HYBRID
RandoopTargetClasses:
  - org.jsoup.Jsoup
  - org.jsoup.safety.Safelist
```

## Outputs

`JsonRequired` and `TestSuiteRequired` choose the artefacts; at least one must be true.

| `JsonRequired` | `TestSuiteRequired` | Produces |
|---|---|---|
| `false` | `true` (default) | JUnit suite split into passing and failing classes |
| `true` | `false` | Executed JSON data (each case's source, follow-up, outputs and verdict) and an HTML report |
| `true` | `true` | Both, as two generation passes. A `GeneratedClassName` ending in `Data` gets `Test` for the JUnit pass |

All files go under `OutputRoot`. If it is omitted and the SUT is under `examples/<name>/src`, it defaults
to `examples/<name>/generated`; otherwise to `generated/` in the repository root.

```text
<OutputRoot>/
  junit-tests/<GeneratedClassName>.java                   generated candidate class
  junit-tests/<Base>PassingTest.java                      tests that passed when executed
  junit-tests/<Base>FailingTest.java                      tests that failed: candidate MR violations
  junit-support/                                          SUT, MR and wrapper sources needed to compile the tests
  data-generator-code/<GeneratedClassName>.java           JSON mode: generated data program
  json-data/<GeneratedClassName>.json                     JSON mode: every executed case
  json-data/<Base>Passing.json, <Base>Failing.json        JSON mode: split by verdict
  reports/<Base>Report.html                               JSON mode: FreeMarker HTML report
  input-domain/inferred-input-domain.yaml                 inferred domain, when inference ran
  metrics.json                                            tokens, repairs, generator actually used, status
```

`<Base>` is the class name without its `Test`/`Data` suffix. The JSON data program is compiled with plain
`javac`, so the LLM builds JSON by hand rather than importing a JSON library. The HTML report summarises
the SUT, target, relation, configuration and pass/fail counts, with expandable case details.

## Running

```bash
mvn test                                                                 # build and run the unit tests
mvn -Pjsoup -DskipTests compile exec:java -Dexec.mainClass=mtllm.OpenaiRunner      # run ./prompt.yaml
mvn -P<profiles> -DskipTests compile exec:java -Dexec.mainClass=mtllm.OpenaiRunner \
  -Dexec.args="path/to/prompt.yaml"
```

`<profiles>` are the prompt's `MavenProfiles` (the root `prompt.yaml` needs `jsoup`). Use Maven exec rather than plain `java` so runtime
dependencies (FreeMarker, Randoop) are on the classpath. `mtllm.OpenaiRunner` delegates to `mtllm.App`.

Generated tests are compiled and run with Maven (`mvn test -Dtest=<GeneratedClassName>`), or with the JUnit
Platform Console when `JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR` is set. During Maven execution the current
generated sources are staged into `target/mtllm-test-sources` so older generated files cannot clash.

## Mutation testing with PIT

PIT runs through Maven profiles; a normal `mvn test` does not run it. Each experiment MR has its own PIT
profile (named in `final-experiment/experiment-manifest.yaml`) that targets only its class under test.
Where the SUT is an external project, the profile first copies its compiled classes into `target/classes`,
because PIT only mutates bytecode found there. The default `pitest` profile mutates
`org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector`:

```bash
mvn -Ppitest test-compile org.pitest:pitest-maven:mutationCoverage
```

The profiles run only the generated `*PassingTest` classes and exclude `*FailingTest`: PIT needs a green
suite before it starts, and the failing tests are kept as violation reports instead. Reports go to
`target/pit-reports/` (`index.html`, `mutations.xml`). The mutation threshold is `0`, so PIT reports
without failing the build. The spatial4j and threeten-extra profiles use PIT's full mutator set; the
others use the default set.

## Source layout

| Package (`src/main/java/mtllm/`) | Responsibility |
|---|---|
| `App`, `OpenaiRunner` | Orchestration and entry point |
| `config/` | Prompt YAML loading, validation and the typed config (`PromptConfig`, `InputGenerator`, `MRProvider`) |
| `sut/` | SUT and target-method resolution, API inspection, construction-graph discovery |
| `domain/` | Input-domain inference and scenario planning |
| `prompt/` | Initial and repair prompt construction |
| `llm/` | LLM client interface and the OpenAI client |
| `generation/` | Writing generated Java |
| `randoop/` | Randoop harvesting, invocation wrappers, callback synthesis, the Randoop JUnit emitter |
| `runner/` | Compiling and executing generated code, quality gate, repair loop, pass/fail splitting |
| `report/` | FreeMarker HTML report (templates in `src/main/resources/reports/`) |
| `util/` | `.env`, JSON and naming helpers |

## Limitations

- MT4J can target any Java SUT callable from JUnit, but quality depends on the context given to the LLM,
  deterministic SUT behaviour, valid object construction and clear domain constraints.
- There is no universal construction representation for arbitrary object graphs. In `LLM` and
  `NEW_HYBRID` modes the LLM still writes the fixture-construction code; the backend parses, compiles,
  executes and checks it.
- Reflection finds that a constructor exists, not undocumented rules (valid timestamp ordering, financial
  invariants, which callback behaviour reaches a meaningful path). The inferred domain and scenario gates
  help without making those rules automatic.
- Generated relations and assertions are candidates for developer review; MT4J does not judge whether an
  MR is correct.
