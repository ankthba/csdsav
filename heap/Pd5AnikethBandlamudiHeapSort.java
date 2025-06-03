/****************************************************************************
 Name: Aniketh Bandlamudi
 Lab Assignment: Heap Sort Lab
 Purpose of the program: 
 To implement heap sort algorithm through deterministic and randomized input scenarios

 What I Learned (be as specific as possible):
 - Refined the heapification process and its role in establishing max heap property
 - Understood the time complexity advantages of heap sort O(n log n) compared to other sorting algorithms
 - Implemented operations while maintaining integrity

 How I feel about this lab:
 This lab provided valuable insights into heap operations
 
 What I am wondering:
 I wonder about potential optimizations for heap sort in specific use cases and its 
 performance  O(n log n) sorting algorithms
 
 Credits: N/A
 
 Students helped: N/A
 ****************************************************************************/


 public class Pd5AnikethBandlamudiHeapSort
 {
    public static void main(String[] args)
    {
    //Part 1: Given a max heap, sort it. Do this part first. 
       double heap[] = {-1,99,80,85,17,30,84,2,16,1};
       display(heap);
       sort(heap);
       display(heap);
       System.out.println(isSorted(heap));
    
    //Part 2:  Generate 10 random numbers, make a heap, sort it.
       int SIZE = 10;
       double[] randomHeap = new double[SIZE + 1];
       randomHeap = createRandom(randomHeap);
       display(randomHeap);
       makeHeap(randomHeap);  
       display(randomHeap); 
       sort(randomHeap);
       display(randomHeap);
       System.out.println(isSorted(randomHeap));
    }
    
    //******* Part 1 ******************************************
    // precondition:
    // postcondition:
    public static void display(double[] array)
    {
       for(int k = 1; k < array.length; k++)
          System.out.print(array[k] + "    ");
       System.out.println("\n");	
    }
    // precondition: array[0] = -1, array[1...n] is a max heap
    // postcondition: array is sorted in ascending order
    public static void sort(double[] array)
    {
        int size = array.length - 1;
        
        // Extract elements from the heap one by one
        for (int i = size; i > 1; i--) {
            // Move current root to end
            swap(array, 1, i);
            
            // Call heapDown on the reduced heap
            heapDown(array, 1, i - 1);
        }
    }
    // precondition: array contains the elements to sort, indices a and b are valid
    // postcondition: elements at indices a and b are swapped
    public static void swap(double[] array, int a, int b)
    {
        double temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    // precondition: array is a max heap from 1 to size, except possibly at position k
    // postcondition: array is a max heap from 1 to size
    public static void heapDown(double[] array, int k, int size)
    {
        int largest = k;
        int left = 2 * k;
        int right = 2 * k + 1;
        
        // Find the largest among node, left child and right child
        if (left <= size && array[left] > array[largest])
            largest = left;
            
        if (right <= size && array[right] > array[largest])
            largest = right;
            
        // If largest is not the node itself, swap and recursively heapify
        if (largest != k) {
            swap(array, k, largest);
            heapDown(array, largest, size);
        }
    }
    // precondition: array contains elements to check
    // postcondition: returns true if array is sorted in ascending order, false otherwise
    public static boolean isSorted(double[] arr)
    {
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    // ****** Part 2 *******************************************
 
    //Generate 100 random numbers between 1 and 100, formatted to 2 decimal places
    //postcondition:  array[0] == -1, the rest of the array is random
    public static double[] createRandom(double[] array)
    {  
        array[0] = -1;
        for (int i = 1; i < array.length; i++) {
            double randomValue = 1 + 99 * Math.random();
            array[i] = Math.round(randomValue * 100) / 100.0; // Format to 2 decimal places
        }
        return array;
    }
    
    //Turn the random array into a MAX heap
    //postcondition:  array[0] == -1, the rest of the array is in heap-order
    private static void makeHeap(double[] array)
    {
        int size = array.length - 1;
        // Start from the last non-leaf node and build the heap bottom-up
        for (int i = size / 2; i >= 1; i--) {
            heapDown(array, i, size);
        }
    }
    
 } // HeapSort_shell

 /*** OUTPUT

 99.0    80.0    85.0    17.0    30.0    84.0    2.0    16.0    1.0    

1.0    2.0    16.0    17.0    30.0    80.0    84.0    85.0    99.0    

true
17.58    52.63    76.7    76.72    49.6    95.72    28.24    42.77    26.49    83.86    

95.72    83.86    76.7    76.72    52.63    17.58    28.24    42.77    26.49    49.6    

17.58    26.49    28.24    42.77    49.6    52.63    76.7    76.72    83.86    95.72    

true


 ***/