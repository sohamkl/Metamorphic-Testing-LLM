import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GeneratedTest {
    private static final Random random = new Random();

    public static void main(String[] args) {
        // JSON array to collect the test pairs
        StringBuilder jsonArray = new StringBuilder("[");
        
        // Generate edge-case test pairs
        jsonArray.append(generateEmptyArrayTest());
        jsonArray.append(generateSingleElementArrayTest());
        jsonArray.append(generateDuplicateElementsTest());
        jsonArray.append(generateNegativeNumbersTest());
        jsonArray.append(generateMixedMagnitudesTest());
        
        // Remove the last comma and close the JSON array
        if (jsonArray.charAt(jsonArray.length() - 1) == ',') {
            jsonArray.setLength(jsonArray.length() - 1);
        }
        jsonArray.append("]");
        
        // Print the final JSON array
        System.out.println(jsonArray.toString());
    }
    
    private static String generateEmptyArrayTest() {
        int[] source = {};
        return String.format("{ \"source\": %s, \"followUp\": %s },", 
                             Arrays.toString(source), 
                             Arrays.toString(source));
    }
    
    private static String generateSingleElementArrayTest() {
        int[] source = { random.nextInt(100) };
        return String.format("{ \"source\": %s, \"followUp\": %s },", 
                             Arrays.toString(source), 
                             Arrays.toString(source));
    }
    
    private static String generateDuplicateElementsTest() {
        int[] source = { 1, 2, 2, 3, 4, 4, 4, 5 };
        return String.format("{ \"source\": %s, \"followUp\": %s },", 
                             Arrays.toString(source), 
                             Arrays.toString(source));
    }
    
    private static String generateNegativeNumbersTest() {
        int[] source = { -10, -2, -3, -1, -5, -7 };
        return String.format("{ \"source\": %s, \"followUp\": %s },", 
                             Arrays.toString(source), 
                             Arrays.toString(source));
    }

    private static String generateMixedMagnitudesTest() {
        Set<Integer> uniqueValues = new HashSet<>();
        while (uniqueValues.size() < 10) {
            uniqueValues.add(random.nextInt(21) - 10); // Generates numbers between -10 and 10
        }
        int[] source = uniqueValues.stream().mapToInt(Integer::intValue).toArray();
        return String.format("{ \"source\": %s, \"followUp\": %s },", 
                             Arrays.toString(source), 
                             Arrays.toString(source));
    }
}