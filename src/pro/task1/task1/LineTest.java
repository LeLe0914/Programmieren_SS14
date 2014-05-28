package task1;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class LineTest {
    Point a = new Point(2,1);
    Point b = new Point(4,5);
    Point c = new Point(5,1);
    Point d = new Point(4,3);
    
    Line l1 = new Line(a,b);
    Line l2 = new Line(c,d);

    @Before
    public void setUp() throws Exception {
        
    }

    @Test
    public void test() {
        Point pI = l1.pointOfIntersection(l2);
        String point = pI.x + "," + pI.y;
        assertEquals("3.0,3.0", point);
        System.out.println(point);
       
        
    }

}
