/**************************************************************************************
 Name: Aniketh Bandlamudi
 Date: 02/26/2025
 What I learned:
 I learned how to implement a heap using a 1-D array.
 I learned how to implement the add, remove, peek, isEmpty, reheapDown, reheapUp, and doubleCapacity methods.
 How I feel about this lab:
 I feel that this lab helped me better understand how to implement a heap using a 1-D array and
 and how to implement different methods to manipulate the heap.
 I am wondering (the what-if moment):
 What if we could optimize the reheapUp and reheapDown methods 
 to improve the performance of the heap operations
 Credits: N/A
***************************************************************************************/
import java.util.*;

public class Pd5AnikethBandlamudiHeap<E extends Comparable<E>> {
    private static final int DEFAULT_CAPACITY = 1024;
    private Comparable[] items; // use a 1-D array instead of ArrayList
    private int numItems; // number of elements in items

    public static void main(String[] args) {
        // Create a HeapPriorQueue_shell object to test all the methods in this class
        Pd5AnikethBandlamudiHeap<Integer> pq = new Pd5AnikethBandlamudiHeap<>();

        // Test isEmpty method
        System.out.println("Is heap empty? " + pq.isEmpty());

        // Test add method
        pq.add(10);
        pq.add(4);
        pq.add(15);
        pq.add(20);
        pq.add(0);

        // Test toString method
        System.out.println("Heap elements: " + pq.toString());

        // Test peek method
        System.out.println("Peek element: " + pq.peek());

        // Test remove method
        System.out.println("Removed element: " + pq.remove());
        System.out.println("Heap elements after removal: " + pq.toString());
    }

    public Pd5AnikethBandlamudiHeap() {
        items = new Comparable[DEFAULT_CAPACITY];
        numItems = 0;
    }

    public Pd5AnikethBandlamudiHeap(int initialCapacity) {
        items = new Comparable[initialCapacity];
        numItems = 0;
    }

    // precondition: the heap has been initialized
    // postcondition: returns true if the heap is empty or false otherwise
    public boolean isEmpty() {
        return numItems == 0;
    }

    // precondition: the heap has been initialized
    // postcondition: returns the minimum element in the heap without removing it or null if the heap is empty
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) items[1];
    }

    // precondition: The heap has been initialized.
    // postcondition: removes and returns the minimum element in the heap or null if the heap is empty, the heap property is maintained
    public E remove() {
        if (isEmpty()) {
            return null;
        }
        E min = (E) items[1];
        items[1] = items[numItems];
        numItems--;
        reheapDown(1);
        return min;
    }

    // precondition: the heap has been initialized
    // postcondition: adds the specified element to the heap, the heap property is maintained, returns true
    public boolean add(E obj) {
        if (numItems == items.length - 1) {
            doubleCapacity();
        }
        items[++numItems] = obj;
        reheapUp();
        return true;
    }

    // precondition: the heap has been initialized
    // postcondition: returns a string representation of the heap elements
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= numItems; i++) {
            sb.append(items[i]).append(" ");
        }
        return sb.toString();
    }

    // precondition: the heap has been initialized and the index is a valid position in the heap
    // postcondition: restores the heap property by moving the element at the specified index down the heap
    private void reheapDown(int index) {
        int child;
        Comparable tmp = items[index];
        while (index * 2 <= numItems) {
            child = index * 2;
            if (child != numItems && items[child + 1].compareTo(items[child]) < 0) {
                child++;
            }
            if (items[child].compareTo(tmp) < 0) {
                items[index] = items[child];
            } else {
                break;
            }
            index = child;
        }
        items[index] = tmp;
    }

    // precondition: the heap has been initialized and the index is a valid position in the heap
    // postcondition: restores the heap property by moving the element at the specified index up the heap
    private void reheapUp() {
        int index = numItems;
        Comparable tmp = items[index];
        while (index > 1 && tmp.compareTo(items[index / 2]) < 0) {
            items[index] = items[index / 2];
            index /= 2;
        }
        items[index] = tmp;
    }

    // precondition: the heap has been initialized
    // postcondition: doubles the capacity of the heap's underlying array
    private void doubleCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }
}

/* OUTPUT

Is heap empty? true
Heap elements: 0 4 15 20 10 
Peek element: 0
Removed element: 0
Heap elements after removal: 4 10 15 20 

*/