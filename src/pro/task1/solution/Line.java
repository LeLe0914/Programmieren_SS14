package solution;

/**
 * 
 * @author NoBl
 * 
 */
public class Line {

    Point p1;
    Point p2;

    Line(Point p1, Point p2) {
        if (p1.equals(p2)) {
            throw new IllegalArgumentException("Identical points don't make a line!");
        }
        this.p1 = p1;
        this.p2 = p2;
    }

    Point intersect(Line other) {
        // using: http://de.wikipedia.org/wiki/Schnittpunkt#Schnittpunkt_zweier_Strecken
        double a1 = this.p2.x - this.p1.x;
        double b1 = other.p2.x - other.p1.x;
        double c1 = other.p1.x - this.p1.x;

        double a2 = this.p2.y - this.p1.y;
        double b2 = other.p2.y - other.p1.y;
        double c2 = other.p1.y - this.p1.y;

        double divisor = a1 * b2 - a2 * b1;
        if (divisor == 0) {
            throw new IllegalArgumentException("Lines don't intersect!"); // parallel!
        }
        double s = (c1 * b2 - c2 * b1) / divisor;
        double t = (a1 * c2 - a2 * c1) / divisor * (-1);

        if (s < 0 || t < 0 || s > 1 || t > 1) {
            throw new IllegalArgumentException("Lines don't intersect!"); // point not on either line
        }

        double x = this.p1.x + s * a1;
        double y = this.p1.y + s * a2;
        return new Point(x, y);
    }
}
