package com.healthcentre.model;

public class Receptionist extends StaffMember {

    private int deskNumber;
    private int hoursPerWeek;

    public Receptionist() {}

    public Receptionist(String name, String surName, String dob, String phoneNo, String staffId,
                        int deskNumber, int hoursPerWeek) {
        super(name, surName, dob, phoneNo, staffId);
        this.deskNumber = deskNumber;
        this.hoursPerWeek = hoursPerWeek;
    }

    @Override
    public String getRole() { return "Receptionist"; }

    public int getDeskNumber() { return deskNumber; }
    public void setDeskNumber(int deskNumber) { this.deskNumber = deskNumber; }

    public int getHoursPerWeek() { return hoursPerWeek; }
    public void setHoursPerWeek(int hoursPerWeek) { this.hoursPerWeek = hoursPerWeek; }
}
