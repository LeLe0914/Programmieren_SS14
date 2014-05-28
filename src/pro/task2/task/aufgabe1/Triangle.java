package task.aufgabe1;

/**
 * The class is representation a Triangle
 * @author Le Wang
 *
 */

public class Triangle {
    /**
     * three points build a Triangle
     */
    Point p1;
    Point p2;
    Point p3;
    
    /**
     * three lines build a Triangle
     */
    Line l1;
    Line l2;
    Line l3;
    
    /**
     * the distance between two points;
     */
    double p1p2;
    double p2p3;
    double p1p3;
    
    /**
     * The constructor with Triangle with three parameter Point
     * @param p1 one point of Triangle
     * @param p2 one point of Triangle
     * @param p3 one point of Triangle
     */
    public Triangle(Point p1, Point p2, Point p3) {
        // check whether these points build a triangle or not.
        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
            throw new IllegalArgumentException("Three points cant build a trangle !");
        } 
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    /**
     * The constructor with Triangle with three parameter Line
     * @param l1 one line of Triangle
     * @param l2 one line of Triangle
     * @param l3 one line of Triangle
     */
    public Triangle(Line l1, Line l2, Line l3) {
        this(l1.pointOfIntersection(l2), l1.pointOfIntersection(l3), l2.pointOfIntersection(l3));
    }
    
    public void shift(double dx, double dy) {
        p1.x = p1.x + dx;
        p2.x = p2.x + dx;
        p3.x = p3.x + dx;
        
        p1.y = p1.y + dy;
        p2.y = p2.y + dy;
        p3.y = p3.y + dy;
    }
    
    public void rotate(double angle) {
        // update point p1's coordinate
        this.p1.x = this.p1.x * Math.cos(angle) - this.p1.y * Math.sin(angle);
        this.p1.y = this.p1.x * Math.sin(angle) + this.p1.y * Math.cos(angle);
        // update point p2's coordinate
        this.p2.x = this.p2.x * Math.cos(angle) - this.p2.y * Math.sin(angle);
        this.p2.y = this.p2.x * Math.sin(angle) + this.p2.y * Math.cos(angle);
        // update point p3's coordinate
        this.p3.x = this.p3.x * Math.cos(angle) - this.p3.y * Math.sin(angle);
        this.p3.y = this.p3.x * Math.sin(angle) + this.p3.y * Math.cos(angle);     
    }
    
    public void rotate(Point center, double angle) {
        double dx = center.x * 2;
        double dy = center.y * 2;
        this.rotate(angle);
        this.shift(dx, dy);
    }
    
    public double circumference() {
        // the distance between p1 and p2
        p1p2 = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
        // the distance between p3 and p2
        p2p3 = Math.sqrt(Math.pow(p2.x - p3.x, 2) + Math.pow(p2.y - p3.y, 2));
        // the distance between p1 and p3
        p1p3 = Math.sqrt(Math.pow(p1.x - p3.x, 2) + Math.pow(p1.y - p3.y, 2));
        return p1p2 + p2p3 + p1p3;
    }
   
    public double area() {
        double s = 0.5 * this.circumference();
        return Math.sqrt(s * (s - p1p2) * (s - p2p3) * (s - p1p3));
    }
    
    public Point barycenter() {
        double x = (double) 1 / 3 * (p1.x + p2.x + p3.x);
        double y = (double) 1 / 3 * (p1.y + p2.y + p3.y);
        return new Point(x, y);
    }
    public static void main(String args[]) {
        Point a = new Point(0, 0);
        Point b = new Point(1, 0);
        Point c = new Point(0, 1);
        Triangle t = new Triangle(a, b, c);
        
        // test method shift();
        t.shift(0.5, 0.5);
        System.out.println("Test method shift()");
        System.out.println(" Point a [" + t.p1.x + " , " + t.p1.y + "]");
        System.out.println(" Point b [" + t.p2.x + " , " + t.p2.y + "]");
        System.out.println(" Point c [" + t.p3.x + " , " + t.p3.y + "]");
        System.out.println();
        
        // test method rotate(double angle) 
        t.rotate(Math.PI);
        System.out.println("Test method rotate(double angle)");
        System.out.printf(" Point a [%.2f , %.2f] \n", t.p1.x, t.p1.y);
        System.out.printf(" Point b [%.2f , %.2f] \n", t.p2.x, t.p2.y);
        System.out.printf(" Point c [%.2f , %.2f] \n", t.p3.x, t.p3.y);
        System.out.println();
        
        // test method rotate(Point center, double angle) 1
        t.rotate(Math.PI); // reset all points' coordinate
        t.rotate(t.p1, Math.PI);
        System.out.println("Test method rotate(Point center, double angle) \n"
                           + "The center point is one of triangle's point");
        System.out.printf(" Point a [%.2f , %.2f] \n", t.p1.x, t.p1.y);
        System.out.printf(" Point b [%.2f , %.2f] \n", t.p2.x, t.p2.y);
        System.out.printf(" Point c [%.2f , %.2f] \n", t.p3.x, t.p3.y);
        System.out.println();
        
        // test method rotate(Point center, double angle) 2
        t.rotate(t.p1, Math.PI); // reset all points' coordinate;
        t.rotate(new Point(0, 0.5), Math.PI);
        System.out.println("Test method rotate(Point center, double angle) \n"
                           + "The center point is not one of triangle's point");
        System.out.printf(" Point a [%.2f , %.2f] \n", t.p1.x, t.p1.y);
        System.out.printf(" Point b [%.2f , %.2f] \n", t.p2.x, t.p2.y);
        System.out.printf(" Point c [%.2f , %.2f] \n", t.p3.x, t.p3.y);
        System.out.println();
        
        //test method double circumference()
        t.rotate(new Point(0, 0.5), Math.PI); // reset all points' coordinate;
        System.out.println("Test method double circumference()");
        System.out.printf("The circumference of triangle is %.2f \n", t.circumference());
        System.out.println();
        
        //test method double area()
        System.out.println("Test method double area()");
        System.out.printf("The area triangle is %.2f \n", t.area());
        System.out.println();
        
        //test method Point barycenter()
        System.out.println("Test method Point barycenter()");
        System.out.printf("The bary point is [%.2f , %.2f] \n", t.barycenter().x, t.barycenter().y);
        System.out.println();
        
    }
   
}
