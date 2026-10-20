package mtllm.randoop;

import java.util.List;

/**
 * Renders an object-MT JUnit 5 test class from Randoop-harvested construction code (approach C).
 *
 * <p>Each case becomes one {@code @Test} that:</p>
 * <ol>
 *   <li>rebuilds the source object using the exact construction code Randoop used
 *       ({@code new Order((List&lt;LineItem&gt;) lineItemList1)} etc.), ending at the statement that
 *       declares the harvested object's variable;</li>
 *   <li>runs the SUT on it, applies the developer follow-up transform, runs the SUT again;</li>
 *   <li>invokes the developer assertion -- which throws {@code AssertionError} when the metamorphic
 *       relation is violated, so the JUnit method fails exactly on a bug-revealing input.</li>
 * </ol>
 *
 * <p>Locals are declared with {@code var} so the emitter never has to render generic or array
 * return types; the constructed object's variable keeps the name Randoop gave it (e.g. {@code order3}),
 * which never collides with the fixed {@code sourceOutput}/{@code followUp}/{@code followUpOutput}
 * names because Randoop names variables as {@code <type><index>}.</p>
 *
 * <p>The SUT/follow-up/assert callees are passed in pre-rendered (e.g. {@code "OrderUtil.calculateTotal"}
 * for a static SUT, {@code "OrderMetamorphicSpec.generateFollowUp"} for the developer spec), so this
 * class stays free of reflection and config knowledge.</p>
 */
public final class RandoopJUnitEmitter {

    private RandoopJUnitEmitter() {
    }

    /**
     * One emitted test: the multi-line Randoop construction code plus the name of the local variable
     * within it that holds the harvested source object.
     */
    public record Case(String constructionCode, String targetVar) {
    }

    /**
     * Render a complete JUnit 5 class. Randoop construction statements and the supplied SUT/MR
     * callees use fully qualified class names, so the generated class remains valid whether the
     * tested classes use the default package or a named package.
     *
     * @param className      the class name (e.g. {@code GeneratedOrderMetamorphicPassingTest})
     * @param cases          one entry per distinct harvested shape
     * @param sutCallee      pre-rendered SUT call target (no parentheses), e.g. {@code OrderUtil.calculateTotal}
     * @param followUpCallee pre-rendered developer follow-up target, e.g. {@code OrderMetamorphicSpec.generateFollowUp}
     * @param assertCallee   pre-rendered developer assertion target, e.g. {@code OrderMetamorphicSpec.assertRelation}
     */
    public static String renderClass(String className, List<Case> cases,
                                     String sutCallee, String followUpCallee, String assertCallee) {
        StringBuilder out = new StringBuilder();
        out.append("import org.junit.jupiter.api.Test;\n\n");
        out.append("public class ").append(className).append(" {\n");

        int index = 1;
        for (Case c : cases) {
            String methodName = String.format("testShape%03d", index++);
            out.append("\n    @Test\n");
            out.append("    public void ").append(methodName).append("() {\n");
            for (String line : c.constructionCode().split("\n", -1)) {
                if (!line.isBlank()) {
                    out.append("        ").append(line.stripTrailing()).append('\n');
                }
            }
            String v = c.targetVar();
            out.append("        var followUp = ").append(followUpCallee).append('(').append(v).append(");\n");
            out.append("        var sourceOutput = ").append(sutCallee).append('(').append(v).append(");\n");
            out.append("        var followUpOutput = ").append(sutCallee).append("(followUp);\n");
            out.append("        ").append(assertCallee).append("(sourceOutput, followUpOutput);\n");
            out.append("    }\n");
        }

        out.append("}\n");
        return out.toString();
    }
}
