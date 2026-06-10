import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a shopping cart being priced.
 *
 * <p>In simple terms, this groups the customer, cart items, and optional discount code.</p>
 */
public final class Cart {
    private final Customer customer;
    private final List<CartItem> items;
    private final DiscountCode discountCode;

    public Cart(Customer customer, List<CartItem> items, DiscountCode discountCode) {
        if (customer == null) {
            throw new IllegalArgumentException("customer must not be null");
        }
        if (items == null) {
            throw new IllegalArgumentException("items must not be null");
        }
        this.customer = customer;
        this.items = Collections.unmodifiableList(new ArrayList<>(items));
        this.discountCode = discountCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public DiscountCode getDiscountCode() {
        return discountCode;
    }
}
