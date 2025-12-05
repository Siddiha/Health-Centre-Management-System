public class Main {
    public static void main(String[] args) {
        System.out.println("=== Westminster Health Centre Management System ===");
        System.out.println("Loading system...");
        
        // Create manager with a limit of 100 staff members
        WestminsterHealthCenterManager manager = new WestminsterHealthCenterManager(100);
        
        // Run the menu system
        manager.runMenu();
    }
}