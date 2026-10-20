#!/usr/bin/env python3
"""Recompute the final-experiment tables from the archived run records.

Reads only committed data:
  final-experiment-run-metadata/all-runs.jsonl          one line per attempted run
  final-experiment-run-metadata/<runId>.generated/       archived generated sources (leak check)
  final-experiment-stripped-oracles/stripped-runs.jsonl  re-measured nl runs (oracle stripping)

Selection rules (the same ones the reports use):
  * all-runs.jsonl also holds pilot and retried attempts; the last record per runId is the run.
    That leaves exactly 480 runs.
  * A run is successful when generation reported PASSED and PIT exited 0. Failed runs are counted
    but never averaged.
  * iauto/oauto runs whose passing suite calls the developer half the LLM was meant to write are
    leaks: excluded from every number.
  * nl runs listed as "stripped" in stripped-runs.jsonl use the stripped PIT results.
  * Kills are PIT KILLED only (TIMED_OUT / RUN_ERROR are not counted).

Usage:  python final-experiment/analysis/analyse.py [--csv-dir DIR]
Prints every table as Markdown; with --csv-dir also writes one CSV per table.
"""

from __future__ import annotations

import argparse
import csv
import glob
import json
import re
from decimal import ROUND_HALF_UP, Decimal
from pathlib import Path
from statistics import mean

FE = Path(__file__).resolve().parents[1]
MODELS = ["luna", "terra", "sol"]
# US$ per million input / output tokens at list price when the experiment ran.
PRICES = {"luna": (0.20, 1.20), "terra": (2.0, 12.0), "sol": (4.0, 20.0)}
MR_ORDER = ["days-between", "range-length", "circle-relate", "longitude-normalize", "rectangle-contains",
            "renko-scaling", "slope-change", "node-filter-clone", "cleaner-idempotence", "matrix-transpose"]
RQ1_CONFIGS = [("randoop", None)] + [("llm", m) for m in MODELS] + [("new-hybrid", m) for m in MODELS]
RQ2_MODES = ["dev", "nl", "iauto", "oauto"]
LEAK_PATTERNS = {"oauto": re.compile(r"\w*MetamorphicSpec[.]generateFollowUp"),
                 "iauto": re.compile(r"[.]assertRelation")}


# ---------------------------------------------------------------- loading

def load_runs() -> list[dict]:
    latest: dict[str, dict] = {}
    with open(FE / "final-experiment-run-metadata" / "all-runs.jsonl", encoding="utf-8") as fh:
        for line in fh:
            row = json.loads(line)
            prev = latest.get(row["runId"])
            if prev is None or row["finishedAt"] >= prev["finishedAt"]:
                latest[row["runId"]] = row

    stripped = {}
    with open(FE / "final-experiment-stripped-oracles" / "stripped-runs.jsonl", encoding="utf-8") as fh:
        for line in fh:
            row = json.loads(line)
            if row.get("label") == "stripped":
                stripped[row["runId"]] = row

    runs = []
    for row in latest.values():
        model = row.get("model") or None
        if model == "no-model":
            model = None
        status = (row.get("frameworkMetrics") or {}).get("status")
        pit_ok = (row.get("pit") or {}).get("returncode") == 0
        pit, cov = row.get("pitSummary") or {}, row.get("pitLineCoverage") or {}
        if row["runId"] in stripped:
            pit, cov = stripped[row["runId"]]["pitSummary"], stripped[row["runId"]]["pitLineCoverage"]
        statuses = pit.get("statuses") or {}
        runs.append({
            "id": row["runId"], "mr": row["mr"], "mode": row["mode"], "gen": row["inputGenerator"],
            "model": model, "rep": row["runNumber"],
            "ok": status == "PASSED" and pit_ok,
            "leak": is_leak(row),
            "stripped": row["runId"] in stripped,
            "killed": statuses.get("KILLED", 0),
            "mutants": pit.get("totalMutations"),
            "covered_mutants": (pit.get("totalMutations") or 0) - statuses.get("NO_COVERAGE", 0),
            "lines": cov.get("coveredLines"), "total_lines": cov.get("totalLines"),
            "fm": row.get("frameworkMetrics") or {},
            "gen_seconds": (row.get("generation") or {}).get("durationSeconds"),
            "tests": (row.get("generatedTestCounts") or {}).get("passing"),
        })
    return runs


def is_leak(row: dict) -> bool:
    pattern = LEAK_PATTERNS.get(row["mode"])
    if pattern is None:
        return False
    archive = FE / "final-experiment-run-metadata" / f"{row['runId']}.generated"
    for path in glob.glob(str(archive / "junit-tests" / "*PassingTest.java")):
        if pattern.search(Path(path).read_text(encoding="utf-8")):
            return True
    return False


def usable(runs):
    return [r for r in runs if r["ok"] and not r["leak"]]


def select(runs, mr=None, mode=None, gen=None, model="*"):
    return [r for r in runs if (mr is None or r["mr"] == mr) and (mode is None or r["mode"] == mode)
            and (gen is None or r["gen"] == gen) and (model == "*" or r["model"] == model)]


# ---------------------------------------------------------------- formatting

def fmt(value, digits=1):
    """Round half up, as the report tables do (1.0550 -> 1.06)."""
    if value is None:
        return "n/a"
    exact = Decimal(repr(round(value, 9)))
    return str(exact.quantize(Decimal(1).scaleb(-digits), rounding=ROUND_HALF_UP))


def cell(rows, key, expected_n, digits=1):
    if not rows:
        return "n/a"
    text = fmt(mean(r[key] for r in rows), digits)
    return text if len(rows) == expected_n else f"{text} ({len(rows)})"


def table(title, header, body, csv_dir, name):
    print(f"\n### {title}\n")
    print("| " + " | ".join(header) + " |")
    print("|" + "---|" * len(header))
    for row in body:
        print("| " + " | ".join(str(c) for c in row) + " |")
    if csv_dir:
        with open(Path(csv_dir) / f"{name}.csv", "w", newline="", encoding="utf-8") as fh:
            writer = csv.writer(fh)
            writer.writerow(header)
            writer.writerows(body)


def config_label(gen, model):
    return "Randoop" if gen == "randoop" else f"{'LLM' if gen == 'llm' else 'Hybrid'} {model.title()}"


# ---------------------------------------------------------------- tables

def summary(runs, csv_dir):
    ok = usable(runs)
    leaks = [r for r in runs if r["ok"] and r["leak"]]
    failed = [r for r in runs if not r["ok"]]
    body = [["runs", len(runs)], ["successful (used)", len(ok)], ["failed", len(failed)],
            ["excluded as leaks", len(leaks)], ["nl runs re-measured after oracle stripping",
                                               sum(r["stripped"] for r in runs)]]
    table("Run summary", ["", "count"], body, csv_dir, "summary")
    print("\nLeaks: " + ", ".join(sorted(r["id"] for r in leaks)))


def rq1(runs, csv_dir):
    ok = usable(select(runs, mode="dev"))
    best_kill, best_line, kill_body, line_body = {}, {}, [], []
    for mr in MR_ORDER:
        means_k, means_l = {}, {}
        krow, lrow = [mr, select(runs, mr=mr)[0]["mutants"] if select(runs, mr=mr) else "n/a"], [mr]
        for gen, model in RQ1_CONFIGS:
            rows = select(ok, mr=mr, gen=gen, model=model)
            krow.append(cell(rows, "killed", 3))
            lrow.append(cell(rows, "lines", 3))
            if rows:
                means_k[(gen, model)] = mean(r["killed"] for r in rows)
                means_l[(gen, model)] = mean(r["lines"] for r in rows)
        best_kill[mr], best_line[mr] = means_k, means_l
        kill_body.append(krow)
        line_body.append(lrow)
    labels = [config_label(g, m) for g, m in RQ1_CONFIGS]
    table("RQ1: mean mutants killed (dev, successful runs; brackets = successful runs when < 3)",
          ["MR", "Mutants"] + labels, kill_body, csv_dir, "rq1_kills")
    table("RQ1: mean covered lines of the class under test", ["MR"] + labels, line_body, csv_dir, "rq1_lines")

    def relative(best, configs, key):
        # Per MR: mean over every successful run of the given configs (so a pooled score pools runs
        # across models), divided by the best single-configuration mean on that MR; then averaged.
        ratios = []
        for mr in MR_ORDER:
            rows = [r for gen, model in configs for r in select(ok, mr=mr, gen=gen, model=model)]
            top = max(best[mr].values())
            if rows and top > 0:
                ratios.append(mean(r[key] for r in rows) / top)
        return mean(ratios) if ratios else None

    body = []
    for gen, model in RQ1_CONFIGS:
        body.append([config_label(gen, model), fmt(relative(best_kill, [(gen, model)], "killed"), 2),
                     fmt(relative(best_line, [(gen, model)], "lines"), 2)])
    for gen in ("llm", "new-hybrid"):
        configs = [(gen, m) for m in MODELS]
        body.append([f"{'LLM' if gen == 'llm' else 'Hybrid'} pooled", fmt(relative(best_kill, configs, "killed"), 2),
                     fmt(relative(best_line, configs, "lines"), 2)])
    table("RQ1: relative kill and line coverage score (per MR: mean / best RQ1 configuration mean, averaged over MRs)",
          ["Configuration", "Kill score", "Line coverage score"], body, csv_dir, "rq1_relative")

    body = []
    for name, gens in (("Randoop", ["randoop"]), ("LLM", ["llm"]), ("Hybrid", ["new-hybrid"])):
        rows = [r for r in ok if r["gen"] in gens and r["covered_mutants"] > 0]
        body.append([name, f"{100 * mean(r['killed'] / r['covered_mutants'] for r in rows):.0f}%"])
    table("RQ1: test strength (killed / covered mutants, mean over runs)", ["Generator", "Test strength"],
          body, csv_dir, "rq1_test_strength")


def rq2(runs, csv_dir):
    nh = usable(select(runs, gen="new-hybrid"))
    body = []
    for mr in MR_ORDER:
        body.append([mr] + [cell(select(nh, mr=mr, mode=mode), "killed", 9) for mode in RQ2_MODES])
    table("RQ2: mean mutants killed, pooled over models, hybrid generator (brackets = successful runs when < 9)",
          ["MR", "dev (human)", "nl (AUTO)", "iauto", "oauto"], body, csv_dir, "rq2_kills")

    body = []
    for model in MODELS:
        body.append([model.title()] + [cell(select(nh, mr=mr, mode=mode, model=model), "killed", 3)
                                       for mr in MR_ORDER for mode in RQ2_MODES])
    table("RQ2: mean mutants killed per MR and model", ["Model"] + [f"{mr} {mode}" for mr in MR_ORDER
                                                                     for mode in RQ2_MODES],
          body, csv_dir, "rq2_kills_by_model")

    def ratio(model_set, mode):
        # Per MR: mean kills of `mode` over the given models' runs divided by mean dev kills over the
        # same models' runs (so "Pooled" pools runs across models), averaged over MRs with results.
        ratios = []
        for mr in MR_ORDER:
            human = [r for r in select(nh, mr=mr, mode="dev") if r["model"] in model_set]
            other = [r for r in select(nh, mr=mr, mode=mode) if r["model"] in model_set]
            if human and other and mean(r["killed"] for r in human) > 0:
                ratios.append(mean(r["killed"] for r in other) / mean(r["killed"] for r in human))
        return mean(ratios) if ratios else None

    body = [[m.title()] + [fmt(ratio([m], mode), 2) for mode in RQ2_MODES[1:]] for m in MODELS]
    body.append(["Pooled"] + [fmt(ratio(MODELS, mode), 2) for mode in RQ2_MODES[1:]])
    table("RQ2: mean ratio of kills to dev with the same model, over MRs with results",
          ["Model", "nl (AUTO)", "iauto", "oauto"], body, csv_dir, "rq2_ratio")


def cost(runs, csv_dir):
    body = []
    groups = [("Randoop", "dev", "randoop", None)]
    groups += [("LLM, dev", "dev", "llm", m) for m in MODELS]
    groups += [(f"Hybrid, {mode}", mode, "new-hybrid", m) for mode in RQ2_MODES for m in MODELS]
    for name, mode, gen, model in groups:
        all_rows = select(runs, mode=mode, gen=gen, model=model)
        rows = usable(all_rows)
        tokens = [r["fm"].get("totalTokens", 0) for r in rows]
        prices = PRICES.get(model, (0, 0))
        dollars = [(r["fm"].get("promptTokens", 0) * prices[0] + r["fm"].get("completionTokens", 0) * prices[1])
                   / 1e6 for r in rows]
        body.append([name, model.title() if model else "n/a", f"{len(rows)}/{len(all_rows)}",
                     fmt(mean(tokens), 0) if rows else "n/a", fmt(mean(dollars), 2) if rows else "n/a",
                     fmt(mean(r["gen_seconds"] for r in rows), 0) if rows else "n/a",
                     fmt(mean(r["fm"].get("totalRepairAttempts", 0) for r in rows), 2) if rows else "n/a",
                     fmt(mean(r["tests"] for r in rows if r["tests"] is not None), 1) if rows else "n/a"])
    table("Mean cost and effort per successful run (list price; time = generation only)",
          ["Configuration", "Model", "Success", "Tokens", "Cost (US$)", "Time (s)", "Repairs", "Tests"],
          body, csv_dir, "cost")
    total_tokens = sum(r["fm"].get("totalTokens", 0) for r in runs)
    total_cost = sum((r["fm"].get("promptTokens", 0) * PRICES[r["model"]][0]
                      + r["fm"].get("completionTokens", 0) * PRICES[r["model"]][1]) / 1e6
                     for r in runs if r["model"])
    print(f"\nAll 480 runs: {total_tokens:,} tokens, US${total_cost:.2f} at list price.")


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--csv-dir", help="Also write one CSV per table into this directory.")
    args = parser.parse_args()
    if args.csv_dir:
        Path(args.csv_dir).mkdir(parents=True, exist_ok=True)
    runs = load_runs()
    summary(runs, args.csv_dir)
    rq1(runs, args.csv_dir)
    rq2(runs, args.csv_dir)
    cost(runs, args.csv_dir)


if __name__ == "__main__":
    main()
