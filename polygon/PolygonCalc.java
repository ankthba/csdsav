import java.util.Scanner;

public class PolygonCalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Polygon polygon = null;

        System.out.println("Choose a polygon: Triangle, Quadrilateral, Pentagon, Hexagon, Octagon");
        String choice = scanner.nextLine();
        switch (choice) {
            case "Triangle":
                System.out.println("Enter base, height, side1, side2, side3");
                polygon = new Triangle(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                break;
            case "Quadrilateral":
                System.out.println("Enter side1, side2, side3, side4");
                polygon = new Quadrilateral(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                break;
            case "Pentagon":
                System.out.println("Enter side");
                polygon = new Pentagon(scanner.nextDouble());
                break;
            case "Hexagon":
                System.out.println("Enter side");
                polygon = new Hexagon(scanner.nextDouble());
                break;
            case "Octagon":
                System.out.println("Enter side");
                polygon = new Octagon(scanner.nextDouble());
                break;
            default:
                System.out.println("Invalid choice");
                System.exit(0);
        }

        System.out.println("Area: " + polygon.area());
        System.out.println("Perimeter: " + polygon.perimeter());
    }
}

interface Polygon {
    double area();
    double perimeter();
}

class Triangle implements Polygon {
    private double base, height, side1, side2, side3;

    public Triangle(double base, double height, double side1, double side2, double side3) {
        this.base = base;
        this.height = height;
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public double area() {
        return 0.5 * base * height;
    }

    @Override
    public double perimeter() {
        return side1 + side2 + side3;
    }
}

class Quadrilateral implements Polygon {
    private double s1, s2, s3, s4;

    public Quadrilateral(double s1, double s2, double s3, double s4) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
    }

    @Override
    public double area() {
        double s = (s1 + s2 + s3 + s4) / 2;
        return Math.sqrt((s - s1) * (s - s2) * (s - s3) * (s - s4));
    }

    @Override
    public double perimeter() {
        return s1 + s2 + s3 + s4;
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