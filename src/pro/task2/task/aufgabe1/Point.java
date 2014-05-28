package task.aufgabe1;
/**
 * The class is representation a Point
 * @author Le Wang
 *
 */
public class Point {
    /**
     *  two dimensional Vector, x-axis and y-axis
     */
    double x;
    double y;

    /**
     * The constructor with parameter x-axis and y-axis
     * @param x x-axis
     * @param y y-axis
     */
    public Point(double x, double y) {
       this.x = x;
       this.y = y;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
   
}
