import static java.lang.Math.sqrt;

public class Point {

public static double x;
public static double y;

public Point (double x, double y){
    this.x=x;
    this.y=y;

}

    public double distance()

    {
        return sqrt((this.y - this.y) * (this.y - this.y) + (this.x - this.x) * (this.x - this.x));
    }

}
