/*****************************************************************************************************************
NAME: Aniketh Bandlamudi
PERIOD: 5
DUE DATE: 12/13/2024
ASSIGNMENT: Text Editor

PURPOSE: 
To create a text editor program that simulates editing functionality using the
Stack data structure. The '-' acts as a backspace and '$' clears the entire line.

LEARNED:
- How to implement a Stack for a basic text editor
- How to handle backspace and clear operations with specific characters
- How to maintain character order when displaying a stack's contents 
- How to use stack objects to reverse and display a string
            
CREDITS: N/A

****************************************************************************************************************/
import java.util.*;
public class Pd5AnikethBandlamudiTextEditor
{

   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      Scanner choice = new Scanner(System.in);
      String again;
      do
      {
         System.out.print("Enter a line of text: ");
         String input = sc.nextLine();
         editText(input);
         System.out.print("\nAgain (y/n)? ");
         again = choice.next();
      }while(!again.equals("n"));
   }//main
   
   //pre:  s is not null
   //post: edits a String according to certain characters it contains and prints the resulted string
   public static void editText(String s)
   {
      Stack<Character> text = new Stack<Character>();
      // your code goes here
      // for loop to iterate through the string
      for (int i = 0; i < s.length(); i++) {
         // get the character at the current index
         char c = s.charAt(i);
         // check if the character is a backspace
         if (c == '-') {
            // check if the stack is not empty
            if (!text.isEmpty()) {
               // remove the top element from the stack
               text.pop();
            }
         }
         // check if the character is a clear
         else if (c == '$') {
            // clear the stack
            text.clear();
         } else {
            // add the character to the stack if it is not a backspace or clear
            text.push(c);
         }
      }
            
            
      printStack(text);
   }//editText
   
   //pre:  none
   //post: prints the Stack in a nicer format, ex. abc instead of [a, b, c]
   public static void printStack(Stack<Character> s)
   {     
      // your code goes here
      // create a temporary stack to store the elements of the original stack
      Stack<Character> temp = new Stack<Character>();
      // print the elements of the original stack in reverse order
      System.out.print("Here is the line you entered: ");
      // pop all the elements from the original stack and push them to the temporary stack
      while(!s.isEmpty()) {
         temp.push(s.pop());
      }

      // pop all the elements from the temporary stack and print them
      while(!temp.isEmpty()) {
         System.out.print(temp.pop());
      }
      System.out.println();
    
   }//printStack
}

/*
----jGRASP exec: java TextEditor_shell
Enter a line of text: Ca-noe$Ra3-fx-t

Here is the line you entered: Raft
Again (y/n)? y
Enter a line of text: AP$$-Compp-utee-r Sic--cei--ience

Here is the line you entered: Computer Science
Again (y/n)? y
Enter a line of text: He$He was astg-- tall ae-s a$ 6 foot,- 3 inchre-- treeu-

Here is the line you entered:  6 foot 3 inch tree
Again (y/n)? y
Enter a line of text: bone matrix and pivot joint$

Here is the line you entered: 
Again (y/n)? y
Enter a line of text: dey$daybsah---reakk-s be-ell

Here is the line you entered: daybreaks bell
Again (y/n)? n

 ----jGRASP: operation complete.
*/