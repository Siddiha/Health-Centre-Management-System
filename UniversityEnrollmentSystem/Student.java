/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universityenrollmentsystem;


public class Student extends Person {
    
    // Attributes
    private String courseTitle;
    private int modulesEnrolled; // Number of modules enrolled in

    // Constructor
    public Student(String name, String surname){
        super(name, surname);
        this.modulesEnrolled = 0;
    }
    
    // SET AND GET METHODS
    // Set Course Title
    public void setCourseTitle(String courseTitle){
        this.courseTitle = courseTitle;
    }
    
    // Set Modules Enrolled
    public void setModulesEnrolled(int modulesEnrolled) {
        this.modulesEnrolled = modulesEnrolled;
    }
    
    // Get Course Title
    public String getCourseTitle(){
        return courseTitle;
    }
    
    // Get Modules Enrolled
    public int getModulesEnrolled() {
        return modulesEnrolled;
    }
    
     @Override
    public String toString(){
        return super.toString() + " Student - Course: " + courseTitle + ", Modules Enrolled: " + modulesEnrolled;
    }
    
    
}