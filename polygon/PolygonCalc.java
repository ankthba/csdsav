/**************************************************** 
Name (Full Name): Aniketh Bandlamudi   Period:   5
Name of the Lab/Assignment:  U1: Polygon
Purpose of the program: To practice creating abstract classes and interfaces

Mistakes made:
Accidentally used scanner for input instead of the hardcoded values.


How I feel about this programming experience: 
I feel like I understand the concept of abstract classes and interfaces better, and got valuable practice with them.


What I Learned: 
I learned how to use abstract classes and interfaces to create a program that can calculate the area and perimeter of different shapes.

****************************************************/

import java.util.Scanner;

public class PolygonCalc {
    public static void main(String[] args) {
        Polygon[] polygons = {
            new IsoscelesTriangle(3, 4, 5),
            new EquilateralTriangle(4),
            new Square(4),
            new Rectangle(4, 6),
            new Pentagon(5),
            new Hexagon(6),
            new Octagon(7)
        };

        for (Polygon polygon : polygons) {
            System.out.println("Polygon: " + polygon.getClass().getSimpleName());
            System.out.println("Area: " + polygon.area());
            System.out.println("Perimeter: " + polygon.perimeter());
            System.out.println();
        }
    }
}

interface Polygon {
    double area();
    double perimeter();
}

abstract class Triangle implements Polygon {
    protected double side1, side2, side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    // pre: side1, side2, side3 are the lengths of the sides of the triangle
    // post: returns the perimeter of the triangle
    public double perimeter() {
        return side1 + side2 + side3;
    }
}

class IsoscelesTriangle extends Triangle {
    private double base, height;

    public IsoscelesTriangle(double base, double height, double side) {
        super(base, side, side);
        this.base = base;
        this.height = height;
    }

    // pre: base and height are the lengths of the base and height of the triangle
    // post: returns the area of the triangle
    public double area() {
        return 0.5 * base * height;
    }
}

class EquilateralTriangle extends Triangle {
    public EquilateralTriangle(double side) {
        super(side, side, side);
    }

    // pre: side1 is the length of the side of the triangle
    // post: returns the area of the triangle
    public double area() {
        return (Math.sqrt(3) / 4) * side1 * side1;
    }
}

abstract class Quadrilateral implements Polygon {
    protected double s1, s2, s3, s4;

    public Quadrilateral(double s1, double s2, double s3, double s4) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
    }

    // pre: s1, s2, s3, s4 are the lengths of the sides of the quadrilateral
    // post: returns the perimeter of the quadrilateral
    public double perimeter() {
        return s1 + s2 + s3 + s4;
    }
}

class Square extends Quadrilateral {
    public Square(double side) {
        super(side, side, side, side);
    }

    // pre: s1 is the length of the side of the square
    // post: returns the area of the square
    public double area() {
        return s1 * s1;
    }
}

class Rectangle extends Quadrilateral {
    private double length, width;

    public Rectangle(double length, double width) {
        super(length, width, length, width);
        this.length = length;
        this.width = width;
    }

    // pre: length and width are the lengths of the sides of the rectangle
    // post: returns the area of the rectangle
    public double area() {
        return length * width;
    }
}

class Pentagon implements Polygon {
    private double side;

    public Pentagon(double side) {
        this.side = side;
    }

    // pre: side is the length of the side of the pentagon
    // post: returns the area of the pentagon
    public double area() {
        return (5 * side * side) / (4 * Math.tan(Math.PI / 5));
    }

    // pre: side is the length of the side of the pentagon
    // post: returns the perimeter of the pentagon
    public double perimeter() {
        return 5 * side;
    }
}

class Hexagon implements Polygon {
    private double side;

    public Hexagon(double side) {
        this.side = side;
    }

    // pre: side is the length of the side of the hexagon
    // post: returns the area of the hexagon
    public double area() {
        return (3 * Math.sqrt(3) * side * side) / 2;
    }

    // pre: side is the length of the side of the hexagon
    // post: returns the perimeter of the hexagon
    public double perimeter() {
        return 6 * side;
    }
}

class Octagon implements Polygon {
    private double side;

    public Octagon(double side) {
        this.side = side;
    }

    // pre: side is the length of the side of the octagon
    // post: returns the area of the octagon
    public double area() {
        return 2 * (1 + Math.sqrt(2)) * side * side;
    }

    // pre: side is the length of the side of the octagon
    // post: returns the perimeter of the octagon
    public double perimeter() {
        return 8 * side;
    }
}

/********* PROGRAM OUTPUTS ******
The program outputs the area and perimeter of each shape in the array of polygons using the hardcoded values.
**********************************/