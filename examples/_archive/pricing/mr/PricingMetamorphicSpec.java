import java.math.BigDecimal;
import java.util.List;

/**
 * Developer-owned metamorphic relation helper for PricingEngine.
 *
 * <p>In simple terms, the LLM can generate source carts, but this file owns how follow-up carts
 * are created and how outputs are compared.</p>
 */
public final class PricingMetamorphicSpec {
    private PricingMetamorphicSpec() {
    }

    public static Cart generateFollowUp(Cart source) {
        List<CartItem> doubledItems = source.getItems().stream()
                .map(item -> item.withQuantity(item.getQuantity() * 2))
                .toList();
        return new Cart(source.getCustomer(), doubledItems, source.getDiscountCode());
    }

    public static void assertRelation(BigDecimal sourceOutput, BigDecimal followUpOutput) {
        BigDecimal expectedFollowUpOutput = sourceOutput.multiply(BigDecimal.valueOf(2));
        if (expectedFollowUpOutput.compareTo(followUpOutput) != 0) {
            throw new AssertionError(
                    "Metamorphic relation violated: expected "
                            + expectedFollowUpOutput
                            + " but was "
                            + followUpOutput);
        }
    }
}
