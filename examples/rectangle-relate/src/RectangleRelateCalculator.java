import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

/**
 * Thin adapter over {@link org.locationtech.spatial4j.shape.impl.RectangleImpl#relate}
 * from Spatial4j, which classifies how two rectangles sit relative to each other
 * (CONTAINS, WITHIN, INTERSECTS or DISJOINT).
 *
 * <p>{@code relate} is an instance method taking one argument, so receiver and argument are
 * wrapped together in {@link RectanglePairInput}. The rectangles are carried as bare
 * coordinates rather than as live {@code Shape} objects so the executed-data JSON stays flat
 * (a live shape holds a reference to its {@link SpatialContext}, which serializes into a
 * large and irrelevant object graph). No geometry lives here — this class builds the two
 * rectangles and delegates.</p>
 *
 * <p>A geodetic context is used, so a rectangle whose {@code minX} exceeds its {@code maxX}
 * legitimately denotes one that crosses the antimeridian.</p>
 */
public final class RectangleRelateCalculator {

    private RectangleRelateCalculator() {
    }

    /** The shared geodetic context both rectangles are built in. */
    public static SpatialContext context() {
        return SpatialContext.GEO;
    }

    /**
     * Classifies how the first rectangle relates to the second.
     *
     * @param input the two rectangles, as coordinates
     * @return the spatial relation, plus whether the two rectangles were equal
     */
    public static RelateOutcome relate(RectanglePairInput input) {
        RectangleImpl first = new RectangleImpl(
                input.firstMinX(), input.firstMaxX(), input.firstMinY(), input.firstMaxY(), context());
        RectangleImpl second = new RectangleImpl(
                input.secondMinX(), input.secondMaxX(), input.secondMinY(), input.secondMaxY(), context());
        return new RelateOutcome(first.relate(second), first.equals(second));
    }
}

/**
 * Source input for the rectangle-relate example: two rectangles as coordinates.
 *
 * <p>Longitudes are -180..180 and latitudes -90..90. {@code minY} must not exceed
 * {@code maxY}; {@code minX} may exceed {@code maxX}, which denotes a rectangle crossing
 * the antimeridian.</p>
 */
final class RectanglePairInput {
    private final double firstMinX;
    private final double firstMaxX;
    private final double firstMinY;
    private final double firstMaxY;
    private final double secondMinX;
    private final double secondMaxX;
    private final double secondMinY;
    private final double secondMaxY;

    RectanglePairInput(double firstMinX, double firstMaxX, double firstMinY, double firstMaxY,
                       double secondMinX, double secondMaxX, double secondMinY, double secondMaxY) {
        this.firstMinX = firstMinX;
        this.firstMaxX = firstMaxX;
        this.firstMinY = firstMinY;
        this.firstMaxY = firstMaxY;
        this.secondMinX = secondMinX;
        this.secondMaxX = secondMaxX;
        this.secondMinY = secondMinY;
        this.secondMaxY = secondMaxY;
    }

    double firstMinX() {
        return firstMinX;
    }

    double firstMaxX() {
        return firstMaxX;
    }

    double firstMinY() {
        return firstMinY;
    }

    double firstMaxY() {
        return firstMaxY;
    }

    double secondMinX() {
        return secondMinX;
    }

    double secondMaxX() {
        return secondMaxX;
    }

    double secondMinY() {
        return secondMinY;
    }

    double secondMaxY() {
        return secondMaxY;
    }
}

/**
 * Result of one {@code relate} call.
 *
 * <p>{@code shapesEqual} is carried alongside the relation because the transpose relation is
 * ambiguous for two equal shapes — see {@code RectangleRelateMetamorphicSpec}.</p>
 */
final class RelateOutcome {
    private final SpatialRelation relation;
    private final boolean shapesEqual;

    RelateOutcome(SpatialRelation relation, boolean shapesEqual) {
        this.relation = relation;
        this.shapesEqual = shapesEqual;
    }

    SpatialRelation relation() {
        return relation;
    }

    boolean shapesEqual() {
        return shapesEqual;
    }
}
