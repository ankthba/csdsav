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
public class Pd5AnikethBandlamudiSLLExploration
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
   */
      /**** uncomment the code below for ListExploration extension  ****/
      
   	System.out.println("First = " + first(head));
      System.out.println("Second = " + second(head));
      ListNode <String> p = pointToLast(head);
      System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
      ListNode  <String> c = copyOfLast(head);
      System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);
   		
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in.next();
      head = insertFirst(head, x);
      head = insertLast(head, x);
      print(head);
   }
   // returns the value of the first node, or null if the list is empty 
   public static String first(ListNode <String> head) 
   {
      if (head == null) {
         return null;
      }

      return head.getValue();
   }
   // returns the value of the second node, or null if the list is empty or 
   // if there is only one node.  
   // hint:  second could call the first of rest. 
   public static String second(ListNode <String>  head) 
   {
      if (head == null || head.getNext() == null) {
         return null;
      }
      
      return head.getNext().getValue();
   }
   // returns a reference to the last node in the list, or null if the 
   // list is empty.
   public static ListNode<String> pointToLast(ListNode <String> h)
   {
      if (h == null) {
         return null;
      }
      
      while(h.getNext() != null) {
         h = h.getNext();
      }

      return h;
   }
   // returns a copy of the last node (not just its value!).  
   // copyOfLast can be recursive.  
   public static ListNode<String> copyOfLast(ListNode <String> h)
   {
      if (h == null) {
         return null;
      }
      if (h.getNext() == null) {
         return new ListNode<String>(h.getValue(), null);
      }
      return copyOfLast(h.getNext());
   }

   //returns a reference to a list whose first node's value is specified by the argument, and the 
   //first node's next links to the original list. 
   public static ListNode<String> insertFirst(ListNode <String> h, String w)
   {
      return new ListNode<String>(w, h);
   }
   
   // returns a reference to a list whose last node's value is specified 
   // by the argument, such that this last node has been appended to 
   //the original list and had its next is set to null 
   public static ListNode<String> insertLast(ListNode <String> h, String w)
   {
      ListNode<String> temp = new ListNode<String>(w, null);
      ListNode<String> p = h;
      if (h == null) {
         return temp;
      }
      while(p.getNext() != null) {
         p = p.getNext();
      }
      p.setNext(temp);
      return h;
   }
   
   public static ListNode <String> copyListIterative (ListNode <String> list)
   {
      if (list == null) {
         return null;
      }

      ListNode<String> newHead = new ListNode<String>(list.getValue(), null);
      ListNode<String> current = newHead;
      ListNode<String> og = list.getNext();

      while(og != null) {
         current.setNext(new ListNode<String>(og.getValue(), null));
         og = og.getNext();
         current = current.getNext();
      }

      return newHead;

   } // copyListIterative
   
   public static ListNode <String> copyListRecursive (ListNode <String> list)
   {
      if (list == null) {
         return null;
      }
      return new ListNode<String>(list.getValue(), copyListRecursive(list.getNext()));
   }  // copyListRecursive
      
   public static ListNode <String> insertLastRecur (ListNode <String> list, String v)
   {
      if (list == null) {
         return new ListNode<String>(v, null);
      }
      list.setNext(insertLastRecur(list.getNext(), v));
      return list;
   } // insertLastRecur

   public static ListNode <String> insertLastIter (ListNode <String> list, String v)
   {
      ListNode<String> temp = new ListNode<String>(v, null);
      ListNode<String> p = list;
      if (list == null) {
         return temp;
      }
      while(p.getNext() != null) {
         p = p.getNext();
      }
      p.setNext(temp);
      return list;
   } // insertLastIter
   
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
   
   public static void printRecur(ListNode<String> head)
   {
      if (head == null) {
         return;
      }
      
      // Print opening bracket only at start
      if (head.getNext() == null) {
         System.out.print("[" + head.getValue() + "]");
      }
      else {
         System.out.print(head.getValue() + ", ");
         printRecur(head.getNext());
      }
   }
   
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
