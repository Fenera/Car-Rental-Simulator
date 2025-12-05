package staff;

public abstract class Staff {

    private int staffID;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public Staff(String name, String email, String phoneNumber, String address, int staffID) {
        this.staffID = staffID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    //Get and Set
    public int getStaffID() { return staffID;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getAddress() { return address;}
    public void setAddress(String address) {this.address = address;}

    // abstract method
    public abstract String getInformation();

}