/**************************************************** 
Name (Full Name): Aniketh Bandlamudi   Period:   5
Name of the Lab/Assignment:  File IO Exercise #2
Purpose of the program: To convert the brace style of a text file to the same line as the opening brace

Mistakes made: Used BufferedReader and PrintWriter instead of Scanner and PrintStream.

How I feel about this programming experience: I feel like I have a better understanding of how to read and write to files in Java.

What I Learned: I learned how to use the Scanner and PrintStream classes to read and write to files.

The credits: No help needed
****************************************************/

import java.io.*;
import java.util.Scanner;

public class Pd5AnikethBandlamudiFileIO2 {
    // Precondition: The input file exists and is a valid text file.
    // Postcondition: A new file is created with the same line brace style.
    public static void main(String[] args) {
        // Strings to store the input and output file paths
        String inputPath = "/Users/anikethbandlamudi/Downloads/Test.txt";
        String outputPath = "/Users/anikethbandlamudi/Downloads/SameLineBraceStyleTest.txt";

        // Try with resources to ensure the scanner and print stream are closed after use
        try (Scanner scanner = new Scanner(new File(inputPath));
        // PrintStream to write to the output file
             PrintStream printStream = new PrintStream(new File(outputPath))) {

            // StringBuilder to store the current line
            StringBuilder currentLine = new StringBuilder();

            // Loop through each line in the input file
            while (scanner.hasNextLine()) {

                // Get the next line from the scanner and strip any leading or trailing whitespace
                String line = scanner.nextLine().strip();
                
                // If the line is an opening brace, append it to the current line and print it
                if (line.equals("{")) {
                    currentLine.append(" {");
                    printStream.println(currentLine);
                    currentLine = new StringBuilder();
                } else {
                    // If the line is not an opening brace, print the current line and start a new line
                    if (currentLine.length() > 0) {
                        printStream.println(currentLine);
                    }
                    currentLine = new StringBuilder(line);
                }
            }

            // If there is any remaining text in the current line, print it
            if (currentLine.length() > 0) {
                printStream.println(currentLine);
            }

        } catch (IOException e) {
            // If the file is not found, print an error message
            if (e instanceof FileNotFoundException) {
                System.err.println("File not found: " + e.getMessage());
                System.err.println("Please check if the input file exists at: " + inputPath);
            } else {
                System.err.println("Error processing file: " + e.getMessage());
            }
        }
    }
}

/********* PROGRAM OUTPUTS ******
The program does not output anything to the console. It creates a new file with the name 
SameLineBraceStyleTest.txt in the same directory as the input file with the same line brace style.
**********************************/
