# PIT Mutation Testing

This project wires PIT through the Maven `pitest` profile. Normal `mvn test` runs are unchanged.

Run mutation testing with:

```sh
mvn -Ppitest test-compile org.pitest:pitest-maven:mutationCoverage
```

The default `pitest` profile mutates:

- `org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector`

It runs only generated `*PassingTest` classes and excludes `*FailingTest` classes. The framework keeps failing tests as bug-revealing artifacts, but PIT requires the selected test suite to be green before mutation analysis starts.

Reports are written to:

```text
target/pit-reports/index.html
target/pit-reports/mutations.xml
```

The mutation threshold is currently `0` so PIT reports are generated without failing the build while generated metamorphic tests are still being evaluated. Raise `mutationThreshold` in the `pitest` profile when you want CI to enforce a minimum score.
