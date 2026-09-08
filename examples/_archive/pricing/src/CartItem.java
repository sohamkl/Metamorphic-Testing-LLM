import java.math.BigDecimal;

/**
 * Represents one item in a shopping cart.
 *
 * <p>In simple terms, this stores a product id, quantity, and unit price.</p>
 */
public final class CartItem {
    private final String sku;
    private final int quantity;
    private final BigDecimal unitPrice;

    public CartItem(String sku, int quantity, BigDecimal unitPrice) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("sku must not be blank");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity must not be negative");
        }
        if (unitPrice == null) {
            throw new IllegalArgumentException("unitPrice must not be null");
        }
        if (unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("unitPrice must not be negative");
        }
        this.sku = sku;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getSku() {
        return sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public CartItem withQuantity(int newQuantity) {
        return new CartItem(sku, newQuantity, unitPrice);
    }
}
