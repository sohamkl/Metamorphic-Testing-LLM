import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Calculates a discounted cart subtotal.
 *
 * <p>In simple terms, this is the shopping-cart SUT. It intentionally contains a realistic bug:
 * GOLD customer pricing mutates the shared discount code while calculating.</p>
 */
public final class PricingEngine {
    private PricingEngine() {
    }

    public static BigDecimal calculateDiscountedSubtotal(Cart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("cart must not be null");
        }

        BigDecimal subtotal = BigDecimal.ZERO;
        for (CartItem item : cart.getItems()) {
            subtotal = subtotal.add(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        BigDecimal discountPercent = BigDecimal.ZERO;
        DiscountCode discountCode = cart.getDiscountCode();
        if (discountCode != null) {
            discountPercent = discountCode.getPercentageOff();
            if (cart.getCustomer().getTier() == CustomerTier.GOLD) {
                discountCode.setPercentageOff(discountCode.getPercentageOff().add(BigDecimal.TEN));
                discountPercent = discountCode.getPercentageOff();
            }
        }

        BigDecimal multiplier = BigDecimal.ONE.subtract(
                discountPercent.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
        return subtotal.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
    }
}
