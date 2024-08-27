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
*/
public class Pd4EdmundLauArrayBasedPolyDriver{
   public static void main(String[] args)    {       double [] c = {1, 0, 3, 4};      double [] c1 = {-2, -5};         Polynomial p1 = new ArrayBasedPolynomial(c);  // 4x^3 + 3x^2 + 1      System.out.println("p1(x) = " + p1);         Polynomial p2 = new ArrayBasedPolynomial (c1);   // - 5x – 2      System.out.println("p2(x) = " + p2);                 // p2(x) = - 5x^1 – 2         Polynomial p3   = new ArrayBasedPolynomial (-4, 1);  // coeff = -4, exp = 1      System.out.println("p3(x) = " + p3);         Polynomial p    = p1.plus(p2).plus(p2);   // 4x^3 + 3x^2 - 10x – 3      System.out.println("p(x) = " + p);       // p(x) = 4x^3 + 3x^2 - 10x^1 – 3         Polynomial p4   = p.minus(p3);   // 4x^3 + 3x^2 - 6x^1 – 3   <====      System.out.println("p4(x) = " + p4);         Polynomial p5   = p4.differentiate();   // 12x^2 + 6x^1 - 6   <====      System.out.println("p5(x) = " + p5);            Polynomial clone = new ArrayBasedPolynomial (p5);      System.out.println("clone(x) = " + clone);         System.out.println ("p5(0) = " + p5.evaluate(0));      System.out.println ("p5(1) = " + p5.evaluate(1));   }}


*/

