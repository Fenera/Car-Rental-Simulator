package Tests;

import edu.augie.finalProgram.taye.Fleet.Fleet;
import edu.augie.finalProgram.taye.Vehicle.*;
import edu.augie.finalProgram.taye.Staff.Manager;
import edu.augie.finalProgram.taye.Log.LogManager;

public class FleetTest {

    public static void main(String[] args) {
        LogManager log = new LogManager();

        Manager manager = new Manager("Saruultugs Oyuntsetseg", "so@hotmail.com", "605-555-555", "2001 Summit Ave", 0000, log);
        LogManager logManager = new LogManager();
        Fleet fleet = new Fleet(manager, logManager);

        // Create some vehicles
        Vehicle sedan = new Sedan(1001, "Toyota", "Camry", 2025, 0, "Red",
                5, "", 4, false, new GasMotor("Gasoline", 30.0, "2.5L", 15.0), 203);

        Vehicle suv = new SUV(1002, "Ford", "Explorer", 2025, 0, "Blue",
                7, "", 4, true, new GasMotor("Gasoline", 25.0, "3.0L", 20.0), 290, 50.0, 5000.0);

        // Add vehicles to fleet
        fleet.addNewVehicle(manager.getStaffID(), sedan, 50.0);
        fleet.addNewVehicle(manager.getStaffID(), suv, 75.0);

        // Test retrieval
        System.out.println("Get vehicle by VIN 1001:");
        System.out.println(fleet.getVehicleByVin(1001));

        System.out.println("\nGet rate by VIN 1002:");
        System.out.println(fleet.getRateByVin(1002));

        // Test filtering by body type
        System.out.println("\nFilter by body type 'SUV':");
        fleet.filterByBodyType("SUV");

        // Test filtering by price
        System.out.println("\nFilter by price $40-$60:");
        fleet.filterByPriceRange(40, 60);

        // Test filtering by horsepower
        System.out.println("\nFilter by horsepower 200-300:");
        fleet.filterByHorsePower(200, 300);

        // Test filtering by MPG
        System.out.println("\nFilter by MPG 20-35:");
        fleet.filterByMPG(20, 35);

        // Test filtering by fuel type
        System.out.println("\nFilter by fuel type 'Gasoline':");
        fleet.filterByFuelType("Gasoline");

        // Test removal
        fleet.removeVehicleFromLot(manager.getStaffID(), 1001);
        System.out.println("\nAfter removing VIN 1001, get vehicle:");
        System.out.println(fleet.getVehicleByVin(1001)); // should print null

        // Show available cars
        System.out.println("\nAvailable cars:");
        fleet.showAvailableCars();
    }
}
