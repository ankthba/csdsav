/***********************************************************************************************************************************************
 * Name: Aniketh Bandlamudi
 * Period: 5
 * Name of the Lab: Doubly, Circular Linked List
 * Purpose of the Program: To investigate different methods used on linked lists, specifically DCLLs.
  * Due Date: 11/20/24
 * Date Submitted: 11/19/24
 * What I learned:
 1. The implementation of a Doubly Circular Linked List and pointer management
 2. Edge cases in DCLL operations (empty list, single node, first/last node) require more consideration
 3. Use cases of Circularly Linked lists where continuous traversal is needed
 * ... 
 * How I feel about this lab: 
 * This lab increased my understanding of linked lsits. It helped me
 * investigate different methods used on linked lists, specifically DCLLs. 
 * 
 * What I wonder: How the Java Garbage Collector works in the background with Linked Lists versus
 * C++ pointers and memory management.
 * 
 * Student(s) who helped me (to what extent): N/A
 * Student(s) whom I helped (to what extent): N/A
 *************************************************************************************************************************************************/
public class Pd5AnikethBandlamudiDCLL <E> {   // Doubly, circular Linked List with a dummy header node
   private int size;
   private DCLNode <E> head = new DCLNode <> (); //dummy node--very useful--simplifies the code
   
   // pre: none
   // post: returns the number of elements in the list
   public int size()
   {
      return size;
   }
   /* appends obj to end of list; increases size;
     @return true  */
   // pre: obj is a valid element to add
   // post: adds obj to end of list and returns true
   public boolean add(E obj)
   {
     addLast(obj);
     return true;
      
   }
   /* inserts obj at position index.  increments size. 
   	*/

   // pre: index is within bounds (0 <= index <= size), obj is a valid element
   // post: inserts obj at specified index position, shifts elements right
   public void add(int index, E obj)
   {
      if (index < 0 || index > size)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

      DCLNode <E> current = head;
      for (int i = 0; i < index; i++) {
         current = current.getNext();
      }

      DCLNode <E> newNode = new DCLNode <E> (obj, current, current.getNext());
      current.getNext().setPrev(newNode);
      current.setNext(newNode);
      size++;

   }
   /* return obj at position index.  
   	*/
   // pre: 0 <= index < size
   // post: returns element at the specified index position
   public E get(int index)
   {
      if (index < 0 || index >= size) {
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      }

      DCLNode <E> current = head.getNext();
      for (int i = 0; i < index; i++) {
         current = current.getNext();
      }

      return current.getValue();
   }
   /* replaces obj at position index.  
   	*/
   // pre: 0 <= index < size, obj is a valid element
   // post: replaces element at specified index with obj
   public void set(int index, E obj)
   {
      if (index < 0 || index >= size) {
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      }

      DCLNode <E> current = head.getNext();
      for (int i = 0; i < index; i++) {
         current = current.getNext();
      }

      current.setValue(obj);
   }
   /*  removes the node from position index.  decrements size.
     @return the object at position index.
    */
   // pre: 0 <= index < size
   // post: removes and returns element at specified index, decrements size
   public E remove(int index)
   {
      if (index < 0 || index >= size) {
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      }

      DCLNode <E> current = head.getNext();
      for (int i = 0; i < index; i++) {
         current = current.getNext();
      }

      DCLNode <E> toRmv = current.getNext();
      current.setNext(toRmv.getNext());
      toRmv.getNext().setPrev(current);
      size--;

      return current.getValue();
   }
   /* inserts obj at front of list; increases size;
     */
   // pre: obj is a valid element
   // post: inserts obj at front of list, increments size
   public void addFirst(E obj)
   {
      DCLNode <E> newNode = new DCLNode <E> (obj, head, head.getNext());
      head.getNext().setPrev(newNode);
      head.setNext(newNode);
      size++;
   }
   /* appends obj to end of list; increases size;
       */
   // pre: obj is a valid element
   // post: appends obj to end of list, increments size
   public void addLast(E obj)
   {
      DCLNode<E> lastNode = head.getPrev();
      DCLNode <E> newNode = new DCLNode <E> (obj, lastNode, head);
      lastNode.setNext(newNode);
      head.setPrev(newNode);
      size++;
   }


   // pre: none
   // post: returns first element in list, or null if empty
   public E getFirst()
   {
      if (size == 0) {
         return null;
      }

      return head.getNext().getValue();
   }

   // pre: none
   // post: returns last element in list, or null if empty 
   public E getLast()
   {
      if (size == 0) {
         return null;
      }

      return head.getPrev().getValue();
   }

   // pre: none
   // post: removes and returns first element, decrements size, returns null if empty
   public E removeFirst()
   {
      if (size == 0) {
         return null;
      }

      DCLNode <E> firstNode = head.getNext();
      head.setNext(firstNode.getNext());
      firstNode.getNext().setPrev(head);
      size--;

      return firstNode.getValue();
   }

   // pre: none
   // post: removes and returns last element, decrements size, returns null if empty
   public E removeLast()
   {
      if (size == 0) {
         return null;
      }

      DCLNode <E> lastNode = head.getPrev();
      head.setPrev(lastNode.getPrev());
      lastNode.getPrev().setNext(head);
      size--;

      return lastNode.getValue();
   }

   // pre: none
   // post: returns string representation of list in format [elem1, elem2, ...]
   public String toString()
   {
      if (size == 0) {
         return "[]";
      }

      StringBuilder sb = new StringBuilder("[");
      DCLNode <E> current = head.getNext();
      while (current != head) {
         sb.append(current.getValue());
         if (current.getNext() != head) {
            sb.append(", ");
         }
         current = current.getNext();
      }
      return sb.append("]").toString();
   }



   public static void main(String args[])
   {
      Pd5AnikethBandlamudiDCLL <String> list = new Pd5AnikethBandlamudiDCLL <> ();	
   
      list.addLast("Apple");
      list.addLast("Banana");
      list.addLast("Cucumber");
      list.add("Dumpling");
      list.add("Escargot");
      System.out.println(list);
      System.out.println("Size: " + list.size());
      Object obj = list.remove(3);
      System.out.println(list);
      System.out.println("Size: " +list.size());
      System.out.println("Removed "+ obj);
      System.out.print("Add at 3:   ");
      list.add(3,"Cheese");
      System.out.println(list);
      System.out.println("Get values at 1 and first: " + list.get(1)+" and " + list.getFirst());
      System.out.println("No change: " +list);
      System.out.println( list.removeFirst() + " is now removed!"); 
      System.out.println(list);
      System.out.print("Add first:  ");
      list.addFirst("Anchovie");
      System.out.println(list);
      System.out.println("Size: " + list.size());
      System.out.print("Set the second:  ");
      list.set(2, "Rread");
      System.out.println(list);
   }
}

class DCLNode <E>
{
   private E value;
   private DCLNode prev;
   private DCLNode next;
   public DCLNode(E arg, DCLNode <E> p, DCLNode <E> n)
   {
      value=arg;
      prev=p;
      next=n;
   }
   public DCLNode()
   {
      value=null;
      next=this;
      prev=this;
   }
   public void setValue(E arg)
   {
      value=arg;
   }
   public void setNext(DCLNode <E> arg)
   {
      next=arg;
   }
   public void setPrev(DCLNode <E> arg)
   {
      prev=arg;
   }
   public DCLNode <E> getNext()
   {
      return next;
   }
   public DCLNode <E> getPrev()
   {
      return prev;
   }
   public E getValue()
   {
      return value;
   }
}  // end of DLNode


/*

Output:

[Apple, Banana, Cucumber, Dumpling, Escargot]
Size: 5
[Apple, Banana, Cucumber, Dumpling]
Size: 4
Removed Dumpling
Add at 3:   [Apple, Banana, Cucumber, Cheese, Dumpling]
Get values at 1 and first: Banana and Apple
No change: [Apple, Banana, Cucumber, Cheese, Dumpling]
Apple is now removed!
[Banana, Cucumber, Cheese, Dumpling]
Add first:  [Anchovie, Banana, Cucumber, Cheese, Dumpling]
Size: 5
Set the second:  [Anchovie, Banana, Rread, Cheese, Dumpling]
 
 */