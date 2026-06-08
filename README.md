# Metamorphic Testing with LLMs

Backend prototype for generating developer-reviewable **JUnit 5 metamorphic tests** from a Java system under test (SUT), a target method, an input domain, and a metamorphic relation.

The current design treats the LLM as a code-generation assistant. Instead of asking for raw JSON test pairs, the tool asks the model to generate a complete JUnit 5 test class with explicit helper methods for:

- source input generation
- follow-up input generation/transformation
- metamorphic relation assertions

This keeps the generated logic readable and debuggable by developers.

The backend also supports three LLM involvement modes. See [MODES.md](MODES.md) for the detailed explanation.

## Requirements

| What | Why |
|------|-----|
| JDK 11+ | Compile and run the backend |
| Maven | Build the project and resolve JUnit 5 |
| OpenAI API key | Generate the JUnit test class |
| JUnit Platform Console Standalone jar | Optional alternative to Maven for compiling/running generated JUnit tests |

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
| `src/main/java/OpenaiRunner.java` | Compatibility entry point that delegates to `mtllm.App` |
| `src/main/java/SortUtil.java` | Simple demo SUT |
| `src/main/java/mtllm/App.java` | Main backend orchestration |
| `src/main/java/mtllm/config/` | Reads and stores `prompt.txt` settings |
| `src/main/java/mtllm/sut/` | Loads SUT source and first-level dependencies |
| `src/main/java/mtllm/prompt/` | Builds initial and repair prompts |
| `src/main/java/mtllm/llm/` | LLM provider interface and OpenAI client |
| `src/main/java/mtllm/generation/` | Writes generated JUnit code |
| `src/main/java/mtllm/runner/` | Compiles, runs, and repairs generated tests |
| `src/main/java/mtllm/util/` | Small helpers for `.env`, JSON, and code fences |
| `prompt.txt` | Active generation config |
| `prompt.class-level.example.txt` | Template config |
| `MODES.md` | Explanation of Mode 1, Mode 2, and Mode 3 |

## Prompt Config

`prompt.txt` uses `Key: value` lines.

```text
SUTClassFile: src/main/java/SortUtil.java
TargetFunction: public static int[] sortArray(int[] arr)
SUTSupportFiles:

MRInput: the follow-up input is a permutation of the source input array
MROutput: the sorted source output and sorted follow-up output are equal arrays
MR:

Count: 8
InputDomain: non-null int arrays; include empty arrays, duplicates, negatives, already sorted arrays, reverse sorted arrays
GeneratedClassName: GeneratedSortUtilMetamorphicTest
Mode: 3
MaxRepairAttempts: 1
```

Prefer `MRInput` plus `MROutput` because it matches the metamorphic-testing form “input relation implies output relation.” `MR` remains as a fallback for relations that are easier to express in one field.

`DataType` is no longer required. The LLM is instructed to infer Java types from the target method signature and SUT source.

`Mode` controls how much the LLM generates:

```text
Mode: 1  source inputs only, printed as JSON
Mode: 2  source inputs + follow-up inputs, printed as JSON
Mode: 3  full JUnit 5 metamorphic test class, filtered to failing cases only
```

Mode 1 and Mode 2 generated code is compiled with plain `javac`, so it must use only the Java standard library and the SUT/support classes. The LLM is instructed to build JSON manually rather than importing Jackson, Gson, or other JSON libraries.

## Run

Build the backend with Maven:

```bash
mvn test
```

Run the generator:

```bash
mvn exec:java -Dexec.mainClass=OpenaiRunner
```

Or compile/run manually without Maven plugins:

```bash
javac -d out/classes src/main/java/*.java src/main/java/mtllm/*.java src/main/java/mtllm/*/*.java
java -cp out/classes OpenaiRunner
```

The generated Mode 3 JUnit class is written to:

```text
generated-tests/<GeneratedClassName>.java
```

When useful for demonstrations, passing and failing generated classes can both live in this same
folder with clear class names, for example `GeneratedOrderUtilMetamorphicPassingTest` and
`GeneratedOrderUtilMetamorphicFailingTest`.

For Mode 1 and Mode 2, generated Java data-generator code is written to:

```text
generated-code/<GeneratedClassName>.java
```

and the JSON data output is written to:

```text
generated-data/<GeneratedClassName>.json
```

`generated-tests/` is ignored by Git because it is runtime output. Maven and VS Code are configured to treat this folder as the generated JUnit test source root.

If `JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR` is configured, the tool compiles/runs generated tests through the JUnit Platform Console. Otherwise, it uses `mvn test -Dtest=<GeneratedClassName>`. In Mode 3, generated tests are expected to contain only MR-violating cases, so JUnit assertion failures are treated as successful bug discovery rather than broken generation. Compilation errors, invalid generated code, and infrastructure failures are still sent back to the LLM for up to `MaxRepairAttempts` repair attempts.

## Current Scope

The backend is designed to be generic at the JUnit integration level: it can target any Java SUT that can be called from generated or developer-written JUnit tests. Practical quality still depends on the context supplied to the LLM, deterministic SUT behavior, valid object construction, and clear input-domain constraints.

Generated metamorphic relations should be treated as developer-reviewable candidates. This prototype does not evaluate MR quality.

## Security

Never commit `.env` or API keys. Review generated tests before trusting them in a real project.
