/**
 * Represents one item that may be recommended to a user.
 *
 * <p>In simple terms, this is a candidate product with a category, rating,
 * popularity score, and stock status.</p>
 */
public final class Product {
    private final String productId;
    private final String category;
    private final double averageRating;
    private final int popularity;
    private final boolean inStock;

    public Product(String productId, String category, double averageRating, int popularity, boolean inStock) {
        this.productId = productId;
        this.category = category;
        this.averageRating = averageRating;
        this.popularity = popularity;
        this.inStock = inStock;
    }

    public String getProductId() {
        return productId;
    }

    public String getCategory() {
        return category;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getPopularity() {
        return popularity;
    }

    public boolean isInStock() {
        return inStock;
    }

    public Product withAverageRating(double newAverageRating) {
        return new Product(productId, category, newAverageRating, popularity, inStock);
    }
}
