import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ioPractice {
    public static void main(String[] args) {
        try {
            File file = new File(System.getProperty("user.home") + "/Downloads/weather.txt");
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextDouble()) {
                double first = scanner.nextDouble();
                if (scanner.hasNextDouble()) {
                    double second = scanner.nextDouble();
                    double change = second - first;
                    System.out.printf("Change: %.1f\n", change);
                }
            }
            
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}