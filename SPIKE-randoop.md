# Spike: Randoop for source-input generation

Branch: `nathan/randoop-input-gen` (off `Soham/redesign-prototype`)
Date: 2026-06-08
Randoop: 4.3.4, Java 17, Windows

## Question
Can Randoop construct diverse, valid object inputs for our object SUT
(`OrderUtil.calculateTotal(Order)`), where the LLM is weak? This requires building a
three-level graph through validation guards: `LineItem` -> `List<LineItem>` -> `Order`.

## Command
```
javac -d build/classes src/main/java/{Order,LineItem,OrderUtil}.java
java -cp "randoop-all-4.3.4.jar;build/classes" randoop.main.Main gentests \
    --testclass=OrderUtil --testclass=Order --testclass=LineItem \
    --time-limit=20 --junit-output-dir=randoop-out2
```

## Result: GO
- 385 regression sequences generated in 20s.
- 493 `new LineItem(...)`, 399 `new Order(...)`, 214 `calculateTotal(...)` calls.
- Randoop successfully builds the full object graph and calls the SUT. The thing the
  LLM struggles with (constructing varied valid objects) Randoop does automatically.

## What worked
- Numeric diversity exceeds what an LLM typically types by hand: negatives via casts
  `(int)(short)-1`, char-as-int `(int)'a'` = 97, `0`, `1`, `100`, byte/short/long/float
  casts. It probes edge values a human/LLM wouldn't enumerate.

## Findings / limitations (these are the research content)
1. **String diversity is poor.** Randoop's seed string pool is tiny - names are only
   `"hi!"` or `""`. `""` fails `LineItem`'s blank-name guard. Fixable with
   `--literals-file` / `--string-maxlen` or a seed-literal file. This is a known Randoop
   weakness and a concrete axis for the LLM-vs-Randoop comparison (LLM is *better* at
   semantically meaningful strings).
2. **Generates many invalid inputs.** Lots of constructions throw (negative qty/price,
   blank name). For MT we need only *valid* source inputs, so a filtering/harvest step
   is required - we can't use Randoop's raw output directly.
3. **Default-package gotcha (fixed).** First run emitted tests into package
   `randoop.spike` while SUTs are in the default package -> all 1057 sequences
   uncompilable (a named-package class cannot reference a default-package class).
   Dropping `--junit-package-name` fixed it. (Lesson: SUT package and test package must
   be compatible.)

## Option (b) implemented and proven: in-process harvest
`spike/RandoopHarvest.java` drives Randoop's generator API directly (no CLI, no parsing
of emitted test files) and pulls live objects out of memory:
  - `OperationModel.createModel(...)` -> operations + class types from the SUT classes
  - `new ComponentManager(SeedSequences.defaultSeeds())`
  - `new ForwardGenerator(ops, observers, Limits(15s,...), comp, classTypes)`
  - `gen.setExecutionVisitor(new DummyVisitor()); setTestCheckGenerator(new DummyCheckGenerator()); setTestPredicate(es -> true)`
  - `gen.createAndClassifySequences(); gen.getRegressionSequences()`
  - for each `ExecutableSequence`: `getAllValues()` -> `ReferenceValue.getObjectValue()`,
    keep those whose runtime type is `Order`.

Result: **2492 distinct live `Order` objects harvested in 15s.** API path works end-to-end.
(Gotcha fixed: `getItems()` returns `Collections.unmodifiableList`, whose concrete class
is non-public under Java modules -> reflective `.size()` throws IllegalAccessException;
cast to `List` instead.)

## KEY FINDING: raw Randoop diversity is poor for MT
Harvested Order size histogram: `{0 items: 2356, 2 items: 136}`.
~94% are EMPTY orders (total 0.0); the rest all have exactly 2 items; none have 1/3/5+.
Randoop builds the graph correctly but, unguided, produces mostly trivial structures and
(from the CLI run) only the strings "hi!"/"". So Randoop alone is NOT enough for diverse
MT source inputs.

## This motivates the LLM+Randoop hybrid (next step)
Randoop accepts seeds: `SeedSequences.objectsToSeeds(List<Object>)` feeds pre-built objects
into the ComponentManager, and `--literals-file` injects literal values. Plan:
  - LLM supplies meaningful SEED values (domain strings like "Laptop"; representative
    quantities/prices; a few fully-built non-trivial Orders).
  - Randoop expands/mutates combinatorially around those seeds.
Each covers the other's weakness: LLM = meaningful but narrow; Randoop = diverse but
trivial/meaningless. Seeds + expansion = meaningful AND diverse.

## Decision taken
Going with (b) - in-process harvest. Proven, clean (no parsing generated code), and fits
"run the functions in memory" from the week-12 notes.
