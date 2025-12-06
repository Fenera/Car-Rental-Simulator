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

public class Fleet {
    private BinarySearchTree<Integer, Vehicle> vehicleByVinBST;
    private BinarySearchTree<Integer, Double> rateByVinBST;
    private LinkedList<Vehicle> availableVehicleLL;
    Manager manager;
    LogManager logManager;

    public Fleet(Manager manager, LogManager logManager){
        this.vehicleByVinBST = new BinarySearchTree<>();
        this.rateByVinBST = new BinarySearchTree<>();
        this.availableVehicleLL = new LinkedList<Vehicle>();
        this.manager = manager;
        this.logManager = logManager;
    }

    public void loadCarsFromFile(String file) throws IOException {
        ReadCSV reader = new ReadCSV(file);
        List<String[]> vehicles = reader.readAll();

        String temp;
        for(int i = 1; i < vehicles.size(); i++){
            String[] r = vehicles.get(i);

            int year = 2025;
            String manufacturer = r[1];
            String model = r[2];
            String engine = r[3];
            temp = r[4].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";
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
            String fuel = r[8];
            temp = r[9].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";
            int numberOfSeats = Integer.parseInt(temp);
            String vehicleClass = r[10].replace("\"", "").trim(); // removes quotes to avoid confusion between "car" and car

//            if(vehicleClass.isEmpty() || !(vehicleClass.equalsIgnoreCase("SUV") ||
//                    vehicleClass.equalsIgnoreCase("Sedan") ||
//                    vehicleClass.equalsIgnoreCase("Coupe") ||
//                    vehicleClass.equalsIgnoreCase("Convertible") ||
//                    vehicleClass.equalsIgnoreCase("Truck") ||
//                    vehicleClass.equalsIgnoreCase("Van") ||
//                    vehicleClass.equalsIgnoreCase("Hatchback") ||
//                    vehicleClass.equalsIgnoreCase("Bus"))){
//                vehicleClass = "SUV";
//            }

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
            temp = r[22].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";
            int standingCapacity = Integer.parseInt(temp);
            temp = r[23].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";
            int length = Integer.parseInt(temp);
            boolean accessibleRamp = r[24].equals("TRUE");
            temp = r[25].replaceAll("[^0-9.]", "").trim();
            if (temp.isEmpty()) temp = "0";
            double trunkVolume = Double.parseDouble(temp);
            temp = r[26].replaceAll("[^0-9.]", "").trim();
            if (temp.isEmpty()) temp = "0";
            double bedLength = Double.parseDouble(temp);
            temp = r[27].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";
            int maxRange = Integer.parseInt(temp);
            temp = r[28].replaceAll("[^0-9]", "").trim();
            if (temp.isEmpty()) temp = "0";
            int batteryHealth = Integer.parseInt(temp);
            temp = r[29].replaceAll("[^0-9.]", "").trim();
            if (temp.isEmpty()) temp = "0";
            double dailyRate = Double.parseDouble(temp);

            Vehicle vehicle;

            if(vehicleClass.equalsIgnoreCase("SUV")){
                if(fuelClass.equalsIgnoreCase("Gas")) {
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
                            new GasMotor(fuel, fuelEconomy, engine, gasTankSize), horsepower, standingCapacity, length, accessibleRamp);
                } else if(fuelClass.equalsIgnoreCase("Hybrid")){
                    vehicle = new Bus(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new HybridMotor(fuelEconomy, gasTankSize, engine, fuel), horsepower, standingCapacity, length, accessibleRamp);
                } else if(fuelClass.equalsIgnoreCase("Plug-In")){
                    vehicle = new Bus(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new PlugInHybrid(fuelEconomy, gasTankSize, fuel), horsepower, standingCapacity, length, accessibleRamp);
                } else if(fuelClass.equalsIgnoreCase("Electric")){
                    vehicle = new Bus(vinNumber, manufacturer, model, year, odometer, color,
                            numberOfSeats, conditionReport, numberDoors, awd,
                            new ElectricMotor(maxRange, batteryHealth), horsepower, standingCapacity, length, accessibleRamp);
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
                    System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
                    continue;
                }
            } else {
                System.out.println("Unknown vehicle class: " + vehicleClass + " for VIN " + vinNumber);
                continue;
            }

            System.out.println("Adding vehicle: VIN=" + vinNumber + " class=" + vehicleClass + " fuel=" + fuelClass);

            vehicleByVinBST.insert(vinNumber, vehicle);
            rateByVinBST.insert(vinNumber, dailyRate);
            availableVehicleLL.append(vehicle);
        }
        System.out.println("Finished loading " + availableVehicleLL.getLength() + " vehicles from file.");
    }

    public boolean carInFleet(int VIN){
        return vehicleByVinBST.contains(VIN);
    }

    public Vehicle getVehicleByVin(int vin){
        return vehicleByVinBST.searchByKey(vin);
    }

    public double getRateByVin(int vin){
        return rateByVinBST.searchByKey(vin);
    }

    public void showInventory(){
        vehicleByVinBST.printTree(vehicleByVinBST.getRoot());
    }

    public void addNewVehicle(int managerID, Vehicle vehicle, double dailyRate){
        if(managerID == manager.getStaffID()){
            vehicleByVinBST.insert(vehicle.getVIN(), vehicle);
            availableVehicleLL.append(vehicle);
            rateByVinBST.insert(vehicle.getVIN(), dailyRate);
            String logMessage = String.format("Manager (%d) added %s to the fleet",
                    managerID, String.format("%s %s (vin=%d)", vehicle.getManufacturer(), vehicle.getModel(), vehicle.getVIN()));
            logManager.addEntry(new LogEntry(LocalDateTime.now(), logMessage, LogType.CAR_ADDED));
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
        }
    }

    public void filterByBodyType(String bodyType){
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();){
            Vehicle vehicle = it.next();
            if(vehicle.getBodyType().equalsIgnoreCase(bodyType)){
                System.out.println(vehicle);
            }
        }
    }

    public void showAvailableCars(){
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();){
            Vehicle vehicle = it.next();
            System.out.println(vehicle);
        }
    }

    public int filterByPriceRange(double lowerLimit, double upperLimit){
        int count = 0;
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();){
            Vehicle vehicle = it.next();
            double dailyRate = rateByVinBST.searchByKey(vehicle.getVIN());
            if(dailyRate >= lowerLimit && dailyRate <= upperLimit){
                count++;
                System.out.println(vehicle);
            }
        }
        return count;
    }

    public int filterByHorsePower(double lowerLimit, double upperLimit){
        int count = 0;
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();){
            Vehicle vehicle = it.next();
            int horsepower = vehicle.getHorsePower();
            if(horsepower >= lowerLimit && horsepower <= upperLimit){
                count++;
                System.out.println(vehicle);
            }
        }
        return count;
    }

    public int filterByMPG(double lowerLimit, double upperLimit){
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
                System.out.println(vehicle);
            }
        }
        return count;
    }

    public int filterByFuelType(String gasType){
        int count = 0;
        for(Iterator<Vehicle> it = availableVehicleLL.items(); it.hasNext();) {
            Vehicle vehicle = it.next();
            Powertrain pt = vehicle.getPowertrain();
            String gas = null;

            if (pt instanceof GasMotor) {
                gas = ((GasMotor) pt).getFuelType();
            } else if (pt instanceof HybridMotor) {
                gas = ((HybridMotor) pt).getFuelType();
            } else if (pt instanceof PlugInHybrid) {
                gas = ((PlugInHybrid) pt).getFuelType();
            } else if(pt instanceof ElectricMotor){
                gas = "Electric";
            } else {
                continue;
            }

            if(gas != null && gas.equalsIgnoreCase(gasType)){
                count++;
                System.out.println(vehicle);
            }
        }
        return count;
    }
}