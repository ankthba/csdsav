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

    @Override
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

    @Override
    public double area() {
        return 0.5 * base * height;
    }
}

class EquilateralTriangle extends Triangle {
    public EquilateralTriangle(double side) {
        super(side, side, side);
    }

    @Override
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

    @Override
    public double perimeter() {
        return s1 + s2 + s3 + s4;
    }
}

class Square extends Quadrilateral {
    public Square(double side) {
        super(side, side, side, side);
    }

    @Override
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

    @Override
    public double area() {
        return length * width;
    }
}

class Pentagon implements Polygon {
    private double side;

    public Pentagon(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return (5 * side * side) / (4 * Math.tan(Math.PI / 5));
    }

    @Override
    public double perimeter() {
        return 5 * side;
    }
}

class Hexagon implements Polygon {
    private double side;

    public Hexagon(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return (3 * Math.sqrt(3) * side * side) / 2;
    }

    @Override
    public double perimeter() {
        return 6 * side;
    }
}

class Octagon implements Polygon {
    private double side;

    public Octagon(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return 2 * (1 + Math.sqrt(2)) * side * side;
    }

    @Override
    public double perimeter() {
        return 8 * side;
    }
}