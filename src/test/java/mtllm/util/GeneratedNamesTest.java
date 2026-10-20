package mtllm.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneratedNamesTest {
    @Test
    void removesOnlyKnownGeneratedSuffixes() {
        assertEquals("GeneratedPricing", GeneratedNames.baseName("GeneratedPricingData"));
        assertEquals("GeneratedPricing", GeneratedNames.baseName("GeneratedPricingTest"));
        assertEquals("GeneratedPricing", GeneratedNames.baseName("GeneratedPricing"));
    }
}
