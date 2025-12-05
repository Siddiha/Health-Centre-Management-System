import java.util.*;
import java.io.*;

public class WestminsterHealthCenterManager implements HealthCenterManager {
    private List<StaffMember> staffMembersList;
    private int staffLimit;
    private int noofDoctors;
    private int noofReceptionists;
    private static final String DATA_FILE = "staffdata.csv";

    public WestminsterHealthCenterManager(int staffLimit) {
        this.staffLimit = staffLimit;
        this.staffMembersList = new ArrayList<>();
        this.noofDoctors = 0;
        this.noofReceptionists = 0;
        loadFromFile(); // Load data on startup
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
            System.out.println("7. Save Data to File");
            System.out.println("8. Run GUI");
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
                    try {
                        saveToFile();
                        System.out.println("Data saved successfully!");
                    } catch (IOException e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    break;
                case 8:
                    runGUI();
                    break;
                case 0:
                    exit = true;
                    System.out.print("Save before exiting? (Y/N): ");
                    String saveChoice = scanner.nextLine().toUpperCase();
                    if (saveChoice.equals("Y")) {
                        try {
                            saveToFile();
                            System.out.println("Data saved successfully!");
                        } catch (IOException e) {
                            System.out.println("Error saving data: " + e.getMessage());
                        }
                    }
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        return exit;
    }

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

    @Override
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

    @Override
    public void sortStaffByName() {
        if (staffMembersList.isEmpty()) {
            System.out.println("Staff list is empty");
            return;
        }
        
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

    @Override
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

    @Override
    public void saveToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            writer.write("TYPE,ID,NAME,SURNAME,DOB,CONTACT,FIELD1,FIELD2,FIELD3");
            writer.newLine();
            
            for (StaffMember staff : staffMembersList) {
                if (staff instanceof Doctor) {
                    Doctor doc = (Doctor) staff;
                    writer.write(String.format("D,%s,%s,%s,%s,%s,%s,%s,%d",
                        doc.getStaffId(), doc.getName(), doc.getSurName(),
                        doc.getDob(), doc.getPhoneNo(),
                        doc.getLicenceNumber(), doc.getSpecialization(),
                        doc.getNumberOfConsultationPerWeek()));
                } else if (staff instanceof Receptionist) {
                    Receptionist rec = (Receptionist) staff;
                    writer.write(String.format("R,%s,%s,%s,%s,%s,%d,%d,",
                        rec.getStaffId(), rec.getName(), rec.getSurName(),
                        rec.getDob(), rec.getPhoneNo(),
                        rec.getDeskNumber(), rec.getHoursPerWeek()));
                }
                writer.newLine();
            }
        }
    }

    @Override
    public void loadFromFile() throws IOException {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("No previous data file found. Starting fresh.");
            return;
        }
        
        staffMembersList.clear();
        noofDoctors = 0;
        noofReceptionists = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine(); // Skip header
            String line;
            
            while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                String[] parts = line.split(",");
                if (parts.length < 8) continue;
                
                String type = parts[0];
                
                if (type.equals("D") && parts.length >= 9) {
                    Doctor doctor = new Doctor(
                        parts[2], // name
                        parts[3], // surname
                        parts[4], // dob
                        parts[5], // phone
                        parts[1], // id
                        parts[6], // licence
                        parts[7], // specialization
                        Integer.parseInt(parts[8]) // consultations
                    );
                    staffMembersList.add(doctor);
                    noofDoctors++;
                } else if (type.equals("R") && parts.length >= 8) {
                    Receptionist receptionist = new Receptionist(
                        parts[2], // name
                        parts[3], // surname
                        parts[4], // dob
                        parts[5], // phone
                        parts[1], // id
                        Integer.parseInt(parts[6]), // desk number
                        Integer.parseInt(parts[7]) // hours
                    );
                    staffMembersList.add(receptionist);
                    noofReceptionists++;
                }
            }
        }
        System.out.println("Loaded " + staffMembersList.size() + " staff members from file.");
    }

    public List<StaffMember> getStaffMembersList() {
        return staffMembersList;
    }
}