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

    public Rental rentVehicle(int vin, Employee employee, Client client, int daysToRent){
        /*
        * Get vehicle from fleet -> check if available -> mark as rented -> add to rental LL -> add to activity log*/
        Vehicle vehicle = fleet.getVehicleByVin(vin);
        if(vehicle == null) throw new IllegalArgumentException(String.format("No Vehicle found with: %d", vin));
        if(!vehicle.isAvailable()){
            throw new IllegalArgumentException(String.format("Vehicle with VIN: %d is not available", vin));
        }

        vehicle.setAvailable(false);

        Rental rental = new Rental(rentalID, vehicle, client, employee,
                LocalDateTime.now(), LocalDateTime.now(),
                fleet.getRateByVin(vin));

        activeRentals.append(vin, rental);
        rentalHistory.append(rental);
        String logMessage = String.format("EmployeeID: %d ==RENTED== %s ==TO== ClientID: %d",
                employee.getStaffID(), String.format("%s %s (vin=%d)"), vehicle.getManufacturer(), vehicle.getModel(), vin,
                client.getClientID());

        logManager.addEntry(new LogEntry(LocalDateTime.now(), logMessage, LogType.CAR_RENTED));

        return rental;
    }

    public void returnVehicle(Employee employee, Client client, int vin, int milesDriven){
        /*
        * Check if vehicle is rented (in LL) -> add mileage -> mark as available -> remove from rentedVehicle -> add to activity log*/
        fleet.getVehicleByVin(vin).setOdometer(milesDriven);

        Rental remove = activeRentals.removeByValue(vin); // remove the rental from active rentals

        String logMessage = String.format("ClientID: %d ==RETURNED== %s ==TO== EmployeeID: %d",
                client.getClientID(), String.format("%s %s (vin=%d)",
                        remove.getVehicle().getManufacturer(), remove.getVehicle().getModel(), vin),
                employee.getStaffID());
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

    public List<Rental> findRentalsByVin(int vin){
        // returns a list of rentals for a specific car
        List<Rental> results = new ArrayList<>();

        // same as method above
        for(Iterator<Rental> it = rentalHistory.items(); it.hasNext(); ){
            Rental r = it.next();
            if(r.getVehicle().getVIN() == vin){
                results.add(r);
            }
        }
        return results;
    }
}
