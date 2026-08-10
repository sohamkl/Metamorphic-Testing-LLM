import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.context.SpatialContextFactory;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

/**
 * Thin adapter over {@link org.locationtech.spatial4j.shape.impl.CircleImpl#relate} from
 * Spatial4j, classifying how a circle sits relative to a rectangle.
 *
 * <p>This is the deepest of the Spatial4j targets: {@code CircleImpl.relate(Rectangle)}
 * feeds {@code relateRectanglePhase2}, the highest-complexity method in either benchmark
 * project, which reasons about where a curved boundary crosses a box edge.</p>
 *
 * <p>Both shapes are carried as bare coordinates rather than as live {@code Shape} objects
 * so the executed-data JSON stays flat. A Euclidean (non-geodetic) context is used because
 * {@code CircleImpl} is the plane implementation — its geodetic counterpart is the separate
 * {@code GeoCircle} subclass. No geometry lives here; this class builds the two shapes and
 * delegates.</p>
 */
public final class CircleRelateCalculator {

    private static final SpatialContext PLANE = createPlaneContext();

    private CircleRelateCalculator() {
    }

    /** The shared Euclidean context both shapes are built in. */
    public static SpatialContext context() {
        return PLANE;
    }

    private static SpatialContext createPlaneContext() {
        SpatialContextFactory factory = new SpatialContextFactory();
        factory.geo = false;
        return factory.newSpatialContext();
    }

    /**
     * Classifies how the circle relates to the rectangle.
     *
     * @param input circle centre and radius, plus rectangle bounds
     * @return CONTAINS, WITHIN, INTERSECTS or DISJOINT
     */
    public static SpatialRelation relate(CircleRectangleInput input) {
        CircleImpl circle = new CircleImpl(
                new PointImpl(input.centreX(), input.centreY(), context()),
                input.radius(),
                context());
        RectangleImpl rectangle = new RectangleImpl(
                input.minX(), input.maxX(), input.minY(), input.maxY(), context());
        return circle.relate(rectangle);
    }
}

/**
 * Source input for the circle-relate example: a circle and a rectangle on a plane.
 *
 * <p>{@code radius} must be non-negative; {@code minX} must not exceed {@code maxX} and
 * {@code minY} must not exceed {@code maxY}.</p>
 */
final class CircleRectangleInput {
    private final double centreX;
    private final double centreY;
    private final double radius;
    private final double minX;
    private final double maxX;
    private final double minY;
    private final double maxY;

    CircleRectangleInput(double centreX, double centreY, double radius,
                         double minX, double maxX, double minY, double maxY) {
        this.centreX = centreX;
        this.centreY = centreY;
        this.radius = radius;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    double centreX() {
        return centreX;
    }

    double centreY() {
        return centreY;
    }

    double radius() {
        return radius;
    }

    double minX() {
        return minX;
    }

    double maxX() {
        return maxX;
    }

    double minY() {
        return minY;
    }

    double maxY() {
        return maxY;
    }
}
