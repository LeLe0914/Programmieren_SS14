package solution;

/**
 * 
 * @author NoBl
 * 
 */
public class Triangle {

    Point p1;
    Point p2;
    Point p3;

    Triangle(Point p1, Point p2, Point p3) {
        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
            throw new IllegalArgumentException("At least two points match so this can't be a valid triangle!");
        }
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    Triangle(Line l1, Line l2, Line l3) {
        this(l1.intersect(l2), l1.intersect(l3), l2.intersect(l3));
    }

}
