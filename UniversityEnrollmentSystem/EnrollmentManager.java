/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package universityenrollmentsystem;


public interface EnrollmentManager {
    
    boolean runMenu(); // Run Menu - main function
    
    void addPerson(); // Add a person (Student or Staff)
    
    void printPersonList(); // Print list of all people 
    
    void runGUI(); // Run main GUI

    void searchPeopleByIDPrefix();
    
    
}
