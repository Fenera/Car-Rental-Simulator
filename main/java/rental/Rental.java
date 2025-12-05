package rental;

import client.Client;
import edu.augie.finalProgram.taye.Vehicles.Vehicle;
import staff.Employee;
import java.time.LocalDateTime;

public class Rental {
    private final int rentalID;
    private Vehicle vehicle;
    private Client customer;
    private Employee employee;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private double rate;

    private boolean rentalActive;

    public Rental(int rentalID, Vehicle vehicle, Client customer, Employee employee,
                  LocalDateTime startDate, LocalDateTime endDate, double rate){
        this.rentalID = rentalID;
        this.vehicle = vehicle;
        this.customer = customer;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rate = rate;

        rentalActive = true;
    }

    // getter and setter methods for all the variables
    public int getRentalID() {
        return rentalID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Client getCustomer() {
        return customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    // methods to calculate cost
    public double calculateRentalCost(){
        return (endDate.getDayOfYear() - endDate.getDayOfYear()) * rate;
    }

    public void endRental(){
        this.rentalActive = false;
    }
}
