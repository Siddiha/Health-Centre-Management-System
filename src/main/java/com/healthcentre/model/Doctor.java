package com.healthcentre.model;

public class Doctor extends StaffMember {

    private String licenceNumber;
    private String specialization;
    private int numberOfConsultationPerWeek;

    public Doctor() {}

    public Doctor(String name, String surName, String dob, String phoneNo, String staffId,
                  String licenceNumber, String specialization, int numberOfConsultationPerWeek) {
        super(name, surName, dob, phoneNo, staffId);
        this.licenceNumber = licenceNumber;
        this.specialization = specialization;
        this.numberOfConsultationPerWeek = numberOfConsultationPerWeek;
    }

    @Override
    public String getRole() { return "Doctor"; }

    public String getLicenceNumber() { return licenceNumber; }
    public void setLicenceNumber(String licenceNumber) { this.licenceNumber = licenceNumber; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public int getNumberOfConsultationPerWeek() { return numberOfConsultationPerWeek; }
    public void setNumberOfConsultationPerWeek(int numberOfConsultationPerWeek) {
        this.numberOfConsultationPerWeek = numberOfConsultationPerWeek;
    }
}
