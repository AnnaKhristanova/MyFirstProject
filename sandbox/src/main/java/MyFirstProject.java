package ru.stqa.pft.sandbox;

import static java.lang.Math.*;

public class MyFirstProject {

    public static void main(String[] args) {

        double x1 = 0;
        double x2 = 5;
        double y1 = 0;
        double y2 = 0;
        System.out.println(distance(x1, x2, y1, y2));
    }

    public static double distance(
            double x1,
            double y1,
            double x2,
            double y2)
    {
        return sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}