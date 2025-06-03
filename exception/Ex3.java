/**
Question 1: Compile the program and report any compile-time error you found.
Question 2: Is ArithmeticException a runtime exception or compile-time exception?
Question 3: If we want to deal with the exception in method third, what should we do?
Question 4: If we want to deal with the exception in method second, what should we do?
Question 5: If we want to deal with the exception in method first, what should we do?
Question 6: If we want to deal with the exception in method main, what should we do?
*/

public class Ex3
{
   public static void first() 
   {
      try {
         second();
      } catch (ArithmeticException e) {
         System.out.println("Caught in first: " + e.getMessage());
      }
   } // first

   public static void second() 
   {
      try {
         third();
      } catch (ArithmeticException e) {
         System.out.println("Caught in second: " + e.getMessage());
      }
   } // second
   
   public static void third() throws ArithmeticException
   {
      throw new ArithmeticException ("Oh, no!");
   } // third
   
   public static void main (String [] args) 
   {
      try {
         first();
      } catch (ArithmeticException e) {
         System.out.println("Caught in main: " + e.getMessage());
      }
      System.out.println ("In main and after first");
   }
}  //
