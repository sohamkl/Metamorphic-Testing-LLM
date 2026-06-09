import mtllm.spec.MetamorphicSpec;

import java.util.List;

/**
 * Developer-owned metamorphic relation for the OrderUtil SUT (Mode 4).
 *
 * <p>Implements MetamorphicSpec typed to the SUT's concrete input/output types so the
 * relation is type-safe regardless of what calculateTotal returns. The framework and
 * Randoop only generate source Order inputs -- they never touch this file.</p>
 *
 * <p>Use INSTANCE for instance-method calls, or the static assertRelation(double, double)
 * convenience for generated test code that uses primitive types.</p>
 */
public class OrderMetamorphicSpec implements MetamorphicSpec<Order, Double> {

    public static final OrderMetamorphicSpec INSTANCE = new OrderMetamorphicSpec();

    @Override
    public Order generateFollowUp(Order source) {
        List<LineItem> followUpItems = source.getItems().stream()
                .map(item -> item.withQuantity(item.getQuantity() * 2))
                .toList();
        return new Order(followUpItems);
    }

    @Override
    public void assertRelation(Double sourceOutput, Double followUpOutput) {
        double expected = sourceOutput * 2;
        if (Math.abs(expected - followUpOutput) > 0.001) {
            throw new AssertionError(
                    "Metamorphic relation violated: expected "
                            + expected + " but was " + followUpOutput);
        }
    }

    // Static convenience wrapper for generated test code that passes primitive doubles.
    // Different signature from assertRelation(Double, Double) so both coexist.
    public static void assertRelation(double sourceOutput, double followUpOutput) {
        INSTANCE.assertRelation(Double.valueOf(sourceOutput), Double.valueOf(followUpOutput));
    }
}
