public final class OrderUtil {
    private OrderUtil() {
    }

    public static double calculateTotal(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("order must not be null");
        }

        double total = 0.0;
        for (LineItem item : order.getItems()) {
            total += item.getQuantity() * item.getUnitPrice();
        }
        return total;
    }
}
