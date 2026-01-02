/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package universityenrollmentsystem;

/**** ENTER HERE YOUR DETAILS:*******
         * 
         * FIRST NAME:Siddiha
         * 
         * LAST NAME:Rimzan
         * 
         * STUDENT ID:20240856
         * 
         */

public class UniversityEnrollmentSystem {

    public static void main(String[] args) {

        EnrollmentManager UoWEnrollment = new WestminsterEnrollmentManager(50);
        boolean exit = false;
        while (!exit){
            exit = UoWEnrollment.runMenu();
        }

        System.out.println("System saving and shutting down. Goodbye!");
    
    }
}
