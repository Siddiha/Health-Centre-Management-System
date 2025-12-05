import java.io.IOException;

public interface HealthCenterManager {
    boolean runMenu();
    void addStaffMember();
    void removeStaffMember();
    void printStaffMember();
    void searchStaffById();
    void sortStaffByName();
    void displayStatistics();
    void runGUI();
    void saveToFile() throws IOException;
    void loadFromFile() throws IOException;
}