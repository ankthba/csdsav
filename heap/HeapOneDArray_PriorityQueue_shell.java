/**************************************************************************************
 Name:   
 Date:
 What I learned:
 How I feel about this lab:

 I am wondering (the what-if moment):
 Credits:
***************************************************************************************/
import java.util.*;

public class HeapOneDArray_PriorityQueue_shell<E extends Comparable<E>> {
    private static final int DEFAULT_CAPACITY = 1024;
    private Comparable[] items; // use a 1-D array instead of ArrayList
    private int numItems; // number of elements in items

    public static void main(String[] args) {
        // Create a HeapPriorQueue_shell object to test all the methods in this class
        HeapOneDArray_PriorityQueue_shell<Integer> pq = new HeapOneDArray_PriorityQueue_shell<>();

        // Test isEmpty method
        System.out.println("Is heap empty? " + pq.isEmpty());

        // Test add method
        pq.add(10);
        System.out.println("Added 10, heap: " + pq);

        // Test peek method
        System.out.println("Peek: " + pq.peek());

        // Test remove method
        System.out.println("Removed: " + pq.remove());
        System.out.println("Heap after removal: " + pq);
    }

    public HeapOneDArray_PriorityQueue_shell() {
        items = new Comparable[DEFAULT_CAPACITY];
        numItems = 0;
    }

    public HeapOneDArray_PriorityQueue_shell(int initialCapacity) {
        items = new Comparable[initialCapacity];
        numItems = 0;
    }

    // precondition:
    // postcondition:
    public boolean isEmpty() {
        return numItems == 0;
    }

    // precondition:
    // postcondition:
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) items[1];
    }

    // precondition:
    // postcondition:
    public E remove() {
        if (isEmpty()) {
            return null;
        }
        E min = (E) items[1];
        items[1] = items[numItems];
        items[numItems] = null;
        numItems--;
        reheapDown(1);
        return min;
    }

    // precondition:
    // postcondition:
    public boolean add(E obj) {
        if (numItems + 1 == items.length) {
            doubleCapacity();
        }
        items[++numItems] = obj;
        reheapUp();
        return true;
    }

    // precondition:
    // postcondition:
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= numItems; i++) {
            sb.append(items[i]).append(" ");
        }
        return sb.toString();
    }

    // precondition:
    // postcondition:
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

    // precondition:
    // postcondition:
    private void reheapUp() {
        int index = numItems;
        Comparable tmp = items[index];
        while (index > 1 && tmp.compareTo(items[index / 2]) < 0) {
            items[index] = items[index / 2];
            index /= 2;
        }
        items[index] = tmp;
    }

    // precondition:
    // postcondition:
    private void doubleCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }
}