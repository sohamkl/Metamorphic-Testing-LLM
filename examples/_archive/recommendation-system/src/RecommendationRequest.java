import java.util.List;

/**
 * The full input passed into the recommendation engine.
 *
 * <p>In simple terms, each test case creates one of these. It contains the user
 * plus all products the engine may choose from.</p>
 */
public final class RecommendationRequest {
    private final UserProfile user;
    private final List<Product> candidateProducts;

    public RecommendationRequest(UserProfile user, List<Product> candidateProducts) {
        this.user = user;
        this.candidateProducts = List.copyOf(candidateProducts);
    }

    public UserProfile getUser() {
        return user;
    }

    public List<Product> getCandidateProducts() {
        return candidateProducts;
    }
}
