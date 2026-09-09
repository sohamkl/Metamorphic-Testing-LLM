# threeten-extra as an MT-testing subject

`examples/threeten-extra/` is a full upstream clone of
[ThreeTen/threeten-extra](https://github.com/ThreeTen/threeten-extra), pinned at commit `5ec7ef4`
(`1.10.1-SNAPSHOT`), with `.git` stripped. It carries the metamorphic-testing artifacts alongside
the library, the same layout as `spatial4j/` and `ta4j/`: `mr/` holds every relation written
against it, one `prompt-*.yaml` per relation per arm, and `generated/` holds each dataset's output.

This folder used to hold a partial vendored copy instead: 9 source files plus 4 hand-written
annotation stubs, registered directly as this project's own main sources. That is gone. The
history matters only because it explains two things that are no longer true, noted at the bottom.

## Build it first

threeten-extra is an external Maven project now, so it must be built before any threeten dataset
will compile or run:

```
mvn -f examples/threeten-extra/pom.xml -DskipTests package
```

That produces `examples/threeten-extra/target/threeten-extra-1.10.1-SNAPSHOT.jar`, which every
threeten profile references by `systemPath`. Builds clean on JDK 17 in about 80 seconds. The
version is fixed by the `threeten.version` property in the root `pom.xml`; bumping the clone means
bumping that property too, or the `systemPath` points at a jar that is not there.

## Profiles

One profile per arm, and they are used **instead of** each other, never together. Two arms of one
dataset cannot share the test source path: each carries its own copy of the developer spec in
`junit-support`, and the Randoop invocation wrapper is named from the target method signature
alone, so both arms emit the same two classes.

| profile | datasets | arm |
| --- | --- | --- |
| `threeten` | `days-between`, `range-length` | LLM |
| `threeten-randoop` | `range-length-randoop` | RANDOOP |
| `threeten-days-between-randoop` | `days-between-randoop` | RANDOOP |
| `threeten-nh` | `range-length-nh` | NEW_HYBRID |

`threeten` can hold both LLM datasets because `Days.between` and `LocalDateRange.lengthInDays` are
different target methods, so their wrappers and specs never collide.

The two RANDOOP profiles register `examples/threeten-extra/mr` directly. The RANDOOP path does not
copy the developer spec into `junit-support` the way the LLM and NEW_HYBRID paths do, and `mr/`
stopped being a main source when this became an external project.

## PIT

Each of the five `pitest-threeten-*` profiles copies the target class out of
`examples/threeten-extra/target/classes` into this project's `target/classes` at `process-classes`,
because PIT mutates bytecode it finds there and threeten's classes no longer compile into it.
Omitting that step produces a quiet "No mutations found" rather than an error. Pair each PIT
profile with its build profile, for example:

```
mvn clean test-compile org.pitest:pitest-maven:mutationCoverage -Pthreeten,pitest-threeten-range-length
```

**Mutation totals shifted when this folder was converted, but scores did not.** The library is
built at `release 8` by its own pom, where before it was compiled at `release 17` as part of this
project. Java 8 desugaring yields more mutable instructions, so totals rose (`Days` 220 to 244,
`LocalDateRange` 499 to 534). Every added mutation landed in `NO_COVERAGE`: covered and killed
counts are identical across all five datasets, so test strength (killed/covered) is unchanged to
the decimal. Whole-class mutation score (killed/all) shifts slightly because the denominator grew,
so those figures are not comparable with reports generated before the conversion.

## Provenance

threeten-extra is a benchmark subject from the SBFT'23 Java test-generation competition
(Jahangirova & Terragni, 2023), which also supplied the class-selection criteria used to pick these
SUTs.

## Two things that are no longer true

- **`AutomaticDiscovery` is now `true`** in all five configs. While the source was vendored with no
  pom, it had to be `false`, and that was load-bearing rather than decorative: the setting defaults
  to true, and its project search walks up from the SUT file until it finds any `pom.xml`, which
  would have been this framework's own. There is a real pom here now, so discovery resolves the
  library's true classpath.
- **The four annotation stubs are gone.** `@FromString` and `@ToString` (joda-convert) and
  `@Nullable` and `@NullMarked` (jspecify) were re-declared here as empty markers, because with no
  pom there was nothing to resolve them from. Discovery now pulls `joda-convert-2.2.4` and
  `jspecify-1.0.0` from Maven Central. Nothing in this folder is non-upstream code any more.

## No adapters

Neither dataset wraps its target method. The framework calls the library method directly:
`InvocationWrapperGenerator` synthesizes the typed input boundary Randoop needs, and under
`InputGenerator: LLM` the model constructs the call itself from the target signature. The
developer-owned relations therefore transform the receiver and the declared arguments and return
`Object[]`, rather than transforming a hand-written input holder.
