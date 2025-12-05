import java.util.*;

public class WestminsterHealthCenterManager implements HealthCenterManager {
    private List<StaffMember> staffMembersList;
    private int staffLimit;
    private int noofDoctors;
    private int noofReceptionists;

    public WestminsterHealthCenterManager(int staffLimit) {
        this.staffLimit = staffLimit;
        this.staffMembersList = new ArrayList<>();
        this.noofDoctors = 0;
        this.noofReceptionists = 0;
    }

    @Override
    public boolean runMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n=== Westminster Health Centre Management System ===");
            System.out.println("1. Add New Staff Member");
            System.out.println("2. Remove Staff Member");
            System.out.println("3. View All Staff");
            System.out.println("4. Search Staff by ID");
            System.out.println("5. Sort Staff by Name");
            System.out.println("6. Display Statistics");
            System.out.println("7. Run GUI");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = getValidIntegerInput(scanner);
            
            switch(choice) {
                case 1:
                    addStaffMember();
                    break;
                case 2:
                    removeStaffMember();
                    break;
                case 3:
                    printStaffMember();
                    break;
                case 4:
                    searchStaffById();
                    break;
                case 5:
                    sortStaffByName();
                    break;
                case 6:
                    displayStatistics();
                    break;
                case 7:
                    runGUI();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        return exit;
    }

    // Input validation method
    private int getValidIntegerInput(Scanner scanner) {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return input;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Please enter a number: ");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    @Override
    public void addStaffMember() {
        if (staffMembersList.size() >= staffLimit) {
            System.out.println("Maximum staff limit reached: " + staffLimit);
            return;
        }

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }
        
        System.out.print("Enter Surname: ");
        String surname = scanner.nextLine();
        if (surname.trim().isEmpty()) {
            System.out.println("Surname cannot be empty!");
            return;
        }
        
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();
        
        System.out.print("Enter Phone Number: ");
        String phoneNo = scanner.nextLine();
        
        System.out.print("Enter Staff ID: ");
        String staffId = scanner.nextLine();
        
        // Check for duplicate ID
        if (isDuplicateId(staffId)) {
            System.out.println("Error: Staff ID '" + staffId + "' already exists!");
            return;
        }

        System.out.print("Enter staff type (D for Doctor, R for Receptionist): ");
        String staffType = scanner.nextLine().toUpperCase();

        StaffMember member = null;

        if (staffType.equals("D")) {
            System.out.print("Enter Licence Number: ");
            String licenceNumber = scanner.nextLine();
            
            System.out.print("Enter Specialization: ");
            String specialization = scanner.nextLine();
            
            System.out.print("Enter Number of Consultations per Week: ");
            int consultations = getValidIntegerInput(scanner);

            member = new Doctor(name, surname, dob, phoneNo, staffId, 
                              licenceNumber, specialization, consultations);
            noofDoctors++;
            
        } else if (staffType.equals("R")) {
            System.out.print("Enter Desk Number: ");
            int deskNumber = getValidIntegerInput(scanner);
            
            System.out.print("Enter Hours per Week: ");
            int hoursPerWeek = getValidIntegerInput(scanner);

            member = new Receptionist(name, surname, dob, phoneNo, staffId, 
                                    deskNumber, hoursPerWeek);
            noofReceptionists++;
            
        } else {
            System.out.println("Invalid staff type!");
            return;
        }

        staffMembersList.add(member);
        System.out.println("Staff member added successfully!");
        System.out.println("Total Doctors: " + noofDoctors + ", Total Receptionists: " + noofReceptionists);
    }

    private boolean isDuplicateId(String staffId) {
        for (StaffMember member : staffMembersList) {
            if (member.getStaffId().equalsIgnoreCase(staffId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void printStaffMember() {
        if (staffMembersList.isEmpty()) {
            System.out.println("Staff list is empty");
        } else {
            System.out.println("\n=== List of Staff Members ===");
            for (StaffMember member : staffMembersList) {
                System.out.println(member);
            }
            System.out.println("Total: " + staffMembersList.size() + " staff members");
        }
    }

    public void searchStaffById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Staff ID to search: ");
        String searchId = scanner.nextLine();
        
        boolean found = false;
        for (StaffMember member : staffMembersList) {
            if (member.getStaffId().equalsIgnoreCase(searchId)) {
                System.out.println("Staff member found:");
                System.out.println(member);
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Staff member with ID '" + searchId + "' not found.");
        }
    }

    public void sortStaffByName() {
        if (staffMembersList.isEmpty()) {
            System.out.println("Staff list is empty");
            return;
        }
        
        // Sort by surname, then by name
        Collections.sort(staffMembersList, new Comparator<StaffMember>() {
            @Override
            public int compare(StaffMember s1, StaffMember s2) {
                int surnameCompare = s1.getSurName().compareToIgnoreCase(s2.getSurName());
                if (surnameCompare != 0) {
                    return surnameCompare;
                }
                return s1.getName().compareToIgnoreCase(s2.getName());
            }
        });
        
        System.out.println("\n=== Staff Sorted by Name ===");
        printStaffMember();
    }

    public void displayStatistics() {
        System.out.println("\n=== Health Centre Statistics ===");
        System.out.println("Total Staff Members: " + staffMembersList.size());
        System.out.println("Doctors: " + noofDoctors);
        System.out.println("Receptionists: " + noofReceptionists);
        
        if (!staffMembersList.isEmpty()) {
            double doctorPercentage = (double) noofDoctors / staffMembersList.size() * 100;
            double receptionistPercentage = (double) noofReceptionists / staffMembersList.size() * 100;
            System.out.printf("Doctor Percentage: %.1f%%\n", doctorPercentage);
            System.out.printf("Receptionist Percentage: %.1f%%\n", receptionistPercentage);
        }
    }

    @Override
    public void removeStaffMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Staff ID to remove: ");
        String staffIdToRemove = scanner.nextLine();

        boolean found = false;
        Iterator<StaffMember> iterator = staffMembersList.iterator();
        
        while (iterator.hasNext()) {
            StaffMember member = iterator.next();
            if (member.getStaffId().equals(staffIdToRemove)) {
                iterator.remove();
                found = true;

                // Update counters
                if (member instanceof Doctor) {
                    noofDoctors--;
                } else if (member instanceof Receptionist) {
                    noofReceptionists--;
                }

                System.out.println("Staff member " + staffIdToRemove + " successfully removed");
                break;
            }
        }

        if (!found) {
            System.out.println("Staff member " + staffIdToRemove + " not found.");
        }
    }


    @Override
    public void runGUI() {
        StaffManagementGUI gui = new StaffManagementGUI(new ArrayList<>(staffMembersList));
        gui.setVisible(true);
    }

    public List<StaffMember> getStaffMembersList() {
        return staffMembersList;
    }
}