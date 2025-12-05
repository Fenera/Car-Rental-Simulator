package utilities;
import client.Client;
import datastructures.LinkedList;
import rental.Rental;
import staff.Employee;

import java.time.LocalDateTime;

// this is the update logger
// writes to a text file in Output folder (below main folder)
// write any update (car rented out, car returned, staff sign-in, client sign-in)



/*
* to do : format time in cleaner format and as String: i.e. 12/5/2025 12:43 pm
* Different types of log constructors (to log rentals, to log sign-ins, to log actions) -> different parameters
*
* */
public class LogEntry {
    /*
    * Attributes:
    * Title (i.e. New Rental, Check-in)
    * Message
    *Time
    * Who: Client or staff updated the log -> name, customerID, staffID, phone number
    * output file: .txt file
    * */

    private LocalDateTime time;
    private String action;
    private Employee employee;
    private Client customer;
    private int vin;
    private Rental rental;
    private String detail;


    public LogEntry(LocalDateTime time, String action, Employee employee,
                  Client customer, int vin, Rental rental, String detail){
        this.time = time;
        this.action = action;
        this.employee = employee;
        this.customer = customer;
        this.vin = vin;
        this.rental = rental;
        this.detail = detail;
    }

    //

    // getter & setter methods

    public String getTime() {
        // converts LocalDateTime object to String time with correct format
        return String.format("%d/%d/%d @ %d:%d:%d", time.getMonth(), time.getDayOfMonth(), time.getYear(),
                time.getHour(), time.getMinute(), time.getSecond());
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Client getCustomer() {
        return customer;
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() { // custom toString() method to track rentals/returns
        return "LogEntry{" +
                "time=" + getTime() +
                ", action='" + action + '\'' +
                ", employeeID=" + employee.getStaffID() +
                ", customerID=" + customer.getClientID() +
                ", vin=" + vin +
                ", rentalID=" + rental.getRentalID() +
                ", detail='" + detail + '\'' +
                '}';
    }

    // getter & setter methods

    public String getTime() {
        // converts LocalDateTime object to String time with correct format
        return String.format("%d/%d/%d @ %d:%d:%d", time.getMonth(), time.getDayOfMonth(), time.getYear(),
                time.getHour(), time.getMinute(), time.getSecond());
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "time=" + getTime() +
                ", message='" + message + '\'' +
                ", type=" + type +
                '}';
    }
}
