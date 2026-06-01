# Metamorphic Testing with LLMs

A Java tool that uses an LLM (OpenAI) to generate **test input data** for metamorphic testing. You describe a class, function, and metamorphic relation — the LLM generates Java code that programmatically produces source and follow-up inputs as JSON.

The LLM only generates **data**, never assertions. The developer owns the test oracle.

---

## Prerequisites

| What | Why |
|------|-----|
| **JDK 17+** | Compile and run the tool |
| **OpenAI API key** | Calls the OpenAI API to generate code |

---

## Setup

1. Create a `.env` file in the project root:

   ```
   OPENAI_API_KEY=sk-...
   ```

   Optional overrides via `.env` or environment variables:
   - `OPENAI_MODEL` — defaults to `gpt-5-mini`
   - `OPENAI_BASE_URL` — defaults to `https://api.openai.com/v1`

2. `.env` is gitignored — keys are never committed.

---

## Project layout

| File | Purpose |
|------|---------|
| `OpenaiRunner.java` | Main runner — reads config, calls LLM, compiles/runs generated code, validates output |
| `prompt.txt` | Config file that controls what gets sent to the LLM |
| `prompt.class-level.example.txt` | Documented example of all config fields |
| `SortUtil.java` | Example SUT (sorting) |
| `Student.java`, `GradeUtil.java` | Example SUT (objects — student grade average) |
| `out.txt` | Generated JSON output (gitignored) |

---

## How to run

```bash
javac OpenaiRunner.java
java OpenaiRunner
```

The tool reads `prompt.txt`, calls the LLM, and writes the generated test data to `out.txt`.

---

## Config (prompt.txt)

Each line is a `Key: value` pair. Lines starting with `#` are comments.

```text
# Path to the SUT class file
SUTClassFile: SortUtil.java

# Method to generate test data for
TargetFunction: public static int[] sortArray(int[] arr)

# Comma-separated dependency files (blank = auto-detect from imports)
SUTSupportFiles:

# Metamorphic relation as input/output pair:
MRInput: the follow-up input is a permutation of the source input
MROutput: sortArray(followUp) must equal sortArray(source)

# Or as a single statement:
# MR: permuting the input does not change the sorted output

# How many test data entries to generate
Count: 5

# Edge cases the generated inputs should cover
InputDomain: empty array, single element, duplicates, negative numbers, mixed magnitudes

# Name of the generated Java class
GeneratedClassName: GeneratedSortData

# How much the LLM generates:
#   inputs-only         — source inputs only
#   inputs-and-followup — source inputs + follow-up transform
Level: inputs-and-followup

# Retry attempts if generated code fails to compile/run
MaxRepairAttempts: 3
```

---

## How it works

1. `prompt.txt` is parsed into a config.
2. The SUT source (and any dependencies) is read and embedded in the prompt for type context.
3. The LLM is asked to generate a Java class with:
   - `generateSources()` — uses real generation logic (loops, Random, helpers), not hardcoded values
   - `generateFollowUp(source)` — applies the MR's input transformation (at `inputs-and-followup` level)
   - `main()` — prints the test data as JSON to stdout
4. The generated code is compiled and run. If it fails, the error is fed back to the LLM for repair (up to `MaxRepairAttempts` times).
5. The JSON output is validated (correct format, enough entries) and written to `out.txt`.

### Output format

```json
[
  {"source": [3, 1, 2], "followUp": [2, 3, 1]},
  {"source": [-5, 0, 5], "followUp": [5, -5, 0]}
]
```

At `inputs-only` level, entries have `"source"` only (no `"followUp"`).

For object SUTs, values are nested JSON matching the object's fields:

```json
[
  {
    "source": {"name": "Alice", "grades": [80.0, 90.0]},
    "followUp": {"name": "Alice", "grades": [80.0, 90.0, 85.0]}
  }
]
```

---

## Levels of LLM involvement

| Level | LLM generates | Developer provides |
|-------|--------------|-------------------|
| `inputs-only` | Source input generation code | Follow-up transform + assertion |
| `inputs-and-followup` | Source inputs + follow-up transform code | Assertion only |

The LLM **never** generates assertions. The developer writes the test oracle in their own JUnit test.

---

## Troubleshooting

| Symptom | Likely cause |
|---------|-------------|
| `Missing prompt.txt` | Run from the project root |
| `Missing OPENAI_API_KEY` | Create `.env` with your key |
| `OpenAI HTTP error 401` | API key is wrong or expired |
| `OpenAI HTTP error 429` | Rate limit or billing quota reached |
| Compile/run failures loop | Check `out.txt` — the generated code may need a better prompt or more repair attempts |
