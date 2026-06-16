public final class LineItem {
    private final String name;
    private final int quantity;
    private final double unitPrice;

    public LineItem(String name, int quantity, double unitPrice) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity must not be negative");
        }
        if (unitPrice < 0.0) {
            throw new IllegalArgumentException("unitPrice must not be negative");
        }
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public LineItem withQuantity(int newQuantity) {
        return new LineItem(name, newQuantity, unitPrice);
    }
}
