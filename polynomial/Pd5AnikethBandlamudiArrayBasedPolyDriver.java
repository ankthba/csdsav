/*



Interface Lab

Purpose: Practice what we learned in APCS A (looping, conditional, method writing, etc.) and gain some experience in how to define an interface and write a concrete class that implements an interface.


Write a Java interface (file name: Polynomial.java)  for (single-variable) polynomials whose coefficients are of type double and whose exponents are of type int and non-negative. Include methods to
Return the degree of the polynomial
Return the coefficient for a given exponent
Evaluate the polynomial at a given value of x
Add two polynomials, returning their sum
Subtract two polynomials, returning their difference
(Extension) Return the derivative of a polynomial (if you are not taking calculus this year, you need to find out what a derivative of a polynomial function is)

Write an array-based implementation of the polynomial interface. (file name: ArrayBasedPolynomial.java) Polynomials should be represented in an array of doubles, indexed by exponent. 

See the driver class below. From the way the ArrayBasedPolynomial class is used below, students decide what other methods should be included in the class.

/**
Polynomial interface
*/
/**
ArrayBasedPolynomial class

public class Pd4EdmundLauArrayBasedPolyDriver{
   public static void main(String[] args)    {       double [] c = {1, 0, 3, 4};      double [] c1 = {-2, -5};         Polynomial p1 = new ArrayBasedPolynomial(c);  // 4x^3 + 3x^2 + 1      System.out.println("p1(x) = " + p1);         Polynomial p2 = new ArrayBasedPolynomial (c1);   // - 5x – 2      System.out.println("p2(x) = " + p2);                 // p2(x) = - 5x^1 – 2         Polynomial p3   = new ArrayBasedPolynomial (-4, 1);  // coeff = -4, exp = 1      System.out.println("p3(x) = " + p3);         Polynomial p    = p1.plus(p2).plus(p2);   // 4x^3 + 3x^2 - 10x – 3      System.out.println("p(x) = " + p);       // p(x) = 4x^3 + 3x^2 - 10x^1 – 3         Polynomial p4   = p.minus(p3);   // 4x^3 + 3x^2 - 6x^1 – 3   <====      System.out.println("p4(x) = " + p4);         Polynomial p5   = p4.differentiate();   // 12x^2 + 6x^1 - 6   <====      System.out.println("p5(x) = " + p5);            Polynomial clone = new ArrayBasedPolynomial (p5);      System.out.println("clone(x) = " + clone);         System.out.println ("p5(0) = " + p5.evaluate(0));      System.out.println ("p5(1) = " + p5.evaluate(1));   }}


*/



public class Pd5AnikethBandlamudiArrayBasedPolyDriver {
   public static void main(String[] args) {
       double[] c = {1, 0, 3, 4};
       double[] c1 = {-2, -5};

       Polynomial p1 = new ArrayBasedPolynomial(c);  // 4x^3 + 3x^2 + 1
       System.out.println("p1(x) = " + p1);

       Polynomial p2 = new ArrayBasedPolynomial(c1);   // - 5x – 2
       System.out.println("p2(x) = " + p2);

       Polynomial p3 = new ArrayBasedPolynomial(-4, 1);  // coeff = -4, exp = 1
       System.out.println("p3(x) = " + p3);

       Polynomial p = p1.plus(p2).plus(p2);   // 4x^3 + 3x^2 - 10x – 3
       System.out.println("p(x) = " + p);

       Polynomial p4 = p.minus(p3);   // 4x^3 + 3x^2 - 6x^1 – 3
       System.out.println("p4(x) = " + p4);

       Polynomial p5 = p4.differentiate();   // 12x^2 + 6x^1 - 6
       System.out.println("p5(x) = " + p5);

       Polynomial clone = new ArrayBasedPolynomial(p5);
       System.out.println("clone(x) = " + clone);

       System.out.println("p5(0) = " + p5.evaluate(0));
       System.out.println("p5(1) = " + p5.evaluate(1));
   }
}

interface Polynomial {
   int getDegree();
   double getCoefficient(int exponent);
   double evaluate(double x);
   Polynomial plus(Polynomial other);
   Polynomial minus(Polynomial other);
   Polynomial differentiate();
}

class ArrayBasedPolynomial implements Polynomial {
   private double[] coefficients;

   public ArrayBasedPolynomial(double[] coefficients) {
       this.coefficients = coefficients;
   }

   public ArrayBasedPolynomial(Polynomial other) {
    this.coefficients = new double[other.getDegree() + 1];
    for (int i = 0; i <= other.getDegree(); i++) {
        this.coefficients[i] = other.getCoefficient(i);
    }
}

   @Override
   public int getDegree() {
       for (int i = coefficients.length - 1; i >= 0; i--) {
           if (coefficients[i] != 0) {
               return i;
           }
       }
       return 0;
   }

   @Override
   public double getCoefficient(int exponent) {
       if (exponent < 0 || exponent >= coefficients.length) {
           return 0;
       }
       return coefficients[exponent];
   }

   @Override
   public double evaluate(double x) {
       double result = 0;
       for (int i = 0; i < coefficients.length; i++) {
           result += coefficients[i] * Math.pow(x, i);
       }
       return result;
   }

   @Override
   public Polynomial plus(Polynomial other) {
       int maxDegree = Math.max(this.getDegree(), other.getDegree());
       double[] newCoefficients = new double[maxDegree + 1];
       
       for (int i = 0; i <= maxDegree; i++) {
           newCoefficients[i] = this.getCoefficient(i) + other.getCoefficient(i);
       }
       
       return new ArrayBasedPolynomial(newCoefficients);
   }

   @Override
   public Polynomial minus(Polynomial other) {
       int maxDegree = Math.max(this.getDegree(), other.getDegree());
       double[] newCoefficients = new double[maxDegree + 1];
       
       for (int i = 0; i <= maxDegree; i++) {
           newCoefficients[i] = this.getCoefficient(i) - other.getCoefficient(i);
       }
       
       return new ArrayBasedPolynomial(newCoefficients);
   }

   @Override
   public Polynomial differentiate() {
       if (getDegree() == 0) {
           return new ArrayBasedPolynomial(new double[]{0});
       }
       
       double[] newCoefficients = new double[getDegree()];
       for (int i = 1; i < coefficients.length; i++) {
           newCoefficients[i - 1] = i * coefficients[i];
       }
       
       return new ArrayBasedPolynomial(newCoefficients);
   }

   @Override
   public String toString() {
       StringBuilder sb = new StringBuilder();
       boolean firstTerm = true;
       
       for (int i = coefficients.length - 1; i >= 0; i--) {
           if (coefficients[i] != 0) {
               if (!firstTerm && coefficients[i] > 0) {
                   sb.append(" + ");
               } else if (!firstTerm) {
                   sb.append(" - ");
               } else if (coefficients[i] < 0) {
                   sb.append("-");
               }
               
               double absCoeff = Math.abs(coefficients[i]);
               if (absCoeff != 1 || i == 0) {
                   sb.append(absCoeff);
               }
               
               if (i > 0) {
                   sb.append("x");
                   if (i > 1) {
                       sb.append("^").append(i);
                   }
               }
               
               firstTerm = false;
           }
       }
       
       return sb.toString();
   }
}