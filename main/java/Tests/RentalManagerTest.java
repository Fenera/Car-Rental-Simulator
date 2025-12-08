package Tests;

import edu.augie.finalProgram.taye.Client.Client;
import edu.augie.finalProgram.taye.Fleet.Fleet;
import edu.augie.finalProgram.taye.Log.LogEntry;
import edu.augie.finalProgram.taye.Log.LogManager;
import edu.augie.finalProgram.taye.Log.LogType;
import edu.augie.finalProgram.taye.Rental.Rental;
import edu.augie.finalProgram.taye.Rental.RentalManager;
import edu.augie.finalProgram.taye.Staff.Employee;
import edu.augie.finalProgram.taye.Vehicle.Sedan;
import edu.augie.finalProgram.taye.Vehicle.Vehicle;

import java.time.LocalDateTime;

public class RentalManagerTest {

    public static void main(String[] args) {
        // Initialize Fleet and LogManager
        LogManager logManager = new LogManager();
        Fleet fleet = new Fleet(null, logManager); // null manager since we are just testing

        // Create Vehicle
        Vehicle vehicle1 = new Sedan(1001, "Toyota", "Camry", 2025, 5000, "Blue",
                5, "", 4, false, null, 200);
        Vehicle vehicle2 = new Sedan(1002, "Honda", "Accord", 2025, 3000, "Red",
                5, "", 4, false, null, 180);

        // Add vehicles to fleet
        fleet.addNewVehicle(0, vehicle1, 50.0);
        fleet.addNewVehicle(0, vehicle2, 45.0);

        // Create Employee and Client
        Employee employee = new Employee("Bob Johnson", "bob@example.com",
                "555-1234", "123 Main St", 201, true);
        Client client = new Client("Alice Smith", "555-555-5555", "alice@example.com", "2001 S Summit Ave");

        // Create RentalManager
        RentalManager rentalManager = new RentalManager(logManager, fleet);

        // Create Rentals
        Rental rental1 = new Rental(vehicle1, client, employee,
                LocalDateTime.of(2025, 12, 1, 10, 0),
                LocalDateTime.of(2025, 12, 5, 10, 0), 50.0);

        Rental rental2 = new Rental(vehicle2, client, employee,
                LocalDateTime.of(2025, 12, 2, 12, 0),
                LocalDateTime.of(2025, 12, 6, 12, 0), 45.0);

        // Rent vehicles
        rentalManager.rentVehicle(rental1);
        rentalManager.rentVehicle(rental2);

        // Print active rentals
        System.out.println("Active Rentals:");
        rentalManager.printRentedVehicles();

        // Print rental history
        System.out.println("\nRental History:");
        rentalManager.printRentalHistory();

        // Return one vehicle
        rentalManager.returnVehicle(rental1);

        // Print active rentals after return
        System.out.println("\nActive Rentals After Return:");
        rentalManager.printRentedVehicles();

        // Display logs
        System.out.println("\nLogs:");
        logManager.displayLogs();
    }
}
