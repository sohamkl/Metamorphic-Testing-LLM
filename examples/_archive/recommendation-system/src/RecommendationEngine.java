import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Scores and ranks products for a user.
 *
 * <p>In simple terms, this is the system under test. It filters products the
 * user should not see, gives each remaining product a score, and returns the
 * products in recommendation order.</p>
 */
public final class RecommendationEngine {
    private RecommendationEngine() {
    }

    public static List<ProductRecommendation> recommend(RecommendationRequest request) {
        List<ScoredProduct> scoredProducts = new ArrayList<>();
        for (Product product : request.getCandidateProducts()) {
            if (!isEligible(request.getUser(), product)) {
                continue;
            }
            scoredProducts.add(new ScoredProduct(product, score(request.getUser(), product)));
        }

        scoredProducts.sort(Comparator
                .comparingDouble(ScoredProduct::score)
                .reversed()
                .thenComparing(scored -> scored.product().getProductId()));

        List<ProductRecommendation> recommendations = new ArrayList<>();
        for (int index = 0; index < scoredProducts.size(); index++) {
            ScoredProduct scored = scoredProducts.get(index);
            recommendations.add(new ProductRecommendation(
                    scored.product().getProductId(),
                    round(scored.score()),
                    index + 1,
                    reasonFor(scored.product())));
        }
        return recommendations;
    }

    private static boolean isEligible(UserProfile user, Product product) {
        return product.isInStock()
                && !user.getBlockedCategories().contains(product.getCategory())
                && !user.getPurchasedProductIds().contains(product.getProductId());
    }

    private static double score(UserProfile user, Product product) {
        double preferenceWeight = preferenceWeight(user, product.getCategory());
        double score = preferenceWeight * 50.0;

        // Intentional realistic bug for the demo: rating is accidentally penalised
        // instead of rewarded.
        score -= product.getAverageRating() * 10.0;

        score += product.getPopularity() * 0.2;
        return score;
    }

    private static double preferenceWeight(UserProfile user, String category) {
        for (CategoryPreference preference : user.getPreferences()) {
            if (preference.getCategory().equals(category)) {
                return preference.getWeight();
            }
        }
        return 0.1;
    }

    private static String reasonFor(Product product) {
        return "category=" + product.getCategory();
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private record ScoredProduct(Product product, double score) {
    }
}
