/**************************************************** 
Name (Full Name): Aniketh Bandlamudi   Period:   5
Name of the Lab/Assignment:  U4: SLL programming assignment
Purpose: To Practice Linked Lists

Mistakes made:
Made small syntax errors that I quickly fixed.


How I feel about this programming experience: 
I feel like this improved my linked lists skills
****************************************************/


public class SingleLinkedList<E> {

    private ListNode<E> head;

    // nested class for ListNode
    private static class ListNode<E> {
        E data;
        ListNode<E> next;

        ListNode(E data) {
            this.data = data;
            this.next = null;
        }
    }

    // inserts a new node with the specified object at the end of the list
    public void insertLast(E obj) {
        ListNode<E> newNode = new ListNode<>(obj);
        if (head == null) {
            head = newNode;
        } else {
            ListNode<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // deletes the first occurrence of the specified object in the list
    public void delete(E obj) {
        if (head == null) return;

        if (head.data.equals(obj)) { 
        // if the head node contains the object
            head = head.next; 
            // remove the head
            return;
        }

        ListNode<E> current = head;
        while (current.next != null && !current.next.data.equals(obj)) {
            current = current.next;
        }

        if (current.next != null) { 
        // object found
            current.next = current.next.next; 
            // skip over the node to delete it
        }
    }

    // finds the first node containing the specified object and returns it
    public ListNode<E> find(E obj) {
        ListNode<E> current = head;
        while (current != null) {
            if (current.data.equals(obj)) {
                return current; 
                // object found
            }
            current = current.next;
        }
        return null; 
        // object not found
    }

    // returns a string representation of the list
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode<E> current = head;
        while (current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        return sb.toString();
    }
}
