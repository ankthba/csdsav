/*
Question 1: What will happen when we compile this program?
Question 2: How do we deal with the compile time error? Where should we 
            make some changes to the program? Use the line # to tell us
            where you modify the program.
Question 3: After fixing the compile time error, what happens when
            run the syntax-free program? Any runtime error?
Question 4: How do we get rid of the runtime error if it exists?


*/
public class Ex0
{
   public static void first() throws Exception 
   {
      throw new Exception();
   } // first

   public static void main (String [] args) 
   {
      try {
         first();
      } catch (Exception e) {
         System.out.println("Caught an exception: " + e.getMessage());
      }
   
      System.out.println ("In main and after first");
   } // main
}  // Ex0

