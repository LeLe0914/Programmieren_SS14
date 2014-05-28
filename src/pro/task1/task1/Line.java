package task1;
/**
 * The class is representation a Line
 * @author Le Wang
 *
 */
public class Line {
    
    /**
     * The constructor with two point
     * @param p0 first point
     * @param p1 second point
     */
    public Line(Point p0, Point p1) {
        this.p0 = p0;
        this.p1 = p1;
    }
    
    /**
     * two points build a line.
     */
    Point p0;
    Point p1;
    
    /**
     * Calculate the line point of intersection with parameter l;
     * @param l parameter l
     * @return Point of intersection
     */
    public Point pointOfIntersection(Line l) {
        // Four points of two line and point of intersection
        Point a = this.p0;
        Point b = this.p1;
        Point c = l.p0;
        Point d = l.p1;
        Point pIntersection = null;
        
        boolean existPoint = false;
        // Calculate area of Triangle "abc" * 2, use vector "ca" and "cb" 
        double area_abc = (a.x - c.x) * (b.y - c.y) - (a.y - c.y) * (b.x - c.x);
        
        // Calculate area of Triangle "abd" * 2, use vector "da" and "db"
        double area_abd = (a.x - d.x) * (b.y - d.y) - (a.y - d.y) * (b.x - d.x);
        
        // Check the point c,d are located in the same side of line"ab" or not
        if(area_abc * area_abd < 0) {
            existPoint = true;
        }
        
        // Calculate area of Triangle "cda" * 2, use vector "ca" and "da"
        double area_cda = (c.x - a.x) * (d.y - a.y) - (c.y - a.y) * (d.x - a.x);
        
        // Calculate area of Triangle "cdb" * 2, use "abc", "abd" and "cda"
        double area_cdb = area_cda + area_abc - area_abd;
        
        // Check the point c,d are located in the same side of line"ab" or not
        if(area_cda * area_cdb < 0) {
            existPoint = true;
        }
        
        // Calculate Point of Intersection, if the point exist.
        if(existPoint == true) {
            double t = area_cda / ( area_abd- area_abc );
            double dx = t * (b.x - a.x),
                   dy = t * (b.y - a.y);
            pIntersection = new Point(a.x + dx, a.y + dy);
            return pIntersection;
        } else {
            return pIntersection;
        }
    }

}
