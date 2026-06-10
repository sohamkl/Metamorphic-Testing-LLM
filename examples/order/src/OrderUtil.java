public final class OrderUtil {
    private OrderUtil() {
    }

    public static double calculateTotal(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("order must not be null");
        }

        double total = 0.0;
        for (LineItem item : order.getItems()) {
            // Deliberate bug for testing failed-case filtering: quantities above 5 are capped.
            int billedQuantity = Math.min(item.getQuantity(), 5);
            total += billedQuantity * item.getUnitPrice();
        }
        return total;
    }
}
