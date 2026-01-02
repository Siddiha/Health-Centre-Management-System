/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universityenrollmentsystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class WestminsterEnrollmentManager implements EnrollmentManager{
    
    // ArrayList
    public ArrayList<Person> personList;
    public int person_limit;
    
    public WestminsterEnrollmentManager(int maxMemebersNumber){
        personList = new ArrayList<Person>();
        person_limit = maxMemebersNumber;
    }

    @Override
    public boolean runMenu() {
         boolean exit = false; 
        
        // Run console menu
        System.out.println("\n-- UNIVERSITY ENROLLMENT SYSTEM CONSOLE MENU--");
        
        System.out.println("To save and exit, press 0");
        System.out.println("To Add a new person, press 1");
        System.out.println("To Print the list of all people press 2");
        System.out.println("To Open GUI, press 3");

        
        // Switch based on selected option
        Scanner s = new Scanner(System.in);
        int choice;
        
        // Basic error handling for choice input
        try {
             choice = s.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number from the menu.");
            s.nextLine(); // Clear the buffer
            return false; // Rerun the menu
        }
       
        switch(choice){
            
             // Exit
            case 0:
                exit = true;
                break;
            
            // Add person
            case 1:
                this.addPerson();
                break;
            // Print person list
            case 2:
                this.printPersonList();
                break;
            
            // GUI
            case 3:
                this.runGUI();
                break;

            case 4:
            this.searchPeopleByIDPrefix();
            break;
            
            case 5:
            this.printListSortedByID();
            break;

            
            default:
                System.out.println("Invalid option selected.");
        }
        
        return exit;
    }

    @Override
    public void addPerson() {
        Scanner s = new Scanner (System.in);
        
        if(personList.size() < person_limit){
            System.out.println("Press 1 if you want to add a Student");
            System.out.println("Press 2 if you want to add a Lecturer");
            
            
            int choicePerson;
            try {
                choicePerson = s.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1, 2 (or 3 for Task 01).");
                s.nextLine();
                return;
            }
            s.nextLine(); // Consume newline
            
            // common questions
            System.out.println("Enter the first name");
            String name = s.nextLine();
            
            System.out.println("Enter the last name");
            String surname = s.nextLine();
            
            System.out.println("Enter the ID");
            String ID = s.nextLine();
            
            
            System.out.println("Enter the date of birth (dd/MM/yyyy format only!)");
            LocalDate date = null;
            boolean parsingSucceds = false;
            while(!parsingSucceds){
                String dob = s.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                
                try{
                    date = LocalDate.parse(dob, formatter);
                    parsingSucceds = true;  
                }catch(DateTimeParseException e){
                    System.out.println("Enter the correct format. It should be dd/MM/yyyy!");
                    parsingSucceds = false;
                }
            }
            
            // check if the person is a student, lecturer, or administrator
            switch(choicePerson){
                case 1:
                    // it is a student
                    System.out.println("Enter the course title");
                    String courseTitle = s.nextLine();
                    
                    System.out.println("Enter the number of modules enrolled:");
                    int modulesEnrolled;
                    try {
                        modulesEnrolled = s.nextInt();
                    } catch (java.util.InputMismatchException e) {
                         System.out.println("Invalid input for modules. Setting to 0.");
                         modulesEnrolled = 0;
                         s.nextLine();
                    }
                    
                    // create a new Student and add to the list
                    Student student = new Student(name, surname);
                    student.setCourseTitle(courseTitle);
                    student.setModulesEnrolled(modulesEnrolled);
                    student.setDob(date);
                    student.setID(ID);
                    this.addPersonToList(student);
                    
                    break;
                    
                case 2:
                    //it is a lecturer
                    System.out.println("Enter the office number");
                    int officeNum;
                    try {
                        officeNum = s.nextInt();
                    } catch (java.util.InputMismatchException e) {
                         System.out.println("Invalid input for office number. Setting to 0.");
                         officeNum = 0;
                         s.nextLine();
                    }
                    s.nextLine(); // Consume newline
                    
                    System.out.println("Enter the specialisation/department");
                    String specialisation = s.nextLine();
                    
                    // create a new lecturer and add to the list
                    Lecturer lecturer = new Lecturer(name, surname);
                    lecturer.setOfficeNumber(officeNum);
                    lecturer.setSpecialisation(specialisation);
                    lecturer.setDob(date);
                    lecturer.setID(ID);
                    this.addPersonToList(lecturer);
                    
                    break;
                
                
                default:
                    System.out.println("Invalid person type selected. Person not added.");
            }
            
        }
        else {
            System.out.println("The system is at full capacity.");
        }
    }
    
    public void addPersonToList(Person person){
        
        //check if there are space available
        if(this.personList.size() < person_limit) {
            personList.add(person);
            System.out.println("Person added successfully.");
        }
        else{
            System.out.println("No more space in the list");
        }
    }

    @Override
    public void searchPeopleByIDPrefix(){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the id prefix: ");
        String prefix = s.nextLine().toUpperCase();

        boolean found = false;
        for (Person person : personList){
            if (person.getID().toUpperCase().startsWith(prefix)){
                System.out.println(person.toString());
                found = true;
            }
        }

        if (!found){
            System.out.println("no people found with id starting with " + prefix);
        }
    }

    @Override
    public void printPersonList() {
        
        
        if (!personList.isEmpty()){
            System.out.println("\n--- ENROLLED PEOPLE LIST ---");
            for(Person member : personList) {
                System.out.println(member.toString());
            }
            System.out.println("---------------------------\n");
        }
        else{
            System.out.println("There are no people in the system.");
        }
    }

    @Override
    public void runGUI() {
        UniversityTableGUI table = new UniversityTableGUI(personList);
        table.setVisible(true);
    }




    public void printListSortedByID(){
        ArrayList<Person> sortedList = new ArrayList<>(personList);
        Comparator<Person> idComparator = new Comparator<Person>(){
            @Override
            public int compare(Person p1, Person p2){
                return p1.getID().compareTo(p2.getID());


            }
        };

        Collections.sort(sortedList, idComparator);
        for (Person person :sortedList){
            System.out.println(person.toString());
        }
    }
}
