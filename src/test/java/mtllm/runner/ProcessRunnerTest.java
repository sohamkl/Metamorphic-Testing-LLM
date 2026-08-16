package mtllm.runner;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessRunnerTest {
    @Test
    void capturesMergedOutputAndExitCode() throws Exception {
        ProcessRunner.Result result = ProcessRunner.run(
                List.of("/bin/sh", "-c", "printf output; printf error >&2; exit 7"),
                Path.of(".").toAbsolutePath());

        assertEquals(7, result.exitCode());
        assertEquals("outputerror", result.output());
        assertFalse(result.timedOut());
    }

    @Test
    void terminatesTimedOutProcess() throws Exception {
        ProcessRunner.Result result = ProcessRunner.run(
                List.of("/bin/sh", "-c", "sleep 5"),
                Path.of(".").toAbsolutePath(), 1);

        assertEquals(124, result.exitCode());
        assertTrue(result.timedOut());
        assertTrue(result.output().contains("Process timed out after 1 seconds"));
    }
}
