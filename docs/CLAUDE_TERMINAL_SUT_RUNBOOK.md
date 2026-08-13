# Claude Terminal Runbook: Adding and Testing a Java SUT

Use this document as context when asking Claude Terminal to add a new Java system under test (SUT)
to the MT-Testing framework. Run all framework commands from the local repository root, referred to
below through the `MT_TESTING_ROOT` shell variable:

```bash
export MT_TESTING_ROOT=/path/to/MT-testing
```

Replace uppercase template tokens such as `DATASET`, `DATASET_PROFILE`, `SUT_SLUG`,
`REQUIRED_JAVA_VERSION`, and `FRAMEWORK_JAVA_VERSION` before executing a command or saving XML/YAML.

## Objective

For a new Maven-based Java project under `examples/<dataset>`, Claude should:

1. Build the original project without modifying its business logic.
2. Create or update its `prompt.yaml` using the original SUT class wherever possible.
3. Add a small Maven profile that exposes the SUT dependency and generated tests to the framework.
4. Run generation, then run the generated passing suite independently.
5. Add a separate PIT profile and run mutation testing only after the baseline suite is green.

Do not create adapters, factories, or long `SUTSupportFiles` lists by default. The current backend can
discover Maven outputs, dependency JARs, target signatures, constructors, factories, implementations,
builders, and bounded object-construction graphs automatically. Add manual support only after the
automatic path fails and the reason is documented.

## Current Backend Behaviour

The framework now performs the following steps:

1. `PromptConfigLoader` reads YAML into Java configuration objects.
2. `ProjectDiscovery` finds the nearest `pom.xml`, `target/classes`, `target/test-classes`, and Maven
   dependency classpath when `AutomaticDiscovery` is enabled and `SUTClasspath` is omitted.
3. `TargetMethodResolver` resolves the exact overload from `TargetFunction`.
4. API inspection reports parameter types, return types, constructors, and static factories.
5. ClassGraph, reflection, Instancio, and JavaParser build a bounded construction graph for complex
   inputs. The graph is capped to avoid exploring an entire dependency ecosystem.
6. Randoop-backed modes use discovered construction paths. Instance, zero-argument, and multi-argument
   methods receive a framework-generated typed invocation wrapper. Instance wrappers include the
   receiver and arguments in one source input.
7. Generated JUnit is compiled and executed. Actual passing and failing test methods are split into
   separate files.

Important limitations:

- The target project must be built first so its `target/classes` or JAR exists.
- Receiver-aware wrappers let Randoop construct complete instance invocations. For `MRProvider: DEV`,
  the follow-up helper must transform the receiver and arguments together using the contract below.
- Automatic discovery cannot infer every undocumented business invariant. Prefer a concise scalar
  `InputDomain`; structured scenario requirements and construction hints are optional enhancements,
  not mandatory developer work.
- Raw `RANDOOP` requires `MRProvider: DEV`. `LLM`, `HYBRID`, and `NEW_HYBRID` can use an LLM-owned
  MR; `HYBRID + LLM` uses LLM-proposed seeds followed by Randoop harvesting before LLM test creation.

## Step 1: Inspect and Build the SUT Project

Confirm its Java requirement before choosing `JAVA_HOME`:

```bash
grep -nE 'maven.compiler.release|maven.compiler.source|maven.compiler.target' \
  examples/DATASET/pom.xml
```

Build the original project:

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v REQUIRED_JAVA_VERSION)
export PATH="$JAVA_HOME/bin:$PATH"

mvn -f examples/DATASET/pom.xml -DskipTests package
```

Verify that at least one of these exists:

```bash
find examples/DATASET/target -maxdepth 2 \
  \( -path '*/classes' -o -name '*.jar' \) -print
```

Do not diagnose a failure as a Java-version problem unless the log contains evidence such as
`UnsupportedClassVersionError`, `invalid target release`, or a Maven Enforcer Java-version failure.
Warnings about `sun.misc.Unsafe` are not Java-version build failures.

## Step 2: Create a Minimal Prompt

Prefer the original public SUT source file. Either `SUTClassFile`, or `ProjectRoot` plus a fully
qualified `SUTClass`, can identify the SUT.

```yaml
ProjectRoot: examples/DATASET
AutomaticDiscovery: true
SUTClassFile: examples/DATASET/src/main/java/com/example/TargetClass.java
TargetFunction: public ReturnType targetMethod(InputType input)

SUTDescription: >-
  Concisely explain what the target computes and the meaning of its input and output.

MRInput: describe exactly how the source input becomes the follow-up input
MROutput: describe the required relationship between source and follow-up outputs
MR: one-sentence summary of the metamorphic relation

InputDomain: >-
  Give concise validity constraints, boundaries, representative behaviours, prohibited values,
  and a request for distinct cases. Treat Count as an upper limit.

Count: 20
InputGenerator: LLM
MavenProfiles:
  - DATASET_PROFILE
JsonRequired: false
TestSuiteRequired: true
MRProvider: LLM
GeneratedClassName: GeneratedTargetClassMetamorphicTest
OutputRoot: examples/DATASET/generated/SUT_SLUG
MaxRepairAttempts: 3
```

Usually omit these fields initially:

```yaml
SUTSupportFiles: []
SUTClasspath: []
RandoopTargetClasses: []
```

Only add them when logs show discovery cannot find a genuinely required project-owned helper or
construction path. Never list dozens of third-party source files when a compiled Maven JAR or
`target/classes` directory already provides them.

For a developer-owned MR, additionally configure:

```yaml
MRProvider: DEV
DeveloperMrFile: examples/DATASET/mr/TargetMetamorphicSpec.java
DeveloperFollowUpMethod: TargetMetamorphicSpec.generateFollowUp
DeveloperAssertMethod: TargetMetamorphicSpec.assertRelation
```

For a wrapped target, the DEV follow-up method accepts the original invocation components and returns
an `Object[]` in the same order. For an instance method, that order is receiver first and then method
arguments. For a static multi-argument method, it contains only the arguments. The assertion method
receives source and follow-up outputs.

## Step 3: Add the SUT Maven Profile

The framework discovers the SUT for generation, but root Maven still needs two things when it runs the
generated tests:

- the SUT artifact on the test compile/runtime classpath;
- the generated JUnit directory registered as a test source.

Add a dataset profile under `<profiles>` in the root `pom.xml`.

### Preferred dependency form

First install the SUT artifact locally:

```bash
mvn -f examples/DATASET/pom.xml -DskipTests install
```

Then use its Maven coordinates:

```xml
<profile>
    <id>DATASET_PROFILE</id>
    <dependencies>
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>example-artifact</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>build-helper-maven-plugin</artifactId>
                <version>3.6.0</version>
                <executions>
                    <execution>
                        <id>add-DATASET_PROFILE-generated-tests</id>
                        <phase>generate-test-sources</phase>
                        <goals>
                            <goal>add-test-source</goal>
                        </goals>
                        <configuration>
                            <sources>
                                <source>examples/DATASET/generated/SUT_SLUG/junit-tests</source>
                                <source>examples/DATASET/generated/SUT_SLUG/junit-support</source>
                            </sources>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</profile>
```

### Local-JAR fallback

If the cloned project cannot be installed, a system-scoped dependency can reference its built JAR:

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>example-artifact</artifactId>
    <version>1.0-SNAPSHOT</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/examples/DATASET/target/example-artifact-1.0-SNAPSHOT.jar</systemPath>
</dependency>
```

This is a practical local-research fallback, but Maven warns that `systemPath` dependencies are not
portable to downstream projects. Prefer a normal dependency whenever possible.

If the SUT requires a newer Java release than the framework default, scope it to this profile:

```xml
<properties>
    <maven.compiler.release>25</maven.compiler.release>
</properties>
```

Do not raise the global compiler release unless every example supports it.

## Step 4: Run Generation

Run from the framework root:

```bash
cd "$MT_TESTING_ROOT"

export JAVA_HOME=$(/usr/libexec/java_home -v FRAMEWORK_JAVA_VERSION)
export PATH="$JAVA_HOME/bin:$PATH"

mvn -Dexec.mainClass=mtllm.OpenaiRunner \
    -Dexec.args="examples/DATASET/prompt.yaml" \
    exec:java
```

`-Dexec.args` is essential. Without it, the runner reads the framework root `prompt.yaml`.

LLM-backed modes require the configured API credentials in `.env` or the shell. Raw `RANDOOP` does
not require an API key.

## Step 5: Run the Generated Suite Directly

Do not trust the generator's final summary alone. Run the generated class through the configured
dataset profile:

```bash
mvn -PDATASET_PROFILE -Dtest=GeneratedTargetClassMetamorphicTest test
```

For the split output, run the passing suite:

```bash
mvn -PDATASET_PROFILE -Dtest=GeneratedTargetClassMetamorphicPassingTest test
```

Expected result:

```text
Tests run: N, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

If Surefire says `No tests matching pattern ... were executed`, this normally means the generated
directory is missing from the active profile's `build-helper:add-test-source` configuration, or
`MavenProfiles` is missing from `prompt.yaml`. It is not normally a Java-version failure.

## Step 6: Add a PIT Profile

PIT mutates classes in the framework's `target/classes`. A SUT that only exists inside a dependency
JAR therefore needs its selected compiled class files copied into that directory before PIT starts.
Create one PIT profile per SUT so mutation targets and reports do not overwrite one another.

```xml
<profile>
    <id>pitest-DATASET_PROFILE-SUT_SLUG</id>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <version>3.3.1</version>
                <executions>
                    <execution>
                        <id>copy-DATASET_PROFILE-sut-for-pitest</id>
                        <phase>process-classes</phase>
                        <goals>
                            <goal>copy-resources</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>${project.build.outputDirectory}</outputDirectory>
                            <resources>
                                <resource>
                                    <directory>${project.basedir}/examples/DATASET/target/classes</directory>
                                    <filtering>false</filtering>
                                    <includes>
                                        <include>com/example/TargetClass*.class</include>
                                    </includes>
                                </resource>
                            </resources>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.pitest</groupId>
                <artifactId>pitest-maven</artifactId>
                <version>${pitest.version}</version>
                <dependencies>
                    <dependency>
                        <groupId>org.pitest</groupId>
                        <artifactId>pitest-junit5-plugin</artifactId>
                        <version>${pitest.junit5.plugin.version}</version>
                    </dependency>
                </dependencies>
                <configuration>
                    <targetClasses>
                        <param>com.example.TargetClass*</param>
                    </targetClasses>
                    <targetTests>
                        <param>GeneratedTargetClassMetamorphicPassingTest</param>
                    </targetTests>
                    <excludedTestClasses>
                        <param>*FailingTest</param>
                    </excludedTestClasses>
                    <additionalSources>
                        <param>${project.basedir}/examples/DATASET/src/main/java</param>
                    </additionalSources>
                    <outputFormats>
                        <param>HTML</param>
                        <param>XML</param>
                    </outputFormats>
                    <reportsDirectory>${project.build.directory}/pit-reports/DATASET_PROFILE-SUT_SLUG</reportsDirectory>
                    <timestampedReports>false</timestampedReports>
                    <mutationThreshold>0</mutationThreshold>
                </configuration>
            </plugin>
        </plugins>
    </build>
</profile>
```

Use `/` in copied class paths and `.` in PIT class names:

```text
Copy include:  com/example/TargetClass*.class
PIT target:    com.example.TargetClass*
```

Use the generated **passing** suite for PIT. Failing metamorphic tests may represent genuine SUT bugs,
but PIT requires the baseline selected tests to pass before mutation analysis.

## Step 7: Run PIT

First confirm the passing suite:

```bash
mvn -PDATASET_PROFILE -Dtest=GeneratedTargetClassMetamorphicPassingTest test
```

Then run PIT with both the dataset and PIT profiles:

```bash
mvn clean \
    -PDATASET_PROFILE,pitest-DATASET_PROFILE-SUT_SLUG \
    test-compile \
    org.pitest:pitest-maven:mutationCoverage
```

Open the configured report:

```bash
open target/pit-reports/DATASET_PROFILE-SUT_SLUG/index.html
```

Interpretation:

- `KILLED`: at least one generated test detected the mutation.
- `SURVIVED`: covering tests ran but none failed; strengthen inputs, assertions, or MRs.
- `NO_COVERAGE`: no selected test executed the mutated code.
- `TIMED_OUT` or `MEMORY_ERROR`: infrastructure/execution problem, not a valid mutation score signal.
- `No mutations found`: the target class was not copied into `target/classes`, the class pattern is
  wrong, or PIT is looking at a dependency JAR instead of the framework output directory.

Increasing `Count` alone does not guarantee a higher mutation score. Improvement usually comes from
source inputs that reach new branches, non-vacuous outputs, stronger metamorphic assertions, and
additional valid MRs that constrain different behaviour.

## Claude Terminal Completion Checklist

Before claiming the integration works, Claude must report evidence for every checked item:

- [ ] Original SUT project builds with its required Java version.
- [ ] Prompt targets an original public method and exact overload.
- [ ] `AutomaticDiscovery: true` is used and unnecessary support files are absent.
- [ ] Dataset Maven profile supplies the SUT dependency and generated test directories.
- [ ] The same profile name appears under `MavenProfiles` in the prompt.
- [ ] Generation command explicitly passes the prompt path.
- [ ] Generated candidate or passing test runs successfully from Maven.
- [ ] PIT profile copies the intended compiled SUT class into framework `target/classes`.
- [ ] PIT targets only the intended class and the generated passing suite.
- [ ] PIT report path and mutation totals are reported.
- [ ] Warnings are distinguished from actual compilation, test, Java-version, and PIT failures.
