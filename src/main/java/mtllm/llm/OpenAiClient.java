package mtllm.llm;

import mtllm.util.CodeFence;
import mtllm.util.JsonUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * LLM client implementation that calls OpenAI's chat completions API.
 *
 * <p>In simple terms, this class sends the generated prompt to OpenAI and extracts the Java code
 * from the API response.</p>
 */
public final class OpenAiClient implements LlmClient {
    private final HttpClient httpClient;
    private final String apiKey;
    private final String model;
    private final String baseUrl;

    public OpenAiClient(String apiKey, String model, String baseUrl) {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .build();
        this.apiKey = apiKey;
        this.model = model;
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
    }

    @Override
    public String complete(String prompt) throws Exception {
        String payload = "{"
                + "\"model\":" + JsonUtil.quote(model) + ","
                + "\"messages\":[{\"role\":\"user\",\"content\":" + JsonUtil.quote(prompt) + "}],"
                + "\"temperature\":0.2"
                + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/chat/completions"))
                .timeout(Duration.ofMinutes(5))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IllegalStateException("OpenAI HTTP error " + response.statusCode() + ": " + response.body());
        }

        String content = JsonUtil.extractOpenAiContent(response.body());
        if (content == null) {
            throw new IllegalStateException("Could not extract response content from OpenAI output.");
        }
        return CodeFence.strip(content).trim();
    }
}
