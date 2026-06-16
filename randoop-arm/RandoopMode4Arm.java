import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Entry point for the Randoop Mode 4 source-generation arm.
 *
 * <ol>
 *   <li>Harvest structurally-distinct Order objects via Randoop (in-process).</li>
 *   <li>Evaluate them against the (buggy) SUT + developer MR -> bug-revealing count + diversity.</li>
 *   <li>Emit a real Mode 4 JUnit candidate test from them (integrates with Soham's runner).</li>
 * </ol>
 *
 * <p>Four arms, each progressively closer to the LLM-seeded hybrid:</p>
 * <ul>
 *   <li>A — raw Randoop (default tiny value pool)</li>
 *   <li>B — Randoop with hand-picked LLM-style values (baseline for comparison)</li>
 *   <li>C — Arm B values + 5 random-seed union (breaks per-run convergence)</li>
 *   <li>D — Arm C but the value pool comes from an actual LLM call (the real hybrid; skipped if
 *       no OPENAI_API_KEY in .env)</li>
 * </ul>
 *
 * <p>Usage: java ... RandoopMode4Arm [timeLimitMillis] [outputDir]</p>
 */
public final class RandoopMode4Arm {

    private RandoopMode4Arm() {
    }

    public static void main(String[] args) throws Exception {
        int timeLimitMillis = args.length > 0 ? Integer.parseInt(args[0]) : 15000;
        Path outputDir = Path.of(args.length > 1 ? args[1] : "generated-tests");
        String className = "GeneratedOrderUtilRandoopTest";

        // Shared multi-seed config used by Arms C and D
        long[] randomSeeds = {0L, 1L, 2L, 3L, 4L};
        int perSeedBudget = Math.max(2000, timeLimitMillis / randomSeeds.length);

        // Hand-picked LLM-style values: realistic names, quantities that cross the qty>5 cap,
        // varied prices. Used by Arms B and C as a controlled baseline.
        List<Object> handPickedSeeds = new ArrayList<>(List.of(
                "Laptop", "USB-C Cable", "Mouse", "Monitor", "Keyboard",
                1, 2, 3, 5, 7, 10, 25, 0,
                9.99, 19.95, 49.50, 100.0, 0.0, 1299.0));

        // SUT wiring -- the ONLY SUT-specific configuration. To target a different object SUT,
        // change these few items: the class-name set (the classes Randoop may build with AND the
        // sources the LLM is shown in Arm D), the target type, the SUT call, the developer's
        // MetamorphicSpec, and the optional size function. The signature is left to the reflection
        // auto-default (no lambda), which for these clean POJOs groups identically to the old
        // hand-written one.
        Set<String> sutClasses = new LinkedHashSet<>(List.of("Order", "LineItem", "OrderUtil"));
        RandoopHarvester<Order> harvester = new RandoopHarvester<>(Order.class, sutClasses);
        Mode4Evaluator<Order, Double> evaluator = new Mode4Evaluator<>(
                OrderUtil::calculateTotal,
                OrderMetamorphicSpec::generateFollowUp,
                OrderMetamorphicSpec::assertRelation,
                harvester::signatureOf,
                o -> o.getItems().size());

        // --- Arm A: raw Randoop (default value pool only) ---
        System.out.println("Harvesting (raw Randoop, " + timeLimitMillis + "ms)...");
        List<Order> rawSources = harvester.harvest(timeLimitMillis);
        Mode4Evaluator.printReport("ARM A: RAW Randoop", evaluator.evaluate(rawSources));

        // --- Arm B: seeded Randoop (hand-picked LLM-style values, 1 seed) ---
        System.out.println("Harvesting (seeded Randoop, " + timeLimitMillis + "ms)...");
        List<Order> seededSources = harvester.harvest(timeLimitMillis, handPickedSeeds);
        Mode4Evaluator.printReport("ARM B: SEEDED Randoop (hand-picked values, 1 seed)",
                evaluator.evaluate(seededSources));

        // --- Arm C: hand-picked seeds + multi-seed union (break per-run convergence) ---
        System.out.println("Harvesting (hand-picked seeds + " + randomSeeds.length
                + " random seeds, " + perSeedBudget + "ms each)...");
        List<Order> unionSources = harvester.harvestMultiSeed(
                perSeedBudget, handPickedSeeds, randomSeeds);
        Mode4Evaluator.printReport("ARM C: SEEDED + multi-seed union",
                evaluator.evaluate(unionSources));

        // --- Arm D: LLM-seeded Randoop (value pool comes from a live LLM call) ---
        // The hybrid: LLM reads the SUT and returns domain-relevant strings/ints/doubles;
        // Randoop builds objects from them. LLM contributes domain knowledge, Randoop contributes
        // volume. Skipped gracefully if no API key is present.
        List<Order> llmSources = new ArrayList<>();
        String apiKey = "";
        try {
            apiKey = LlmValueSeeder.readEnvValue("OPENAI_API_KEY");
        } catch (Exception ignored) {
        }

        if (apiKey.isEmpty()) {
            System.out.println("ARM D: skipped -- no OPENAI_API_KEY found in .env");
        } else {
            System.out.println("Asking LLM for seed values...");
            // Load the source of every configured SUT class so the LLM sees the data classes
            // (constructors/validation guards) AND the method under test -- generic over sutClasses,
            // so swapping in a different SUT loads the right sources automatically. Files are found
            // by walking up from CWD (handles running from worktrees). Comments are stripped inside
            // LlmValueSeeder so the LLM must infer edge cases from the code, not from a comment.
            List<String> sutSources = new ArrayList<>();
            for (String cn : sutClasses) {
                Path p = findRepoFile(Path.of("examples/order/src/" + cn + ".java"));
                if (Files.exists(p)) {
                    sutSources.add(Files.readString(p, StandardCharsets.UTF_8));
                } else {
                    System.out.println("ARM D: note -- source for " + cn + " not found (" + p + "), skipped");
                }
            }
            List<Object> llmSeeds = LlmValueSeeder.generateSeeds(apiKey, "gpt-4o-mini", sutSources);
            System.out.println("Harvesting (LLM-seeded + " + randomSeeds.length
                    + " random seeds, " + perSeedBudget + "ms each)...");
            llmSources = harvester.harvestMultiSeed(perSeedBudget, llmSeeds, randomSeeds);
            Mode4Evaluator.printReport("ARM D: LLM-SEEDED Randoop (live LLM value pool)",
                    evaluator.evaluate(llmSources));
        }

        // Emit the Mode 4 candidate test from the richest arm (most distinct shapes).
        List<Order> best = richest(rawSources, seededSources, unionSources, llmSources);
        String testSource = Mode4TestEmitter.emit(className, best);
        Files.createDirectories(outputDir);
        Path testFile = outputDir.resolve(className + ".java");
        Files.writeString(testFile, testSource, StandardCharsets.UTF_8);
        System.out.println("Wrote Mode 4 candidate test (" + best.size() + " @Test methods) to:");
        System.out.println("  " + testFile.toAbsolutePath());
    }

    @SafeVarargs
    private static List<Order> richest(List<Order>... arms) {
        List<Order> best = arms[0];
        for (List<Order> arm : arms) {
            if (arm.size() > best.size()) best = arm;
        }
        return best;
    }

    /** Find a repo-relative path by walking up from CWD. Handles running from a worktree. */
    private static Path findRepoFile(Path repoRelative) {
        Path dir = Path.of(".").toAbsolutePath().normalize();
        for (int i = 0; i < 6; i++) {
            Path candidate = dir.resolve(repoRelative);
            if (Files.exists(candidate)) return candidate;
            Path parent = dir.getParent();
            if (parent == null) break;
            dir = parent;
        }
        return repoRelative; // fallback, will fail with a clear message if not found
    }
}
