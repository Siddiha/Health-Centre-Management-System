//the difference between interface and abstract classes are abstract classes cannot be instantiated
// (we can create object for the class.)
public interface HealthCenterManager {
    boolean runMenu();
    void addStaffMemberList();
    void printStaffMember();
    void runGUI(); // all the methods in this interface are abstract methods
    // by default all the methods are public.

}
