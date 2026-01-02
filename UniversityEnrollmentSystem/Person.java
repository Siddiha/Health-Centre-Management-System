/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universityenrollmentsystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Person {
    private String name;
    private String surname;
    private LocalDate dob;
    private String ID; // Staff ID or Student ID
    
    // Constructor
    public Person(String name, String surname){
        this.name = name;
        this.surname = surname;
    }
    
    // SET AND GET METHODS
    
    // Setters
    public void setName(String name){ this.name = name; }
    public void setSurname(String surname){ this.surname = surname; }
    public void setDob(LocalDate dob){ this.dob = dob; }
    public void setID(String ID) { this.ID = ID; }
    
    // Getters
    public String getName(){ return name; }
    public String getSurname(){ return surname; }
    public LocalDate getDob() { return dob; }
    
    // Get Date of Birth in String "dd/MM/yyyy" format
    public String getStringDate(){
        if (dob == null) return "N/A";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dob.format(formatter);
        return formattedDate;
    }

    public String getID() { return ID; }
    
    
    @Override
    public String toString(){
        return name + " " + surname +  ", ID: " + ID + ", DOB: "  + getStringDate() + ", ";
    }
    
}
