import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("  WESTMINSTER HEALTH CENTRE MANAGEMENT  ");
        System.out.println("              SYSTEM v2.0               ");
        System.out.println("=========================================");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter maximum staff capacity: ");
        int capacity = 100; // Default
        
        try {
            capacity = Integer.parseInt(scanner.nextLine());
            if (capacity <= 0) {
                System.out.println("Invalid capacity. Using default (100).");
                capacity = 100;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Using default capacity (100).");
        }
        
        WestminsterHealthCenterManager manager = new WestminsterHealthCenterManager(capacity);
        
        System.out.println("\nSystem initialized with capacity: " + capacity);
        System.out.println("Type 'help' for commands or '0' to exit.");
        
        manager.runMenu();
        
        scanner.close();
    }
}