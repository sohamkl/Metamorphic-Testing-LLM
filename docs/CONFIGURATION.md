# Output And MR Provider Configuration

> Examples in this document use the threeten-extra days-between MR from the final experiment
> (`final-experiment/final-experiment-sut-prompts/threeten-extra/Days/`). The live SUTs are
> the-algorithms-java, jsoup, ta4j, spatial4j and threeten-extra.

The public configuration no longer uses numbered modes. Developers choose the output artifact and who owns the MR implementation:

```text
JsonRequired: true|false
TestSuiteRequired: true|false
MRProvider: DEV|LLM
OutputRoot: examples/threeten-extra/generated/days-between
```

`MRProvider` means who writes the metamorphic relation implementation:

```text
DEV = developer provides Java MR helper methods
LLM = LLM generates the follow-up transformation and assertion code from MRInput and MROutput
```

At least one output must be requested. The backend supports JSON output, JUnit test-suite output, or both in one run.

`OutputRoot` controls where generated artifacts are written. For example:

```text
OutputRoot: examples/threeten-extra/generated/days-between
```

creates:

```text
examples/threeten-extra/generated/days-between/data-generator-code/
examples/threeten-extra/generated/days-between/json-data/
examples/threeten-extra/generated/days-between/junit-tests/
examples/threeten-extra/generated/days-between/junit-support/
examples/threeten-extra/generated/days-between/reports/
```

If `OutputRoot` is omitted and the SUT is inside `examples/<name>/src`, the backend defaults to
`examples/<name>/generated`.

## JSON + Developer MR

```text
JsonRequired: true
TestSuiteRequired: false
MRProvider: DEV
DeveloperMrFile: examples/threeten-extra/mr/DaysBetweenMetamorphicSpec.java
DeveloperFollowUpMethod: DaysBetweenMetamorphicSpec.generateFollowUp
DeveloperAssertMethod: DaysBetweenMetamorphicSpec.assertRelation
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
<OutputRoot>/json-data/<GeneratedClassName>.json
<OutputRoot>/json-data/<GeneratedClassNameWithoutData>Passing.json
<OutputRoot>/json-data/<GeneratedClassNameWithoutData>Failing.json
<OutputRoot>/reports/<GeneratedClassNameWithoutData>Report.html
```

This is the lowest LLM-responsibility option because the developer controls the MR transformation and assertion.

## JUnit + Developer MR

```text
JsonRequired: false
TestSuiteRequired: true
MRProvider: DEV
DeveloperMrFile: examples/threeten-extra/mr/DaysBetweenMetamorphicSpec.java
DeveloperFollowUpMethod: DaysBetweenMetamorphicSpec.generateFollowUp
DeveloperAssertMethod: DaysBetweenMetamorphicSpec.assertRelation
```

The LLM generates JUnit source-input tests. Each test calls the developer-owned follow-up and assertion methods.

Outputs:

```text
<OutputRoot>/junit-tests/<GeneratedClassNameWithoutTest>PassingTest.java
<OutputRoot>/junit-tests/<GeneratedClassNameWithoutTest>FailingTest.java
```

SUT/support/MR files needed for compilation are copied into `<OutputRoot>/junit-support`, not into
`<OutputRoot>/junit-tests`.

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
<OutputRoot>/json-data/<GeneratedClassName>.json
<OutputRoot>/json-data/<GeneratedClassNameWithoutData>Passing.json
<OutputRoot>/json-data/<GeneratedClassNameWithoutData>Failing.json
<OutputRoot>/reports/<GeneratedClassNameWithoutData>Report.html
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
<OutputRoot>/junit-tests/<GeneratedClassNameWithoutTest>PassingTest.java
<OutputRoot>/junit-tests/<GeneratedClassNameWithoutTest>FailingTest.java
```

This is the highest LLM-responsibility option and the fastest end-to-end JUnit demo. The final
experiment calls this configuration AUTO (mode `nl`).

## Split MR Ownership (i-AUTO / o-AUTO)

With `MRProvider: DEV`, the developer may supply only one half of the relation and leave the other to
the LLM. Name only the developer-owned method, and describe the LLM-owned half in prose:

```text
# i-AUTO: developer input transformation, LLM-written output relation
MRProvider: DEV
DeveloperMrFile: examples/threeten-extra/mr/DaysBetweenMetamorphicSpec.java
DeveloperFollowUpMethod: DaysBetweenMetamorphicSpec.generateFollowUp
MROutput: <the output relation, in prose>

# o-AUTO: LLM-written input transformation, developer output relation
MRProvider: DEV
DeveloperMrFile: examples/threeten-extra/mr/DaysBetweenMetamorphicSpec.java
DeveloperAssertMethod: DaysBetweenMetamorphicSpec.assertRelation
MRInput: <the input transformation, in prose>
```

`PromptConfigLoader` requires `DeveloperMrFile`, at least one of the two methods, and the prose for
whichever half is left to the LLM. `RANDOOP` needs both developer methods, so split ownership is
LLM/NEW_HYBRID only.

## JSON + JUnit Together

This combination generates both executed JSON data/report output and generated JUnit test-suite output:

```text
JsonRequired: true
TestSuiteRequired: true
```

Internally, the backend runs this as two generation passes:

```text
1. JSON/data/report generation
2. JUnit test-suite generation
```

If `GeneratedClassName` ends in `Data`, the JUnit class name is derived by replacing that suffix with `Test`. For example:

```text
GeneratedDaysBetweenMetamorphicData
GeneratedDaysBetweenMetamorphicTest
```
