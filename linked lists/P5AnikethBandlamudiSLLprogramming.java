/**************************************************** 
Name (Full Name): Aniketh Bandlamudi   Period:   5
Name of the Lab/Assignment:  U4: SLL programming assignment
Purpose: To Practice Linked Lists

Mistakes made:
Made small syntax errors that I quickly fixed.


How I feel about this programming experience: 
I feel like this improved my linked lists skills
****************************************************/



public class P5AnikethBandlamudiSLLprogramming<E> {

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
            head = head.next;
            return;
        }

        ListNode<E> current = head;
        while (current.next != null && !current.next.data.equals(obj)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    // finds the first node containing the specified object and returns it
    public ListNode<E> find(E obj) {
        ListNode<E> current = head;
        while (current != null) {
            if (current.data.equals(obj)) {
                return current;
            }
            current = current.next;
        }
        return null;
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

    // main method to test the SingleLinkedList class
    public static void main(String[] args) {
        P5AnikethBandlamudiSLLprogramming<Integer> list = new P5AnikethBandlamudiSLLprogramming<>();

        // testing insertLast method
        System.out.println("Inserting elements at the end:");
        list.insertLast(10);
        list.insertLast(20);
        list.insertLast(30);
        System.out.println("List after insertions: " + list);

        // testing delete method
        System.out.println("\nDeleting an element (20) from the list:");
        list.delete(20);
        System.out.println("List after deletion: " + list);

        // testing find method
        System.out.println("\nFinding an element (10) in the list:");
        ListNode<Integer> foundNode = list.find(10);
        System.out.println("Found node: " + (foundNode != null ? foundNode.data : "null"));

        System.out.println("\nTrying to find an element (40) that is not in the list:");
        foundNode = list.find(40);
        System.out.println("Found node: " + (foundNode != null ? foundNode.data : "null"));
    }
}
