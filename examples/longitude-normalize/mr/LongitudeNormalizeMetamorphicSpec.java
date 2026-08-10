/**
 * Developer-owned metamorphic relation for {@code DistanceUtils.normLonDEG} (Spatial4j).
 *
 * <p><b>Relation (periodicity):</b> longitude is periodic with a period of 360 degrees, so
 * adding one full revolution to a longitude must normalize to the same meridian.</p>
 *
 * <p><b>Antimeridian corner case.</b> -180 and +180 name the <i>same</i> meridian, and the
 * library returns whichever of the two it was handed (its fast path returns any input already
 * within -180..180 unchanged). So {@code normLonDEG(-180) == -180} while
 * {@code normLonDEG(-180 + 360) == +180}: the same meridian under two names, not a defect.
 * The assertion below therefore treats the two poles of the antimeridian as equal, and is
 * exact everywhere else.</p>
 */
public final class LongitudeNormalizeMetamorphicSpec {

    /** One full revolution of longitude, in degrees. */
    private static final double FULL_REVOLUTION_DEG = 360.0;

    /** Tolerance for double rounding introduced by the shift. */
    private static final double TOLERANCE_DEG = 1e-9;

    private LongitudeNormalizeMetamorphicSpec() {
    }

    public static LongitudeInput generateFollowUp(LongitudeInput source) {
        return new LongitudeInput(source.longitudeDeg() + FULL_REVOLUTION_DEG);
    }

    public static void assertRelation(double sourceOutput, double followUpOutput) {
        if (isAntimeridian(sourceOutput) && isAntimeridian(followUpOutput)) {
            return;
        }
        if (Math.abs(sourceOutput - followUpOutput) > TOLERANCE_DEG) {
            throw new AssertionError("Adding " + FULL_REVOLUTION_DEG
                    + " degrees changed the normalized longitude from " + sourceOutput
                    + " to " + followUpOutput + ".");
        }
    }

    private static boolean isAntimeridian(double normalizedLongitudeDeg) {
        return Math.abs(Math.abs(normalizedLongitudeDeg) - 180.0) <= TOLERANCE_DEG;
    }
}
