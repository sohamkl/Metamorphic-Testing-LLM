package mtllm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

    @Test
    void identifiesMissingRandoopSeeds() {
        assertTrue(App.hasNoRandoopSeeds(null));
        assertTrue(App.hasNoRandoopSeeds(""));
        assertTrue(App.hasNoRandoopSeeds("  [ ]  "));
        assertTrue(App.hasNoRandoopSeeds("\n[]\n"));
    }

    @Test
    void acceptsNonEmptyRandoopSeeds() {
        assertFalse(App.hasNoRandoopSeeds("[{}]"));
    }
}
