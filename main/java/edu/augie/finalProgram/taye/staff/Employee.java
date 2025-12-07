package edu.augie.finalProgram.taye.staff;

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
        return String.format("""
            +────────────────────────────────────────
            │────────────────────────────────────────
            │ID:    %d                          \s
            │Name:          %s                        \s
            │Email:      %s                    \s
            │Phone:         %s                          \s
            │Address:        %s                          \s
            │Active:      %s                   \s
            +────────────────────────────────────────
           \s""", super.getStaffID(), super.getName(), super.getEmail(), super.getPhoneNumber(),
                super.getAddress(), isActive ? "Yes" : "No"
        );
    }
}


