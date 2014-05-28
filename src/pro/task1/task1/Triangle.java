package task1;

/**
 * The class is representation a Triangle
 * @author Le Wang
 *
 */

public class Triangle {
    
    /**
     * The constructor with Triangle with three parameter Point
     * @param p0 one point of Triangle
     * @param p1 one point of Triangle
     * @param p2 one point of Triangle
     */
    public Triangle(Point p0, Point p1, Point p2) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
    }
    
    /**
     * The constructor with Triangle with three parameter Line
     * @param l0 one line of Triangle
     * @param l1 one line of Triangle
     * @param l2 one line of Triangle
     */
    public Triangle(Line l0, Line l1, Line l2) {
        if(l0.p0.equals(l2.p1) && l0.p1.equals(l1.p0) && l1.p1.equals(l2.p0)) {
            this.l0 = l0;
            this.l1 = l1;
            this.l2 = l2;
        } else {
            System.out.println("this is not a triangle !!");
        }
    }
    
    /**
     * three points build a Triangle
     */
    Point p0;
    Point p1;
    Point p2;
    
    /**
     * three lines build a Triangle
     */
    Line l0;
    Line l1;
    Line l2;
}
