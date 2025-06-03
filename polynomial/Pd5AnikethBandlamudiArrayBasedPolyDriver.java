/*******
Name: Aniketh Bandlamudi
Period: 5
Name of the Program: U1 Lab: Polynomial
Purpose of Program: To create a polynomial class that can be used to perform various operations on polynomials.

What I Learned/Reflection: 
a. I applied the concept of polymorphism by creating an interface and implementing it in a class.

b. I applied the concept of encapsulation by making the coefficients array private and providing 
public methods to access and modify the coefficients.

c. I applied the concept of abstraction by creating a separate class for the polynomial and providing
methods to perform various operations on the polynomial.
 
Credits: I developed the code myself.
Students whom I helped: I explained the conecpt of derivatives, differentiation, and power rule to Malek Swilam.
*******/

// Driver class to test the Polynomial interface and ArrayBasedPolynomial class
public class Pd5AnikethBandlamudiArrayBasedPolyDriver {

    // Main method to test the Polynomial interface and ArrayBasedPolynomial class
   public static void main(String[] args) {

    // Test cases for the Polynomial interface and ArrayBasedPolynomial class
       double[] c = {1, 0, 3, 4};
       double[] c1 = {-2, -5};

       // Create a polynomial p1 with coefficients {1, 0, 3, 4}
       Polynomial p1 = new ArrayBasedPolynomial(c);  // 4x^3 + 3x^2 + 1
       System.out.println("p1(x) = " + p1);

       // Create a polynomial p2 with coefficients {-2, -5}
       Polynomial p2 = new ArrayBasedPolynomial(c1);   // - 5x – 2
       System.out.println("p2(x) = " + p2);

       // Create a polynomial p3 with coefficients {-4, 1}
       Polynomial p3 = new ArrayBasedPolynomial(-4, 1);  // coeff = -4, exp = 1
       System.out.println("p3(x) = " + p3);

       // Add p1, p2, and p2 to create a new polynomial p
       Polynomial p = p1.plus(p2).plus(p2);   // 4x^3 + 3x^2 - 10x – 3
       System.out.println("p(x) = " + p);

       // Subtract p3 from p to create a new polynomial p4
       Polynomial p4 = p.minus(p3);   // 4x^3 + 3x^2 - 6x^1 – 3
       System.out.println("p4(x) = " + p4);

       // Differentiate p4 to create a new polynomial p5
       Polynomial p5 = p4.differentiate();   // 12x^2 + 6x^1 - 6
       System.out.println("p5(x) = " + p5);

       // Clone p5 to create a new polynomial clone
       Polynomial clone = new ArrayBasedPolynomial(p5);
       System.out.println("clone(x) = " + clone);

       // Evaluate p5 at x = 0 and x = 1
       System.out.println("p5(0) = " + p5.evaluate(0));
       System.out.println("p5(1) = " + p5.evaluate(1));
   }
}

// Interface for a polynomial
interface Polynomial {
    // Method to get the degree of the polynomial
    int degree();
    // Method to get the coefficient of the polynomial at a given exponent
    double coefficient(int exponent);
    // Method to evaluate the polynomial at a given value of x
    double evaluate(double x);
    // Method to add two polynomials
    Polynomial plus(Polynomial other);
    // Method to subtract two polynomials   
    Polynomial minus(Polynomial other);
    // Method to differentiate the polynomial
    Polynomial differentiate();
    // Method to convert the polynomial to a string
    String toString();
}


// Class to represent a polynomial using an array
class ArrayBasedPolynomial implements Polynomial {
    private double[] coefficients;

    // Constructor to initialize the polynomial with a given array of coefficients
    public ArrayBasedPolynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    // Constructor to initialize the polynomial with a given coefficient and exponent
    public ArrayBasedPolynomial(double coefficient, int exponent) {
        coefficients = new double[exponent + 1];
        coefficients[exponent] = coefficient;
    }

    // Constructor to initialize the polynomial with a given polynomial
    public ArrayBasedPolynomial(Polynomial polynomial) {
        int degree = polynomial.degree();
        coefficients = new double[degree + 1];
        for (int i = 0; i <= degree; i++) {
            coefficients[i] = polynomial.coefficient(i);
        }
    }

    // Precondition: The polynomial is not empty
    // Postcondition: The degree of the polynomial is returned
    public int degree() {
        for (int i = coefficients.length - 1; i >= 0; i--) {
            if (coefficients[i] != 0) {
                return i;
            }
        }
        return 0;
    }

    // Precondition: The exponent is non-negative
    // Postcondition: The coefficient of the polynomial at the given exponent is returned
    public double coefficient(int exponent) {
        if (exponent < coefficients.length) {
            return coefficients[exponent];
        }
        return 0;
    }

    // Precondition: The value of x is a double
    // Postcondition: The value of the polynomial at the given value of x is returned
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    // Precondition: The other polynomial is not empty
    // Postcondition: The sum of the two polynomials is returned
    public Polynomial plus(Polynomial other) {
        int maxDegree = Math.max(this.degree(), other.degree());
        double[] resultCoefficients = new double[maxDegree + 1];

        for (int i = 0; i <= maxDegree; i++) {
            resultCoefficients[i] = this.coefficient(i) + other.coefficient(i);
        }
        return new ArrayBasedPolynomial(resultCoefficients);
    }

    // Precondition: The other polynomial is not empty
    // Postcondition: The difference of the two polynomials is returned
    public Polynomial minus(Polynomial other) {
        return this.plus(((ArrayBasedPolynomial) other).scale(-1.0));
    }

    // Precondition: The factor is a double
    // Postcondition: The polynomial is scaled by the given factor
    private Polynomial scale(double factor) {
        double[] resultCoefficients = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; i++) {
            resultCoefficients[i] = coefficients[i] * factor;
        }
        return new ArrayBasedPolynomial(resultCoefficients);
    }

    // Precondition: The polynomial is not empty
    // Postcondition: The derivative of the polynomial is returned
    public Polynomial differentiate() {
        double[] resultCoefficients = new double[coefficients.length - 1];
        for (int i = 1; i < coefficients.length; i++) {
            resultCoefficients[i - 1] = coefficients[i] * i;
        }
        return new ArrayBasedPolynomial(resultCoefficients);
    }

    // Precondition: The polynomial is not empty
    // Postcondition: The polynomial is converted to a string
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = coefficients.length - 1; i >= 0; i--) {
            if (coefficients[i] != 0) {
                if (sb.length() > 0) {
                    if (coefficients[i] > 0) {
                        sb.append(" + ");
                    } else {
                        sb.append(" ");
                    }
                }
                sb.append(coefficients[i]);
                if (i > 0) {
                    sb.append("x^").append(i);
                }
            }
        }
        return sb.toString();
    }
}

/********* PROGRAM OUTPUTS ******
p1(x) = 4.0x^3 + 3.0x^2 + 1.0
p2(x) = -5.0x^1 -2.0
p3(x) = -4.0x^1
p(x) = 4.0x^3 + 3.0x^2 -10.0x^1 -3.0
p4(x) = 4.0x^3 + 3.0x^2 -6.0x^1 -3.0
p5(x) = 12.0x^2 + 6.0x^1 -6.0
clone(x) = 12.0x^2 + 6.0x^1 -6.0
p5(0) = -6.0
p5(1) = 12.0
**********************************/
