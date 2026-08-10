# Vendored library sources for the real-world SUTs

The five real-world example datasets (`days-between`, `date-range-overlap`,
`longitude-normalize`, `rectangle-relate`, `circle-relate`) test methods from two open-source
libraries rather than purpose-written code. Those libraries are vendored here as source.

## Why vendored as source rather than added as Maven dependencies

The pipeline compiles a run's SUT with

```
javac -encoding UTF-8 -d <classes> <SUTClassFile> <SUTSupportFiles...> <DeveloperMrFile> <generated>
```

(`mtllm/runner/DataGeneratorRunner.java`, `compile`). There is no `-cp` flag on that command
and no configuration key for an extra classpath, so a library JAR cannot be put in front of the
SUT compile. Every dependency therefore has to be a source file listed in `SUTSupportFiles`.

## Provenance

| Directory | Project | Upstream |
| --- | --- | --- |
| `threeten-extra/` | threeten-extra | https://github.com/ThreeTen/threeten-extra |
| `spatial4j/` | Spatial4j | https://github.com/locationtech/spatial4j |
| `stubs/` | *not upstream* — see below | — |

Both projects are benchmark subjects from the SBFT'23 Java test-generation competition
(Jahangirova & Terragni, 2023), which also supplied the class-selection criteria used to pick
these SUTs.

Only the minimal transitive closure needed to compile each target is vendored, not the whole
project: 9 files from threeten-extra and 38 from Spatial4j. The three Spatial4j datasets share
one identical closure, because `SpatialContext` pulls in the same set for all of them.

## Modifications to upstream source

Sources are copied verbatim with two exceptions, both recorded here because they are
deviations from the released libraries.

1. **`spatial4j/.../context/SpatialContextFactory.java`** — two lines in `checkDefaultFormats()`
   that register the GeoJSON reader and writer are commented out. `GeoJSONReader` requires the
   `org.noggit` JSON parser, which cannot be placed on the SUT compile classpath (see above).
   This only affects optional shape *serialization format* registration; no geometry,
   distance, or `relate` logic is touched, and none of the five SUTs read or write GeoJSON.

2. **`stubs/`** — `@FromString`, `@ToString` (joda-convert) and `@Nullable`, `@NullMarked`
   (jspecify) are re-declared here as empty marker annotations. threeten-extra's sources
   reference them, but they carry no runtime behaviour and both libraries are compile-scope
   metadata only. These four files are stubs written for this repository, **not** upstream code.

## Refreshing

The vendored trees are snapshots. To update, re-clone the two upstream repositories, recompute
the compile closure for each target class, and re-copy — then re-run the compile check for all
five datasets.
