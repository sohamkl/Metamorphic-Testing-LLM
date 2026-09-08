/**
 * Represents the customer attached to a shopping cart.
 *
 * <p>In simple terms, this class stores the customer's loyalty tier.</p>
 */
public final class Customer {
    private final CustomerTier tier;

    public Customer(CustomerTier tier) {
        if (tier == null) {
            throw new IllegalArgumentException("tier must not be null");
        }
        this.tier = tier;
    }

    public CustomerTier getTier() {
        return tier;
    }
}
