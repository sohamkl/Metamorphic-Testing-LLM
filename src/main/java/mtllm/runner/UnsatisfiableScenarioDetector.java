package mtllm.runner;

import mtllm.config.PromptConfig;
import mtllm.config.ScenarioRequirement;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Finds inferred scenarios that cannot be satisfied, so the pipeline can retire them instead of
 * demanding them forever.
 *
 * <p>The inference step plans scenarios from the SUT source and the prose metamorphic relation. It
 * never sees the developer MR's Java source, so it can plan a scenario that contradicts a
 * precondition it had no way to know about. {@code GeneratedTestQualityGate} then requires every
 * planned scenario to be present, and {@code RepairLoop} keeps asking the model to supply it, so an
 * impossible scenario burns the whole repair budget and fails the run. Observed on ta4j
 * {@code SlopeChangeSwingDetector}: the domain planned {@code EMPTY_SERIES_SENTINEL}, but the
 * developer MR throws unless {@code getBeginIndex() == 0}, which an empty {@code BaseBarSeries}
 * can never satisfy.</p>
 *
 * <p><b>The discriminator is the throw site, not the retry count.</b> A test whose exception
 * originates <em>outside</em> the generated class, in the developer MR spec or in the SUT's own
 * argument validation, is being refused by a contract the generated code cannot change: the
 * scenario is impossible. An exception raised inside the generated class is ordinary bad codegen
 * and is what the repair loop exists to fix. Keying on the throw site rather than on repeated
 * attempts means a single run is enough to decide, which is what lets this be tested with
 * {@code MaxRepairAttempts: 0}.</p>
 *
 * <p>Scenarios that are simply <em>absent</em> from the suite are untouched: that is the model
 * being lazy, and the gate must keep demanding those or its coverage guarantee is worthless.</p>
 */
public final class UnsatisfiableScenarioDetector {

    /** A stack frame line: "\tat some.pkg.Type.method(File.java:12)". */
    private static final Pattern FRAME =
            Pattern.compile("^\\s+at\\s+([\\w.$]+)\\.([\\w$<>]+)\\(");

    /** An exception header line: "java.lang.IllegalArgumentException: message". */
    private static final Pattern EXCEPTION_HEADER =
            Pattern.compile("^([\\w.$]*(?:Exception|Error))(?::\\s?(.*))?$");

    private UnsatisfiableScenarioDetector() {
    }

    /** One scenario retired, with enough detail to report why. */
    public record Retired(String scenarioId, String testMethod, String exception, String message) {
        public String describe() {
            String detail = message == null || message.isBlank() ? "" : ": " + message;
            return scenarioId + " (test " + testMethod + " -> " + exception + detail + ")";
        }
    }

    /**
     * Scans a Maven/Surefire run for tests that failed against a precondition owned by code the
     * generator does not control, and maps them back to the scenarios that planned them.
     *
     * @param mavenOutput raw run output, which carries the full stack traces
     * @param config      the run config, for the generated class name and the scenario list
     * @return one entry per retirable scenario, empty when nothing qualifies
     */
    public static List<Retired> detect(String mavenOutput, PromptConfig config) {
        if (mavenOutput == null || mavenOutput.isBlank()) {
            return List.of();
        }
        if (!config.inputDomainRequirements().isStructured()) {
            return List.of();
        }
        String generatedClass = config.generatedClassName();
        List<Retired> retired = new ArrayList<>();
        Set<String> seenScenarios = new LinkedHashSet<>();

        String[] lines = mavenOutput.split("\r?\n");
        for (int i = 0; i < lines.length; i++) {
            Matcher header = EXCEPTION_HEADER.matcher(lines[i].trim());
            if (!header.matches() || !lines[i].equals(lines[i].stripLeading())) {
                continue;
            }
            Frames frames = collectFrames(lines, i + 1, generatedClass);
            if (frames.topClass == null || frames.testMethod == null) {
                continue;
            }
            // Thrown from inside the generated class is a codegen bug; that is the repair loop's job.
            if (simpleName(frames.topClass).equals(generatedClass)) {
                continue;
            }
            String scenarioId = scenarioFor(frames.testMethod, config);
            if (scenarioId == null || !seenScenarios.add(scenarioId)) {
                continue;
            }
            retired.add(new Retired(
                    scenarioId, frames.testMethod, simpleName(header.group(1)), header.group(2)));
        }
        return List.copyOf(retired);
    }

    /** Test method names to delete from the generated suite. */
    public static Set<String> testMethods(List<Retired> retired) {
        Set<String> names = new LinkedHashSet<>();
        for (Retired r : retired) {
            names.add(r.testMethod());
        }
        return names;
    }

    /** Scenario ids to stop demanding. */
    public static Set<String> scenarioIds(List<Retired> retired) {
        Set<String> ids = new LinkedHashSet<>();
        for (Retired r : retired) {
            ids.add(r.scenarioId());
        }
        return ids;
    }

    private record Frames(String topClass, String testMethod) {
    }

    /**
     * Reads the frames under an exception header. The first frame is where it was thrown; the
     * deepest frame still inside the generated class is the @Test method that triggered it.
     */
    private static Frames collectFrames(String[] lines, int start, String generatedClass) {
        String topClass = null;
        String testMethod = null;
        for (int i = start; i < lines.length; i++) {
            Matcher frame = FRAME.matcher(lines[i]);
            if (!frame.find()) {
                if (lines[i].isBlank() || lines[i].stripLeading().startsWith("Caused by")) {
                    continue;
                }
                break;
            }
            String frameClass = frame.group(1);
            String frameMethod = frame.group(2);
            if (topClass == null) {
                topClass = frameClass;
            }
            if (simpleName(frameClass).equals(generatedClass)) {
                testMethod = frameMethod;
            }
        }
        return new Frames(topClass, testMethod);
    }

    /** Matches a test method back to its scenario, preferring the longest id so prefixes lose. */
    private static String scenarioFor(String testMethod, PromptConfig config) {
        String haystack = normalized(testMethod);
        String best = null;
        for (ScenarioRequirement scenario : config.inputDomainRequirements().scenarios()) {
            String id = scenario.id();
            if (id == null || id.isBlank()) {
                continue;
            }
            if (haystack.contains(normalized(id))
                    && (best == null || id.length() > best.length())) {
                best = id;
            }
        }
        return best;
    }

    private static String simpleName(String qualified) {
        int dot = qualified.lastIndexOf('.');
        return dot < 0 ? qualified : qualified.substring(dot + 1);
    }

    private static String normalized(String value) {
        return value.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]", "");
    }
}
