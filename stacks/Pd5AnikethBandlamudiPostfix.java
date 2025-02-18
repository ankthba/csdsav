/***********************************************************************
Name: Aniketh Bandlamudi
Period: 5
Date: 12/19/24
What I Learned: 
- I learned how to use the Stack class to evaluate postfix expressions.
- I learned how to use the getNumericValue method to convert a character to an integer.
Credit (person who helped me): N/A
Student(s) whom I helped (to what extent): N/A
************************************************************************/    
import java.util.*;
   public class Pd5AnikethBandlamudiPostfix
   {
      public static void main(String[] args)
      {
         System.out.println("Enter a valid postfix expression (single digits only),");
         System.out.println("such as 35*1+");
         Scanner keyboard = new Scanner(System.in);
         String s = keyboard.next();  
         while(!s.equals("-1"))
         { 
            System.out.println(s + "  --->  " + eval(s) + "\n");
                System.out.println((s = "354*+7*") + " = " + eval(s) + "\n");
                  System.out.println((s = "82-") + " = " + eval(s) + "\n");
                  System.out.println((s = "82/") + " = " + eval(s) + "\n");
            s = keyboard.next();
         }
      }
      
      public static int eval(String x)
      {
         // create a stack
         Stack<Integer> stack = new Stack<Integer>();
         // scan the expression from left to right
         for (char ch : x.toCharArray()) {
            // if the character is a digit, push it onto the stack
            if (Character.isDigit(ch)) {
               // convert the character to an integer and push it
               stack.push(Character.getNumericValue(ch));
            }
            // if the character is an operator, pop two operands off the stack
            else if (isOperator(ch)) {
               // if there are fewer than two operands, the expression is invalid
               if (stack.size() < 2) {
                  // throw an exception
                  throw new IllegalArgumentException("Invalid postfix expression");
               }
               // pop the operands
               int b = stack.pop();
               int a = stack.pop();
               // evaluate the operator and push the result back onto the stack
               stack.push(eval(a, b, ch));
            }
         }
         // if there is more than one value on the stack, the expression is invalid
         if (stack.size() != 1) {  
            // throw an exception
            throw new IllegalArgumentException("Invalid postfix expression");
         }
         // return the result
         return stack.pop();
      }


      public static int eval(int a, int b, char ch)
      {
         // switch case used to determine the operator and perform the operation
          switch(ch) {
              case '+': return a + b;
              case '-': return a - b;
              case '*': return a * b;
              case '/': 
              
            // if the divisor is zero, throw an exception
            if (b == 0){
               throw new ArithmeticException("Division by zero");
            } 
            // return the result of the division
            return a / b;
            // if the operator is invalid, throw an exception
            default: 
               throw new IllegalArgumentException("Invalid operator: " + ch);
          }
      }

      // method to determine if a character is an operator
      public static boolean isOperator(char ch)
      {
         // return true if the character is an operator
         return ch == '+' || ch == '-' || ch == '*' || ch == '/';
      }
   }

   /* OUTPUT:

   Enter a valid postfix expression (single digits only),
   such as 35*1+
   345*+
   345*+  --->  23

   54*+7* = 161

   82- = 6

   82/ = 4





    */