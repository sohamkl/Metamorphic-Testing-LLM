# The Final Experiment

Everything needed to rerun or audit the experiment reported in the Part IV project reports is under
`final-experiment/`. How the framework itself works is in [FRAMEWORK.md](FRAMEWORK.md); setup is in the
[README](../README.md#setup).

Contents: [Design](#design) · [Subjects](#subjects) · [Environment](#environment) ·
[Rerunning](#rerunning) · [Results layout](#results-layout) · [Analysis](#analysis) ·
[Oracle stripping](#oracle-stripping-auto-runs)

## Design

- **Research questions.** RQ1 compares the input generators with developer-owned MRs. RQ2 compares who
  owns the MR, all with the hybrid generator.
- **Subjects and MRs.** 10 metamorphic relations across five subjects ([Subjects](#subjects)). Each
  relation is one `runs` entry in `final-experiment/experiment-manifest.yaml`, which also records its
  prompts, generated class names and PIT profile.
- **MR ownership modes.** `dev` (human: developer writes the transformation and the assertion in Java),
  `nl` (AUTO: the LLM implements both from the natural-language MR), `iauto` (developer transformation,
  LLM-written output relation) and `oauto` (LLM-written transformation, developer output relation). See
  [MR ownership](FRAMEWORK.md#mr-ownership).
- **Input generators.** `LLM`, `NEW_HYBRID` (called HYBRID in the reports) and `RANDOOP`. `RANDOOP` makes no
  API calls and runs only in `dev` mode.
- **Models.** `gpt-5.6-luna`, `gpt-5.6-terra` and `gpt-5.6-sol`, all with `reasoningEffort: medium`.
- **Generation settings.** `Count: 50`, `MaxRepairAttempts: 3`, input domain always inferred.
- **Repetitions.** 3 per configuration, 480 runs in total:
  - RQ1, `dev`: 7 configurations (LLM and NEW_HYBRID for each of the 3 models, plus RANDOOP) x 10 MRs x
    3 reps = 210 runs.
  - RQ2, `nl`, `iauto`, `oauto`: NEW_HYBRID x 3 models x 10 MRs x 3 reps = 90 runs each (270). RQ2's
    `dev` baseline is the RQ1 NEW_HYBRID runs.
- **Effectiveness metric.** PIT mutants killed in the class under test, compared only within an MR
  (mutant totals differ between classes).

## Subjects

Four of the five subjects (jsoup, ta4j, spatial4j, threeten-extra) come from the benchmark of the SBFT 2023
Java tool competition. Each folder is a copy of the upstream project, with the MT-specific files beside
the library code (`mr/` or `mt-testing/mr/`).

| Folder | Upstream | Version in this repo | Licence | MRs in the experiment |
|--------|----------|----------------------|---------|-----------------------|
| `examples/the-algorithms-java/` | https://github.com/TheAlgorithms/Java | `1.0-SNAPSHOT` | MIT | matrix-transpose |
| `examples/jsoup/` | https://github.com/jhy/jsoup | `1.23.2-SNAPSHOT` | MIT | node-filter-clone, cleaner-idempotence |
| `examples/ta4j/` | https://github.com/ta4j/ta4j | `0.23.1-SNAPSHOT` | MIT | slope-change, renko-scaling |
| `examples/spatial4j/` | https://github.com/locationtech/spatial4j | `0.9-SNAPSHOT` | Apache 2.0 | circle-relate, longitude-normalize, rectangle-contains |
| `examples/threeten-extra/` | https://github.com/ThreeTen/threeten-extra | `1.10.1-SNAPSHOT` (upstream commit `5ec7ef4`) | BSD 3-Clause | days-between, range-length |

MRs explored during development but not used in the experiment are in each subject's `exploratory/`
folder, with a README describing them.

## Environment

All 480 runs used Windows, JDK 25.0.4.1 and Maven 3.9.11, between 14 and 20 September 2026 (NZ time). On
Windows the runner does not switch JDKs per subject (`javaVersion` in the manifest is recorded but only
honoured where `/usr/libexec/java_home` exists), so every run used the inherited JDK 25; each run's
`resolvedJavaHome` is in its metadata.

The Randoop runs are deterministic. Rerunning the 10 Randoop configurations on a second machine (Windows 11
with the same JDK and Maven versions and Python 3.12.3) reproduced every archived kill count and
covered-line count exactly, so they double as a regression check for the framework.

## Rerunning

The runner executes one combination at a time: it writes the prompt, runs generation, runs the generated
passing tests, runs PIT, and archives everything. List the planned runs first:

```bash
python final-experiment/run-final-experiment.py --list --runs-per-combination 3
```

Commands matching the experiment:

```bash
# RQ1: dev mode, all three generators
python final-experiment/run-final-experiment.py --mode dev --runs-per-combination 3 --continue-on-error

# RQ2: nl / iauto / oauto, NEW_HYBRID only
python final-experiment/run-final-experiment.py --mode nl --mode iauto --mode oauto \
  --input-generator new-hybrid --runs-per-combination 3 --continue-on-error
```

Use `--run <id>`, `--model <id>` and `--start-run <n>` to restrict or resume, `--skip-pit` to skip mutation
testing, and `--dry-run` to print the Maven commands without running them. The runner temporarily sets
`OPENAI_MODEL` in `.env` per run (restoring the file afterwards) and passes `OPENAI_REASONING_EFFORT` from
the manifest.

**The runner writes into the archive.** It appends to `final-experiment-run-metadata/all-runs.jsonl` and
writes per-run files beside it. To rerun without touching the committed results, copy the manifest, point
its `metadataRoot` and `pitReportArchiveRoot` at a scratch directory, and pass `--manifest <copy>`. For
example, the offline Randoop regression check:

```bash
python final-experiment/run-final-experiment.py --manifest <copy> --mode dev \
  --input-generator randoop --runs-per-combination 1
```

LLM output is non-deterministic, so LLM reruns will not reproduce the archived numbers exactly; the
archived results are the data the reports use.

## Results layout

| Path | Contents |
|------|----------|
| `final-experiment/experiment-manifest.yaml` | Models, modes, input generators, Maven command templates and the 10 run definitions |
| `final-experiment/run-final-experiment.py` | Serial experiment runner |
| `final-experiment/final-experiment-sut-prompts/<sut>/<target>/` | The `dev`, `nl`, `iauto` and `oauto` prompt YAML for each MR (40 prompts) |
| `final-experiment/final-experiment-run-metadata/all-runs.jsonl` | One JSON line per attempted run: status, timings, token usage, kills, coverage. It also holds pilot and retried attempts, so it has more lines than the 480 reported runs |
| `final-experiment/final-experiment-run-metadata/<run-id>.*` | Per run: the exact prompt used, metadata JSON, generation, test and PIT logs, and the archived generated sources (`.generated/`, including the failing tests that record MR violations) |
| `final-experiment/final-experiment-pit-reports/<run-id>/` | PIT reports for every run |
| `final-experiment/final-experiment-stripped-oracles/` | The 22 oracle-stripped `nl` runs plus one unmodified control replay ([Oracle stripping](#oracle-stripping-auto-runs)) |
| `final-experiment/final-experiment-pit-reports-stripped/` | PIT reports for the stripped re-runs |
| `final-experiment/analysis/` | `analyse.py` (results tables), `OracleStripper.java`, `verify_oracle_stripping.py`, and the committed `results/` |

Run IDs follow `<sut>__<mr>__<mode>__<generator>__<model>__runNNN`. Randoop runs use `no-model` as the
model component.

**Treat the archive as read-only.** The run records store relative paths into these folders, so moving or
renaming anything in them breaks the links between records and files.

## Analysis

`final-experiment/analysis/analyse.py` reads only the archived data and prints every results table (run
summary, RQ1 kills, coverage and relative scores, test strength, RQ2 kills and ratios, cost and effort):

```bash
python final-experiment/analysis/analyse.py                                    # Markdown tables
python final-experiment/analysis/analyse.py --csv-dir final-experiment/analysis/results
```

The committed output is in `final-experiment/analysis/results/` (`tables.md` plus one CSV per table). The
selection rules match the reports:

- `all-runs.jsonl` also holds pilot and retried attempts; the last record per run ID is the run, which
  leaves exactly 480 runs.
- A run is successful when generation reported `PASSED` and PIT exited 0 (445 runs). Failed runs (32) are
  counted but never averaged.
- An `iauto`/`oauto` run whose passing suite calls the developer half the LLM was meant to write is a leak
  and is excluded (3 runs, all `oauto` node-filter-clone).
- Kills are PIT `KILLED` only. Relative scores divide, per MR, a configuration's mean by the best RQ1
  configuration's mean (RQ1) or by `dev` with the same models (RQ2), then average over MRs; "pooled" rows
  pool runs across models before dividing.
- Cost uses list prices per million tokens (input / output): Luna US$0.20 / $1.20, Terra $2 / $12,
  Sol $4 / $20.

## Oracle stripping (AUTO runs)

With `MRProvider: LLM` the model sometimes added conventional expected-value assertions (for example a
hard-coded expected rank) beside the metamorphic check. To measure only the relation, those assertions
were removed with `final-experiment/analysis/OracleStripper.java` and the affected suites were re-run
through the test and PIT steps:

- In a `@Test` method, a JUnit assertion is kept if it references a follow-up value or calls a method
  declared in the test class; otherwise it is removed.
- In a helper method, an assertion comparing against an `expectedSource*` value is removed.

22 of the 85 successful `nl` runs had something removed. `final-experiment-stripped-oracles/` holds, per
run, the stripped suite, the list of removed and kept statements (`.removed.txt`), and the test and PIT
logs; `stripped-runs.jsonl` holds the re-measured results, which `analyse.py` uses for those runs. The
extra `..._control` entry is matrix-transpose terra run001 replayed **unmodified** through the same path,
which reproduced its original 32 kills and validates the replay. Check the tool against the archive with:

```bash
python final-experiment/analysis/verify_oracle_stripping.py
```

It reproduces all 22 stripped suites and removed-statement lists byte for byte, and confirms that exactly
those 22 of the 85 passing `nl` suites have anything to strip.
