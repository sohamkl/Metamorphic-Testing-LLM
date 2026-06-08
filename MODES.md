# LLM Generation Modes

The framework supports four user-facing modes. They are ordered by increasing LLM responsibility.

## Mode 1: Developer-Defined MR Helpers With Executed JSON Data

Use this when you want JSON data rather than JUnit tests, while keeping MR logic under developer control.

```text
Mode: 1
DeveloperMrFile: src/main/java/PricingMetamorphicSpec.java
DeveloperFollowUpMethod: PricingMetamorphicSpec.generateFollowUp
DeveloperAssertMethod: PricingMetamorphicSpec.assertRelation
```

The LLM generates candidate source inputs. The generated Java data program then calls developer-owned methods and the real SUT to compute:

```text
source         = LLM-generated source input
followUp       = developer follow-up method applied to source
sourceOutput   = real SUT result for source
followUpOutput = real SUT result for followUp
passed         = developer assertion result for sourceOutput/followUpOutput
```

The full JSON is written to:

```text
generated-data/<GeneratedClassName>.json
```

The backend also splits the full JSON by actual `passed` value:

```text
generated-data/<GeneratedClassNameWithoutData>Passing.json
generated-data/<GeneratedClassNameWithoutData>Failing.json
```

Best for:

- lowest LLM responsibility among the remaining modes
- inspecting MT data without running tests in the IDE
- exporting source/follow-up/output pairs for reports or evaluation
- showing that follow-up/output values come from executed backend code rather than LLM guesses

## Mode 2: Developer-Defined MR Helpers With LLM-Generated JUnit Tests

Use this when you want the developer to control the MR transformation and assertion, but still want the LLM to generate source inputs and JUnit test methods.

```text
Mode: 2
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

After Maven/JUnit executes the candidate class, the backend writes actual passing and failing files:

```text
generated-tests/<GeneratedClassNameWithoutTest>PassingTest.java
generated-tests/<GeneratedClassNameWithoutTest>FailingTest.java
```

Best for:

- complex or semantic MRs
- cases where the developer trusts LLM-generated inputs but not LLM-generated oracle logic
- showing a practical hybrid between manual MT frameworks and fully generated tests

## Mode 3: LLM-Generated Source, Follow-Up, Assertion, And Executed JSON Data

Use this when you want the LLM to generate source inputs, the follow-up transformation, and the MR assertion, then have the generated Java data program execute the SUT and classify each data pair.

```text
Mode: 3
```

The LLM generates a Java data-generator class with:

```java
generateSources()
generateFollowUp(source)
assertMetamorphicRelation(sourceOutput, followUpOutput)
main(String[] args)
```

The generated class prints JSON like:

```json
[
  {
    "source": {},
    "followUp": {},
    "sourceOutput": 0,
    "followUpOutput": 0,
    "passed": true
  }
]
```

The backend splits the full JSON by actual `passed` value:

```text
generated-data/<GeneratedClassNameWithoutData>Passing.json
generated-data/<GeneratedClassNameWithoutData>Failing.json
```

Best for:

- checking whether the LLM can apply both the MR input transformation and MR output assertion
- producing reusable source/follow-up/output fixtures
- getting passing/failing JSON data without creating JUnit test files

## Mode 4: Full JUnit 5 Candidate Tests, Split By Actual Results

Use this when you want the LLM to generate full runnable JUnit 5 candidate tests, then let the framework split actual passing and failing cases after execution.

```text
Mode: 4
```

The LLM generates a JUnit class with:

```java
@Test
void generatedMetamorphicCase1() {
    ...
}

generateFollowUp(source)
assertMetamorphicRelationFor(source)
assertMetamorphicRelation(sourceOutput, followUpOutput)
```

The LLM generates candidate source inputs and MR helper code. It does not decide which tests pass or fail. The backend runs the candidate class with Maven/JUnit, reads the JUnit XML report, and writes separate files for actual passing and actual failing methods.

The generated candidate test is first written to:

```text
generated-tests/<GeneratedClassName>.java
```

After execution, the backend writes:

```text
generated-tests/<GeneratedClassNameWithoutTest>PassingTest.java
generated-tests/<GeneratedClassNameWithoutTest>FailingTest.java
```

Best for:

- fastest end-to-end MT demo
- JUnit integration
- highest LLM responsibility
- showing only bug-revealing cases in IDE/Maven output

## Mapping To MT Concepts

| MT concept | Mode 1 | Mode 2 | Mode 3 | Mode 4 |
|---|---|---|---|---|
| LLM responsibility | Lowest | Low/medium | Medium/high | Highest |
| Source input generation | LLM | LLM | LLM | LLM |
| Follow-up input generation | Developer method called by backend-run code | Developer | LLM | LLM |
| Source/follow-up outputs | SUT executed by backend-run data generator | SUT executed by JUnit | SUT executed by generated Java main | SUT executed by JUnit |
| Output relation/assertion | Developer | Developer | LLM-generated, developer-reviewed | LLM-generated, developer-reviewed |
| Output artifact | JSON source/follow-up/output data split by pass/fail | JUnit 5 passing/failing classes from actual results | JSON source/follow-up/output data split by pass/fail | JUnit 5 passing/failing classes from actual results |
| Run target | Generated Java main | Maven/JUnit | Generated Java main | Maven/JUnit |

## Recommended MVP Usage

For the research prototype, Mode 4 is the strongest end-to-end JUnit demo because it shows full JUnit integration.

The modes now form a clear trust ladder:

- Mode 1: developer controls MR transformation/assertion, LLM generates source-input data
- Mode 2: developer controls MR transformation/assertion, LLM generates source-input JUnit tests
- Mode 3: LLM writes source inputs, follow-up transformation, and assertion for JSON data
- Mode 4: LLM writes full JUnit metamorphic candidate tests
