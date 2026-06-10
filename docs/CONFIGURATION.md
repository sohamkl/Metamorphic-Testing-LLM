# Output And MR Provider Configuration

The public configuration no longer uses numbered modes. Developers choose the output artifact and who owns the MR implementation:

```text
JsonRequired: true|false
TestSuiteRequired: true|false
MRProvider: DEV|LLM
```

`MRProvider` means who writes the metamorphic relation implementation:

```text
DEV = developer provides Java MR helper methods
LLM = LLM generates the follow-up transformation and assertion code from MRInput and MROutput
```

At least one output must be requested. For now, the backend supports either JSON output or JUnit test-suite output in one run. Supporting both in the same run is planned next.

## JSON + Developer MR

```text
JsonRequired: true
TestSuiteRequired: false
MRProvider: DEV
DeveloperMrFile: examples/pricing/mr/PricingMetamorphicSpec.java
DeveloperFollowUpMethod: PricingMetamorphicSpec.generateFollowUp
DeveloperAssertMethod: PricingMetamorphicSpec.assertRelation
```

The LLM generates candidate source inputs. The generated Java data program calls developer-owned MR methods and the real SUT to compute:

```text
source
followUp
sourceOutput
followUpOutput
passed
```

Outputs:

```text
generated/json-data/<GeneratedClassName>.json
generated/json-data/<GeneratedClassNameWithoutData>Passing.json
generated/json-data/<GeneratedClassNameWithoutData>Failing.json
```

This is the lowest LLM-responsibility option because the developer controls the MR transformation and assertion.

## JUnit + Developer MR

```text
JsonRequired: false
TestSuiteRequired: true
MRProvider: DEV
DeveloperMrFile: examples/pricing/mr/PricingMetamorphicSpec.java
DeveloperFollowUpMethod: PricingMetamorphicSpec.generateFollowUp
DeveloperAssertMethod: PricingMetamorphicSpec.assertRelation
```

The LLM generates JUnit source-input tests. Each test calls the developer-owned follow-up and assertion methods.

Outputs:

```text
generated/junit-tests/<GeneratedClassNameWithoutTest>PassingTest.java
generated/junit-tests/<GeneratedClassNameWithoutTest>FailingTest.java
```

This is useful when developers want runnable tests but still want to own MR correctness.

## JSON + LLM MR

```text
JsonRequired: true
TestSuiteRequired: false
MRProvider: LLM
```

The LLM generates source inputs, follow-up transformation code, and assertion code from `MRInput` and `MROutput`. The generated Java data program runs the real SUT and classifies each case.

Outputs:

```text
generated/json-data/<GeneratedClassName>.json
generated/json-data/<GeneratedClassNameWithoutData>Passing.json
generated/json-data/<GeneratedClassNameWithoutData>Failing.json
```

This is useful for inspecting generated data without creating JUnit files, but the generated MR code still needs review.

## JUnit + LLM MR

```text
JsonRequired: false
TestSuiteRequired: true
MRProvider: LLM
```

The LLM generates the full JUnit candidate test class, including source inputs, follow-up transformation, and assertion logic from `MRInput` and `MROutput`. The backend runs JUnit and splits actual passing/failing test methods.

Outputs:

```text
generated/junit-tests/<GeneratedClassNameWithoutTest>PassingTest.java
generated/junit-tests/<GeneratedClassNameWithoutTest>FailingTest.java
```

This is the highest LLM-responsibility option and the fastest end-to-end JUnit demo.

## Current Limitation

This combination is intentionally rejected for now:

```text
JsonRequired: true
TestSuiteRequired: true
```

The next implementation step is to support generating both JSON data and JUnit tests in one run.
