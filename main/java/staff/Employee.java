package staff;

public class Employee extends Staff {


    public Employee(String name, String email, String phoneNumber, String address, int staffID) {
        super(name, email, phoneNumber, address, staffID);
    }


    public void showInventory() {}

    @Override
    public String getInformation() {
        return "Employee Information:\n" +
                "ID: " + getStaffID() + "\n" +
                "Name: " + getName() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Phone: " + getPhoneNumber() + "\n" +
                "Address: " + getAddress() + "\n";
    }
}


