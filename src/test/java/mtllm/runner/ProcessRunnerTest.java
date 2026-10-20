package mtllm.runner;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessRunnerTest {
    private static final boolean WINDOWS =
            System.getProperty("os.name", "").toLowerCase(Locale.ROOT).startsWith("windows");

    /** Writes "output" to stdout and "error" to stderr, then exits 7. */
    private static List<String> failingCommand() {
        if (!WINDOWS) {
            return List.of("/bin/sh", "-c", "printf output; printf error >&2; exit 7");
        }
        // Passed as separate tokens so the command line reaches cmd unquoted and it can see the '&'.
        return List.of("cmd", "/c", "echo", "output", "&", "echo", "error", "1>&2", "&", "exit", "7");
    }

    /** Stays alive for roughly five seconds. */
    private static List<String> sleepingCommand() {
        if (!WINDOWS) {
            return List.of("/bin/sh", "-c", "sleep 5");
        }
        // ping is the portable Windows sleep; 'timeout' fails when stdin is not a console.
        return List.of("cmd", "/c", "ping", "-n", "6", "127.0.0.1");
    }

    @Test
    void capturesMergedOutputAndExitCode() throws Exception {
        ProcessRunner.Result result = ProcessRunner.run(
                failingCommand(),
                Path.of(".").toAbsolutePath());

        assertEquals(7, result.exitCode());
        if (WINDOWS) {
            // cmd's echo appends a line break to each write, so only the merge itself is asserted.
            assertTrue(result.output().contains("output"), result.output());
            assertTrue(result.output().contains("error"), result.output());
        } else {
            assertEquals("outputerror", result.output());
        }
        assertFalse(result.timedOut());
    }

    @Test
    void terminatesTimedOutProcess() throws Exception {
        ProcessRunner.Result result = ProcessRunner.run(
                sleepingCommand(),
                Path.of(".").toAbsolutePath(), 1);

        assertEquals(124, result.exitCode());
        assertTrue(result.timedOut());
        assertTrue(result.output().contains("Process timed out after 1 seconds"));
    }
}
