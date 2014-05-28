package task.aufgabe1;
/**
 * The class is representation a Line
 * @author Le Wang
 *
 */
public class Line {
    /**
     * two points build a line.
     */
    Point p1;
    Point p2;
    
    
    /**
     * The constructor with two point
     * @param p1 first point
     * @param p2 second point
     * @throws Exception 
     */
    public Line(Point p1, Point p2)  {
        // check p1 and p1 same or not
        if (p1.equals(p2)) {
            throw new IllegalArgumentException("these points cant build a line!");
        }
        
        this.p1 = p1;
        this.p2 = p2;
    }
    
   
    /**
     * Calculate the line point of intersection with parameter l;
     * @param l parameter l
     * @return Point of intersection
     */
    public Point pointOfIntersection(Line l) {
        // Four points of two line and point of intersection
        Point a = this.p1;
        Point b = this.p2;
        Point c = l.p1;
        Point d = l.p2;
        Point pIntersection = null;
        
        // if denominator == 0 , two lines are parallel  
        double denominator = (b.y - a.y) * (d.x - c.x) - (a.x - b.x) * (c.y - d.y);  
        if (denominator == 0) {  
            throw new IllegalArgumentException("Lines don't intersect!"); // parallel!  
        }  
        
        // calculate the point of intersection
        double x = ((b.x - a.x) * (d.x - c.x) * (c.y - a.y)   
                   + (b.y - a.y) * (d.x - c.x) * a.x   
                   - (d.y - c.y) * (b.x - a.x) * c.x) / denominator;
        double y = -((b.y - a.y) * (d.y - c.y) * (c.x - a.x)   
                   + (b.x - a.x) * (d.y - c.y) * a.y   
                   - (d.x - c.x) * (b.y - a.y) * c.y) / denominator;  
        
        // check this point is on both lines or not
        if (
                (x - a.x) * (x - b.x) <= 0 && (y - a.y) * (y - b.y) <= 0   // this point is on this line
             && (x - c.x) * (x - d.x) <= 0 && (y - c.y) * (y - d.y) <= 0   // this point is on other line 
           ) {
            pIntersection = new Point(x, y);
        } else {
            throw new IllegalArgumentException("Lines don't intersect!"); // point not on either line
        }
        
        return pIntersection;
                
    }

}
