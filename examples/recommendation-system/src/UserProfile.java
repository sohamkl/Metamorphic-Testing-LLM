import java.util.List;
import java.util.Set;

/**
 * Stores the recommendation-relevant data for one user.
 *
 * <p>In simple terms, this says what categories the user likes, what categories
 * they blocked, and which products they already bought.</p>
 */
public final class UserProfile {
    private final String userId;
    private final List<CategoryPreference> preferences;
    private final Set<String> blockedCategories;
    private final Set<String> purchasedProductIds;

    public UserProfile(
            String userId,
            List<CategoryPreference> preferences,
            Set<String> blockedCategories,
            Set<String> purchasedProductIds) {
        this.userId = userId;
        this.preferences = List.copyOf(preferences);
        this.blockedCategories = Set.copyOf(blockedCategories);
        this.purchasedProductIds = Set.copyOf(purchasedProductIds);
    }

    public String getUserId() {
        return userId;
    }

    public List<CategoryPreference> getPreferences() {
        return preferences;
    }

    public Set<String> getBlockedCategories() {
        return blockedCategories;
    }

    public Set<String> getPurchasedProductIds() {
        return purchasedProductIds;
    }
}
