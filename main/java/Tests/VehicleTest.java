package Tests;

import edu.augie.finalProgram.taye.Vehicle.Powertrain;
import edu.augie.finalProgram.taye.Vehicle.SUV;

public class VehicleTest {
    public static void main(String[] args) {
        // Create a Powertrain stub for testing
        Powertrain gasEngine = new Powertrain() {
            @Override
            public double getRange() { return 400; }
            @Override
            public String getMotor() { return "Gas Engine"; }
        };

        // Instantiate an SUV
        SUV suv = new SUV(
                12345, "Toyota", "Highlander", 2025, 15000, "Blue",
                7, "Good", 4, true, gasEngine, 295, 2000.0, 5000.0
        );

        // Print initial SUV info
        System.out.println("Initial SUV info:");
        System.out.println(suv);

        // Test setters
        suv.setColor("Red");
        suv.setHorsePower(300);
        suv.setAllWheelDrive(false);
        suv.setCargoCapacity(2100);
        suv.setTowingCapacity(5200);

        // Print updated SUV info
        System.out.println("Updated SUV info:");
        System.out.println(suv);

        // Test canTow method
        System.out.println("Can tow 5000 lb? " + suv.canTow(5000)); // true
        System.out.println("Can tow 5300 lb? " + suv.canTow(5300)); // false

        // Test getBodyType
        System.out.println("Body type: " + suv.getBodyType());
    }
}
