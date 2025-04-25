//*********************************************************************************************************************************
//Name:   Aniketh Bandlamudi
// Period: 5                                                
// Date: 4/9/2-25
// What I learned: I learned how to implement bidirectional dictionaries using TreeMaps and Sets
// How I feel about this lab: I enjoyed seeing how TreeMaps organize dictionary entries while Sets handle multiple translations
// What I wonder: I wonder how this implementation could be extended to handle phrases or expressions that don't translate directly between languages
//***********************************************************************************************************************************

import java.io.*;
import java.util.*;
public class Pd5AnikethBandlamudiDictionary
{
   private static PrintWriter pw;
   public static void main(String[] args) throws Exception
   {
   
      /***************************************************
                        PART I
       **************************************************/
      try
      {
         System.setOut(new PrintStream(new FileOutputStream("Pd5AnikethBandlamudiDictionaryOutput.txt")));
         pw = new PrintWriter(new FileOutputStream("Pd5AnikethBandlamudiDictionaryOutput.txt"));
      }
      catch(Exception e)
      {
      } 
    
   
      Map<String, Set<String>> eng2spn = new TreeMap<String, Set<String>>();
      Scanner infile = new Scanner(new File("spanglish.txt"));
      // Scanner infile = new Scanner(new File("/Users/ankthba/Downloads/spanglish.txt"));
      while(infile.hasNext())
      {
         add(eng2spn, infile.next(), infile.next());
      }
      infile.close();
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
      
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
      pw.close();    // close the file
      /***************************************************
                 PART II 
       **************************************************/
   
      // The two maps are still in the memory. Part II can interact with the user and add
      // new word translations to the output file.
      // pw = new PrintWriter(new File ("Pd6EdmundLaudictionaryOutput.txt"));
   
      // Write your Part II code here
      // Menu options: translate from (1) English to Spanish 
      //                              (2) Spanish to English 
      //                              (3) Add a new tranlation: (a) from English->Spanish (b) from Spanish->English
      //                              (4) Exit
     
      // close the output file
      pw.close();
   
   }
   
   // Note: This method displays the contents of a dictionary (map)
   // Postcondition: All entries in the map are printed to the output
   public static void display(Map<String, Set<String>> m)
   {
       // Iterate through each entry in the map
       for(Map.Entry<String, Set<String>> entry : m.entrySet()) {
           // Print the key (word) followed by its translations (values)
           System.out.println("\t" + entry.getKey() + " " + entry.getValue());
       }
       System.out.println();
   }

   // Note: This method adds a word and its translation to the dictionary
   // postcondition: The word-translation pair is added to the dictionary
   public static void add(Map<String, Set<String>> engToSpnDictionary, String word, String translation)
   {
       // If the word doesn't exist in the dictionary yet, create a new set for it
       if(!engToSpnDictionary.containsKey(word)) {
           engToSpnDictionary.put(word, new TreeSet<String>());
       }
       
       // Add the translation to the set of translations for this word
       engToSpnDictionary.get(word).add(translation);
   }

   // Note: This method reverses the dictionary (from eng->spn to spn->eng)
   // postcondition: returns a new map where keys become values and values become keys
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> engToSpnDictionary)
   {
       // Create a new TreeMap for the reversed dictionary
       Map<String, Set<String>> reversed = new TreeMap<String, Set<String>>();
       
       // Iterate through each entry in the original dictionary
       for(Map.Entry<String, Set<String>> entry : engToSpnDictionary.entrySet()) {
           String key = entry.getKey();
           Set<String> values = entry.getValue();
           
           // For each value in the set, add it as a key in the reversed map
           for(String value : values) {
               if(!reversed.containsKey(value)) {
                   reversed.put(value, new TreeSet<String>());
               }
               reversed.get(value).add(key);
           }
       }
       return reversed;
   }
}
      /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/