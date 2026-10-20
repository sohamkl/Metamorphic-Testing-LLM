/**
 * MR for DistanceUtils.normLonDEG (Spatial4j).
 *
 * Longitude wraps every 360 degrees, so adding 360 to a longitude should land back on the
 * same spot on the globe.
 *
 * Except -180 and 180 are the same spot written as two different numbers, and normLonDEG
 * doesn't merge them into one. So we treat -180 and 180 as equal, and check everything else
 * normally.
 */
public final class LongitudeNormalizeMetamorphicSpec {

    /** One full revolution of longitude in degrees */
    private static final double FULL_REVOLUTION_DEG = 360.0;

    /** Tolerance for the double rounding introduced by the shift */
    private static final double TOLERANCE_DEG = 1e-9;

    private LongitudeNormalizeMetamorphicSpec() {
    }

    public static double generateFollowUp(double longitudeDeg) {
        return longitudeDeg + FULL_REVOLUTION_DEG;
    }

    public static void assertRelation(double sourceOutput, double followUpOutput) {
        // -180 and +180 are the same meridian so that is not a violation.
        boolean bothAreAntimeridian = isAntimeridian(sourceOutput) && isAntimeridian(followUpOutput);
        if (bothAreAntimeridian) {
            return;
        }

        double difference = Math.abs(sourceOutput - followUpOutput);
        if (difference > TOLERANCE_DEG) {
            throw new AssertionError("Adding " + FULL_REVOLUTION_DEG
                + " degrees changed the normalized longitude from " + sourceOutput
                + " to " + followUpOutput + ".");
        }
    }


    private static boolean isAntimeridian(double normalizedLongitudeDeg) {
        double distanceFromAntimeridian = Math.abs(Math.abs(normalizedLongitudeDeg) - 180.0);
        if (distanceFromAntimeridian <= TOLERANCE_DEG) {
            return true;
        }
        return false;
    }
}
