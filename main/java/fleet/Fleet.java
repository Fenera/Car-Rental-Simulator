package fleet;
import datastructures.BinarySearchTree;
import datastructures.LinkedList;
import edu.augie.finalProgram.taye.*;
import staff.Manager;
import utilities.LogEntry;
import utilities.LogManager;
import utilities.LogType;
import utilities.ReadCSV;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

// this stores all the cars
// get the cars the csv file (use the CSVLoader.java to get the values and format...)
// this is the engine -> BST is initialized here (get car, add car, remove car)
// more -> add updates to logger(car ... was checked out at 12:00 pm by ...)
// user should be able to search by specific criteria (i.e mpg > 20) -> return linkedlist of all cars that meet this criteria



public class Fleet {
    private BinarySearchTree<Integer, Vehicle> vehicleByVinBST; // this is to store all the cars on the fleet by their vin number
    private BinarySearchTree<Integer, Double> rateByVinBST; // stores the rate (daily) of each vehicle and their vin number
    private LinkedList<Vehicle> availableVehicleLL; // this is to store all the cars that are available
    Manager manager;
    LogManager logManager;

    public Fleet(Manager manager, LogManager logManager){
        this.vehicleByVinBST = new BinarySearchTree<>();
        this.availableVehicleLL = new LinkedList<Vehicle>();
        this.manager = manager;
        this.logManager = logManager;
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
            int inventory = Integer.valueOf(r[14]); // not implemented
            int odometer = Integer.valueOf(r[15]);
            String color = r[16];
            int numberDoors = Integer.valueOf(r[17]);
            boolean awd = r[18].equals("TRUE");
            String fuelClass = r[19]; // GAS, Electric, Plug-In (hybrid), Hybrid


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
            int maxRange = Integer.valueOf(r[27]);
            int batteryHealth = Integer.valueOf(r[28]);
            double dailyRate = Double.valueOf(r[29]); // ($/day)


            Vehicle vehicle; // instantiate Vehicle object


            // determine the class of the vehicle to create appropriate object (SUV, Sedan...)
            // determine the fuel classes of these vehicles to have the right arguments for creating the Vehicle objects

            if(vehicleClass.equals("SUV")){
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

            vehicleByVinBST.insert(vinNumber, vehicle); // insert the vin and vehicle into the BST
            rateByVinBST.insert(vinNumber, dailyRate);
            availableVehicleLL.append(vehicle);
            /*
            * to do: Add Vehicle objects to LL
            *  else gas, plugIN, hybrid, electric....
            * */
        }
    }

    public boolean carInFleet(int VIN){
        // checks to see if the car is part of the fleet
        return vehicleByVinBST.contains(VIN);
    }
    public Vehicle getVehicleByVin(int vin){
        return vehicleByVinBST.searchByKey(vin);
    }

    public double getRateByVin(int vin){
        return rateByVinBST.searchByKey(vin);
    }

    public void showInventory(){
        // Displays all the cars in the fleet
        // BinarySearchTree<Integer, Vehicle>
        vehicleByVinBST.printTree(vehicleByVinBST.getRoot());
    }

    // or Manager object
    public void addNewVehicle(int managerID, Vehicle vehicle, double dailyRate){
        if(managerID == manager.getStaffID()){ // confirm the manager's id is valid
            vehicleByVinBST.insert(vehicle.getVIN(), vehicle);
            availableVehicleLL.append(vehicle);
            rateByVinBST.insert(vehicle.getVIN(), dailyRate);
            // create message to add to log entry
            String logMessage = String.format("Manager (%d) added %s to the fleet",
                    managerID, String.format("%s %s (vin=%d)", vehicle.getManufacturer(), vehicle.getModel(), vehicle.getVIN()));
            // create log entry and add it to log manager
            logManager.addEntry(new LogEntry(LocalDateTime.now(), logMessage, LogType.CAR_ADDED));
        } else {
            // do something
        }
    }

    public void removeVehicleFromLot(int managerID, int vin) {
        Vehicle vehicle = vehicleByVinBST.searchByKey(vin);
        if(managerID == manager.getStaffID()){
            vehicleByVinBST.delete(vin);
            availableVehicleLL.delete(vehicle);
            rateByVinBST.delete(vin);

            String logMessage = String.format("Manager (%d) removed %s from the fleet",
                    managerID, String.format("%s %s (vin=%d)", vehicle.getManufacturer(), vehicle.getModel(), vehicle.getVIN()));
            logManager.addEntry(new LogEntry(LocalDateTime.now(), logMessage, LogType.CAR_REMOVED));
        } else{
            // do something
        }
    }
}
