import java.util.List;

/**
 * Developer-owned metamorphic relation helper for OrderUtil examples.
 *
 * <p>In simple terms, this file keeps the MR transformation and assertion under developer
 * control while the LLM only generates source-input JUnit tests that call these methods.</p>
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
        double expectedFollowUpOutput = sourceOutput * 2;
        if (Math.abs(expectedFollowUpOutput - followUpOutput) > 0.001) {
            throw new AssertionError(
                    "Metamorphic relation violated: expected "
                            + expectedFollowUpOutput
                            + " but was "
                            + followUpOutput);
        }
    }
}
