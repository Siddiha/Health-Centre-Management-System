package com.healthcentre.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "role")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Doctor.class, name = "Doctor"),
    @JsonSubTypes.Type(value = Receptionist.class, name = "Receptionist")
})
public abstract class StaffMember implements Comparable<StaffMember> {

    private String name;
    private String surName;
    private String dob;
    private String phoneNo;
    private String staffId;

    public StaffMember() {}

    public StaffMember(String name, String surName, String dob, String phoneNo, String staffId) {
        this.name = name;
        this.surName = surName;
        this.dob = dob;
        this.phoneNo = phoneNo;
        this.staffId = staffId;
    }

    public abstract String getRole();

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurName() { return surName; }
    public void setSurName(String surName) { this.surName = surName; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }

    @Override
    public int compareTo(StaffMember o) {
        return this.staffId.compareTo(o.staffId);
    }
}
