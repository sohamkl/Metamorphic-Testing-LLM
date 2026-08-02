package mtllm.llm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OpenAiClientTest {

    @Test
    void omitsTemperatureForGpt5Models() {
        String payload = OpenAiClient.buildPayload("gpt-5.6-sol", "Generate tests");

        assertFalse(payload.contains("\"temperature\""));
    }

    @Test
    void retainsConfiguredTemperatureForEarlierModels() {
        String payload = OpenAiClient.buildPayload("gpt-4.1", "Generate tests");

        assertTrue(payload.contains("\"temperature\":0.2"));
    }
}
