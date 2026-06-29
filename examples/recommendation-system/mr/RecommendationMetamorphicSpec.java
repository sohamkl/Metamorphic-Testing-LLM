import java.util.ArrayList;
import java.util.List;

/**
 * Developer-owned metamorphic relation for the recommendation example.
 *
 * <p>In simple terms, this defines the expected behaviour: if an eligible
 * product gets a better rating, its recommendation score should not go down.</p>
 */
public final class RecommendationMetamorphicSpec {
    private static final double RATING_INCREASE = 0.5;
    private static final double EPSILON = 0.0001;

    private RecommendationMetamorphicSpec() {
    }

    public static RecommendationRequest generateFollowUp(RecommendationRequest source) {
        String targetProductId = targetProductId(source);
        List<Product> followUpProducts = new ArrayList<>();
        for (Product product : source.getCandidateProducts()) {
            if (product.getProductId().equals(targetProductId)) {
                followUpProducts.add(product.withAverageRating(increaseRating(product.getAverageRating())));
            } else {
                followUpProducts.add(product);
            }
        }
        return new RecommendationRequest(source.getUser(), followUpProducts);
    }

    public static void assertRelation(
            List<ProductRecommendation> sourceOutput,
            List<ProductRecommendation> followUpOutput) {
        if (sourceOutput.isEmpty()) {
            return;
        }

        String targetProductId = sourceOutput.get(0).getProductId();
        ProductRecommendation sourceRecommendation = findByProductId(sourceOutput, targetProductId);
        ProductRecommendation followUpRecommendation = findByProductId(followUpOutput, targetProductId);

        if (sourceRecommendation == null || followUpRecommendation == null) {
            throw new AssertionError("The product with improved rating disappeared from recommendations.");
        }
        if (followUpRecommendation.getScore() + EPSILON < sourceRecommendation.getScore()) {
            throw new AssertionError("Improving a product rating should not decrease its recommendation score. "
                    + "Product " + targetProductId + " had source score "
                    + sourceRecommendation.getScore() + " and follow-up score "
                    + followUpRecommendation.getScore() + ".");
        }
    }

    private static String targetProductId(RecommendationRequest source) {
        List<ProductRecommendation> sourceOutput = RecommendationEngine.recommend(source);
        if (sourceOutput.isEmpty()) {
            return "";
        }
        return sourceOutput.get(0).getProductId();
    }

    private static double increaseRating(double rating) {
        return Math.min(5.0, rating + RATING_INCREASE);
    }

    private static ProductRecommendation findByProductId(
            List<ProductRecommendation> recommendations,
            String productId) {
        for (ProductRecommendation recommendation : recommendations) {
            if (recommendation.getProductId().equals(productId)) {
                return recommendation;
            }
        }
        return null;
    }
}
