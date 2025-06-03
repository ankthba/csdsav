/*****************************************************************************************************************
NAME:      Aniketh Bandlamudi
PERIOD:    5
DUE DATE:  3/28/25

PURPOSE:    Implement a set data structure using an array and basic set operations.

WHAT I LEARNED:   How to implement a set data structure using an array and basic set operations.
         
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITE): N/A

****************************************************************************************************************/

// class to implement a set data structure using an array and basic set operations
public class Pd5AnikethBandlamudiMySet {
    private Object[] elements;
    private int size;

    // constructor to initialize the array and size
    public Pd5AnikethBandlamudiMySet() {
        elements = new Object[10];
        size = 0;
    }

    // pre: element is not null
    // post: adds the element to the set if it is not already present
    public boolean add(Object element) {
        if (!contains(element)) {
            if (size == elements.length) {
                resize();
            }
            elements[size++] = element;
            return true;
        }
        return false;
    }

    // pre: element is not null
    // post: removes the element from the set if it is present
    public boolean remove(Object element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                elements[i] = elements[size - 1];
                elements[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    // pre: element is not null
    // post: returns true if the set contains the element, false otherwise
    public boolean contains(Object element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    // pre: none
    // post: returns the number of elements in the set
    public int size() {
        return size;
    }

    // pre: none
    // post: doubles the size of the array
    private void resize() {
        Object[] newElements = new Object[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    // test the set data structure
    // pre: none
    // post: test the set data structure
    public static void main(String[] args) {
        Pd5AnikethBandlamudiMySet mySet = new Pd5AnikethBandlamudiMySet();
        System.out.println("Adding elements:");
        System.out.println("Add 1: " + mySet.add(1));
        System.out.println("Add 2: " + mySet.add(2));
        System.out.println("Add 1 again: " + mySet.add(1));
        System.out.println("Current size: " + mySet.size());

        System.out.println("\nChecking elements:");
        System.out.println("Contains 1: " + mySet.contains(1));
        System.out.println("Contains 3: " + mySet.contains(3));

        System.out.println("\nRemoving elements:");
        System.out.println("Remove 2: " + mySet.remove(2));
        System.out.println("Current size after removal: " + mySet.size());
        System.out.println("Contains 2: " + mySet.contains(2));
    }
}

/* OUTPUT

Adding elements:
Add 1: true
Add 2: true
Add 1 again: false
Current size: 2

Checking elements:
Contains 1: true
Contains 3: false

Removing elements:
Remove 2: true
Current size after removal: 1
Contains 2: false

 */