import java.io.IOException;

public interface HealthCenterManager {
    boolean runMenu();
    void addStaffMember();
    void removeStaffMember();
    void printStaffMember();
<<<<<<< HEAD:Health-Centre/HealthCenterManager.java
    void runGUI(); // all the methods in this interface are abstract methods
    // by default all the methods are public.

    //abstract classes we can create create constructors but interfaces we cannot create constructors

}
=======
    void searchStaffById();
    void sortStaffByName();
    void displayStatistics();
    void runGUI();
    void saveToFile() throws IOException;
    void loadFromFile() throws IOException;
}
>>>>>>> b9e1a9c6a9b9363aa1b5f7cba044904542eaa064:HealthCenterManager.java
