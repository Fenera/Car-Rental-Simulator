package staff;

public class Employee extends Staff {

    private boolean isActive; // employee status (true = working)

    public Employee(String name, String email, String phoneNumber,
                    String address, int staffID, boolean isActive) {
        super(name, email, phoneNumber, address, staffID);
        this.isActive = isActive;
    }


    public void showInventory() {}

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
}


