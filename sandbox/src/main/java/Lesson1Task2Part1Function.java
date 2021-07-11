import java.awt.Point;

import static java.lang.Math.sqrt;

public class Lesson1Task2Part1Function {

    public static void main(String[] args) {

        Point p1 = new Point(0,6);
        Point p2 = new Point(0,0);

        System.out.println(distance(p1,p2));
    }

    public static double distance(Point p1, Point p2)

    {
        return sqrt((p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p1.x) * (p2.x - p1.x));
    }
}