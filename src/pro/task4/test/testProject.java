package test;

import static org.junit.Assert.*;
import org.junit.Test;

import battleship.Battleship;

public class testProject {

    @Test
    public void test_isBorderLegal() {
        assertEquals(true, Battleship.isBorderLegal(8, 9));
        assertEquals(false, Battleship.isBorderLegal(6, 9));
    }
    

}
