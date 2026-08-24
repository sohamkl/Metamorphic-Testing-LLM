package mtllm.runner;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeneratedJUnitScenarioMergerTest {

    private static final String EXISTING = """
            import org.junit.jupiter.api.Test;
            import static org.junit.jupiter.api.Assertions.assertEquals;
            public class GeneratedExampleTest {
                @Test void normalPathOriginal() { verify(7); }
                private void verify(int source) { assertEquals(source, source); }
            }
            """;

    @Test
    void addsOnlyRequestedScenarioTestsAndPreservesExistingMembers() {
        String addition = """
                import org.junit.jupiter.api.Test;
                import java.util.List;
                public class GeneratedExampleTest {
                    @Test void thresholdBoundaryBelow() { verify(9); }
                    @Test void thresholdBoundaryExact() { verify(10); }
                    @Test void unrelatedExtra() { verify(99); }
                    private void verify(int source) { throw new AssertionError(); }
                    private int newFixture() { return 10; }
                }
                """;

        String merged = GeneratedJUnitScenarioMerger.merge(
                EXISTING, addition, "GeneratedExampleTest", Map.of("THRESHOLD_BOUNDARY", 2));

        var unit = StaticJavaParser.parse(merged);
        var type = unit.getClassByName("GeneratedExampleTest").orElseThrow();
        assertEquals(3, type.getMethods().stream().filter(GeneratedJUnitScenarioMergerTest::isTest).count());
        assertTrue(type.getMethodsByName("normalPathOriginal").get(0).toString().contains("verify(7)"));
        assertEquals(1, type.getMethodsByName("verify").size());
        assertEquals(1, type.getMethodsByName("newFixture").size());
        assertTrue(unit.getImports().stream().anyMatch(value -> value.getNameAsString().equals("java.util.List")));
        assertTrue(type.getMethodsByName("unrelatedExtra").isEmpty());
    }

    @Test
    void rejectsAnAdditionThatDoesNotSupplyAllMissingTests() {
        String addition = """
                import org.junit.jupiter.api.Test;
                public class GeneratedExampleTest {
                    @Test void thresholdBoundaryOnlyOne() { verify(9); }
                }
                """;

        IllegalArgumentException failure = assertThrows(IllegalArgumentException.class,
                () -> GeneratedJUnitScenarioMerger.merge(
                        EXISTING, addition, "GeneratedExampleTest", Map.of("THRESHOLD_BOUNDARY", 2)));

        assertTrue(failure.getMessage().contains("supplied 1 of 2"));
    }

    private static boolean isTest(MethodDeclaration method) {
        return method.getAnnotations().stream()
                .anyMatch(annotation -> annotation.getNameAsString().equals("Test"));
    }
}
