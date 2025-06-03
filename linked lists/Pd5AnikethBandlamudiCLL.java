/***********************************************************************************************************************************************
 * Name: Aniketh Bandlamudi        
 * Period: 5
 * Name of the Lab: Circular Linked List
 * 
 * Purpose of the Program: 
 * To implement and demonstrate a generic circular linked list data structure
 * with operations for insertion, deletion, and traversal
 * 
 * Due Date: 11/26/24
 * Date Submitted: 11/25/24
 * 
 * What I learned: 
 * 1. How to maintain circular references by connecting the last node to the head
 * 2. Proper handling of edge cases (empty, single node)
 * 3. Implementation of deep copying to avoid reference sharing
 * 4. Using generics to create flexible data structures
 * 
 * ... 
 * How I feel about this lab: 
 * This lab increased my understanding of linked list implementations and pointer manipulation.
 * 
 * What I wonder: 
 * How the time complexity of operations might differ between circular and regular linked lists,
 * specifically for tail operations given direct access to the head
 * 
 * Student(s) who helped me (to what extent):  N/A
 * Student(s) whom I helped (to what extent): N/A
 *************************************************************************************

/*  CircularLinkedList <E> class
*/
class Pd5AnikethBandlamudiCLL<E> {
    private ListNode<E> head;  // head points to a circular linked list
    
    // Constructor - make 'head' a copy of 'list'
    public Pd5AnikethBandlamudiCLL(ListNode<E> list) {
        head = copy(list);
        if (head != null) {
            ListNode<E> temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(head); // make it circular
        }
    }
    
    // Helper method to copy a list
    // pre: list is not null
    // post: creates a deep copy of the list as a circular linked list
    private ListNode<E> copy(ListNode<E> list) {
        if (list == null) return null;
        ListNode<E> newHead = new ListNode<>(list.getValue(), null);
        ListNode<E> curr = newHead;
        list = list.getNext();
        while (list != null) {
            curr.setNext(new ListNode<>(list.getValue(), null));
            curr = curr.getNext();
            list = list.getNext();  
        }
        return newHead;
    }

    // pre: value is not null
    // post: inserts a new node with the given value at the front of the list
    public void insertFirst(E obj) {
        if (head == null) {
            head = new ListNode<>(obj, null);
            head.setNext(head);
        } else {
            ListNode<E> last = getLast();
            head = new ListNode<>(obj, head);
            last.setNext(head);
        }
    }

    // pre: value is not null
    // post: inserts a new node with the given value at the end of the list
    public void insertLast(E obj) {
        if (head == null) {
            head = new ListNode<>(obj, null);
            head.setNext(head);
        } else {
            ListNode<E> last = getLast();
            last.setNext(new ListNode<>(obj, head));
        }
    }

    // pre: list is not empty
    // post: returns the first node in the list
    public ListNode<E> getFirst() {
        return head;
    }

    // pre: list is not empty
    // post: returns the last node in the list
    public ListNode<E> getLast() {
        if (head == null) return null;
        ListNode<E> curr = head;
        while (curr.getNext() != head) {
            curr = curr.getNext();
        }
        return curr;
    }

    // pre: list is not empty
    // post: deletes the last node in the list and returns its value
    public E deleteLast() {
        if (head == null) return null;
        if (head.getNext() == head) {
            E value = head.getValue();
            head = null;
            return value;
        }
        ListNode<E> curr = head;
        while (curr.getNext().getNext() != head) {
            curr = curr.getNext();
        }
        E value = curr.getNext().getValue();
        curr.setNext(head);
        return value;
    }

    // pre: list is not empty
    // post: deletes the first node in the list and returns its value
    public E deleteFirst() {
        if (head == null) return null;
        E value = head.getValue();
        if (head.getNext() == head) {
            head = null;
        } else {
            ListNode<E> last = getLast();
            head = head.getNext();
            last.setNext(head);
        }
        return value;
    }

    // post: returns a string representation of the list
    public String toString() {
        if (head == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        ListNode<E> curr = head;
        do {
            sb.append(curr.getValue());
            curr = curr.getNext();
            if (curr != head) sb.append(", ");
        } while (curr != head);
        return sb.append("]").toString();
    }
}

// Test class implementation
 class TestCircular {
    public static void main(String[] args) {
        ListNode<Integer> list = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, null)));
        
        // Create CLL and test methods
        Pd5AnikethBandlamudiCLL<Integer> cll = new Pd5AnikethBandlamudiCLL<>(list);
        System.out.println("Initial list: " + cll);
        
        cll.insertFirst(0);
        System.out.println("After insertFirst(0): " + cll);
        
        cll.insertLast(4);
        System.out.println("After insertLast(4): " + cll);
        
        System.out.println("First value: " + cll.getFirst().getValue());
        System.out.println("Last value: " + cll.getLast().getValue());
        
        System.out.println("Deleted first: " + cll.deleteFirst());
        System.out.println("After deleteFirst: " + cll);
        
        System.out.println("Deleted last: " + cll.deleteLast());
        System.out.println("After deleteLast: " + cll);
    }
}

/*  ListNode <E> class
*/
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


/* OUTPUT
Initial list: [1, 2, 3]
After insertFirst(0): [0, 1, 2, 3]
After insertLast(4): [0, 1, 2, 3, 4]
First value: 0
Last value: 4
Deleted first: 0
After deleteFirst: [1, 2, 3, 4]
Deleted last: 4
After deleteLast: [1, 2, 3]
 */