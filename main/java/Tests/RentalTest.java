package Tests;

import edu.augie.finalProgram.taye.Client.Client;
import edu.augie.finalProgram.taye.Log.LogManager;
import edu.augie.finalProgram.taye.Rental.Rental;
import edu.augie.finalProgram.taye.Staff.Employee;
import edu.augie.finalProgram.taye.Vehicle.Vehicle;
import edu.augie.finalProgram.taye.Vehicle.Sedan;

import java.time.LocalDateTime;

public class RentalTest {
    public static void main(String[] args) {
        // Create mock Vehicle
        Vehicle vehicle = new Sedan(12345, "Toyota", "Camry", 2025,
                5000, "Blue", 5, "", 4, false, null, 200);

        LogManager log = new LogManager();

        // Create mock Client
        Client client = new Client("Alice Smith", "555-555-5555", "alice@example.com", "2001 S Summit Ave");

        // Create mock Employee
        Employee employee = new Employee("Bob Johnson", "bob@example.com",
                "555-1234", "123 Main St", 201, true);
        // Set rental dates
        LocalDateTime start = LocalDateTime.of(2025, 12, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2025, 12, 5, 10, 0);

        // Create Rental
        Rental rental = new Rental(vehicle, client, employee, start, end, 50.0);

        // Display rental info
        System.out.println("Rental Details:");
        System.out.println(rental);

        // Test setters
        rental.setRate(55.0);
        rental.setVehicle(vehicle);
        rental.setCustomer(client);
        rental.setEmployee(employee);

        System.out.println("\nAfter updating rate:");
        System.out.println("Rate: " + rental.getRate());

        // End rental
        rental.endRental();
        System.out.println("Rental ended.");
    }
}
