import org.locationtech.spatial4j.distance.DistanceUtils;

/**
 * Thin adapter over {@link org.locationtech.spatial4j.distance.DistanceUtils#normLonDEG}
 * from Spatial4j, which folds an arbitrary longitude into the range -180..+180 degrees.
 *
 * <p>The library method takes a bare {@code double}; the pipeline drives a single
 * reference-type source input, so the value is wrapped in {@link LongitudeInput}. No
 * normalization logic lives here — this class only unwraps and delegates.</p>
 */
public final class LongitudeNormalizer {

    private LongitudeNormalizer() {
    }

    /**
     * Normalizes the input longitude into -180..+180 degrees.
     *
     * @param input the longitude to normalize, in degrees
     * @return the equivalent longitude within -180..+180
     */
    public static double normalize(LongitudeInput input) {
        return DistanceUtils.normLonDEG(input.longitudeDeg());
    }
}

/**
 * Source input for the longitude-normalization example: one longitude in degrees.
 */
final class LongitudeInput {
    private final double longitudeDeg;

    LongitudeInput(double longitudeDeg) {
        this.longitudeDeg = longitudeDeg;
    }

    double longitudeDeg() {
        return longitudeDeg;
    }
}
