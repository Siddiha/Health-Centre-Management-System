import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class WestminsterHealthCenterManager implements HealthCenterManager {
<<<<<<< HEAD:Health-Centre/WestminsterHealthCenterManager.java
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
            
    }

    return false;

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
=======
    private List<StaffMember> staffMembersList;
    private int staffLimit;
    private static final String DATA_FILE = "staffdata.csv";
    
    public WestminsterHealthCenterManager(int staffLimit) {
        this.staffLimit = staffLimit;
        this.staffMembersList = new ArrayList<>();
        loadFromFile();
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
            System.out.println("9. Load from File");
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
                        System.out.println("✓ Data saved successfully!");
                    } catch (IOException e) {
                        System.out.println("✗ Error saving data: " + e.getMessage());
                    }
                    break;
                case 8:
                    runGUI();
                    break;
                case 9:
                    try {
                        loadFromFile();
                        System.out.println("✓ Data loaded successfully!");
                    } catch (IOException e) {
                        System.out.println("✗ Error loading data: " + e.getMessage());
                    }
                    break;
                case 0:
                    exit = true;
                    System.out.print("Save before exiting? (Y/N): ");
                    String saveChoice = scanner.nextLine().toUpperCase();
                    if (saveChoice.equals("Y") || saveChoice.equals("YES")) {
                        try {
                            saveToFile();
                            System.out.println("✓ Data saved successfully!");
                        } catch (IOException e) {
                            System.out.println("✗ Error saving data: " + e.getMessage());
                        }
                    }
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("✗ Invalid choice! Please try again.");
>>>>>>> b9e1a9c6a9b9363aa1b5f7cba044904542eaa064:WestminsterHealthCenterManager.java
            }
        }
        return exit;
    }
    
    @Override
    public void addStaffMember() {
        try {
            if (staffMembersList.size() >= staffLimit) {
                throw new InvalidInputException("staff limit", "Maximum limit reached: " + staffLimit);
            }
            
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Enter Staff ID (e.g., D001/R001): ");
            String staffId = scanner.nextLine().trim().toUpperCase();
            
            // Check for duplicate ID
            for (StaffMember member : staffMembersList) {
                if (member.getStaffId().equalsIgnoreCase(staffId)) {
                    throw new DuplicateIdException(staffId);
                }
            }
            
            if (!Validator.isValidStaffId(staffId)) {
                throw new InvalidInputException("Staff ID", "must be 3-20 alphanumeric characters");
            }
            
            System.out.print("Enter Name: ");
            String name = scanner.nextLine().trim();
            if (!Validator.isValidName(name)) {
                throw new InvalidInputException("Name", "must contain only letters and spaces");
            }
            
            System.out.print("Enter Surname: ");
            String surname = scanner.nextLine().trim();
            if (!Validator.isValidName(surname)) {
                throw new InvalidInputException("Surname", "must contain only letters and spaces");
            }
            
            System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
            String dob = scanner.nextLine().trim();
            if (!Validator.isValidDOB(dob)) {
                throw new InvalidInputException("Date of Birth", "must be in YYYY-MM-DD format and age 18-100");
            }
            
            System.out.print("Enter Phone Number: ");
            String phoneNo = scanner.nextLine().trim();
            if (!Validator.isValidPhone(phoneNo)) {
                throw new InvalidInputException("Phone Number", "must be 10-15 digits");
            }
            
            System.out.print("Enter staff type (D for Doctor, R for Receptionist): ");
            String staffType = scanner.nextLine().toUpperCase();
            
            StaffMember member;
            
            if (staffType.equals("D")) {
                System.out.print("Enter Licence Number: ");
                String licenceNumber = scanner.nextLine().trim();
                if (!Validator.isValidLicenceNumber(licenceNumber)) {
                    throw new InvalidInputException("Licence Number", "must be 5-20 alphanumeric characters");
                }
                
                System.out.print("Enter Specialization: ");
                String specialization = scanner.nextLine().trim();
                if (!Validator.isValidSpecialization(specialization)) {
                    throw new InvalidInputException("Specialization", "must contain only letters and spaces");
                }
                
                System.out.print("Enter Number of Consultations per Week (0-100): ");
                int consultations = getValidIntegerInput(scanner);
                if (!Validator.isValidConsultations(consultations)) {
                    throw new InvalidInputException("Consultations", "must be between 0 and 100");
                }
                
                member = new Doctor(name, surname, dob, phoneNo, staffId, 
                                  licenceNumber, specialization, consultations);
                System.out.println("✓ Doctor added successfully!");
                
            } else if (staffType.equals("R")) {
                System.out.print("Enter Desk Number (1-999): ");
                int deskNumber = getValidIntegerInput(scanner);
                if (!Validator.isValidDeskNumber(deskNumber)) {
                    throw new InvalidInputException("Desk Number", "must be between 1 and 999");
                }
                
                System.out.print("Enter Hours per Week (1-80): ");
                int hoursPerWeek = getValidIntegerInput(scanner);
                if (!Validator.isValidHoursPerWeek(hoursPerWeek)) {
                    throw new InvalidInputException("Hours per Week", "must be between 1 and 80");
                }
                
                member = new Receptionist(name, surname, dob, phoneNo, staffId, 
                                        deskNumber, hoursPerWeek);
                System.out.println("✓ Receptionist added successfully!");
                
            } else {
                throw new InvalidInputException("Staff Type", "must be 'D' or 'R'");
            }
            
            staffMembersList.add(member);
            System.out.println("Total staff members: " + staffMembersList.size());
            
        } catch (CustomException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Unexpected error: " + e.getMessage());
        }
    }
    
    @Override
    public void removeStaffMember() {
        try {
            if (staffMembersList.isEmpty()) {
                System.out.println("✗ Staff list is empty!");
                return;
            }
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Staff ID to remove: ");
            String staffIdToRemove = scanner.nextLine().trim();
            
            StaffMember toRemove = null;
            for (StaffMember member : staffMembersList) {
                if (member.getStaffId().equalsIgnoreCase(staffIdToRemove)) {
                    toRemove = member;
                    break;
                }
            }
            
            if (toRemove == null) {
                throw new StaffNotFoundException(staffIdToRemove);
            }
            
            System.out.println("\nStaff member to remove:");
            System.out.println(toRemove);
            System.out.print("\nAre you sure you want to remove this staff member? (Y/N): ");
            String confirm = scanner.nextLine().toUpperCase();
            
            if (confirm.equals("Y") || confirm.equals("YES")) {
                staffMembersList.remove(toRemove);
                System.out.println("✓ Staff member removed successfully!");
            } else {
                System.out.println("✗ Removal cancelled.");
            }
            
        } catch (CustomException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }
    
    @Override
    public void printStaffMember() {
        if (staffMembersList.isEmpty()) {
            System.out.println("No staff members in the system.");
            return;
        }
        
        System.out.println("\n=== List of Staff Members ===");
        System.out.println("Total: " + staffMembersList.size() + " staff members\n");
        
        System.out.println("DOCTORS:");
        System.out.println("--------");
        boolean hasDoctors = false;
        for (StaffMember member : staffMembersList) {
            if (member instanceof Doctor) {
                System.out.println(member);
                System.out.println("Role: " + member.getRole());
                System.out.println("---");
                hasDoctors = true;
            }
        }
        if (!hasDoctors) System.out.println("No doctors found.");
        
        System.out.println("\nRECEPTIONISTS:");
        System.out.println("--------------");
        boolean hasReceptionists = false;
        for (StaffMember member : staffMembersList) {
            if (member instanceof Receptionist) {
                System.out.println(member);
                System.out.println("Role: " + member.getRole());
                System.out.println("---");
                hasReceptionists = true;
            }
        }
        if (!hasReceptionists) System.out.println("No receptionists found.");
    }
    
    @Override
    public void searchStaffById() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Staff ID to search: ");
            String searchId = scanner.nextLine().trim();
            
            StaffMember found = null;
            for (StaffMember member : staffMembersList) {
                if (member.getStaffId().equalsIgnoreCase(searchId)) {
                    found = member;
                    break;
                }
            }
            
            if (found == null) {
                throw new StaffNotFoundException(searchId);
            }
            
            System.out.println("\n✓ Staff member found:");
            System.out.println("=====================");
            System.out.println(found);
            System.out.println("Role: " + found.getRole());
            
            if (found instanceof Doctor) {
                Doctor doc = (Doctor) found;
                System.out.println("Licence: " + doc.getLicenceNumber());
                System.out.println("Specialization: " + doc.getSpecialization());
                System.out.println("Consultations/Week: " + doc.getNumberOfConsultationPerWeek());
            } else if (found instanceof Receptionist) {
                Receptionist rec = (Receptionist) found;
                System.out.println("Desk Number: " + rec.getDeskNumber());
                System.out.println("Hours/Week: " + rec.getHoursPerWeek());
            }
            
        } catch (CustomException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }
    
    @Override
    public void sortStaffByName() {
        if (staffMembersList.isEmpty()) {
            System.out.println("✗ Staff list is empty!");
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
        
        System.out.println("\n✓ Staff sorted by name:");
        printStaffMember();
    }
    
    @Override
    public void displayStatistics() {
        System.out.println("\n=== Health Centre Statistics ===");
        System.out.println("================================");
        
        int total = staffMembersList.size();
        int doctors = 0;
        int receptionists = 0;
        
        for (StaffMember member : staffMembersList) {
            if (member instanceof Doctor) {
                doctors++;
            } else if (member instanceof Receptionist) {
                receptionists++;
            }
        }
        
        System.out.println("Total Staff Members: " + total);
        System.out.println("Doctors: " + doctors);
        System.out.println("Receptionists: " + receptionists);
        
        if (total > 0) {
            double doctorPercentage = (double) doctors / total * 100;
            double receptionistPercentage = (double) receptionists / total * 100;
            
            System.out.printf("Doctor Percentage: %.1f%%\n", doctorPercentage);
            System.out.printf("Receptionist Percentage: %.1f%%\n", receptionistPercentage);
            
            // Additional statistics for doctors
            if (doctors > 0) {
                int totalConsultations = 0;
                for (StaffMember member : staffMembersList) {
                    if (member instanceof Doctor) {
                        totalConsultations += ((Doctor) member).getNumberOfConsultationPerWeek();
                    }
                }
                double avgConsultations = (double) totalConsultations / doctors;
                System.out.printf("Average consultations per doctor: %.1f/week\n", avgConsultations);
            }
            
            // Additional statistics for receptionists
            if (receptionists > 0) {
                int totalHours = 0;
                for (StaffMember member : staffMembersList) {
                    if (member instanceof Receptionist) {
                        totalHours += ((Receptionist) member).getHoursPerWeek();
                    }
                }
                double avgHours = (double) totalHours / receptionists;
                System.out.printf("Average hours per receptionist: %.1f/week\n", avgHours);
            }
        } else {
            System.out.println("No staff members in the system.");
        }
    }
    
    @Override
    public void runGUI() {
        try {
            StaffManagementGUI gui = new StaffManagementGUI(new ArrayList<>(staffMembersList), this);
            gui.setVisible(true);
        } catch (Exception e) {
            System.out.println("✗ Error starting GUI: " + e.getMessage());
        }
    }
    
    @Override
    public void saveToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            // Write header
            writer.write("TYPE,ID,NAME,SURNAME,DOB,CONTACT,FIELD1,FIELD2,FIELD3");
            writer.newLine();
            
            // Write data
            for (StaffMember staff : staffMembersList) {
                if (staff instanceof Doctor) {
                    Doctor doc = (Doctor) staff;
                    writer.write(String.format("D,%s,%s,%s,%s,%s,%s,%s,%d",
                        doc.getStaffId(), 
                        doc.getName(), 
                        doc.getSurName(),
                        doc.getDob(), 
                        doc.getPhoneNo(),
                        doc.getLicenceNumber(), 
                        doc.getSpecialization(),
                        doc.getNumberOfConsultationPerWeek()));
                } else if (staff instanceof Receptionist) {
                    Receptionist rec = (Receptionist) staff;
                    writer.write(String.format("R,%s,%s,%s,%s,%s,%d,%d,",
                        rec.getStaffId(), 
                        rec.getName(), 
                        rec.getSurName(),
                        rec.getDob(), 
                        rec.getPhoneNo(),
                        rec.getDeskNumber(), 
                        rec.getHoursPerWeek()));
                }
                writer.newLine();
            }
        }
    }
    
    @Override
    public void loadFromFile() throws IOException {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("✗ No data file found. Starting with empty list.");
            return;
        }
        
        staffMembersList.clear();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String header = reader.readLine(); // Skip header
            if (header == null || !header.startsWith("TYPE")) {
                throw new IOException("Invalid file format");
            }
            
            String line;
            int count = 0;
            int errors = 0;
            
            while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length < 8) {
                        errors++;
                        continue;
                    }
                    
                    String type = parts[0].trim();
                    
                    if (type.equals("D") && parts.length >= 9) {
                        // Parse doctor data
                        Doctor doctor = new Doctor(
                            parts[2].trim(), // name
                            parts[3].trim(), // surname
                            parts[4].trim(), // dob
                            parts[5].trim(), // phone
                            parts[1].trim(), // id
                            parts[6].trim(), // licence
                            parts[7].trim(), // specialization
                            Integer.parseInt(parts[8].trim()) // consultations
                        );
                        staffMembersList.add(doctor);
                        count++;
                        
                    } else if (type.equals("R") && parts.length >= 8) {
                        // Parse receptionist data
                        Receptionist receptionist = new Receptionist(
                            parts[2].trim(), // name
                            parts[3].trim(), // surname
                            parts[4].trim(), // dob
                            parts[5].trim(), // phone
                            parts[1].trim(), // id
                            Integer.parseInt(parts[6].trim()), // desk number
                            Integer.parseInt(parts[7].trim()) // hours
                        );
                        staffMembersList.add(receptionist);
                        count++;
                    } else {
                        errors++;
                    }
                    
                } catch (Exception e) {
                    errors++;
                }
            }
            
            if (errors > 0) {
                System.out.println("⚠ Loaded " + count + " records, " + errors + " errors encountered.");
            } else {
                System.out.println("✓ Successfully loaded " + count + " staff records.");
            }
            
        } catch (Exception e) {
            throw new IOException("Error reading file: " + e.getMessage());
        }
    }
    
    // Helper method to get valid integer input
    private int getValidIntegerInput(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter value: ");
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("✗ Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
    
    public List<StaffMember> getStaffMembersList() {
        return Collections.unmodifiableList(staffMembersList);
    }
    
    public void addStaffMember(StaffMember member) {
        staffMembersList.add(member);
    }
    
    public void removeStaffMember(StaffMember member) {
        staffMembersList.remove(member);
    }
}