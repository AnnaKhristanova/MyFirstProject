import static java.lang.Math.sqrt;

public class Point {

public static double x;
public static double y;

    public Point (double x, double y){
    this.x=x;
    this.y=y;
}

    public double distance(Point p2)

    {
        return sqrt((p2.y - y) * (p2.y - y) + (p2.x - x) * (p2.x - x));
    }

}
