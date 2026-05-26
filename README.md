# Metamorphic Testing with LLMs

A Java tool that uses an LLM (OpenAI) to automatically generate metamorphic test pairs for a class you provide. Instead of writing tests by hand, you describe the class, the function, and the metamorphic relation — the LLM generates the test inputs.

---

## Prerequisites

| What | Why |
|------|-----|
| **JDK 11+** (17 recommended) | Compile and run the tool |
| **OpenAI API key** | Calls the OpenAI API to generate tests |
| **Internet connection** | Required to reach the OpenAI API |

Check Java is installed:

```bash
java -version
javac -version
```

---

## OpenAI API key setup

1. Create a `.env` file in the project root:

   ```
   OPENAI_API_KEY=sk-...
   OPENAI_ORG_ID=org-...
   ```

   Optional: add `OPENAI_BASE_URL=...` if you use a compatible proxy (defaults to `https://api.openai.com/v1`).
   Optional: add `OPENAI_MODEL=...` to change the model (defaults to `gpt-4o-mini`).

2. `.env` is listed in `.gitignore` so keys are never committed.

---

## Project layout

| File | Purpose |
|------|---------|
| `OpenaiRunner.java` | Main runner — reads config, calls OpenAI, writes output |
| `prompt.txt` | Config file that controls what gets sent to the LLM |
| `SortUtil.java` | Example class used as the system under test |
| `out.txt` | LLM response is written here (gitignored) |

---

## How to configure prompt.txt

`prompt.txt` controls everything the LLM is told. Each line is a `Key: value` pair. Lines starting with `#` are comments.

```text
# Path to the Java class you want to test
SUTClassFile: SortUtil.java

# The specific function within that class to focus on
TargetFunction: public static int[] sortArray(int[] arr)

# Optional: comma-separated list of helper/dependency files to include.
# If left blank, the tool will auto-detect imports from SUTClassFile and
# include any matching .java files it finds in the project.
SUTSupportFiles:

# The metamorphic relation — describe what relationship must hold
MR: Permutation

# How many test pairs to generate
Count: 5

# The data type of the inputs
DataType: int[]

# Edge cases and constraints to cover
InputDomain: empty array, single element, duplicates, negative numbers, mixed magnitudes
```

### Key notes

- **SUTClassFile** — the full source of this class is injected into the LLM prompt so it understands exactly what it is testing.
- **SUTSupportFiles** — if your class depends on other classes, list them here (comma-separated). If left blank, the tool scans the project for files matching the imports in your SUT class and includes them automatically (first-level dependencies only).
- **MR** — the metamorphic relation tells the LLM what relationship the test pairs must satisfy (e.g. two permutations of the same array should sort to the same result).

---

## How to run

From the project root:

```bash
javac OpenaiRunner.java
java OpenaiRunner
```

The LLM response is written to `out.txt`. The format is a JSON array of test pairs:

```json
[
  { "source": [3, 1, 2], "followUp": [2, 3, 1] },
  { "source": [-5, 0, 5], "followUp": [5, -5, 0] }
]
```

---

## How it works

1. `prompt.txt` is read and parsed into a config object.
2. The source code of the SUT class (`SUTClassFile`) is read from disk and embedded into the prompt.
3. If no support files are manually listed, the tool reads the SUT's import statements and searches the project for matching `.java` files, including them automatically.
4. A prompt is built containing the full SUT source, any dependency sources, the metamorphic relation, the data type, the input domain constraints, and the number of pairs to generate.
5. The prompt is sent to OpenAI via the chat completions API.
6. The response is extracted from the API's JSON envelope and written to `out.txt`.

---

## Troubleshooting

| Symptom | Likely cause |
|---------|-------------|
| `Missing prompt.txt` | Run from the project root directory, not a subdirectory |
| `Missing OPENAI_API_KEY` | Create a `.env` file with your key (see setup section above) |
| `OpenAI HTTP error 401` | API key is wrong or expired — check your key |
| `OpenAI HTTP error 429` | Rate limit or billing quota reached on your OpenAI account |
| `Could not extract response content` | Unexpected response shape from the API — check `out.txt` for the raw response |

---

## Security note

Never commit `.env` or paste API keys anywhere public. Rotate your key immediately if it leaks.
