//Name: Aniketh Bandlamudi
//Date: 4/3/2025
// What I learned: How to use HashMap and TreeMap, how to iterate through maps using an Iterator, and how to reverse a map by handling one to-many relationships using Lists
// How I feel about this lab: This lab helped me understand the applications of Maps and how to manipulate their data structures
// What I wonder: I could solve this by creating two parallel arrays (one for actors, one for grades) and implementing custom search methods, or by creating an Actor class with a grade field and using an ArrayList of Actor objects
// Credits: N/A


import java.util.*;
public class Pd5AnikethBandlamudiActingSchool
{
   public static void main(String[] args)
   {

      Map<String, String> sGrades = new HashMap<String, String>();     //HashMap
   
      sGrades.put("Jack Nicholson", "A-");
      sGrades.put("Humphrey Bogart", "A+");
      sGrades.put("Audrey Hepburn", "A");
      sGrades.put("Meryl Streep", "A-");
      sGrades.put("Jimmy Stewart", "A");
   
       // What you need to do:
       // 1. display initial data.  Use an iterator instead of using the built-in toString method of HashMap
       // Creates an iterator for the map entries and loops through each entry to display key value pairs
       System.out.println("Original Map (Actor -> Grade):");
       Iterator<Map.Entry<String, String>> it = sGrades.entrySet().iterator();
       while(it.hasNext()) {
           Map.Entry<String, String> entry = it.next();
           System.out.println(entry.getKey() + " -> " + entry.getValue());
       }
       System.out.println();
       
       // 2. reverse the map--use TreeMap
       //Explanation: Creates a TreeMap where each grade (former value) becomes a key that maps to a list of actors (former keys) with that grade
       Map<String, List<String>> reversedMap = new TreeMap<>();
       
       for(Map.Entry<String, String> entry : sGrades.entrySet()) {
           String actor = entry.getKey();
           String grade = entry.getValue();
           
           if(!reversedMap.containsKey(grade)) {
               reversedMap.put(grade, new ArrayList<>());
           }
           reversedMap.get(grade).add(actor);
       }
       
       // 3. display the reversed map
       // Explanation: Iterates through the reversed TreeMap to display each grade and its associated list of actors, leveraging TreeMap's natural ordering to sort grades
       System.out.println("Reversed Map (Grade -> Actors):");
       for(Map.Entry<String, List<String>> entry : reversedMap.entrySet()) {
           System.out.println(entry.getKey() + " -> " + entry.getValue());
       }
       
   } // main
} // ActingSchool_shell


/* OUTPUT
Original Map (Actor -> Grade):
Audrey Hepburn -> A
Meryl Streep -> A-
Jimmy Stewart -> A
Humphrey Bogart -> A+
Jack Nicholson -> A-

Reversed Map (Grade -> Actors):
A -> [Audrey Hepburn, Jimmy Stewart]
A+ -> [Humphrey Bogart]
A- -> [Meryl Streep, Jack Nicholson]
*/