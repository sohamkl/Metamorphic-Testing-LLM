#!/usr/bin/env python3
"""Check that OracleStripper.java reproduces the committed stripped-oracle results.

1. Reproduction: for each of the 22 runs in final-experiment-stripped-oracles/, strip the archived original
   passing suite and compare with the committed <runId>__stripped.PassingTest.java and .removed.txt.
2. Selection: strip every passing nl suite (85 runs) and check that exactly those 22 have anything removed.

Needs Java 11+ and JavaParser on the local Maven repository (`mvn compile` downloads it).
Outputs go to a temporary directory; nothing under final-experiment/ is modified.

Usage:  python final-experiment/analysis/verify_oracle_stripping.py
"""

from __future__ import annotations

import glob
import json
import os
import re
import subprocess
import sys
import tempfile
from pathlib import Path

FE = Path(__file__).resolve().parents[1]
REPO = FE.parent
TOOL = Path(__file__).resolve().parent / "OracleStripper.java"
STRIPPED = FE / "final-experiment-stripped-oracles"
METADATA = FE / "final-experiment-run-metadata"


def javaparser_jar() -> str:
    version = re.search(r"<javaparser.version>([^<]+)<", (REPO / "pom.xml").read_text(encoding="utf-8")).group(1)
    jar = Path.home() / ".m2" / "repository" / "com" / "github" / "javaparser" / "javaparser-core" / version \
        / f"javaparser-core-{version}.jar"
    if not jar.exists():
        sys.exit(f"JavaParser {version} not found at {jar}; run `mvn compile` first.")
    return str(jar)


def strip(jar: str, source: str, out_java: Path, out_log: Path) -> list[str]:
    subprocess.run(["java", "-cp", jar, str(TOOL), source, str(out_java), str(out_log)],
                   check=True, capture_output=True)
    return [line for line in out_log.read_text(encoding="utf-8").splitlines() if line.strip()]


def original_suite(run_id: str) -> str | None:
    found = glob.glob(str(METADATA / f"{run_id}.generated" / "junit-tests" / "*PassingTest.java"))
    return found[0] if found else None


def main() -> int:
    jar = javaparser_jar()
    failures = 0
    with tempfile.TemporaryDirectory() as tmp:
        out = Path(tmp)

        committed = sorted(STRIPPED.glob("*__stripped.PassingTest.java"))
        java_ok = log_ok = 0
        for expected_java in committed:
            stripped_id = expected_java.name[:-len(".PassingTest.java")]
            run_id = stripped_id[:-len("__stripped")]
            got_log = strip(jar, original_suite(run_id), out / f"{stripped_id}.java", out / f"{stripped_id}.txt")
            expected_log = [line for line in (STRIPPED / f"{stripped_id}.removed.txt").read_text(encoding="utf-8")
                            .splitlines() if line.strip()]
            same_java = (out / f"{stripped_id}.java").read_text(encoding="utf-8").strip() \
                == expected_java.read_text(encoding="utf-8").strip()
            java_ok += same_java
            log_ok += got_log == expected_log
            if not same_java or got_log != expected_log:
                failures += 1
                print(f"MISMATCH {stripped_id}: java={'ok' if same_java else 'DIFF'} "
                      f"log={'ok' if got_log == expected_log else 'DIFF'}")
        print(f"Reproduction: stripped suite identical {java_ok}/{len(committed)}, "
              f"removed.txt identical {log_ok}/{len(committed)}")

        committed_ids = {p.name[:-len("__stripped.PassingTest.java")] for p in committed}
        selected, scanned = set(), 0
        for meta_path in sorted(METADATA.glob("*__nl__*.json")):
            meta = json.loads(meta_path.read_text(encoding="utf-8"))
            run_id = meta_path.stem
            source = original_suite(run_id)
            if (meta.get("test") or {}).get("returncode") != 0 or source is None:
                continue
            scanned += 1
            if any(line.startswith("REMOVED") for line in strip(jar, source, out / "sel.java", out / "sel.txt")):
                selected.add(run_id)
        print(f"Selection: {scanned} passing nl suites scanned, {len(selected)} with assertions removed, "
              f"{len(committed_ids)} committed")
        if selected != committed_ids:
            failures += 1
            print("  selected but not committed:", sorted(selected - committed_ids))
            print("  committed but not selected:", sorted(committed_ids - selected))

    print("OK" if failures == 0 else f"FAILED ({failures})")
    return 1 if failures else 0


if __name__ == "__main__":
    sys.exit(main())
