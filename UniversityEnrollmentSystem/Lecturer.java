/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universityenrollmentsystem;


public class Lecturer extends Person {

    private int officeNumber;
    private String specialisation;
    
    public Lecturer(String name, String surname){
        super(name, surname);
        officeNumber = 0;
    }
    
    public void setOfficeNumber(int officeNumber){
        this.officeNumber = officeNumber;
    }
    
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
    
    public int getOfficeNumber() {
        return officeNumber;
    }
    
    public String getSpecialisation() {
        return specialisation;
    }
    
    @Override
    public String toString(){
        return super.toString() + " Lecturer - Office Number: " + officeNumber + ", Specialisation: " + specialisation; 
    }
}