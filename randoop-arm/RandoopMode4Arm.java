import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Entry point for the Randoop Mode 4 source-generation arm.
 *
 * <ol>
 *   <li>Harvest structurally-distinct Order objects via Randoop (in-process).</li>
 *   <li>Evaluate them against the (buggy) SUT + developer MR -> bug-revealing count + diversity.</li>
 *   <li>Emit a real Mode 4 JUnit candidate test from them (integrates with Soham's runner).</li>
 * </ol>
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

        // --- Arm A: raw Randoop (default value pool only) ---
        System.out.println("Harvesting (raw Randoop, " + timeLimitMillis + "ms)...");
        List<Order> rawSources = RandoopOrderHarvester.harvest(timeLimitMillis);
        Mode4Evaluator.printReport("ARM A: RAW Randoop", Mode4Evaluator.evaluate(rawSources));

        // --- Arm B: seeded Randoop (LLM-style meaningful values added to the pool) ---
        // Stand-in for LLM-supplied seeds: realistic names, varied quantities (incl. > 5 cap), prices.
        List<Object> seeds = new ArrayList<>(List.of(
                "Laptop", "USB-C Cable", "Mouse", "Monitor", "Keyboard",
                1, 2, 3, 5, 7, 10, 25, 0,
                9.99, 19.95, 49.50, 100.0, 0.0, 1299.0));
        System.out.println("Harvesting (seeded Randoop, " + timeLimitMillis + "ms)...");
        List<Order> seededSources = RandoopOrderHarvester.harvest(timeLimitMillis, seeds);
        Mode4Evaluator.printReport("ARM B: SEEDED Randoop (LLM-style values, 1 seed)", Mode4Evaluator.evaluate(seededSources));

        // --- Arm C: seeded + multi-seed union (break per-run convergence) ---
        long[] randomSeeds = {0L, 1L, 2L, 3L, 4L};
        int perSeedBudget = Math.max(2000, timeLimitMillis / randomSeeds.length);
        System.out.println("Harvesting (seeded + " + randomSeeds.length + " random seeds, "
                + perSeedBudget + "ms each)...");
        List<Order> unionSources = RandoopOrderHarvester.harvestMultiSeed(perSeedBudget, seeds, randomSeeds);
        Mode4Evaluator.printReport("ARM C: SEEDED + multi-seed union", Mode4Evaluator.evaluate(unionSources));

        // Emit the Mode 4 candidate test from the richest arm.
        List<Order> sources = unionSources;
        String testSource = Mode4TestEmitter.emit(className, sources);
        Files.createDirectories(outputDir);
        Path testFile = outputDir.resolve(className + ".java");
        Files.writeString(testFile, testSource, StandardCharsets.UTF_8);
        System.out.println("Wrote Mode 4 candidate test (" + sources.size() + " @Test methods) to:");
        System.out.println("  " + testFile.toAbsolutePath());
    }
}
