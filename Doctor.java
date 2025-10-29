

public class Doctor extends StaffMember {
    private String licenceNumber;
    private String specialisation;
    private String consultationsPerWeek;

    
}

public Doctor(String id, String name, String surname, String dob, String contactNo, String licenceNumber, String specialisation, String consultationsPerWeek){
    super(id, name, surname, dob, contactNo);
    this.licenceNumber = licenceNumber;
    this.specialisation = specialisation;
    this.consultationsPerWeek = consultationsPerWeek;

}



