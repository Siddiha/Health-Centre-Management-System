import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WestminsterHealthCenterManager implements HealthCenterManager {
     private List<StaffMember> staffMemberslist;
     private int staffLimit;
     private  int noofDoctors;
     private int noofReceptionsits;

     public WestminsterHealthCenterManager(int staffLimit, List<StaffMember> staffMembersList){
        this.staffLimit = staffLimit;
        this.staffMemberslist = staffMembersList;
        this.noofDoctors = 0;
        this.noofReceptionsits = 0;
     }

    public boolean runMenu(){
        System.out.println("=welcome to the the Westminster health center management==");
        System.out.println("1. Add New staff member");
        System.out.println("2. Remove Staff Member");
        System.out.println("3.View All Staff");
        System.out.println("4.Search staff by 10");
        System.out.println("0.Exit ");

        System.out.println("Enter your choice");
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();
        
        switch(choice){
            case 1:
            addStaffMember();

            case 2:
            removeStaffMember();
            break;

            case 3:
            printStaffMember();
            break;
            
        return false;
    }

    public void addStaffMember(StaffMember staff){
        staffMemberslist.add(staff);
    }

    public void addStaffMember(){
        if (staffMemberslist.size() >= staffLimit){
            System.out.println("Maximum staff member limit reached: ");
            return;
        }

        Scanner s1 = new Scanner(System.in);
        System.out.println("Enter the Name: ");
        String Name = s1.next();
        System.out.println("Enter the surname: ");
        String surname = s1.next();
        System.out.println("Enter the Date of birth : ");
        String dateofbirth = s1.next();
        System.out.println("Enter the phone Number: ");
        String phoneno = s1.next();
        System.out.println("Enter the staffId");
        String staffid = s1.next();

        System.out.println("Do you wantedf to add a dcotor or a Receptionist");

        System.out.println("Type D for doctor, R for Receptionist: ");
        String staffType = s1.next();

        StaffMember member = null;

        while (true){
        switch(staffType){
            case "D":
            case "d":
            System.out.println("Enter the Licence Number ");
            String licenceNumber = s1.next();
            System.out.println("Enter the specialization: ");
            String specialsation = s1.next();
            System.out.println("Enter the Number of Consultation: ");
            int nnumberConsulat = s1.nextInt();

            member = new Doctor(Name, surname, dateofbirth, staffType, staffid, licenceNumber, specialsation, nnumberConsulat);
            noofDoctors++;
            break;



            case "R":
            case "r":
            System.out.println("Enter the Desk Number: ");
            int deskNumber = s1.nextInt();
            System.out.println("Enter the hoursPerWeek: ");
            int hoursPerWeek = s1.nextInt();

            member = new Receptionist(Name, surname, phoneno, dateofbirth, staffid, deskNumber, hoursPerWeek);
            noofReceptionsits++;
            break;




            default:
            System.out.println("Invalid choice please try again");
            return;
        }
        
        staffMemberslist.add(member);
        System.out.println("Staff memeber is successfully added: ");
    }
}

      
    public void printStaffMember(){

        if (staffMemberslist.isEmpty()){
            System.out.println("staff list is isEmpty");
        }else{
            System.out.println("== List of Staff Member== ");

            for (staffMemberslist member: staffMembersList){
                System.out.println(member);

            }


        }
        //colections.sort mthod uses compareto method in the staff memeber
        //if no comapreto method is available then th sorting will not work
        // for the soerting to work , implement the comparable interface and overide the compareTo method 


        // it will use nameComparator to sort cx earlier it uses compareto emthod to comapre it 
        Collections.sort(staffMemberslist, new NameComparator());
       
                                          // if u wanted surnamecomparator then use the surname comparator
    }


    public void runGUI(){

    }

    public void removeStaffMember(){
        System.out.println("Enter the staff id of the memeber you wish to remove :");
        Scanner s2 = new Scanner(System.in);
        String staffIDRemove = s2.next();


boolean found = false;

        for ( StaffMember member: staffMemberslist){

            if (member.getStaffId().equals(staffIDRemove)){
                found = true;
                staffMemberslist.remove(member);
                System.out.println("Staff member " + staffIDRemove + "successfully removed");

                //it returns ture if not return false here 
                if (member instanceof Doctor){
                    noofDoctors--;



                }else{
                    noofReceptionsits--;

                }
                break;

    

            }

        }

        //if not found then it retrn nothing in here 
        if (!found){
            System.out.println("Staff member" + staffIDRemove + "is not found in the list ");

        }

    }
}