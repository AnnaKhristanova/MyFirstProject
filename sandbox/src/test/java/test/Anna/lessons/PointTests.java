package test.Anna.lessons;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

import static org.testng.Assert.assertEquals;

public class PointTests {
    @Test
    public void testDistance(){
        Point p1 = new Point(0,6);
        Point p2 = new Point(0,0);
        assertEquals(p1.distance(p2), 6.0);
    }
}
