/*
 * TASK 01: Alumni class using inheritance & encapsulation
 */
package universityenrollmentsystem;

// inheritance
public class Alumni extends Person {
    
    // Instance variables
    private int graduationYear;
    private String degreeTitle;
    private String currentEmployer;
    
    // Constructor with name and surname parameters, calling parent constructor
    public Alumni(String name, String surname) {
        super(name, surname);
    }
    
    // Getters and Encapsulations
    public int getGraduationYear() {
        return graduationYear;
    }
    
    public String getDegreeTitle() {
        return degreeTitle;
    }
    
    public String getCurrentEmployer() {
        return currentEmployer;
    }
    
    // Setters
    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }
    
    public void setDegreeTitle(String degreeTitle) {
        this.degreeTitle = degreeTitle;
    }
    
    public void setCurrentEmployer(String currentEmployer) {
        this.currentEmployer = currentEmployer;
    }
    
    // Override toString() to include Alumni info + Person info
    @Override
    public String toString() {
        return super.toString() + " Alumni - Graduation Year: " + graduationYear + 
               ", Degree: " + degreeTitle + ", Current Employer: " + currentEmployer;
    }
}