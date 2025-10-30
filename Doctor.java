public class Doctor extends StaffMember {
    private String licenceNumber;
    private String specialization;
    private int numberOfConsultationPerWeek;

    public Doctor(String name, String surName, String dob, String phoneNo, String staffId, String licenceNumber, String specialization, int numberOfConsultationPerWeek){
        super(name, surName, dob, phoneNo, staffId);// call to the super class constructor
        // to initialize the attributes of the super class(staffmember)
        this.licenceNumber = licenceNumber;
        this.specialization = specialization;
        this.numberOfConsultationPerWeek = numberOfConsultationPerWeek;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getNumberOfConsultationPerWeek() {
        return numberOfConsultationPerWeek;
    }

    public void setNumberOfConsultationPerWeek(int numberOfConsultationPerWeek) {
        this.numberOfConsultationPerWeek = numberOfConsultationPerWeek;
    }

    @Override
    public String toString(){
        return super.toString()+" Licence Number "+licenceNumber+" Specialization "+specialization+" Number of Consultation per Week "+numberOfConsultationPerWeek ;
    }



}


















