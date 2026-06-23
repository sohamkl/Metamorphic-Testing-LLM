/**
 * Represents one recommended product in the output list.
 *
 * <p>In simple terms, this is what the engine returns: product id, score, rank,
 * and a short reason.</p>
 */
public final class ProductRecommendation {
    private final String productId;
    private final double score;
    private final int rank;
    private final String reason;

    public ProductRecommendation(String productId, double score, int rank, String reason) {
        this.productId = productId;
        this.score = score;
        this.rank = rank;
        this.reason = reason;
    }

    public String getProductId() {
        return productId;
    }

    public double getScore() {
        return score;
    }

    public int getRank() {
        return rank;
    }

    public String getReason() {
        return reason;
    }
}
