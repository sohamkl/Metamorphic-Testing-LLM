package mtllm.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilTokenUsageTest {

    @Test
    void readsTheUsageSplitFromAChatCompletionResponse() {
        String body = """
                {
                  "choices": [{"message": {"role": "assistant", "content": "hi"}}],
                  "usage": {
                    "prompt_tokens": 12043,
                    "completion_tokens": 3891,
                    "total_tokens": 15934
                  }
                }
                """;

        assertEquals(12043, JsonUtil.extractUsageField(body, "prompt_tokens"));
        assertEquals(3891, JsonUtil.extractUsageField(body, "completion_tokens"));
        assertEquals(15934, JsonUtil.extractUsageField(body, "total_tokens"));
    }

    @Test
    void readsTheUsageSplitWhenTheResponseIsMinified() {
        String body = "{\"choices\":[{\"message\":{\"content\":\"hi\"}}],"
                + "\"usage\":{\"prompt_tokens\":10,\"completion_tokens\":5,\"total_tokens\":15}}";

        assertEquals(10, JsonUtil.extractUsageField(body, "prompt_tokens"));
        assertEquals(5, JsonUtil.extractUsageField(body, "completion_tokens"));
        assertEquals(15, JsonUtil.extractUsageField(body, "total_tokens"));
    }

    @Test
    void readsCompletionTokensRatherThanTheNestedReasoningCount() {
        String body = "{\"usage\":{\"prompt_tokens\":10,\"completion_tokens\":900,"
                + "\"total_tokens\":910,\"completion_tokens_details\":{\"reasoning_tokens\":880}}}";

        assertEquals(900, JsonUtil.extractUsageField(body, "completion_tokens"));
        assertEquals(910, JsonUtil.extractUsageField(body, "total_tokens"));
    }

    @Test
    void ignoresATokenCountThatSitsOutsideTheUsageObject() {
        String body = "{\"prompt_tokens\":999,\"usage\":{\"prompt_tokens\":10,\"total_tokens\":15}}";

        assertEquals(10, JsonUtil.extractUsageField(body, "prompt_tokens"));
    }

    @Test
    void returnsZeroWhenUsageIsAbsentOrUnparseable() {
        assertEquals(0, JsonUtil.extractUsageField(null, "total_tokens"));
        assertEquals(0, JsonUtil.extractUsageField("", "total_tokens"));
        assertEquals(0, JsonUtil.extractUsageField("{\"choices\":[]}", "total_tokens"));
        assertEquals(0, JsonUtil.extractUsageField("{\"usage\":{\"prompt_tokens\":10}}", "total_tokens"));
        assertEquals(0, JsonUtil.extractUsageField("{\"usage\":{\"total_tokens\":\"lots\"}}", "total_tokens"));
        assertEquals(0, JsonUtil.extractUsageField("{\"usage\":{\"total_tokens\":15}}", null));
    }
}
