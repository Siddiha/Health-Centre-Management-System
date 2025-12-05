//we are using abstraction to control the access level of the class
//if we want to define a abstract method inside a class the class must be abstracted class
//compareto it gives three vaues 

//comapre is here it compares with the other object  staffmember with another staff memebet
public abstract class StaffMember implements Comparable<StaffMember> {
    private String name;
    private String surName;
    private String dob;
    private String phoneNo;
    private String staffId;

    public StaffMember(String name, String surName, String dob, String phoneNo, String staffId) {
        this.name = name;
        this.surName = surName;
        this.dob = dob;
        this.phoneNo = phoneNo;
        this.staffId = staffId;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        return "Staff Member Name " + getName() + " Surname " + getSurName() + " DOB " + getDob()
                + " Phone Number " + getPhoneNo() + " StaffID " + getStaffId();
    }


    // this is to copare with the objects 
    public int compareTo(StaffMember o) {

        return this.staffId.compareTo(o.staffId);

    }





}



























