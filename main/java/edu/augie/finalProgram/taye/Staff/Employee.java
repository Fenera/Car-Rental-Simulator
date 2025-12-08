package edu.augie.finalProgram.taye.Staff;

public class Employee extends Staff {

    private boolean isActive; // employee status (true = working)

    // constructor
    public Employee(String name, String email, String phoneNumber,
                    String address, int staffID, boolean isActive) {
        super(name, email, phoneNumber, address, staffID);
        this.isActive = isActive;
    }

    // setter/getter

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    @Override
    public String getInformation() {
        return "================================================\n" +
                "ID: " + getStaffID() + "\n" +
                "Name: " + getName() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Phone: " + getPhoneNumber() + "\n" +
                "Address: " + getAddress() + "\n" +
                "================================================\n\n";
    }

    // Custom toString for Employee
    @Override
    public String toString() {
        return String.format(
                "+----------------------------------------\n" +
                "|----------------------------------------\n" +
                "|ID:     %d\n" +
                "|Name:   %s\n" +
                "|Email:  %s\n" +
                "|Phone:  %s\n" +
                "|Address:%s\n" +
                "|Active: %s\n" +
                "+----------------------------------------\n",
                super.getStaffID(),
                super.getName(),
                super.getEmail(),
                super.getPhoneNumber(),
                super.getAddress(),
                isActive ? "Yes" : "No"
        );
    }

}


