import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.ExpressionStmt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Removes non-metamorphic oracles from a generated passing JUnit suite, so a run is measured on its
 * metamorphic relation only.
 *
 * <p>Rules:
 * <ul>
 *   <li>In a {@code @Test} method, a JUnit assertion is kept when it references a follow-up value or calls a
 *       method declared in the test class (an inlined MR, or the MR helper). Any other assertion checks the
 *       source output against a fixed expectation and is removed.</li>
 *   <li>In a helper method, a JUnit assertion is removed when it compares against an {@code expectedSource*}
 *       value, i.e. a hard-coded expected source output passed in by the test.</li>
 * </ul>
 * Every assertion decided on inside a {@code @Test} method is logged as KEPT or REMOVED; removals inside
 * helpers are logged as REMOVED. Per method, removals are logged before kept assertions.
 *
 * <p>Usage: {@code java -cp javaparser-core.jar OracleStripper.java <in PassingTest.java> <out .java> <out removed.txt>}
 */
public final class OracleStripper {

    private static final Set<String> TEST_ANNOTATIONS = Set.of("Test", "ParameterizedTest", "RepeatedTest");

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("usage: OracleStripper <input .java> <output .java> <removed.txt>");
            System.exit(2);
        }
        CompilationUnit cu = StaticJavaParser.parse(Path.of(args[0]));
        List<String> log = strip(cu);
        Files.writeString(Path.of(args[1]), cu.toString(), StandardCharsets.UTF_8);
        Files.write(Path.of(args[2]), log, StandardCharsets.UTF_8);
        System.out.println(log.stream().filter(l -> l.startsWith("REMOVED")).count() + " assertions removed");
    }

    static List<String> strip(CompilationUnit cu) {
        Set<String> localMethods = new HashSet<>();
        cu.findAll(MethodDeclaration.class).forEach(m -> localMethods.add(m.getNameAsString()));

        List<String> log = new ArrayList<>();
        for (MethodDeclaration method : cu.findAll(MethodDeclaration.class)) {
            boolean isTest = method.getAnnotations().stream()
                    .anyMatch(a -> TEST_ANNOTATIONS.contains(a.getName().getIdentifier()));
            List<ExpressionStmt> toRemove = new ArrayList<>();
            List<String> kept = new ArrayList<>();
            for (ExpressionStmt stmt : method.findAll(ExpressionStmt.class)) {
                if (!isJUnitAssertion(stmt)) {
                    continue;
                }
                String line = method.getNameAsString() + "\t" + stmt.toString().strip();
                if (isTest) {
                    if (referencesFollowUp(stmt) || callsLocalMethod(stmt, localMethods)) {
                        kept.add("KEPT\t" + line);
                    } else {
                        log.add("REMOVED\t" + line);
                        toRemove.add(stmt);
                    }
                } else if (referencesExpectedSource(stmt)) {
                    log.add("REMOVED\t" + line);
                    toRemove.add(stmt);
                }
            }
            log.addAll(kept);
            toRemove.forEach(Node::remove);
        }
        return log;
    }

    private static boolean isJUnitAssertion(ExpressionStmt stmt) {
        if (!stmt.getExpression().isMethodCallExpr()) {
            return false;
        }
        MethodCallExpr call = stmt.getExpression().asMethodCallExpr();
        String name = call.getNameAsString();
        if (!name.startsWith("assert") && !name.equals("fail")) {
            return false;
        }
        if (call.getScope().isPresent()) {
            return call.getScope().get().toString().endsWith("Assertions");
        }
        // An unscoped call is a JUnit static import unless the test class declares a helper with that name
        // (e.g. assertMetamorphicRelation), which is the MR itself and never an assertion to strip.
        return stmt.findCompilationUnit()
                .map(cu -> cu.findAll(MethodDeclaration.class).stream().noneMatch(m -> m.getNameAsString().equals(name)))
                .orElse(true);
    }

    private static boolean referencesFollowUp(Node node) {
        return node.findAll(SimpleName.class).stream()
                .anyMatch(n -> n.getIdentifier().toLowerCase(Locale.ROOT).contains("followup"));
    }

    private static boolean callsLocalMethod(ExpressionStmt stmt, Set<String> localMethods) {
        MethodCallExpr outer = stmt.getExpression().asMethodCallExpr();
        return outer.getArguments().stream()
                .flatMap(arg -> arg.findAll(MethodCallExpr.class).stream())
                .anyMatch(c -> c.getScope().isEmpty() && localMethods.contains(c.getNameAsString()));
    }

    private static boolean referencesExpectedSource(Node node) {
        return node.findAll(NameExpr.class).stream()
                .anyMatch(n -> n.getNameAsString().startsWith("expectedSource"));
    }
}
