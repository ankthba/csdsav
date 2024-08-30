import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Pd5AnikethBandlamudiFileIO1 {
    public static void main(String[] args) {
        PrintStream output = System.out;
        Scanner scanner = new Scanner(System.in);
        
        output.print("Enter the name of the file: ");
        String fileName = scanner.nextLine();
        scanner.close();

        int characters = 0;
        int words = 0;
        int lines = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines++;
                characters += line.replaceAll("\\s+", "").length();
                words += line.trim().split("\\s+").length;
            }
        } catch (IOException e) {
            output.println("An error occurred while reading the file: " + e.getMessage());
            return;
        }

        output.println("Information about " + fileName + ":");
        output.println(characters + " characters");
        output.println(words + " words");
        output.println(lines + " lines");
    }
}
