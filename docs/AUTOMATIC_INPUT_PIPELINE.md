# Automatic Input-Domain Pipeline

When an LLM-backed run omits `InputDomain`, the framework now performs these steps:

1. Resolve the project, exact target method, SUT source, classpath, and bounded construction graph.
2. Ask the LLM for structured YAML containing grounded constraints, diversity dimensions, and measurable scenarios.
3. Parse that YAML with SnakeYAML and validate it with the same typed model used for developer-authored `InputDomain` values.
4. Repair malformed inferred YAML once, then fail clearly instead of silently continuing with an unusable domain.
5. Persist the validated result at `<OutputRoot>/input-domain/inferred-input-domain.yaml`.
6. Allocate deterministic scenario slots within the configured `Count` upper limit and include them in the generation prompt.
7. For HYBRID, pass the current validated inferred domain to the Randoop subprocess. A stale artifact is ignored.
8. Parse generated JUnit with JavaParser before compilation. Reject malformed Java, empty required suites, excess tests, duplicate test bodies, missing scenario IDs, and test methods with no reachable assertion.
9. If missing scenario coverage is the only defect, request only the absent `@Test` methods and merge them into the original class with JavaParser. Existing tests are retained and the combined suite is revalidated.
10. Use whole-class repair for defects that cannot be fixed additively, such as broken assertions, duplicate bodies, compilation errors, or runtime errors.
11. Compile and execute candidates and split actual passing and failing tests.

An explicit scalar or structured `InputDomain` is never replaced. Raw `RANDOOP` remains offline and does not invoke domain inference.

## Current Boundary

The framework does not yet have a universal construction-plan intermediate representation capable of rendering every arbitrary Java object graph. In LLM and NEW_HYBRID modes, the LLM still writes concrete fixture-construction Java using the discovered constructors, factories, implementations, and seed traces. The backend parses, compiles, executes, and quality-checks that code.

This boundary matters because API reachability and semantic validity are different problems. Reflection can discover that a constructor exists, but it cannot reliably infer undocumented rules such as valid timestamp ordering, financial invariants, or which callback behavior activates a meaningful path. The persisted inferred domain and scenario gates improve this without claiming that those business rules are fully automatic.
