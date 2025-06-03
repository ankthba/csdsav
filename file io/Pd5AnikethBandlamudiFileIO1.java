/**************************************************** 
Name (Full Name): Aniketh Bandlamudi   Period:   5
Name of the Lab/Assignment:  File IO Exercise #1
Purpose of the program: To output the number of characters, words, and lines in a file.

Mistakes made: Used BufferedReader and PrintWriter instead of Scanner and PrintStream.

How I feel about this programming experience: I feel like I have a better understanding of how to read and write to files in Java.

What I Learned: I learned how to use the Scanner class to read from a file.

The credits: No help needed
****************************************************/

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Pd5AnikethBandlamudiFileIO1 {
    // Precondition: The input file exists and is a valid text file.
    // Postcondition: The program outputs the number of characters, words, and lines in the file.
    public static void main(String[] args) {
        String fileName = "file.txt";
        PrintStream output = System.out;

        // Initialize variables to store the number of characters, words, and lines
        int characters = 0;
        int words = 0;
        int lines = 0;

        // Try with resources to ensure the scanner is closed after use
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            // Loop through each line in the file
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lines++;
                characters += line.replaceAll("\\s+", "").length();
                words += line.trim().split("\\s+").length;
            }
        } catch (IOException e) {
            // If an error occurs while reading the file, print an error message
            output.println("An error occurred while reading the file: " + e.getMessage());
            return;
        }

        // Output the information about the file
        output.println("Information about " + fileName + ":");
        output.println(characters + " characters");
        output.println(words + " words");
        output.println(lines + " lines");
    }
}

/********* PROGRAM OUTPUTS ******
The program outputs the number of characters, words, and lines in the file.
**********************************/
