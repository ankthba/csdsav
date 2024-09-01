import java.io.*;
import java.util.Scanner;

public class Pd5AnikethBandlamudiFileIO2 {
    public static void main(String[] args) {
        String inputPath = "/Users/anikethbandlamudi/Downloads/Test.txt";
        String outputPath = "/Users/anikethbandlamudi/Downloads/SameLineBraceStyleTest.txt";

        try (Scanner scanner = new Scanner(new File(inputPath));
             PrintStream printStream = new PrintStream(new File(outputPath))) {

            StringBuilder currentLine = new StringBuilder();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().strip();
                
                if (line.equals("{")) {
                    currentLine.append(" {");
                    printStream.println(currentLine);
                    currentLine = new StringBuilder();
                } else {
                    if (currentLine.length() > 0) {
                        printStream.println(currentLine);
                    }
                    currentLine = new StringBuilder(line);
                }
            }

            if (currentLine.length() > 0) {
                printStream.println(currentLine);
            }

        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.err.println("File not found: " + e.getMessage());
                System.err.println("Please check if the input file exists at: " + inputPath);
            } else {
                System.err.println("Error processing file: " + e.getMessage());
            }
        }
    }
}
