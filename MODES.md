# LLM Generation Modes

The framework supports four user-facing modes. Each mode controls how much code the LLM is allowed to generate.

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

## Mode 3: Full JUnit 5 Candidate Tests, Split By Actual Results

Use this when you want the LLM to generate full runnable JUnit 5 candidate tests, then let the
framework split actual passing and failing cases after execution.

```text
Mode: 3
```

The LLM generates a JUnit class with:

```java
@Test
failingMetamorphicCase1()

generateFollowUp(source)
assertMetamorphicRelationFor(source)
assertMetamorphicRelation(sourceOutput, followUpOutput)
```

The LLM generates candidate source inputs. It does not decide which ones pass or fail. The backend
runs the candidate class with Maven/JUnit, reads the JUnit XML report, and writes separate files
for actual passing and actual failing methods.

The generated candidate test is first written to:

```text
generated-tests/<GeneratedClassName>.java
```

After execution, the backend writes:

```text
generated-tests/<GeneratedClassNameWithoutTest>PassingTest.java
generated-tests/<GeneratedClassNameWithoutTest>FailingTest.java
```

Compilation errors and broken generated code are still treated as generation failures.

Best for:

- fastest end-to-end MT demo
- JUnit integration
- keeping the final suite small when many candidate inputs are possible
- showing only bug-revealing cases in IDE/Maven output

## Mode 4: Developer-Defined MR Helpers With LLM-Generated JUnit Tests

Use this when you want the developer to control the MR transformation and assertion, but still want
the LLM to generate source inputs and JUnit test methods.

```text
Mode: 4
DeveloperMrFile: src/main/java/OrderMetamorphicSpec.java
DeveloperFollowUpMethod: OrderMetamorphicSpec.generateFollowUp
DeveloperAssertMethod: OrderMetamorphicSpec.assertRelation
```

The developer helper file owns the MR logic:

```java
OrderMetamorphicSpec.generateFollowUp(source)
OrderMetamorphicSpec.assertRelation(sourceOutput, followUpOutput)
```

The LLM-generated candidate JUnit class should only create source inputs and call the developer-owned methods:

```java
@Test
void generatedSourceCase1() {
    Order source = ...;
    double sourceOutput = OrderUtil.calculateTotal(source);
    Order followUp = OrderMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = OrderUtil.calculateTotal(followUp);
    OrderMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
}
```

The LLM must not redefine `generateFollowUp`, must not redefine the assertion method, and must not
inline the assertion logic. This keeps domain-specific MR correctness under developer control.
After Maven/JUnit executes the candidate class, the backend writes actual passing and failing files
using the same naming scheme as Mode 3.

Best for:

- complex or semantic MRs
- cases where the developer trusts LLM-generated inputs but not LLM-generated oracle logic
- showing a practical hybrid between manual MT frameworks and fully generated tests

## Mapping To MT Concepts

| MT concept | Mode 1 | Mode 2 | Mode 3 | Mode 4 |
|---|---|---|---|---|
| Source input generation | LLM | LLM | LLM | LLM |
| Follow-up input generation | Developer | LLM | LLM | Developer |
| Output relation/assertion | Developer | Developer | LLM-generated, developer-reviewed | Developer |
| Output artifact | JSON data | JSON data | JUnit 5 passing/failing classes from actual results | JUnit 5 passing/failing classes from actual results |
| Run target | Generated Java main | Generated Java main | Maven/JUnit | Maven/JUnit |

## Recommended MVP Usage

For the research prototype, Mode 3 is the strongest demo because it shows JUnit integration.

Mode 1 and Mode 2 are useful to show flexibility:

- Mode 1: lowest trust in LLM
- Mode 2: medium trust in LLM
- Mode 4: developer controls MR transformation/assertion, LLM generates source-input JUnit tests
- Mode 3: highest automation, but requires developer review
