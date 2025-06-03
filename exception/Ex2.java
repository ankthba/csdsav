/**
Question 1: Compile the program and report any compile-time error you found.
Question 2: Is MyException a runtime exception or compile-time exception?
Question 3: If we want to deal with the exception in method third, what should we do?
Question 4: If we want to deal with the exception in method second, what should we do?
Question 5: If we want to deal with the exception in method first, what should we do?
Question 6: If we want to deal with the exception in method main, what should we do?
*/
public class Ex2
{
   public static void first()   throws MyException
   {
      second();
   } // first

   public static void second() throws MyException  
   {
      third();
   } // second
   
   public static void third() throws MyException 
   {
      throw new MyException ("yours");   
   } // third
   
   public static void main (String [] args) {
      try {
         first();
      } catch (MyException e) {
         System.out.println("Caught MyException: " + e.getMessage());
      }
      
      System.out.println ("In main and after first");
   }
}  // Ex2

/* Define a subclass of Exception */
class MyException extends Exception {
    
    /** Constructor: an instance with message m*/
   public MyException(String m) {
      super(m);
   }
    
    /** Constructor: an instance with no message */
   public MyException() {
      super();
   }
}