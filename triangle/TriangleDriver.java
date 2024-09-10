public class TriangleDriver {
    public static void main(String[] args) {
        try {
            Triangle t = new Triangle(1, 2, 3);
        } catch (IllegalTriangleException e) {
            System.out.println(e.getMessage());
        }
    }

}

class Triangle {
    private int a;
    private int b;
    private int c;

    public Triangle(int a, int b, int c) throws IllegalTriangleException {
        this.a = a;
        this.b = b;
        this.c = c;
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalTriangleException("The sum of any two sides must be greater than the third side");
        }
    }

}

class IllegalTriangleException extends Exception {
    public IllegalTriangleException(String message) {
        super(message);
    }
}