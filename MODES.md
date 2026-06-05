# LLM Generation Modes

The framework supports three user-facing modes. Each mode controls how much code the LLM is allowed to generate.

## Mode 1: Source Inputs Only

Use this when you want the LLM to help create diverse source inputs, but you want the developer to write the follow-up transformation and assertion manually.

```text
Mode: 1
```

The LLM generates a Java data-generator class with:

```java
generateSources()
main(String[] args)
```

The generated class prints JSON like:

```json
[
  {"source": ...},
  {"source": ...}
]
```

The validated JSON output is written to:

```text
generated-data/<GeneratedClassName>.json
```

Mode 1 generated code must use only the Java standard library and the SUT/support classes. It should build JSON manually with `StringBuilder`; it should not import Jackson, Gson, or other JSON libraries.

Developer still writes:

```java
generateFollowUp(source)
assertMetamorphicRelation(...)
```

Best for:

- early exploration of valid inputs
- high developer control
- cases where follow-up transformation is too domain-specific to trust to the LLM

## Mode 2: Source Inputs and Follow-Up Inputs

Use this when you want the LLM to generate both source inputs and the follow-up transformation, but you still want the developer to write the assertion/oracle.

```text
Mode: 2
```

The LLM generates a Java data-generator class with:

```java
generateSources()
generateFollowUp(source)
main(String[] args)
```

The generated class prints JSON like:

```json
[
  {"source": ..., "followUp": ...},
  {"source": ..., "followUp": ...}
]
```

The validated JSON output is written to:

```text
generated-data/<GeneratedClassName>.json
```

Mode 2 generated code must use only the Java standard library and the SUT/support classes. It should build JSON manually with `StringBuilder`; it should not import Jackson, Gson, or other JSON libraries.

The follow-up input is generated from the source input using `MRInput`.

Developer still writes:

```java
assertMetamorphicRelation(sourceOutput, followUpOutput)
```

Best for:

- checking whether the LLM can apply the MR input transformation
- keeping assertion logic human-controlled
- producing reusable source/follow-up fixtures

## Mode 3: Full JUnit 5 Metamorphic Test

Use this when you want the LLM to generate the full runnable JUnit 5 test class.

```text
Mode: 3
```

The LLM generates a JUnit class with:

```java
@ParameterizedTest
@MethodSource("generateSources")
testMetamorphicRelation(...)

generateSources()
generateFollowUp(source)
assertMetamorphicRelation(sourceOutput, followUpOutput)
```

The generated test is written to:

```text
generated-tests/<GeneratedClassName>.java
```

Maven/JUnit runs it as a normal test.

Best for:

- fastest end-to-end MT demo
- JUnit integration
- showing generated tests in IDE/Maven output

## Mapping To MT Concepts

| MT concept | Mode 1 | Mode 2 | Mode 3 |
|---|---|---|---|
| Source input generation | LLM | LLM | LLM |
| Follow-up input generation | Developer | LLM | LLM |
| Output relation/assertion | Developer | Developer | LLM-generated, developer-reviewed |
| Output artifact | JSON data | JSON data | JUnit 5 test class |
| Run target | Generated Java main | Generated Java main | Maven/JUnit |

## Recommended MVP Usage

For the research prototype, Mode 3 is the strongest demo because it shows JUnit integration.

Mode 1 and Mode 2 are useful to show flexibility:

- Mode 1: lowest trust in LLM
- Mode 2: medium trust in LLM
- Mode 3: highest automation, but requires developer review
