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
import java.util.Iterator;
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

    public void loadCarsFromFile(String file) throws IOException {
        ReadCSV reader = new ReadCSV(file); // create ReadCSV object
        List<String[]> vehicles = reader.readAll(); // readAll creates a List of arrays(rows)

        for(int i = 1; i < vehicles.size(); i++) { // iterate through the rows (skip the first row with i=1 -> i=0 is header)
            String[] r = vehicles.get(i); // get row array

            for (int j = 1; j < r.length; j++) {
                // general vehicle attributes
                // get data from file and store in variables

                /*
                 * raw variable is used to make converting String -> int (avoids NumberFormatException)
                 * */

                String raw = r[j].trim(); // this raw is for ints in string form (i.e. 364 hp) or doubles in string form

                // avoid NA and blanks
                if(!raw.equalsIgnoreCase("NA") || raw.isBlank()) {

                    // all the indexes (columns) that contain int values
                    int intValue = 0;
                    double doubleValue = 0.0;
                    if (j == 4 || j == 5 || j == 7 || j == 9 || j == 13 || j == 14
                            || j == 15 || j == 17 || j == 22 || j == 23 || j == 27 || j == 28) {
                        raw = raw.replaceAll("[^0-9]", "");
                        intValue = Integer.parseInt(raw);
                    }
                    // all indexes (columns) that contain double values
                    if (j == 6 || j == 11 || j == 12 || j == 20 || j == 21 || j == 25 || j == 26 || j == 29) {
                        raw = raw.replaceAll("[^0-9]", "");
                        doubleValue = Double.parseDouble(raw);
                    }

                    int year = 2025;
                    String manufacturer = r[1].trim(); // r[0] is row number so start at r[1]
                    String model = r[2].trim();
                    String engine = r[3].trim();
                    int horsepower = intValue;
                    int topSpeedMPH = (int) (intValue * 0.621371); // convert kmh to mph and convert to int
                    double zeroSixty = doubleValue;
                    int priceMSRP = intValue;
                    String fuel = r[8].trim();
                    int numberOfSeats = intValue;
                    String vehicleClass = r[10].trim(); // SUV, Sedan..
                    double fuelEconomy = doubleValue;
                    double gasTankSize = doubleValue;
                    int vinNumber = intValue;
                    int inventory = intValue; // not implemented
                    int odometer = intValue;
                    String color = r[16].trim();
                    int numberDoors = intValue;
                    boolean awd = r[18].trim().equals("TRUE");
                    String fuelClass = r[19].trim(); // GAS, Electric, Plug-In (hybrid), Hybrid


                    // Update in the future (if needed)
                    String conditionReport = "";

                    // vehicle class-specific attributes (i.e. bed length for trucks)

                    double cargoCapacity = doubleValue;
                    double towingCapacity = doubleValue;
                    int standingCapacity = intValue;
                    int length = intValue;
                    boolean accessibleRamp = r[24].trim().equals("TRUE");
                    double trunkVolume = doubleValue;
                    double bedLength = doubleValue;
                    int maxRange = intValue;
                    int batteryHealth = intValue;
                    double dailyRate = Double.valueOf(r[29]); // ($/day)


                    Vehicle vehicle; // instantiate Vehicle object


                    // determine the class of the vehicle to create appropriate object (SUV, Sedan...)
                    // determine the fuel classes of these vehicles to have the right arguments for creating the Vehicle objects

                    if (vehicleClass.equals("SUV")) {
                        if (Objects.equals(fuelClass, "Gas")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Hybrid")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Plug-In")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Electric")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new ElectricMotor(maxRange, batteryHealth), horsepower, cargoCapacity, towingCapacity);
                        } else {
                            continue; // skip the current iteration if fuelClass is invalid
                        }
                    } else if (vehicleClass.equals("Sedan")) {
                        if (Objects.equals(fuelClass, "Gas")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Hybrid")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Plug-In")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Electric")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new ElectricMotor(maxRange, batteryHealth), horsepower, cargoCapacity, towingCapacity);
                        } else {
                            continue; // skip the current iteration if fuelClass is invalid
                        }
                    } else if (vehicleClass.equals("Coupe")) {
                        if (Objects.equals(fuelClass, "Gas")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Hybrid")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Plug-In")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Electric")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new ElectricMotor(maxRange, batteryHealth), horsepower, cargoCapacity, towingCapacity);
                        } else {
                            continue; // skip the current iteration if fuelClass is invalid
                        }
                    } else if (vehicleClass.equals("Bus")) {
                        if (Objects.equals(fuelClass, "Gas")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Hybrid")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Plug-In")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Electric")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new ElectricMotor(maxRange, batteryHealth), horsepower, cargoCapacity, towingCapacity);
                        } else {
                            continue; // skip the current iteration if fuelClass is invalid
                        }
                    } else if (vehicleClass.equals("Truck")) {
                        if (Objects.equals(fuelClass, "Gas")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Hybrid")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Plug-In")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Electric")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new ElectricMotor(maxRange, batteryHealth), horsepower, cargoCapacity, towingCapacity);
                        } else {
                            continue; // skip the current iteration if fuelClass is invalid
                        }
                    } else if (vehicleClass.equals("Van")) {
                        if (Objects.equals(fuelClass, "Gas")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Hybrid")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Plug-In")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, cargoCapacity, towingCapacity);
                        } else if (Objects.equals(fuelClass, "Electric")) {
                            vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                                    numberOfSeats, conditionReport, numberDoors, awd,
                                    new ElectricMotor(maxRange, batteryHealth), horsepower, cargoCapacity, towingCapacity);
                        } else {
                            continue; // skip the current iteration if fuelClass is invalid
                        }
                    } else {
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

    public void filterByBodyType(String bodyType){
        // prints out all cars that have a certain body type

        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();){ // iterate through vehicles
            Vehicle vehicle = it.next();
            if(vehicle.getBodyType().equalsIgnoreCase(bodyType)){ // check if body type input match with method output
                System.out.println(vehicle); // print the vehicle
            }

        }
    }

    public void showAvailableCars(){
        // shows all cars that are available
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();){ // iterate through vehicles
            Vehicle vehicle = it.next();
            System.out.println(vehicle);
        }
    }

    public int filterByPriceRange(double lowerLimit, double upperLimit){
        // Filter cars with daily rates between the two limits

        int count = 0; // counts the number of cars that meet this criteria
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();){
            Vehicle vehicle = it.next(); // vehicle in available vehicles linked list
            double dailyRate = rateByVinBST.searchByKey(vehicle.getVIN()); // daily rate of vehicle
            if(dailyRate >= lowerLimit && dailyRate <= upperLimit){
                count ++;
                System.out.println(vehicle);
            }
        }

        return count;
    }

    public int filterByHorsePower(double lowerLimit, double upperLimit){
        // Filter cars with horsepower between the two limits

        int count = 0; // counts the number of cars that meet this criteria
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();){
            Vehicle vehicle = it.next(); // vehicle in available vehicles linked list
            int horsepower = vehicle.getHorsePower();

            if(horsepower >= lowerLimit && horsepower <= upperLimit){
                count ++;
                System.out.println(vehicle);
            }
        }

        return count;
    }

    public int filterByMPG(double lowerLimit, double upperLimit){
        // Filter cars with horsepower between the two limits

        int count = 0; // counts the number of cars that meet this criteria
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();) {
            Vehicle vehicle = it.next(); // vehicle in available vehicles linked list
            Powertrain pt = vehicle.getPowertrain(); // get the powertrain of the vehicle
            double mpg;

            // see if the powertrains are instances of these motors that contain fuel economy (mpg)
            // get the mpg
            if (pt instanceof GasMotor) {
                GasMotor motor = (GasMotor) pt;
                mpg = motor.getFuelEfficiency();
                count++;
            } else if (pt instanceof HybridMotor) {
                HybridMotor motor = (HybridMotor) pt;
                mpg = motor.getFuelEfficiency();
                count++;
            } else if (pt instanceof PlugInHybrid) {
                PlugInHybrid motor = (PlugInHybrid) pt;
                mpg = motor.getFuelEfficiency();
                count++;
            } else {
                continue;
            }
            if (mpg >= lowerLimit && mpg <= upperLimit) {
                System.out.println(vehicle);
            }
        }
        return count;
    }

    public int filterByFuelType(String gasType){
        // filter the cars by their gas type (petrol, diesel, electric, hybrid...)
        int count = 0; // counts the number of cars that meet this criteria
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();) {
            Vehicle vehicle = it.next(); // vehicle in available vehicles linked list
            Powertrain pt = vehicle.getPowertrain(); // get the powertrain of the vehicle
            String gas;

            // see if the powertrains are instances of these motors that contain fuel economy (mpg)
            // get the mpg
            if (pt instanceof GasMotor) {
                GasMotor motor = (GasMotor) pt;
                gas = motor.getFuelType();
                count++;
            } else if (pt instanceof HybridMotor) {
                HybridMotor motor = (HybridMotor) pt;
                gas = motor.getFuelType();
                count++;
            } else if (pt instanceof PlugInHybrid) {
                PlugInHybrid motor = (PlugInHybrid) pt;
                gas = motor.getFuelType();
                count++;
            }  else if(pt instanceof ElectricMotor){
                ElectricMotor motor = (ElectricMotor) pt;
                gas = motor.getFuelType();
            }
            else {
                continue;
            }
            if(gas.equalsIgnoreCase(gasType)){
                System.out.println(vehicle);
            }
        }
        return count;

    }
}
