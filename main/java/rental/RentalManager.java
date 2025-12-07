package rental;

import client.Client;
import datastructures.LinkedList;
import datastructures.LinkedList2;
import edu.augie.finalProgram.taye.Vehicle;
import fleet.Fleet;
import staff.Employee;
import utilities.LogEntry;
import utilities.LogManager;
import utilities.LogType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
* https://stackoverflow.com/questions/33072986/iterator-for-a-linkedlist?newreg=370eb6f9b9a84074a6fd7cf5b7e4932e
* Source accessed: 12/4
* */
public class RentalManager {

    private Fleet fleet;
    private Employee employee; // stores the employee renting out the car
    private Client customer; // stores the customer renting the car
    private LinkedList2<Integer, Rental> activeRentals; // stores active rentals
    private LinkedList2<Integer, Rental> rentalEmployee;
    private LinkedList<Rental> rentalHistory; // stores all rentals (active + past)
    private static int rentalID = 0; // unique id (static so not tied to object)
    private LogManager logManager;



    public RentalManager(Fleet fleet, LogManager logManager){
        this.fleet = fleet;
        this.logManager = logManager;
        activeRentals = new LinkedList2<>();
        rentalHistory = new LinkedList<>();
        rentalEmployee = new LinkedList2<>();
        rentalID ++;
    }

    public void rentVehicle(Rental rental){

        activeRentals.append(rental.getVehicle().getVIN(), rental);
        rentalHistory.append(rental);
        String logMessage = String.format("EmployeeID: %d ==RENTED== %s ==TO== ClientID: %d",
                employee.getStaffID(), String.format("%s %s (vin=%d)"), rental.getVehicle().getManufacturer(),
                rental.getVehicle().getModel(), rental.getVehicle().getVIN(),
                rental.getCustomer().getClientID());

        logManager.addEntry(new LogEntry(LocalDateTime.now(), logMessage, LogType.CAR_RENTED));
        activeRentals.append(rental.getVehicle().getVIN(), rental);
    }

    public void returnVehicle(Rental rental){

        activeRentals.removeByValue(rental.getVehicle().getVIN());

        String logMessage = String.format("EmployeeID: %d ==PROCESSED RETURN== %s ==FOR== ClientID: %d",
                employee.getStaffID(), String.format("%s %s (vin=%d)"), rental.getVehicle().getManufacturer(),
                rental.getVehicle().getModel(), rental.getVehicle().getVIN(),
                rental.getCustomer().getClientID());

        logManager.addEntry(new LogEntry(LocalDateTime.now(), logMessage, LogType.CAR_RETURNED));
    }

    public LinkedList2<Integer, Rental> getAllActiveRentals(){
        // returns all active rentals
        return activeRentals;
    }

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

        // iterate through rental history
        for(Iterator<Rental> it = rentalHistory.items(); it.hasNext(); ){
            Rental r = it.next(); // grab current rental
            if(r.getVehicle().getVIN() == vin){
                System.out.println(r.getVehicle());
            }
        }
    }

    public Rental findActiveRentalsByVin(int vin){
        Rental found = null;
        // use iterator to iterate activeRental values (Rental objects)
        for (Iterator<Rental> it = activeRentals.iterator(); it.hasNext(); ) {
            Rental r = it.next();
            if(r.getVehicle().getVIN() == vin){ // see if the vehicle with that vin is an active rental
                found = r;
            }
        }

        return found; // return the rental
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
        // prints rental history
        // use iterator to iterate through Rentals LL
        for(Iterator<Rental> it = rentalHistory.items(); it.hasNext(); ) {
            System.out.print(it.next()); // print the rental
        }
    }
}
