/*****************************************************************************************************************
NAME:      Aniketh Bandlamudi
PERIOD:    5
DUE DATE:  3/24/25

PURPOSE:    Implement three different collision resolution schemes in hash tables
            and appropriate search algorithms for each.

WHAT I LEARNED:    
         How different collision resolution strategies work and their implementations.
         
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITE): N/A

****************************************************************************************************************/
/***********************************************************************************
   Assignment:  This hashing program results in collisions.
                You are to implement three different collision schemes: 
                linear probing, relative prime probing (use the first relatively prime 
                number of the length of the hash table as the step increase), and 
                chaining.  Then implement a search algorithm that is appropriate
                for each collision scheme.
 ***********************************************************************************/
import java.util.*;
import javax.swing.*;

public class Pd5AnikethBandlamudiHashing
{
   public static void main(String[] args)
   {
      int arrayLength = Integer.parseInt(JOptionPane.showInputDialog(
                         "Hashing!\n"+
                         "Enter the size of the array:  ")); // enter 20
       
      int numItems = Integer.parseInt(JOptionPane.showInputDialog(
                         "Add n items:  "));                 // enter 15
     
      int scheme = Integer.parseInt(JOptionPane.showInputDialog(
           "The Load Factor is " + (double)numItems/arrayLength +
           "\nWhich collision scheme?\n"+
           "1. Linear Probing\n" +
           "2. Relatively Prime Probing\n"+
           "3. Chaining"));
      Hashtable table = null;
      switch( scheme )
      {
         case 1:   
            table = new HashtableLinearProbe(arrayLength);
            break;
         case 2: // rehash using the first relatively prime of arrayLength
            table = new HashtableRelativePrime(arrayLength); 
            break;
         case 3:  
            table = new HashtableChaining(arrayLength);
            break;
         default:  System.exit(0);    
      }
      for(int i = 0; i < numItems; i++)
         table.add("Item" + i);
      String action = JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1));
      int itemNumber = 0;
      if (action != null)
      {
         itemNumber = Integer.parseInt(action);
         while( itemNumber != -1 )
         {
            String key = "Item" + itemNumber;
            int index = table.indexOf(key); 
            if( index >= 0)    //found it
               System.out.println(key + " found  at index " + index);
            else
               System.out.println(key + " not found!");
            action = JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1));
            if (action != null)
               itemNumber = Integer.parseInt(action); 
            else
               itemNumber = -1;                         
         } 
      }
      System.out.println ("Goodbye!");
      System.exit(0);
   } // main
} // Hashing

interface Hashtable
{
   void add(Object obj);
   int indexOf(Object obj);
}


class HashtableLinearProbe implements Hashtable
{
   private Object[] array;
   public HashtableLinearProbe(int size)
   {
      array = new Object[size];                  
   }

   //pre:  obj is not null
   //post: obj is placed in the hash table array
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if (array[index] == null)  //empty
      {
         //insert it
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else    //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = linearProbe(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   //pre:  index is within the bounds of the array
   //post: returns the next open spot in the array
   public int linearProbe(int index)
   {
      //be sure to wrap around the array.
      index = (index + 1) % array.length;
      while (array[index] != null) {
         index = (index + 1) % array.length;
      }
      return index;
   }
   
   //pre:  obj is not null
   //post: returns the index of obj in the array or -1 if not found
   public int indexOf(Object obj)     
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return index;
         }
         else    //search for it in a linear probe manner
         {
            index = (index + 1) % array.length;
            System.out.println("Looking at index " + index);
         }
      } // while
      return -1; //not found
   } // indexOf
} // HashtableLinearProbe


class HashtableRelativePrime implements Hashtable
{
   private Object[] array;
   private int constant = 2;
   
   public HashtableRelativePrime(int size)
   {
      array = new Object[size];
      //find a constant that is relatively prime to the size of the array
      for (int i = 2; i < size; i++) {
         if (gcd(i, size) == 1) {
            constant = i;
            break;
         }
      }
   }
   
   //pre:  a and b are positive
   //post: returns the greatest common divisor of a and b
   private int gcd(int a, int b) {
      if (b == 0) return a;
      return gcd(b, a % b);
   }
   
   //pre:  obj is not null
   //post: obj is placed in the hash table array
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index] == null)  //empty
      {
         //insert it
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = rehash(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   //pre:  index is within the bounds of the array
   //post: returns the next open spot in the array
   public int rehash(int index)
   {
      index = (index + constant) % array.length;
      while (array[index] != null) {
         index = (index + constant) % array.length;
      }
      return index;
   }
   
   //pre:  obj is not null
   //post: returns the index of obj in the array or -1 if not found
   public int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a rehashing manner
         {
            index = (index + constant) % array.length;
            System.out.println("Looking at index " + index);
         }
      }
      return -1; //not found
   }
} //  HashtableRelativePrime


class HashtableChaining implements Hashtable
{
   private LinkedList[] array;
   public HashtableChaining(int size)
   {
      //instantiate the array
      //instantiate the LinkedLists
      array = new LinkedList[size];
      for (int i = 0; i < size; i++) {
         array[i] = new LinkedList();
      }
   }
   
   //pre:  obj is not null
   //post: obj is placed in the hash table array
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      array[index].addFirst(obj);
      System.out.println(obj + "\t" + code + " " + " at " +index + ": "+ array[index]);
   }  
   
   //pre:  obj is not null
   //post: returns the index of obj in the array or -1 if not found
   public int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      if(!array[index].isEmpty())
      {
         if(array[index].contains(obj))  //found it
         {
            return index;
         }
         else //search for it in a chaining manner
         {
            // If the object isn't in the list at this index, it's not in the table
         }
      }
      return -1; //not found
   } // indexOf
} // HashtableChaining

/* SAMPLE OUTPUT

Item0   70973277        7
Item1   70973278        8
Item2   70973279        9
Item3   70973280        0
Item4   70973281        1
Item3 found  at index 0
Item4 found  at index 1
Item2 found  at index 9
Item1 found  at index 8
Item0 found  at index 7


 */