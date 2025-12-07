package edu.augie.finalProgram.taye.rental;

import edu.augie.finalProgram.taye.client.Client;
import edu.augie.finalProgram.taye.datastructures.LinkedList;
import edu.augie.finalProgram.taye.datastructures.LinkedList2;
import edu.augie.finalProgram.taye.staff.Employee;
import edu.augie.finalProgram.taye.utilities.LogEntry;
import edu.augie.finalProgram.taye.utilities.LogManager;
import edu.augie.finalProgram.taye.utilities.LogType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
* https://stackoverflow.com/questions/33072986/iterator-for-a-linkedlist?newreg=370eb6f9b9a84074a6fd7cf5b7e4932e
* Source accessed: 12/4
* */
public class RentalManager {

    private Employee employee; // stores the employee renting out the car
    private Client customer; // stores the customer renting the car
    private LinkedList2<Integer, Rental> activeRentals; // stores active rentals
    private LinkedList<Rental> rentalHistory; // stores all rentals (active + past)
    private LogManager logManager;



    public RentalManager(LogManager logManager){
        // initialize the variables
        this.logManager = logManager;
        activeRentals = new LinkedList2<>();
        rentalHistory = new LinkedList<>();
    }

    public void rentVehicle(Rental rental){
        // Method for renting out a vehicle from the inventory

        // add the vehicle to active rentals and edu.augie.finalProgram.taye.rental history LL
        activeRentals.append(rental.getVehicle().getVIN(), rental);
        rentalHistory.append(rental);

        // create a log message
        String logMessage = String.format("EmployeeID: %d ==RENTED== %s ==TO== ClientID: %d",
                employee.getStaffID(), String.format("%s %s (vin=%d)"), rental.getVehicle().getManufacturer(),
                rental.getVehicle().getModel(), rental.getVehicle().getVIN(),
                rental.getCustomer().getClientID());

        // add it to log manager
        logManager.addEntry(new LogEntry(LocalDateTime.now(), logMessage, LogType.CAR_RENTED));
    }

    public void returnVehicle(Rental rental){
        // Method for returning a edu.augie.finalProgram.taye.rental to the inventory

        // remove the vehicle from active rentals
        activeRentals.removeByValue(rental.getVehicle().getVIN());

        // create a message for the log
        String logMessage = String.format("EmployeeID: %d ==PROCESSED RETURN== %s ==FOR== ClientID: %d",
                employee.getStaffID(), String.format("%s %s (vin=%d)"), rental.getVehicle().getManufacturer(),
                rental.getVehicle().getModel(), rental.getVehicle().getVIN(),
                rental.getCustomer().getClientID());

        // add the message to the log manager
        logManager.addEntry(new LogEntry(LocalDateTime.now(), logMessage, LogType.CAR_RETURNED));
    }

    // not used in program
    public List<Rental> findRentalsByEmployee(int staffID){
        // returns a list of employees and their rentals
        List<Rental> results = new ArrayList<>();

        // use iterator to iterate through Rentals LL
        for(Iterator<Rental> it = rentalHistory.items(); it.hasNext(); ) {
            Rental r = it.next();
            if(r.getEmployee().getStaffID() == staffID){ // check if id's match
                results.add(r); // add them to LL
            }
        }
        return results;
    }


    public void printRentalByVin(int vin){
        // prints rentals of vehicles with given vin number

        // iterate through edu.augie.finalProgram.taye.rental history
        for(Iterator<Rental> it = rentalHistory.items(); it.hasNext(); ){
            Rental r = it.next(); // grab current edu.augie.finalProgram.taye.rental
            if(r.getVehicle().getVIN() == vin){
                System.out.println(r.getVehicle());
            }
        }
    }

    public Rental findActiveRentalsByVin(int vin){
        // returns active edu.augie.finalProgram.taye.rental with that vin

        Rental found = null;
        // use iterator to iterate activeRental values (Rental objects)
        for (Iterator<Rental> it = activeRentals.iterator(); it.hasNext(); ) {
            Rental r = it.next();
            if(r.getVehicle().getVIN() == vin){ // see if the vehicle with that vin is an active edu.augie.finalProgram.taye.rental
                found = r;
            }
        }

        return found; // return the edu.augie.finalProgram.taye.rental (or null if non was found)
    }

    public void printRentedVehicles(){
        // prints all the vehicles that are rented
        List<Rental> results = new ArrayList<>();

        // use iterator to iterate activeRental values (Rental objects)
        for (Iterator<Rental> it = activeRentals.iterator(); it.hasNext(); ) {
            Rental r = it.next();
            System.out.println(r);
        }
    }

    public void printRentalHistory(){
        // prints edu.augie.finalProgram.taye.rental history
        // use iterator to iterate through Rentals LL
        for(Iterator<Rental> it = rentalHistory.items(); it.hasNext(); ) {
            System.out.print(it.next()); // print the edu.augie.finalProgram.taye.rental
        }
    }
}
