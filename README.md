# MT-testing

Small Java workspace for **prompting a local or cloud LLM** from a text file and writing the reply to disk, plus a toy `SortUtil` and **runnable metamorphic-style tests** under `LLM-output-files/`.

---

## Prerequisites

| What | Why |
|------|-----|
| **JDK 11+** (17 recommended) | Compile and run `OllamaRunner`, `SortUtil`, tests |
| **Ollama** (optional) | Only if you use provider `ollama` |
| **OpenAI API key** (optional) | Only if you use provider `openai` |
| **Internet** | Required for OpenAI; Ollama is usually local |

Check Java:

```bash
java -version
javac -version
```

---

## Install Ollama

### macOS (Homebrew)

```bash
brew install ollama
```

On some macOS versions, Homebrew may try to build dependencies (for example **MLX**) that need **full Xcode** and a **newer macOS**. If `brew install ollama` fails with messages about Xcode or Sonoma, use one of the alternatives below.

### macOS (official app)

Download and install from [https://ollama.com/download](https://ollama.com/download). That path often avoids the Homebrew build issues above.

### Docker (works when native brew install fails)

```bash
docker pull ollama/ollama
docker run -d --name ollama -p 11434:11434 ollama/ollama
docker exec -it ollama ollama pull llama3.2:1b
```

Ollama’s HTTP API should be reachable at `http://localhost:11434` (this matches `OllamaRunner`).

Check:

```bash
curl -sS http://localhost:11434/api/tags | head
```

---

## OpenAI API key

1. Copy the example env file:

   ```bash
   cp .env.example .env
   ```

2. Edit `.env` and set:

   ```bash
   OPENAI_API_KEY=sk-...
   ```

   Optional: `OPENAI_BASE_URL=` if you use a compatible proxy (defaults to `https://api.openai.com/v1`).

`.env` is listed in `.gitignore` so keys are not committed.

---

## Project layout

| Path | Purpose |
|------|---------|
| `OllamaRunner.java` | Reads a prompt file, calls **Ollama** or **OpenAI**, writes model text to an output file |
| `prompt.txt` | Example prompt you can edit |
| `SortUtil.java` | Demo `sortArray` implementation |
| `LLM-output-files/output-1-test.java` | Example **fixed** LLM-shaped tests (runnable `main`) |
| `.env.example` | Template for OpenAI settings |

---

## Prompt workflow

1. Put your instructions in a text file (for example `prompt.txt`).
2. Compile the runner from the **repository root**:

   ```bash
   cd /path/to/MT-testing
   javac OllamaRunner.java
   ```

3. Run with **explicit provider** (recommended so nothing is ambiguous):

   **Ollama** (model must exist locally; example `llama3.2:1b`):

   ```bash
   java -cp . OllamaRunner ollama llama3.2:1b prompt.txt out.txt
   ```

   **OpenAI**:

   ```bash
   java -cp . OllamaRunner openai gpt-4o-mini prompt.txt out.txt
   ```

4. Open `out.txt` (or whatever output path you passed) for the model response.

### Shorter three-argument form

```bash
java -cp . OllamaRunner <model> <inputFile> <outputFile>
```

In the current code, this form uses provider **`ollama`** and treats the first argument as the **Ollama model name**.

---

## Run `SortUtil` and the example tests

From the repo root:

```bash
javac SortUtil.java
java -cp . SortUtil
```

Run the LLM-shaped runnable tests:

```bash
javac SortUtil.java LLM-output-files/output-1-test.java
java -cp ".:LLM-output-files" Output1Test
```

---

## Troubleshooting

| Symptom | Likely cause |
|---------|----------------|
| `Could not find or load main class OllamaRunner` | Run from project root after `javac`, and use `java -cp . OllamaRunner ...` |
| `Connection refused` to Ollama | Ollama not running or Docker container not up / port `11434` not published |
| OpenAI `429` / `insufficient_quota` | Billing or quota on the OpenAI account; not a Java bug |
| `brew install ollama` fails (MLX / Xcode / Sonoma) | Use the **official installer** or **Docker** (see above) |

---

## Security note

Never commit `.env` or paste API keys into prompts that get logged publicly. Rotate keys if they leak.
