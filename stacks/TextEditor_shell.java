/*****************************************************************************************************************
NAME:    
PERIOD: 
DUE DATE: 
ASSIGNMENT: 

PURPOSE: 

LEARNED:
- 
            
CREDITS: 

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
      
            
            
      printStack(text);
   }//editText
   
   //pre:  none
   //post: prints the Stack in a nicer format, ex. abc instead of [a, b, c]
   public static void printStack(Stack<Character> s)
   {     
      // your code goes here
    
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