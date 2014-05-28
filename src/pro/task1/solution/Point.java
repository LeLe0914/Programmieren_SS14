package solution;

/**
 * 
 * @author NoBl
 * 
 */
public class Point {

    double x;
    double y;

    private static final double EPS = 0.0001; // epsilon used to compare double values

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (Math.abs(this.x - other.x) > EPS || Math.abs(this.y - other.y) > EPS) {
            return false;
        }
        return true;
    }

}
