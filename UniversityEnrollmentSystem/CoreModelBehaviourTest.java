/*
 * TASK 06: JUnit testing for Person, Student, and Lecturer classes
 */
package universityenrollmentsystem;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * TASK 06: CoreModelBehaviourTest class (1 mark)
 * Unit tests for Person, Student, and Lecturer classes
 */
public class CoreModelBehaviourTest {

    public void testPersonSurnameSetterAndGetter() {
        // Create a concrete Person instance using an existing subclass
        Person person = new Student("Alice", "Brown");
        // Call the setter to change the surname to a known value
        person.setSurname("Smith");
        
        // Use assertEquals to verify that getSurname() returns "Smith"
        assertEquals("Smith", person.getSurname());
    }


    public void testStudentConstructorAndModuleMethods() {
        // Create a Student with a given name and surname
        Student student = new Student("John", "Doe");
        
        // Use setter methods to assign a course title and number of modules
        student.setCourseTitle("Computer Science");
        student.setModulesEnrolled(5);
        
        // Verify with assertions that getCourseTitle() and getModulesEnrolled() return expected values
        assertEquals("Computer Science", student.getCourseTitle());
        assertEquals(5, student.getModulesEnrolled());
    }
    
    
    public void testLecturerToStringContainsOfficeAndSpecialisation() {
        // Create a Lecturer with a known name and surname
        Lecturer lecturer = new Lecturer("Jane", "Smith");
        
        // Set the office number and specialisation using the appropriate setters
        lecturer.setOfficeNumber(205);
        lecturer.setSpecialisation("Artificial Intelligence");
        
        // Call toString() and use assertTrue to check the string contains both values
        String result = lecturer.toString();
        assertTrue(result.contains("205"));
        assertTrue(result.contains("Artificial Intelligence"));
    }
}