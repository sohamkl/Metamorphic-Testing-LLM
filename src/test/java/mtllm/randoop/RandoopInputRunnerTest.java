package mtllm.randoop;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RandoopInputRunnerTest {

    @Test
    void acceptsSuccessfulSuiteCompileGate() {
        assertDoesNotThrow(() -> RandoopInputRunner.requireSuccessfulSuiteCompile(0, ""));
    }

    @Test
    void rejectsFailedSuiteCompileGate() {
        IllegalStateException failure = assertThrows(
                IllegalStateException.class,
                () -> RandoopInputRunner.requireSuccessfulSuiteCompile(1, "generated test did not compile"));

        assertTrue(failure.getMessage().contains("Randoop JUnit suite compile-gate failed"));
        assertTrue(failure.getMessage().contains("generated test did not compile"));
    }
}
