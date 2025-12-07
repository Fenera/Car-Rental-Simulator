package edu.augie.finalProgram.taye.Fleet;
import edu.augie.finalProgram.taye.Vehicle.*;
import edu.augie.finalProgram.taye.DataStructures.BinarySearchTree;
import edu.augie.finalProgram.taye.DataStructures.LinkedList;
import edu.augie.finalProgram.taye.Staff.Manager;
import edu.augie.finalProgram.taye.Utilities.LogEntry;
import edu.augie.finalProgram.taye.Utilities.LogManager;
import edu.augie.finalProgram.taye.Utilities.LogType;
import edu.augie.finalProgram.taye.Utilities.ReadCSV;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

public class Fleet {
    // attributes
    private BinarySearchTree<Integer, Vehicle> vehicleByVinBST; // instantiate bst to store VIN and Vehicle object
    private BinarySearchTree<Integer, Double> rateByVinBST; // instantiate bst to store VIN and daily rate ($)
    private LinkedList<Vehicle> availableVehicleLL; // instantiate LL to store available Vehicles
    Manager manager; // instantiate
    LogManager logManager;

    public Fleet(Manager manager, LogManager logManager){
        // initialize attributes
        this.vehicleByVinBST = new BinarySearchTree<>();
        this.rateByVinBST = new BinarySearchTree<>();
        this.availableVehicleLL = new LinkedList<Vehicle>();
        this.manager = manager;
        this.logManager = logManager;
    }

    public void loadCarsFromFile(String file) throws IOException {
        /*
        * Takes in String file path
        * Processes file and extracts columns and assigns values to variables
        * Does checks to determine what kind of Vehicle object to create
        * Creates correct Vehicle type given the data
        * */
        ReadCSV reader = new ReadCSV(file); // instantiate method that will read the file
        List<String[]> vehicles = reader.readAll(); // returns a List of an array of the rows

        /*
        * vehicles.get(0) -> row 1
        * String[] r = vehicles.get(0)
        * r[0] -> first column in row 1
        * */
        String temp; // this will be used for int and double values to clean up the data

        for(int i = 1; i < vehicles.size(); i++){ // iterate through rows (skip the title row)
            String[] r = vehicles.get(i); // get array of the columns in that row

            int year = 2025; // dataset is from 2025 so year attribute for all Vehicles is 2025

            // get all the attributes
            String manufacturer = r[1];
            String model = r[2];
            String engine = r[3];


            temp = r[4].replaceAll("[^0-9]", "").trim(); // remove characters so parseInt will work
            if (temp.isEmpty()) temp = "0"; // if the data value does not apply to that vehicle (i.e. bed size for sedan then set value to 0)
            int horsepower = Integer.parseInt(temp);

            temp = r[5].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";
            int topSpeedMPH = (int) (Integer.parseInt(temp) * 0.621371);

            temp = r[6].replaceAll("[^0-9.]", "").trim();
            if (temp.isEmpty()) temp = "0";
            double zeroSixty = Double.parseDouble(temp);

            temp = r[7].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";
            int priceMSRP = Integer.parseInt(temp);

            // removes quotation marks from String so variable does not store string with quotation marks
            String fuel = r[8].replace("\"", "").trim();

            temp = r[9].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";
            int numberOfSeats = Integer.parseInt(temp);

            String vehicleClass = r[10].replace("\"", "").trim(); // removes quotes to avoid confusion between "car" and car

            temp = r[11].replaceAll("[^0-9.]", "").trim();
            if (temp.isEmpty()) temp = "0";
            double fuelEconomy = Double.parseDouble(temp);

            temp = r[12].replaceAll("[^0-9.]", "").trim();
            if (temp.isEmpty()) temp = "0";
            double gasTankSize = Double.parseDouble(temp);

            temp = r[13].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";
            int vinNumber = Integer.parseInt(temp);

            temp = r[14].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";
            int inventory = Integer.parseInt(temp);

            temp = r[15].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";
            int odometer = Integer.parseInt(temp);

            String color = r[16];
            temp = r[17].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";

            int numberDoors = Integer.parseInt(temp);
            boolean awd = r[18].equals("TRUE");
            String fuelClass = r[19].replaceAll("\"", "").trim(); // Remove quotes

            String conditionReport = "";

            temp = r[20].replaceAll("[^0-9.]", "").trim();
            if (temp.isEmpty()) temp = "0";
            double cargoCapacity = Double.parseDouble(temp);

            temp = r[21].replaceAll("[^0-9.]", "").trim();
            if (temp.isEmpty()) temp = "0";
            double towingCapacity = Double.parseDouble(temp);

            // For Bus Object
            temp = r[22].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";
            double length = Double.parseDouble(temp);

            boolean accessibleRamp = r[24].equals("TRUE");
            temp = r[23].replaceAll("[^0-9.]", "").trim();
            if (temp.isEmpty()) temp = "0";

            // for Truck object
            double bedLength = Double.parseDouble(temp);
            temp = r[25].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";

            // for Electric Powertrain object
            int maxRange = Integer.parseInt(temp);
            temp = r[26].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";
            int batteryHealth = Integer.parseInt(temp);

            // For rateByVin() BST
            temp = r[27].replaceAll("[^0-9.]", "").trim();
            if (temp.isEmpty()) temp = "0";
            double dailyRate = Double.parseDouble(temp);

            Vehicle vehicle; // instantiate Vehicle object

            // Do checks of fuelClass and Body type to create the correct Vehicle object
            // Check if it is an SUV
            if(vehicleClass.equalsIgnoreCase("SUV")){
                // Gas SUV?
                if(fuelClass.equalsIgnoreCase("Gas")) {
                    // Create Gas SUV
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, cargoCapacity, towingCapacity);
                } else if(fuelClass.equalsIgnoreCase("Hybrid")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, cargoCapacity, towingCapacity);
                } else if(fuelClass.equalsIgnoreCase("Plug-In")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, cargoCapacity, towingCapacity);
                } else if(fuelClass.equalsIgnoreCase("Electric")){
                    vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new ElectricMotor(maxRange, batteryHealth), horsepower, cargoCapacity, towingCapacity);
                } else{
                    System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
                    continue;
                }
            } else if(vehicleClass.equalsIgnoreCase("Sedan")){
                // Sedan constructor: (vin, make, model, year, odometer, color, seats, condition, doors, awd, powertrain, hp)
                if(fuelClass.equalsIgnoreCase("Gas")) {
                    vehicle = new Sedan(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower);
                } else if(fuelClass.equalsIgnoreCase("Hybrid")){
                    vehicle = new Sedan(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower);
                } else if(fuelClass.equalsIgnoreCase("Plug-In")){
                    vehicle = new Sedan(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower);
                } else if(fuelClass.equalsIgnoreCase("Electric")){
                    vehicle = new Sedan(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new ElectricMotor(maxRange, batteryHealth), horsepower);
                } else{
                    System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
                    continue;
                }
            } else if(vehicleClass.equalsIgnoreCase("Coupe")){
                // Coupe constructor: same as Sedan
                if(fuelClass.equalsIgnoreCase("Gas")) {
                    vehicle = new Coupe(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower);
                } else if(fuelClass.equalsIgnoreCase("Hybrid")){
                    vehicle = new Coupe(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower);
                } else if(fuelClass.equalsIgnoreCase("Plug-In")){
                    vehicle = new Coupe(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower);
                } else if(fuelClass.equalsIgnoreCase("Electric")){
                    vehicle = new Coupe(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new ElectricMotor(maxRange, batteryHealth), horsepower);
                } else{
                    System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
                    continue;
                }
            } else if(vehicleClass.equalsIgnoreCase("Convertible")){
                // Convertible constructor: same as Sedan
                if(fuelClass.equalsIgnoreCase("Gas")) {
                    vehicle = new Convertible(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower);
                } else if(fuelClass.equalsIgnoreCase("Hybrid")){
                    vehicle = new Convertible(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower);
                } else if(fuelClass.equalsIgnoreCase("Plug-In")){
                    vehicle = new Convertible(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower);
                } else if(fuelClass.equalsIgnoreCase("Electric")){
                    vehicle = new Convertible(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new ElectricMotor(maxRange, batteryHealth), horsepower);
                } else{
                    System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
                    continue;
                }
            } else if(vehicleClass.equalsIgnoreCase("Bus")){
                // Bus constructor: (..., hp, standingCapacity, length, accessibleRamp)
                if(fuelClass.equalsIgnoreCase("Gas")) {
                    vehicle = new Bus(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, length, accessibleRamp);
                } else if(fuelClass.equalsIgnoreCase("Hybrid")){
                    vehicle = new Bus(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, length, accessibleRamp);
                } else if(fuelClass.equalsIgnoreCase("Plug-In")){
                    vehicle = new Bus(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, length, accessibleRamp);
                } else if(fuelClass.equalsIgnoreCase("Electric")){
                    vehicle = new Bus(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new ElectricMotor(maxRange, batteryHealth), horsepower, length, accessibleRamp);
                } else{
                    System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
                    continue;
                }
            } else if(vehicleClass.equalsIgnoreCase("Truck")){
                // Truck constructor: (..., powertrain, bedLength, towingCapacity, hp)
                if(fuelClass.equalsIgnoreCase("Gas")) {
                    vehicle = new Truck(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), bedLength, towingCapacity, horsepower);
                } else if(fuelClass.equalsIgnoreCase("Hybrid")){
                    vehicle = new Truck(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), bedLength, towingCapacity, horsepower);
                } else if(fuelClass.equalsIgnoreCase("Plug-In")){
                    vehicle = new Truck(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new PlugInHybrid(fuelEconomy, gasTankSize, fuel), bedLength, towingCapacity, horsepower);
                } else if(fuelClass.equalsIgnoreCase("Electric")){
                    vehicle = new Truck(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new ElectricMotor(maxRange, batteryHealth), bedLength, towingCapacity, horsepower);
                } else{
                    System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
                    continue;
                }
            } else if(vehicleClass.equalsIgnoreCase("Van")){
                // Van constructor: (..., hp, cargoCapacity)
                if(fuelClass.equalsIgnoreCase("Gas")) {
                    vehicle = new Van(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, cargoCapacity);
                } else if(fuelClass.equalsIgnoreCase("Hybrid")){
                    vehicle = new Van(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, cargoCapacity);
                } else if(fuelClass.equalsIgnoreCase("Plug-In")){
                    vehicle = new Van(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, cargoCapacity);
                } else if(fuelClass.equalsIgnoreCase("Electric")){
                    vehicle = new Van(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new ElectricMotor(maxRange, batteryHealth), horsepower, cargoCapacity);
                } else{
                    System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
                    continue;
                }
            } else if(vehicleClass.equalsIgnoreCase("Hatchback")){
                // Hatchback constructor: (..., powertrain, cargoCapacity, hp)
                if(fuelClass.equalsIgnoreCase("Gas")) {
                    vehicle = new Hatchback(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), cargoCapacity, horsepower);
                } else if(fuelClass.equalsIgnoreCase("Hybrid")){
                    vehicle = new Hatchback(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), cargoCapacity, horsepower);
                } else if(fuelClass.equalsIgnoreCase("Plug-In")){
                    vehicle = new Hatchback(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new PlugInHybrid(fuelEconomy, gasTankSize, fuel), cargoCapacity, horsepower);
                } else if(fuelClass.equalsIgnoreCase("Electric")){
                    vehicle = new Hatchback(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new ElectricMotor(maxRange, batteryHealth), cargoCapacity, horsepower);
                } else{

                    // print statements below are for testing
                    //System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
                    continue;
                }
            } else {
                //System.out.println("Unknown vehicle class: " + vehicleClass + " for VIN " + vinNumber);
                continue;
            }

            //System.out.println("Adding vehicle: VIN = " + vinNumber + " class = " + vehicleClass + " fuel = " + fuelClass);

            // add the vin and vehicle object to bst
            vehicleByVinBST.insert(vinNumber, vehicle);
            rateByVinBST.insert(vinNumber, dailyRate);

            // add the vehicle to available LL to make it accessible for renting
            availableVehicleLL.append(vehicle);
        }
        //System.out.println("done loading " + availableVehicleLL.getLength() + " cars");
    }


    public Vehicle getVehicleByVin(int vin){
        // returns Vehicle object given vin
        if(vehicleByVinBST.contains(vin)) {
            return vehicleByVinBST.searchByKey(vin); // uses searchByKey in BST to get Value from Key
        }
        return null; // null if no object found with that vin
    }

    public double getRateByVin(int vin){
        // returns the rate (double) of the vehicle given its vin
        if(vehicleByVinBST.contains(vin)) {
            return rateByVinBST.searchByKey(vin); // searches in rate bst and returns the daily rate
        }
        return -1.0; // -1 if no vehicle found with that vin
    }

    public void showInventory(){
        // prints the entire inventory
        vehicleByVinBST.printTree(vehicleByVinBST.getRoot()); // use printTree to print the bst
    }

    public void addNewVehicle(int managerID, Vehicle vehicle, double dailyRate){
        // method used by manager
        // adds new vehicle to lot

        // check if the manager's id is valid
        if(managerID == manager.getStaffID()){
            // add the vehicle to the bst's and the linked list
            vehicleByVinBST.insert(vehicle.getVIN(), vehicle);
            availableVehicleLL.append(vehicle);
            rateByVinBST.insert(vehicle.getVIN(), dailyRate);
            // create an update log message
            String logMessage = String.format("Manager (%d) added %s to the edu.augie.finalProgram.taye.Fleet",
                    managerID, String.format("%s %s (vin=%d)", vehicle.getManufacturer(), vehicle.getModel(), vehicle.getVIN()));

            // add message to the logManager with the current time and the action
            logManager.addEntry(new LogEntry(LocalDateTime.now(), logMessage, LogType.CAR_ADDED));
        }
    }

    public void removeVehicleFromLot(int managerID, int vin) {
        // removes vehicle from lot given vin

        Vehicle vehicle = vehicleByVinBST.searchByKey(vin); // find vehicle
        if(managerID == manager.getStaffID()){ // check if managerID is valid

            // delete from BST's and LL
            vehicleByVinBST.delete(vin);
            availableVehicleLL.delete(vehicle);
            rateByVinBST.delete(vin);

            // create log message
            String logMessage = String.format("Manager (%d) removed %s from the edu.augie.finalProgram.taye.Fleet",
                    managerID, String.format("%s %s (vin=%d)", vehicle.getManufacturer(), vehicle.getModel(), vehicle.getVIN()));

            // add it to log manager
            logManager.addEntry(new LogEntry(LocalDateTime.now(), logMessage, LogType.CAR_REMOVED));
        }
    }

    public int filterByBodyType(String bodyType){
        // prints cars that have the body type given

        int count = 0; // counter

        // iterate through the available vehicle LL
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();){
            Vehicle vehicle = it.next(); // get the vehicle object
            if(vehicle.getBodyType().equalsIgnoreCase(bodyType)){ // check if body type matches
                count ++;
                System.out.println(count + ") \n" + vehicle); // print counter and body type
            }
        }
        return count; // return count
    }

    public void showAvailableCars(){
        // prints all the cars that are available (for rent)
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();){
            Vehicle vehicle = it.next(); // get vehicle
            System.out.println(vehicle);
        }
    }

    public int filterByPriceRange(double lowerLimit, double upperLimit){
        // prints all vehicles with prices between the lower limit and upper limit

        int count = 0;
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();){
            Vehicle vehicle = it.next(); // get vehicle
            double dailyRate = rateByVinBST.searchByKey(vehicle.getVIN()); // get the vehicles rate
            if(dailyRate >= lowerLimit && dailyRate <= upperLimit){ // check if it is between the range
                count++;
                System.out.println("\n" + count + ") Daily Rate  = $" + dailyRate + " \n" + vehicle);
            }
        }
        return count; // return the counter
    }

    public int filterByHorsePower(double lowerLimit, double upperLimit){
        // same logic as method above but for horsepower
        int count = 0;
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();){
            Vehicle vehicle = it.next();
            int horsepower = vehicle.getHorsePower();
            if(horsepower >= lowerLimit && horsepower <= upperLimit){
                count++;
                System.out.println("\n" + count + ") Horsepower = " + horsepower + "hp \n" + vehicle);
            }
        }
        return count;
    }

    public int filterByMPG(double lowerLimit, double upperLimit){
        // same logic as method above but for mpg
        int count = 0;
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();) {
            Vehicle vehicle = it.next();
            Powertrain pt = vehicle.getPowertrain();
            double mpg = -1;

            if (pt instanceof GasMotor) {
                mpg = ((GasMotor) pt).getFuelEfficiency();
            } else if (pt instanceof HybridMotor) {
                mpg = ((HybridMotor) pt).getFuelEfficiency();
            } else if (pt instanceof PlugInHybrid) {
                mpg = ((PlugInHybrid) pt).getFuelEfficiency();
            } else {
                continue;
            }

            if (mpg >= lowerLimit && mpg <= upperLimit) {
                count++;
                System.out.println("\n" + count + ") Fuel Economy = " + mpg + " mpg \n" + vehicle);
            }
        }
        return count;
    }

    public int filterByFuelType(String gasType){
        // filters vehicles by their fuel type

        int count = 0;
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();) {
            Vehicle vehicle = it.next(); // get vehicle
            Powertrain pt = vehicle.getPowertrain(); // get the vehicles power train and store it
            String gas = null; // initialize gas

            // check if the vehicle's powertrain is an instance of GasMotor
            if (pt instanceof GasMotor) {
                gas = ((GasMotor) pt).getFuelType(); // set gas to the gas motors fuel type

            }
            // check if the vehicle's pt is in an instance of Hybrid motor
            else if (pt instanceof HybridMotor) {
                gas = ((HybridMotor) pt).getFuelType(); // set gas to hybrid motors fuel type (hybrid)
            }
            //.. same logic for rest
            else if (pt instanceof PlugInHybrid) {
                gas = ((PlugInHybrid) pt).getFuelType();
            }

            else if(pt instanceof ElectricMotor){
                gas = "Electric";
            }

            else {
                continue; // if the power train is not an instance of the above, skip the current iteration
            }

            // see if the gas from the power train matches the one the user gave
            if(gas != null && gas.equalsIgnoreCase(gasType)){
                count++; // increment counter
                System.out.println("\n" + count + ") Fuel Type = " + gas + " " + vehicle); // print the vehicle
            }
        }
        return count;
    }

    public double getRate(int vin){
        // returns the rate (double) of the vehicle given its vin #
        return rateByVinBST.searchByKey(vin);
    }
}