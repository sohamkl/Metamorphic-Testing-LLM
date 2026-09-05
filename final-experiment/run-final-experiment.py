#!/usr/bin/env python3
"""Serial runner for the final MT-testing experiment.

The script intentionally keeps the experiment boring and auditable:
one manifest entry, one prompt mode, one input generator, one model, one run number at a time.
"""

from __future__ import annotations

import argparse
import datetime as dt
import json
import os
import re
import shutil
import subprocess
import sys
import time
import xml.etree.ElementTree as ET
from pathlib import Path
from typing import Any


REPO_ROOT = Path(__file__).resolve().parents[1]
MANIFEST = REPO_ROOT / "final-experiment" / "experiment-manifest.yaml"
DEFAULT_PATH = "/bin:/usr/bin:/opt/homebrew/bin"


def main() -> int:
    args = parse_args()
    manifest = load_manifest(args.manifest)
    validate_manifest(manifest)

    defaults = manifest.get("defaults", {})
    models = filter_named(manifest["models"], "id", args.models)
    modes = args.modes or manifest.get("modes", ["dev", "nl"])
    input_generators = filter_named(manifest["inputGenerators"], "id", args.input_generators)
    runs = filter_named(manifest["runs"], "id", args.runs)
    runs_per_combination = args.runs_per_combination or int(defaults.get("runsPerCombination", 1))

    planned = [
        (run, mode, input_generator, model, run_no)
        for run in runs
        for mode in modes
        for input_generator in input_generators
        if mode in input_generator["modes"]
        for model in models_for_input_generator(input_generator, models)
        for run_no in range(1, runs_per_combination + 1)
    ]

    if args.list:
        for run, mode, input_generator, model, run_no in planned:
            print(run_id(run, mode, input_generator, model, run_no))
        return 0

    original_env = read_text(REPO_ROOT / defaults.get("envFile", ".env"))
    try:
        for run, mode, input_generator, model, run_no in planned:
            execute_one(args, manifest, run, mode, input_generator, model, run_no)
    finally:
        if not args.dry_run:
            write_text(REPO_ROOT / defaults.get("envFile", ".env"), original_env)

    return 0


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description="Run the final MT-testing experiment serially.")
    parser.add_argument("--manifest", type=Path, default=MANIFEST)
    parser.add_argument("--dry-run", action="store_true", help="Print commands without running them.")
    parser.add_argument("--list", action="store_true", help="List planned run IDs and exit.")
    parser.add_argument("--run", dest="runs", action="append", help="Restrict to a manifest run id.")
    parser.add_argument("--mode", dest="modes", action="append", choices=["dev", "nl"], help="Restrict to a mode.")
    parser.add_argument(
        "--input-generator",
        dest="input_generators",
        action="append",
        choices=["llm", "new-hybrid", "hybrid", "randoop"],
        help="Restrict to an input generator.",
    )
    parser.add_argument("--model", dest="models", action="append", help="Restrict to a model id.")
    parser.add_argument("--runs-per-combination", type=int, help="Override defaults.runsPerCombination.")
    parser.add_argument("--skip-pit", action="store_true", help="Skip PIT after generation/test.")
    parser.add_argument("--keep-generated", action="store_true", help="Do not delete generated output before a run.")
    parser.add_argument("--continue-on-error", action="store_true", help="Continue after a failed run.")
    return parser.parse_args()


def execute_one(args: argparse.Namespace, manifest: dict[str, Any], run: dict[str, Any],
                mode: str, input_generator: dict[str, Any], model: dict[str, str], run_no: int) -> None:
    defaults = manifest["defaults"]
    rid = run_id(run, mode, input_generator, model, run_no)
    metadata_root = REPO_ROOT / defaults["metadataRoot"]
    pit_archive_root = REPO_ROOT / defaults["pitReportArchiveRoot"]
    generated_root = REPO_ROOT / run["generatedOutputRoot"]
    source_prompt = run["prompts"][mode]
    run_prompt = metadata_root / f"{rid}.prompt.yaml"
    model_name = model.get("openaiModel")
    generation_profiles = ",".join(run["generationProfiles"])

    generation_log = metadata_root / f"{rid}.generation.log"
    test_log = metadata_root / f"{rid}.test.log"
    pit_log = metadata_root / f"{rid}.pit.log"
    run_json = metadata_root / f"{rid}.json"
    generated_archive = metadata_root / f"{rid}.generated"
    pit_archive = pit_archive_root / rid

    generation_cmd = [
        "mvn", f"-P{generation_profiles}", "-DskipTests", "compile", "exec:java",
        "-Dexec.mainClass=mtllm.OpenaiRunner",
        f"-Dexec.args={rel(run_prompt)}",
    ]
    test_cmd = [
        "mvn", f"-P{generation_profiles}", f"-Dtest={run['passingTestClass']}", "test",
    ]
    pit = run["pit"]
    pit_cmd = [
        "mvn", f"-P{generation_profiles},{pit.get('profile')}",
        "process-classes", "test-compile", "org.pitest:pitest-maven:mutationCoverage",
    ] if pit.get("status") == "ready" else []

    env = build_run_env(run["javaVersion"], model_name)

    print(f"\n== {rid} ==")
    print(f"source prompt: {source_prompt}")
    print(f"run prompt: {rel(run_prompt)}")
    print(f"input generator: {input_generator['configValue']}")
    print(f"model: {model_name or 'not used'}")
    print(f"java: {env.get('JAVA_HOME', 'current')}")

    if args.dry_run:
        print("$ " + sh_join(generation_cmd))
        print("$ " + sh_join(test_cmd))
        if pit_cmd and not args.skip_pit:
            print("$ " + sh_join(pit_cmd))
        return

    metadata_root.mkdir(parents=True, exist_ok=True)
    pit_archive_root.mkdir(parents=True, exist_ok=True)
    write_run_prompt(REPO_ROOT / source_prompt, run_prompt, input_generator["configValue"])
    if model_name:
        update_env_file(REPO_ROOT / defaults["envFile"], defaults["modelEnvKey"], model_name)
    if not args.keep_generated:
        reset_directory(generated_root)

    started = utc_now()
    t0 = time.monotonic()
    generation = run_command(generation_cmd, generation_log, env)
    metrics = read_json(generated_root / "metrics.json")

    test = {"returncode": None, "skipped": True}
    pit_result = {"returncode": None, "skipped": True}
    if generation["returncode"] == 0:
        test = run_command(test_cmd, test_log, env)
        if test["returncode"] == 0 and pit_cmd and not args.skip_pit:
            pit_result = run_command(pit_cmd, pit_log, env)
            archive_pit_report(REPO_ROOT / pit["reportDir"], pit_archive)

    if generated_root.exists():
        reset_directory(generated_archive)
        shutil.copytree(generated_root, generated_archive, dirs_exist_ok=True)

    summary = {
        "runId": rid,
        "sut": run["sut"],
        "target": run["target"],
        "mr": run["mr"],
        "mode": mode,
        "inputGenerator": input_generator["id"],
        "inputGeneratorConfigValue": input_generator["configValue"],
        "model": model["id"],
        "openaiModel": model_name,
        "runNumber": run_no,
        "requestedJavaVersion": run["javaVersion"],
        "resolvedJavaHome": env.get("JAVA_HOME"),
        "sourcePrompt": source_prompt,
        "runPrompt": rel(run_prompt),
        "generatedOutputRoot": run["generatedOutputRoot"],
        "generatedArchive": rel(generated_archive),
        "pitReportArchive": rel(pit_archive) if pit_archive.exists() else None,
        "startedAt": started,
        "finishedAt": utc_now(),
        "durationSeconds": round(time.monotonic() - t0, 3),
        "generation": generation,
        "test": test,
        "pit": pit_result | {"profile": pit.get("profile"), "sourceReportDir": pit.get("reportDir")},
        "frameworkMetrics": metrics,
        "pitSummary": read_pit_summary(pit_archive / "mutations.xml"),
    }
    write_json(run_json, summary)
    append_jsonl(metadata_root / "all-runs.jsonl", summary)

    if not args.keep_generated:
        reset_directory(generated_root)

    failed = generation["returncode"] != 0 or test.get("returncode") not in (0, None) or pit_result.get("returncode") not in (0, None)
    if failed and not args.continue_on_error:
        raise SystemExit(f"Run failed: {rid}. See {run_json}")


def load_manifest(path: Path) -> dict[str, Any]:
    try:
        import yaml  # type: ignore
        return yaml.safe_load(path.read_text())
    except ModuleNotFoundError:
        return load_manifest_without_pyyaml(path)


def load_manifest_without_pyyaml(path: Path) -> dict[str, Any]:
    lines = path.read_text().splitlines()
    data: dict[str, Any] = {"models": [], "modes": [], "inputGenerators": [], "defaults": {}, "runs": []}
    section = None
    current: dict[str, Any] | None = None
    subsection = None
    subsection_indent = 0
    block_key = None
    block_indent = 0
    block_lines: list[str] = []

    def finish_block() -> None:
        nonlocal block_key, block_lines
        if block_key and current is not None:
            target = current if section == "runs" else data["defaults"]
            target[block_key] = " ".join(line.strip() for line in block_lines).strip()
        block_key = None
        block_lines = []

    for raw in lines:
        if not raw.strip() or raw.lstrip().startswith("#"):
            continue
        indent = len(raw) - len(raw.lstrip(" "))
        line = raw.strip()
        if block_key and indent > block_indent:
            block_lines.append(line)
            continue
        finish_block()
        if subsection is not None and indent <= subsection_indent and not line.startswith("- "):
            subsection = None

        if not raw.startswith(" ") and line.endswith(":"):
            section = line[:-1]
            current = None
            subsection = None
            subsection_indent = 0
            continue

        if subsection == "generationProfiles" and line.startswith("- "):
            current[subsection].append(parse_scalar(line[2:]))
            continue

        if section in {"models", "inputGenerators", "runs"} and line.startswith("- "):
            current = {}
            data[section].append(current)
            subsection = None
            rest = line[2:]
            if rest:
                key, value = split_key_value(rest)
                current[key] = parse_scalar(value)
            continue

        if section == "modes" and line.startswith("- "):
            data["modes"].append(parse_scalar(line[2:]))
            continue

        if current is None and section == "defaults":
            key, value = split_key_value(line)
            if value in {">-", "|-"}:
                block_key = key
                block_indent = indent
            else:
                data["defaults"][key] = parse_scalar(value)
            continue

        if current is None:
            continue

        if line.endswith(":"):
            subsection = line[:-1]
            subsection_indent = indent
            current[subsection] = [] if subsection == "generationProfiles" else {}
            continue

        key, value = split_key_value(line)
        if value in {">-", "|-"}:
            block_key = key
            block_indent = indent
        elif subsection in {"prompts", "pit"}:
            current[subsection][key] = parse_scalar(value)
        else:
            current[key] = parse_scalar(value)

    finish_block()
    return data


def split_key_value(line: str) -> tuple[str, str]:
    if ":" not in line:
        return line, ""
    key, value = line.split(":", 1)
    return key.strip(), value.strip()


def parse_scalar(value: str) -> Any:
    if value == "null":
        return None
    if value == "true":
        return True
    if value == "false":
        return False
    if value.startswith("[") and value.endswith("]"):
        inner = value[1:-1].strip()
        return [] if not inner else [part.strip() for part in inner.split(",")]
    if re.fullmatch(r"\d+", value):
        return int(value)
    return value.strip('"')


def validate_manifest(manifest: dict[str, Any]) -> None:
    for run in manifest["runs"]:
        for mode, prompt in run["prompts"].items():
            require_path(REPO_ROOT / prompt, f"{run['id']} {mode} prompt")
        require_path(REPO_ROOT / run["generatedOutputRoot"], f"{run['id']} output root")


def filter_named(items: list[dict[str, Any]], key: str, selected: list[str] | None) -> list[dict[str, Any]]:
    if not selected:
        return items
    selected_set = set(selected)
    found = [item for item in items if item[key] in selected_set]
    missing = selected_set - {item[key] for item in found}
    if missing:
        raise SystemExit(f"Unknown {key}: {', '.join(sorted(missing))}")
    return found


def models_for_input_generator(
        input_generator: dict[str, Any], models: list[dict[str, str]]) -> list[dict[str, str]]:
    if input_generator.get("modelDependent", True):
        return models
    return [{"id": "no-model", "openaiModel": None}]


def run_id(run: dict[str, Any], mode: str, input_generator: dict[str, Any],
           model: dict[str, str], run_no: int) -> str:
    return f"{run['sut']}__{run['mr']}__{mode}__{input_generator['id']}__{model['id']}__run{run_no:03d}"


def build_run_env(java_version: int, model_name: str | None) -> dict[str, str]:
    env = os.environ.copy()
    env["PATH"] = DEFAULT_PATH + os.pathsep + env.get("PATH", "")
    if model_name:
        env["OPENAI_MODEL"] = model_name
    java_home = resolve_java_home(java_version, env)
    if java_home:
        env["JAVA_HOME"] = java_home
        env["PATH"] = str(Path(java_home) / "bin") + os.pathsep + env["PATH"]
    return env


def resolve_java_home(java_version: int, env: dict[str, str]) -> str:
    java_home = Path("/usr/libexec/java_home")
    if not java_home.exists():
        return ""
    requested = "1.8" if int(java_version) == 8 else str(java_version)
    result = subprocess.run(
        [str(java_home), "-v", requested],
        cwd=REPO_ROOT,
        env=env,
        text=True,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
    )
    return result.stdout.strip() if result.returncode == 0 else ""


def run_command(cmd: list[str], log_path: Path, env: dict[str, str]) -> dict[str, Any]:
    start = time.monotonic()
    with log_path.open("w") as log:
        log.write("$ " + sh_join(cmd) + "\n\n")
        proc = subprocess.run(cmd, cwd=REPO_ROOT, env=env, text=True, stdout=log, stderr=subprocess.STDOUT)
    return {
        "command": cmd,
        "returncode": proc.returncode,
        "durationSeconds": round(time.monotonic() - start, 3),
        "log": rel(log_path),
    }


def update_env_file(path: Path, key: str, value: str) -> None:
    lines = read_text(path).splitlines()
    updated = False
    out = []
    for line in lines:
        if line.startswith(f"{key}="):
            out.append(f"{key}={value}")
            updated = True
        else:
            out.append(line)
    if not updated:
        out.append(f"{key}={value}")
    write_text(path, "\n".join(out) + "\n")


def write_run_prompt(source: Path, destination: Path, input_generator: str) -> None:
    text = source.read_text()
    updated, count = re.subn(
        r"(?m)^InputGenerator:\s*.*$",
        f"InputGenerator: {input_generator}",
        text,
        count=1,
    )
    if count == 0:
        updated = text.rstrip() + f"\nInputGenerator: {input_generator}\n"
    destination.parent.mkdir(parents=True, exist_ok=True)
    destination.write_text(updated)


def archive_pit_report(source: Path, destination: Path) -> None:
    if not source.exists():
        return
    reset_directory(destination)
    shutil.copytree(source, destination, dirs_exist_ok=True)


def read_pit_summary(mutations_xml: Path) -> dict[str, Any]:
    if not mutations_xml.exists():
        return {}
    counts: dict[str, int] = {}
    detected = 0
    total = 0
    root = ET.parse(mutations_xml).getroot()
    for mutation in root.findall("mutation"):
        total += 1
        status = mutation.attrib.get("status", "UNKNOWN")
        counts[status] = counts.get(status, 0) + 1
        if mutation.attrib.get("detected") == "true":
            detected += 1
    return {"totalMutations": total, "detectedMutations": detected, "statuses": counts}


def reset_directory(path: Path) -> None:
    if path.exists():
        shutil.rmtree(path)
    path.mkdir(parents=True, exist_ok=True)


def read_json(path: Path) -> dict[str, Any]:
    if not path.exists():
        return {}
    return json.loads(path.read_text())


def write_json(path: Path, payload: dict[str, Any]) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(payload, indent=2, sort_keys=True) + "\n")


def append_jsonl(path: Path, payload: dict[str, Any]) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("a") as out:
        out.write(json.dumps(payload, sort_keys=True) + "\n")


def read_text(path: Path) -> str:
    return path.read_text() if path.exists() else ""


def write_text(path: Path, text: str) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(text)


def require_path(path: Path, label: str) -> None:
    if not path.exists():
        raise SystemExit(f"Missing {label}: {path}")


def rel(path: Path) -> str:
    try:
        return str(path.relative_to(REPO_ROOT))
    except ValueError:
        return str(path)


def sh_join(cmd: list[str]) -> str:
    return " ".join(shell_quote(part) for part in cmd)


def shell_quote(value: str) -> str:
    if re.fullmatch(r"[A-Za-z0-9_./:=,+@-]+", value):
        return value
    return "'" + value.replace("'", "'\"'\"'") + "'"


def utc_now() -> str:
    return dt.datetime.now(dt.UTC).isoformat(timespec="seconds")


if __name__ == "__main__":
    sys.exit(main())
