package staff;

public class Manager extends Staff {

    public Manager(String name, String email, String phoneNumber, String address, int staffID) {
        super(name, email, phoneNumber, address, staffID);
    }

    // Manager functionalities
    public void viewEmployeeInfo() {}
    public void seeRentedCars() {}
    public void viewPaymentLogs() {}
    public void addCarToFleet() {}
    public void removeCarFromFleet() {}

    @Override
    public String getInformation() {
        return "Manager Information:\n" +
                "ID: " + getStaffID() + "\n" +
                "Name: " + getName() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Phone: " + getPhoneNumber() + "\n" +
                "Address: " + getAddress() + "\n";
    }
}



