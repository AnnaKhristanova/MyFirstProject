

public class MyFirstProject {

    public static void main(String[] args) {

        Point p1 = new Point(18,8.0);
        Point p2 = new Point(5,10.0);
        System.out.println("p1 is (" + p1.x + ", " + p1.y + ")");
        System.out.println("p2 is (" + p2.x + ", " + p2.y + ")");
        System.out.println(p1.distance(p2));
    }


}