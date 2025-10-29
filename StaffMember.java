public abstract class StaffMember{
    private String id;
    private String name;
    private String surname;
    private String dob;
    private String contactNo;

}

public StaffMember(String id, String name, String surname, String dob, String contactNo) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.contactNo = contactNo;
}

public String getID(){
    return id;


}

public void setId(String id){
    this.id = id;
}


 public String getName() {
        return name;
    }


public void setName(String name) {
this.name = name;
}

public String getSurname() {
        return surname;
    }


public void setSurname(String surname) {
this.surname = surname;
}

public String getDob() {
return dob;
}
 
public void setDob(String dob) {
    this.dob = dob;
}

public String getContactNo() {
    return contactNo;
}

public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

public  abstract String getRole();