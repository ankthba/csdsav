import java.io.*;
import java.util.Scanner;

public class Pd5AnikethBandlamudiFileIO2
{
    public static void main(String[] args) {
        countFileStats();
        convertBraceStyle();
    }

    public static void countFileStats() {
        try (Scanner input = new Scanner(System.in);
             Scanner fileScanner = new Scanner(new File(input.nextLine()))) {
            
            System.out.print("Enter the name of the file: ");
            String fileName = input.nextLine();
            
            int charCount = 0, wordCount = 0, lineCount = 0;
            
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lineCount++;
                charCount += line.replaceAll("\\s", "").length();
                wordCount += line.split("\\s+").length;
            }
            
            System.out.println("Information about " + fileName + ":");
            System.out.println(charCount + " characters");
            System.out.println(wordCount + " words");
            System.out.println(lineCount + " lines");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static void convertBraceStyle() {
        try (Scanner input = new Scanner(System.in);
             BufferedReader reader = new BufferedReader(new FileReader(input.nextLine()));
             PrintStream writer = new PrintStream(new FileOutputStream("NextLineBraceStyleTest.txt"))) {
            
            System.out.print("Enter the name of the Java file to convert: ");
            String line;
            boolean nextLineBrace = false;
            
            while ((line = reader.readLine()) != null) {
                if (nextLineBrace) {
                    writer.print(line.trim());
                    nextLineBrace = false;
                } else {
                    writer.println(line);
                }
                
                if (line.trim().endsWith("{")) {
                    nextLineBrace = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}