import java.util.List;

/**
 * Developer-owned metamorphic relation for the OrderUtil SUT.
 *
 * <p>Self-contained: default package, static methods, no framework import — so it compiles
 * standalone under Soham's pipeline (which javac's the dev-MR file without {@code mtllm} on the
 * classpath), matching PricingMetamorphicSpec / MatrixRankMetamorphicSpec. The Randoop harness
 * now also calls these by name (method references / reflection) rather than via an interface,
 * so this single self-contained spec satisfies both consumers.</p>
 *
 * <p>MR: doubling the quantity of every line item should double the order total.</p>
 */
public final class OrderMetamorphicSpec {

    private OrderMetamorphicSpec() {
    }

    public static Order generateFollowUp(Order source) {
        List<LineItem> followUpItems = source.getItems().stream()
                .map(item -> item.withQuantity(item.getQuantity() * 2))
                .toList();
        return new Order(followUpItems);
    }

    public static void assertRelation(double sourceOutput, double followUpOutput) {
        double expected = sourceOutput * 2;
        if (Math.abs(expected - followUpOutput) > 0.001) {
            throw new AssertionError(
                    "Metamorphic relation violated: expected "
                            + expected + " but was " + followUpOutput);
        }
    }
}
