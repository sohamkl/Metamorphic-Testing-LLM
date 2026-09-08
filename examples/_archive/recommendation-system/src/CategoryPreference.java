/**
 * Stores how strongly a user prefers a product category.
 *
 * <p>In simple terms, this says "this user likes BOOKS with weight 0.8" or
 * "this user only weakly likes SPORTS with weight 0.2".</p>
 */
public final class CategoryPreference {
    private final String category;
    private final double weight;

    public CategoryPreference(String category, double weight) {
        this.category = category;
        this.weight = weight;
    }

    public String getCategory() {
        return category;
    }

    public double getWeight() {
        return weight;
    }
}
