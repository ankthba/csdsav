/*********************************************************************************
NAME:
PERIOD:
DATE SUBMITTED:
DUE DATE: 
ASSIGNMENT: 

PURPOSE OF THE LAB: 


MISTAKES MADE:
-
-


NEW CONCEPTS LEARNED:
-
-

HOW I FEEL ABOUT THIS LAB:
-
-

CREDITS: 

STUDENTS WHOM I HELPED: 
*/
import java.util.*;
public class ListExplorationExtensionShell
{
   public static void main(String[] args)
   {
      ListNode <String> head = new ListNode <>("hello", null);
      
      head = new ListNode <>("foo", head);
      head = new ListNode <>("boo", head);
      head = new ListNode <>("nonsense", head);
      head = new ListNode <>("computer",
         	 new ListNode <>("science",
         	 new ListNode <>("java",
         	 new ListNode <>("coffee", head))));
   
      print(head);
   /*
      ListNode <String > list = copyListIterative(head);
      ListNode <String> list1 = copyListRecursive(head);
      printRecur (head);
      ListNode <String> list2 = insertFirst(head);
      ListNode <String> list3 = insertLast (head);
      for (; list1 != null; list1 = list1.getNext())
      {  
         System.out.println (list1.getValue());
      }
      
      /**** uncomment the code below for ListExploration extension  ****/
      
   	// System.out.println("First = " + first(head));
      // System.out.println("Second = " + second(head));
      // ListNode  <String> p = pointerToLast(head);
      // System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
      // ListNode  <String> c = copyOfLast(head);
      // System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);
   	// 	
      // Scanner in = new Scanner(System.in);
      // System.out.print("Insert what? ");
      // String x = in.next();
      // head = insertFirst(head, x);
      // head = insertLast(head, x)
      // print(head);
   }
   // returns the value of the first node, or null if the list is empty 
   public static String first(ListNode <String> head) 
   {
      return null;
   }
   // returns the value of the second node, or null if the list is empty or 
   // if there is only one node.  
   // hint:  second could call the first of rest. 
   public static String second(ListNode <String>  head) 
   {
      return null;
   }
   // returns a reference to the last node in the list, or null if the 
   // list is empty.
   public static ListNode<String> pointToLast(ListNode <String> h)
   {
      return null;
   }
   // returns a copy of the last node (not just its value!).  
   // copyofLast can be recursive.  
   public static ListNode<String> copyOfLast(ListNode <String> h)
   {
      return null;
   }
   //returns a reference to a list whose first node's value is specified by the argument, and the 
   //first node's next links to the original list. 

   public static ListNode<String> insertFirst(ListNode <String> h, String w)
   {
      return null;
   }
   
   // returns a reference to a list whose last node's value is specified 
   // by the argument, such that this last node has been appended to 
   //the original list and had its next is set to null 
   public static ListNode<String> insertLast(ListNode <String> h, String w)
   {
      return null;
   }
   /*
   public static ListNode <String> copyListIterative (ListNode <String> list)
   {
   } // copyListIterative
   
   public static ListNode <String> copyListRecursive (ListNode <String> list)
   {
   }  // copyListRecursive
      
   public static ListNode <String> insertLastRecur (ListNode <String> list, String v)
   {
   } // insertLastRecur

   public static ListNode <String> insertLastIter (ListNode <String> list, String v)
   {
   } // insertLastIter
   */
   public static void print(ListNode  <String> head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   } // print
   /*
   public static void printRecur (ListNode <String> head)
   {
      if (head != null)
      {
         System.out.println (head.getValue());
         printRecur (head.getNext());
      }
   } // printRecur
   
   /* enter your code here */
      
      
} // ListExploration


class ListNode <E> 
{    
   private E value;    
   private ListNode <E>  next; 
   public ListNode  (E  initValue, ListNode <E> initNext)    
   { 
      value = initValue; 
      next = initNext; 
   }  
   public E getValue()  
   { 
      return value; 
   }    
  
   public ListNode <E> getNext() 
   { 
      return next;  
   } 
   
   public void setValue(E theNewValue)
   { 
      value = theNewValue;
   }
   
   public void setNext(ListNode  <E> theNewNext)
   { 
      next = theNewNext; 
   }
}  // end of ListNode

/*
Sample run:   
[computer, science, java, coffee, nonsense, boo, foo, hello]
First = computer
Second = science
Pointer to Last = hello at ListNode@ddfc2
Copy of Last = hello at ListNode@15dca2
Insert what? what?
[what?, computer, science, java, coffee, nonsense, boo, foo, hello, what?]


*/
