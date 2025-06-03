/***********************************************************************
Name: Aniketh Bandlamudi
Period: 5
Date: 12/17/24
What I Learned: 
   - I learned how to use the Stack class to check if a string has balanced grouping symbols.
   - I learned how to use the indexOf method to check if a character is a left or right grouping symbol.
   - I learned how to use the substring method to get a character at a specific index.
Credit (person who helped me): N/A
Student(s) whom I helped (to what extent): N/A
************************************************************************/   
import java.util.*;
public class Pd5BandlamudiAnikethParenMatch
{
   // string of left grouping symbols
   public static final String left  = "([{<";
   // string of right grouping symbols, in the same order
   public static final String right = ")]}>";
   public static void main(String[] args)
   {
   
      // prints directions
      System.out.println("Enter an expression with grouping symbols,");
      System.out.println("such as (2+3)-[5*(6+1)]IndexMals");
      // creates a scanner object
      Scanner keyboard = new Scanner(System.in);
      // reads in the first string
      String s = keyboard.next(); 
      // while the string is not -1 
      while(!s.equals("-1"))
      {
         // calls the check method and stores the result in a boolean variable
         boolean flag = check(s);
         // if the flag is true, print the string is good
         if(flag)
            System.out.println(s + " is good.");
         // else print the string is bad
         else
            System.out.println("No. Bad.  " + s);
         System.out.println();
         s = keyboard.next();
      }
   }
   // precondition:
   // postcondition: 
   public static boolean check(String s)
   {
      // creates a stack object
      Stack<String> stack = new Stack<String>();
      // for loop that goes through the string
      for(int i = 0; i < s.length(); i++)
      {
         // creates a string object that stores the character at the index
         String ch = s.substring(i, i+1);
         // if the character is a left grouping symbol, push it onto the stack
         if(left.indexOf(ch) >= 0)
            // pushes the character onto the stack
            stack.push(ch);
            // if the character is a right grouping symbol
         else if(right.indexOf(ch) >= 0)
         {
            // if the stack is empty, return false
            if(stack.isEmpty())
               // returns false
               return false;
            // if the character at the top of the stack is not the same as the character at the index
            if(left.indexOf(stack.peek()) != right.indexOf(ch))
               return false;
            // pops the character off the stack
            stack.pop();
         }
      }
      // returns true if the stack is empty
      return stack.isEmpty();
   }
}
// Program outputs should include all the test cases described in the lab specifications
/*

Enter an expression with grouping symbols,
such as (2+3)-[5*(6+1)]IndexMals
5+7

5+7 is good.

(5+7)
(5+7) is good.

)5+7(
No. Bad.  )5+7(

((5+7)*3)
((5+7)*3) is good.

[(5+7)*]3
[(5+7)*]3 is good.

<{5+7}*3>
<{5+7}*3> is good.

(5+7)*3
(5+7)*3 is good.

5+(7*3)
5+(7*3) is good.

((5+7)*3
No. Bad.  ((5+7)*3

[(5+7]*3)   
No. Bad.  [(5+7]*3)

[(5+7)*3])  
No. Bad.  [(5+7)*3])

([(5+7)*3]
No. Bad.  ([(5+7)*3]



 */