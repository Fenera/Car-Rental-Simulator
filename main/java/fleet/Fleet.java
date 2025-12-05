package fleet;
import datastructures.BinarySearchTree;
import datastructures.LinkedList;
import edu.augie.finalProgram.taye.Vehicles.*;
import utilities.ReadCSV;
import utilities.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

// this stores all the cars
// get the cars the csv file (use the CSVLoader.java to get the values and format...)
// this is the engine -> BST is initialized here (get car, add car, remove car)
// more -> add updates to logger(car ... was checked out at 12:00 pm by ...)
// user should be able to search by specific criteria (i.e mpg > 20) -> return linkedlist of all cars that meet this criteria



public class Fleet {
    private BinarySearchTree<String, Vehicle> vehicleByVinBST; // this is to store all the cars on the fleet
    private LinkedList<Vehicle> availableVehicleLL; // this is to store all the cars that are available

    public Fleet(){
        this.vehicleByVinBST = new BinarySearchTree<>();
        this.availableVehicleLL = new LinkedList<Vehicle>();
    }

    public void loadFromFile(String file) throws IOException {
        ReadCSV reader = new ReadCSV(file);
        List<String[]> vehicles = reader.readAll();

        for(String[] r: vehicles){ // iterate through the rows

            // general vehicle attributes
            // get data from file and store in variables
            int year = 2025;
            String manufacturer = r[1]; // r[0] is row number so start at r[1]
            String model = r[2];
            String engine = r[3];
            int horsepower = Integer.valueOf(r[4]);
            int topSpeedMPH = (int) (Integer.valueOf(r[5]) * 0.621371); // convert kmh to mph and convert to int
            double zeroSixty = Double.valueOf(r[6]);
            int priceMSRP = Integer.valueOf(r[7]);
            String fuel = r[8];
            int numberOfSeats = Integer.valueOf(r[9]);
            String vehicleClass = r[10]; // SUV, Sedan..
            double fuelEconomy = Double.valueOf(r[11]);
            double gasTankSize = Double.valueOf(r[12]);
            int vinNumber = Integer.valueOf(r[13]);
            int inventory = Integer.valueOf(r[14]);
            int odometer = Integer.valueOf(r[15]);
            String color = r[16];
            int numberDoors = Integer.valueOf(r[17]);
            boolean awd = r[18].equals("TRUE");
            String fuelClass = r[19];


            // Update in the future (if needed)
            String conditionReport = "";

            // vehicle class-specific attributes (i.e. bed length for trucks)

            double cargoCapacity = Double.valueOf(r[20]);
            double towingCapacity = Double.valueOf(r[21]);
            int standingCapacity = Integer.valueOf(r[22]);
            int length = Integer.valueOf(r[23]);
            boolean accessibleRamp = r[24].equals("TRUE");
            double trunkVolume = Double.valueOf(r[25]);
            double bedLength = Double.valueOf(r[26]);


            vehicleByVinBST.insert(Integer.valueOf(vinNumber)); // VIN is the last column in the array
            Vehicle vehicle; // instantiate Vehicle object

            // determine the class of the vehicle to create appropriate object (SUV, Sedan...)
            if(vehicleClass.equals("SUV")){
                if(Objects.equals(fuelClass, "Gas")) {
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, cargoCapacity, towingCapacity);
                }
            } else if(vehicleClass.equals("Sedan")){
                if(Objects.equals(fuelClass, "Gas")) {
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Hybrid")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Plug-In")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Electric")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new ElectricMotor(maxRange, batteryHealth), horsepower, cargoCapacity, towingCapacity);
                } else{
                    continue; // skip the current iteration if fuelClass is invalid
                }
            }   else if(vehicleClass.equals("Coupe")){
                if(Objects.equals(fuelClass, "Gas")) {
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Hybrid")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Plug-In")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Electric")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new ElectricMotor(maxRange, batteryHealth), horsepower, cargoCapacity, towingCapacity);
                } else{
                    continue; // skip the current iteration if fuelClass is invalid
                }
            }   else if(vehicleClass.equals("Bus")){
                if(Objects.equals(fuelClass, "Gas")) {
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Hybrid")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Plug-In")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Electric")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new ElectricMotor(maxRange, batteryHealth), horsepower, cargoCapacity, towingCapacity);
                } else{
                    continue; // skip the current iteration if fuelClass is invalid
                }
            }   else if(vehicleClass.equals("Truck")){
                if(Objects.equals(fuelClass, "Gas")) {
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Hybrid")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Plug-In")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Electric")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new ElectricMotor(maxRange, batteryHealth), horsepower, cargoCapacity, towingCapacity);
                } else{
                    continue; // skip the current iteration if fuelClass is invalid
                }
            }   else if(vehicleClass.equals("Van")){
                if(Objects.equals(fuelClass, "Gas")) {
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Hybrid")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Plug-In")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, cargoCapacity, towingCapacity);
                } else if(Objects.equals(fuelClass, "Electric")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new ElectricMotor(maxRange, batteryHealth), horsepower, cargoCapacity, towingCapacity);
                } else{
                    continue; // skip the current iteration if fuelClass is invalid
                }
            } else{
                throw new RuntimeException("The car cannot be added to the fleet");
            }

            /*
            * to do: Add Vehicle objects to LL
            *  else gas, plugIN, hybrid, electric....
            * */
        }
    }

    public void getVehicleByVin(){

    }
    // or Manager object
    public void addNewVehicle(int managerID){

    }

    public void removeVehicleFromLot(int managerID) {

    }
}
