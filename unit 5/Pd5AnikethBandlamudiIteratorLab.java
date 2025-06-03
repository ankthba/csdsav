/*****************************************************************************************************************
NAME: Aniketh Bandlamudi
PERIOD: 5
DUE DATE: 12/11/2024
ASSIGNMENT: Iterator Lab

PURPOSE: To practice using iterators and for each loops in Java.

WHAT I LEARNED: How to manipulate collections using iterators and for each loops.
            
CREDITS: N/A

****************************************************************************************************************/

   // NOTE :  Use only for-each loops or iterators, NOT regular for-loops
   //        Points will be taken off if regular for loops are used.
import java.io.*;
import java.util.*;
public class Pd5AnikethBandlamudiIteratorLab
{
   public static void main(String[] args)
   {
      System.out.println("Iterator Lab\n");
      int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};
      for(int n : rawNumbers )
         System.out.print(n + " ");    
      ArrayList<Integer> numbers = createNumbers(rawNumbers);
      System.out.println("ArrayList: "+ numbers);      //Implicit Iterator!
      System.out.println("Count negative numbers: " + countNeg(numbers));
      System.out.println("Average: " + average(numbers));
      System.out.println("Replace negative numbers: " + replaceNeg(numbers));
      System.out.println("Delete zeross: " + deleteZero(numbers));
      String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
               "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
      ArrayList<String> movies = createMovies(rawMovies);
      System.out.println("Movies: " + movies);
      System.out.println("Movies: " +  removeDupes(movies));
   }
   // pre: an array of just int values 
   // post: return an ArrayList containing all the values
   public static ArrayList<Integer> createNumbers(int[] rawNumbers) 
   {
      ArrayList<Integer> nums = new ArrayList<>();
      // copy each number from array to ArrayList
      for(int num : rawNumbers) {
         nums.add(num);
      }
      return nums;
   }
   // pre: an array of just Strings  
   // post: return an ArrayList containing all the Strings
   public static ArrayList<String> createMovies(String[] rawWords) 
   {
      ArrayList<String> movies = new ArrayList<>();
      // copy each movie title from array to ArrayList
      for(String movie : rawWords) {
         movies.add(movie);
      }
      return movies;
   }
   
   // pre: ArrayList a is not empty and contains only Integer objects
   // post: return the number of negative values in the ArrayList a
   public static int countNeg(ArrayList<Integer> a)
   {
      int count = 0;
      // increment counter for each negative number found
      for (int num : a) {
         if (num < 0) {
            count++;
         }
      }
      return count;
   }
   // pre: ArrayList a is not empty and contains only Integer objects
   // post: return the average of all values in the ArrayList a
   public static double average(ArrayList<Integer> a)
   {
      double sum = 0;
      // add up all numbers
      for (int num : a) {
         sum += num;
      }
      // return average (sum divided by count)
      return sum / a.size();
   }
   
   // NOTE: in this method, you must use an iterator, NO for-each loop
      // pre: ArrayList a is not empty and contains only Integer objects
      // post: replaces all negative values with 0 
      public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a)
      {
        Iterator<Integer> it = a.iterator();
        int index = 0;
        // traverse list with iterator and replace negatives
        while(it.hasNext()) {
          int num = it.next();
          if(num < 0) {
            a.set(index, 0);
          }
          index++;
        }
        return a;
      }
      
   // NOTE: in this method, you must use an iterator, NO for-each loop
   // pre: ArrayList a is not empty and contains only Integer objects
   // post: deletes all zeros in the ArrayList a
   public static ArrayList<Integer> deleteZero(ArrayList<Integer> a) {
      Iterator<Integer> it = a.iterator();
      // use iterator to safely remove elements while traversing
      while(it.hasNext()) {
         int num = it.next();
         if(num == 0) {
            it.remove();
         }
      }
      return a;
   }
   // pre: ArrayList a is not empty and contains only String objects
   // post: return ArrayList without duplicate movie titles
	// strategy: start with an empty array and add movies as needed
   public static ArrayList<String> removeDupes(ArrayList<String> a) {
      ArrayList<String> uniqueMovies = new ArrayList<>();
      // only add movies that haven't been seen before
      for(String movie : a) {
         if(!uniqueMovies.contains(movie)) {
            uniqueMovies.add(movie);
         }
      }
      return uniqueMovies;
   }
   
}

/* OUTPUT

-9 4 2 5 -10 6 -4 24 20 -28 ArrayList: [-9, 4, 2, 5, -10, 6, -4, 24, 20, -28]
Count negative numbers: 4
Average: 1.0
Replace negative numbers: [0, 4, 2, 5, 0, 6, 0, 24, 20, 0]
Delete zeross: [4, 2, 5, 6, 24, 20]
Movies: [High_Noon, High_Noon, Star_Wars, Tron, Mary_Poppins, Dr_No, Dr_No, Mary_Poppins, High_Noon, Tron]
Movies: [High_Noon, Star_Wars, Tron, Mary_Poppins, Dr_No]


 */