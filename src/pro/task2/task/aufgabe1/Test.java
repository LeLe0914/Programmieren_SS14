package task.aufgabe1;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * 
 * @author NoBl
 * 
 */
public class Test {

    private static Point p1;
    private static Point p2;
    private static Point p3;
    private static Point q1;
    private static Point q2;

    private static Line lp1p2;
    private static Line lp1q1;
    private static Line lp2q1;
    private static Line lp2q2;
    private static Line lp1p3;
    private static Line lq1q2;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        p1 = new Point(0, 0);
        p2 = new Point(1, 0);
        p3 = new Point(0.5, 0);
        q1 = new Point(0, 1);
        q2 = new Point(1, 1);

        lp1p2 = new Line(p1, p2);
        lp1q1 = new Line(p1, q1);
        lp2q1 = new Line(p2, q1);
        lp2q2 = new Line(p2, q2);
        lp1p3 = new Line(p1, p3);
        lq1q2 = new Line(q1, q2);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void noLineTest() throws Exception {
        @SuppressWarnings("unused")
        Line l1 = new Line(p1, p1);
    }

    @org.junit.Test
    public void lineTest() throws Exception {
        Line l = new Line(p1, p2);
        assertTrue("Line shouldn't be null", l != null);
        assertTrue("Line coord x1 = p1.x", l.p1.x == p1.x);
        assertTrue("Line coord y1 = p1.y", l.p1.y == p1.y);
        assertTrue("Line coord x2 = p2.x", l.p2.x == p2.x);
        assertTrue("Line coord y1 = p2.y", l.p2.y == p2.y);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void linepointOfIntersectionHorizontal() {
        // two horizontal lines
        lp1p2.pointOfIntersection(lq1q2);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void linepointOfIntersectionVertical() {
        // two vertical lines
        lp1q1.pointOfIntersection(lp2q2);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void linepointOfIntersectionSame() {
        // two same lines
        lp1p2.pointOfIntersection(lp1p2);
    }

    /**
     * Tests if we have an pointOfIntersectionion where two lines end
     */
    @org.junit.Test
    public void linepointOfIntersectionAtp2() {
        Point x = lp1p2.pointOfIntersection(lp2q2);
        assertEquals(p2.x, x.x, 0.0001);
        assertEquals(p2.y, x.y, 0.0001);
    }

    /**
     * Tests if pointOfIntersectionion tests consider an pointOfIntersectionion point NOT on either line (one line too short, ...)
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void lineNopointOfIntersection() {
        Point x = lp1p3.pointOfIntersection(lp2q2);
        assertEquals(p2.x, x.x, 0.0001);
        assertEquals(p2.y, x.y, 0.0001);
    }

    /**
     * Triangle with 2/3 points identical
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void noTrianglePoints() {
        @SuppressWarnings("unused")
        Triangle t = new Triangle(p1, p1, p2);
    }

    /**
     * Tests if we do get a triangle
     */
    @org.junit.Test
    public void trianglePoints() {
        Triangle t = new Triangle(p1, p2, p3);
        assertTrue("Triangle exists", t != null);
    }

    /**
     * Tests if we can't construct an illegal triangle out of lines
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void noTriangleLines() {
        @SuppressWarnings("unused")
        Triangle t = new Triangle(lp1p3, lp1q1, lp2q1);
    }

    /**
     * Tests if we can construct an valid triangle made of lines
     */
    @org.junit.Test
    public void triangleLines() {
        @SuppressWarnings("unused")
        Triangle t = new Triangle(lp1p2, lp1q1, lp2q1);
    }

}
