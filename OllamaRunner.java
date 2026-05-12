import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OllamaRunner {
    public static void main(String[] args) throws Exception {
        // --- STEP 1: Read the configuration from prompt.txt ---
        List<String> lines = Files.readAllLines(Path.of("prompt.txt"), StandardCharsets.UTF_8);
        String sut = "Unknown SUT";
        String mr = "Unknown MR";
        String count = "5";
        String dataType = "int[]";

        for (String line : lines) {
            if (line.startsWith("SUT:")) sut = line.substring(4).trim();
            else if (line.startsWith("MR:")) mr = line.substring(3).trim();
            else if (line.startsWith("Count:")) count = line.substring(6).trim();
            else if (line.startsWith("DataType:")) dataType = line.substring(9).trim();
        }

        // --- STEP 2: Build the exact prompt for the LLM ---
        String constructedPrompt = 
            "Target: " + sut + "\n" +
            "Metamorphic Relation: " + mr + "\n" +
            "Task: Generate exactly " + count + " edge-case test pairs.\n" +
            "Constraint: Output ONLY valid JSON in this exact schema: [ { \"source\": [1, 2], \"followUp\": [2, 1] } ] " +
            "where values are of type " + dataType + ". No markdown, no conversational text, no Java code.";

        System.out.println("Sending prompt to Ollama...");

        // --- STEP 3: Send HTTP POST Request to Ollama ---
        // Escape the prompt to safely fit inside a JSON string
        String escapedPrompt = constructedPrompt.replace("\\", "\\\\")
                                                .replace("\"", "\\\"")
                                                .replace("\n", "\\n")
                                                .replace("\r", "\\r")
                                                .replace("\t", "\\t");

        String payload = "{\"model\": \"llama3\", \"prompt\": \"" + escapedPrompt + "\", \"stream\": false}";

        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:11434/api/generate"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        if (res.statusCode() != 200) {
            throw new RuntimeException("HTTP Error: " + res.statusCode() + " - " + res.body());
        }

        // --- STEP 4: Extract the generated text from Ollama's JSON response ---
        Pattern responsePattern = Pattern.compile("\"response\"\\s*:\\s*\"(.*?)\"(?:,|})", Pattern.DOTALL);
        Matcher responseMatcher = responsePattern.matcher(res.body());
        
        if (!responseMatcher.find()) {
            throw new RuntimeException("Could not find 'response' field in Ollama output.");
        }
        
        String rawResponse = responseMatcher.group(1);
        
        // Un-escape the text back into readable JSON format
        String extractedJson = rawResponse.replace("\\n", "\n")
                                          .replace("\\\"", "\"")
                                          .replace("\\t", "\t")
                                          .replace("\\r", "\r")
                                          .replace("\\\\", "\\");

        Files.writeString(Path.of("out.txt"), extractedJson, StandardCharsets.UTF_8);
        System.out.println("Wrote extracted LLM response to out.txt\n");

        // --- STEP 5: Parse the test arrays and run them ---
        System.out.println("Running Metamorphic Tests...");
        
        Pattern arrayPattern = Pattern.compile("\"source\"\\s*:\\s*\\[(.*?)\\]\\s*,\\s*\"followUp\"\\s*:\\s*\\[(.*?)\\]", Pattern.DOTALL);
        Matcher arrayMatcher = arrayPattern.matcher(extractedJson);

        int passed = 0;
        int total = 0;

        while (arrayMatcher.find()) {
            total++;
            int[] source = parseArray(arrayMatcher.group(1));
            int[] followUp = parseArray(arrayMatcher.group(2));

            // Execute the actual sorting method against both arrays
            int[] sortedSource = SortUtil.sortArray(source);
            int[] sortedFollowUp = SortUtil.sortArray(followUp);

            boolean isPass = Arrays.equals(sortedSource, sortedFollowUp);
            
            if (isPass) {
                passed++;
                System.out.println("[PASS] Source: " + Arrays.toString(source) + " -> FollowUp: " + Arrays.toString(followUp));
            } else {
                System.out.println("[FAIL] Source: " + Arrays.toString(source) + " -> FollowUp: " + Arrays.toString(followUp));
                System.out.println("       Sorted Source: " + Arrays.toString(sortedSource));
                System.out.println("       Sorted FollowUp: " + Arrays.toString(sortedFollowUp));
            }
        }

        // --- STEP 6: Report final results ---
        System.out.println("\n--- Test Summary ---");
        if (total == 0) {
            System.out.println("Warning: No tests were generated/parsed successfully.");
        } else {
            System.out.println("Total tests parsed: " + total);
            System.out.println("Passed: " + passed);
            System.out.println("Failed: " + (total - passed));
        }
    }

    // Helper method to convert comma-separated string into int[] array
    private static int[] parseArray(String innerContent) {
        if (innerContent == null || innerContent.trim().isEmpty()) {
            return new int[0];
        }
        String[] parts = innerContent.split(",");
        List<Integer> list = new ArrayList<>();
        for (String p : parts) {
            String trimmed = p.trim();
            if (!trimmed.isEmpty()) {
                list.add(Integer.parseInt(trimmed));
            }
        }
        
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
