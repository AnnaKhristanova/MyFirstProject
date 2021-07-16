import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance(){

        Point p1 = new Point(18,8.0);
        Point p2 = new Point(5,10.0);
        Assert.assertEquals(p1.distance(p2), 13.152946437965905);
    }
}
