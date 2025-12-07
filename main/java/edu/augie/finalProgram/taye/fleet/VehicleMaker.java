package edu.augie.finalProgram.taye.fleet;

import edu.augie.finalProgram.taye.Vehicle.*;

public class VehicleMaker {

    // same arguments as Vehicle object parameters
    public Vehicle make(String bodyType, String fuelClass, int vinNumber, String manufacturer,
                        String model, int year, int odometer, String color,
                        int numberDoors, int numberOfSeats, String conditionReport, boolean awd, String fuelType,
                        double fuelEconomy, String engine, double gasTankSize, int horsepower) {

        /*
        * Same code as loadCarsFromFile() method in Fleet.java but a few less variables
        * Creates a basic Vehicle class (No extra attributes you may find in Vehicle subclasses like bed length)
        * Primary reason for making this is to avoid complication when making vehicle in managerMenu() method in edu.augie.finalProgram.taye.Menu.java
        * Also it is more compact to use a method
        * */

        Vehicle vehicle = null;

        if (bodyType.equalsIgnoreCase("SUV")) {
            if (fuelClass.equalsIgnoreCase("Gas")) {
                vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new GasMotor(fuelType, fuelEconomy, engine, gasTankSize), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Hybrid")) {
                vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new HybridMotor(fuelEconomy, gasTankSize, engine, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Plug-In")) {
                vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new PlugInHybrid(fuelEconomy, gasTankSize, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Electric")) {
                vehicle = new SUV(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new ElectricMotor(300, 100), horsepower);
            } else {
                System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
            }
        } else if (bodyType.equalsIgnoreCase("Sedan")) {
            // Sedan constructor: (vin, make, model, year, odometer, color, seats, condition, doors, awd, powertrain, hp)
            if (fuelClass.equalsIgnoreCase("Gas")) {
                vehicle = new Sedan(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new GasMotor(fuelType, fuelEconomy, engine, gasTankSize), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Hybrid")) {
                vehicle = new Sedan(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new HybridMotor(fuelEconomy, gasTankSize, engine, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Plug-In")) {
                vehicle = new Sedan(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new PlugInHybrid(fuelEconomy, gasTankSize, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Electric")) {
                vehicle = new Sedan(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new ElectricMotor(300, 100), horsepower);
            } else {
                System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
            }
        } else if (bodyType.equalsIgnoreCase("Coupe")) {
            // Coupe constructor: same as Sedan
            if (fuelClass.equalsIgnoreCase("Gas")) {
                vehicle = new Coupe(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new GasMotor(fuelType, fuelEconomy, engine, gasTankSize), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Hybrid")) {
                vehicle = new Coupe(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new HybridMotor(fuelEconomy, gasTankSize, engine, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Plug-In")) {
                vehicle = new Coupe(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new PlugInHybrid(fuelEconomy, gasTankSize, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Electric")) {
                vehicle = new Coupe(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new ElectricMotor(300, 100), horsepower);
            } else {
                System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
            }
        } else if (bodyType.equalsIgnoreCase("Convertible")) {
            // Convertible constructor: same as Sedan
            if (fuelClass.equalsIgnoreCase("Gas")) {
                vehicle = new Convertible(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new GasMotor(fuelType, fuelEconomy, engine, gasTankSize), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Hybrid")) {
                vehicle = new Convertible(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new HybridMotor(fuelEconomy, gasTankSize, engine, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Plug-In")) {
                vehicle = new Convertible(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new PlugInHybrid(fuelEconomy, gasTankSize, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Electric")) {
                vehicle = new Convertible(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new ElectricMotor(300, 100), horsepower);
            } else {
                System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
            }
        } else if (bodyType.equalsIgnoreCase("Bus")) {
            // Bus constructor: (..., hp, standingCapacity, length, accessibleRamp)
            if (fuelClass.equalsIgnoreCase("Gas")) {
                vehicle = new Bus(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new GasMotor(fuelType, fuelEconomy, engine, gasTankSize), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Hybrid")) {
                vehicle = new Bus(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new HybridMotor(fuelEconomy, gasTankSize, engine, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Plug-In")) {
                vehicle = new Bus(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new PlugInHybrid(fuelEconomy, gasTankSize, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Electric")) {
                vehicle = new Bus(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new ElectricMotor(300, 100), horsepower);
            } else {
                System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
            }
        } else if (bodyType.equalsIgnoreCase("Truck")) {
            // Truck constructor: (..., powertrain, bedLength, towingCapacity, hp)
            if (fuelClass.equalsIgnoreCase("Gas")) {
                vehicle = new Truck(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new GasMotor(fuelType, fuelEconomy, engine, gasTankSize), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Hybrid")) {
                vehicle = new Truck(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new HybridMotor(fuelEconomy, gasTankSize, engine, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Plug-In")) {
                vehicle = new Truck(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new PlugInHybrid(fuelEconomy, gasTankSize, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Electric")) {
                vehicle = new Truck(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new ElectricMotor(300, 100), horsepower);
            } else {
                System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
            }
        } else if (bodyType.equalsIgnoreCase("Van")) {
            // Van constructor: (..., hp, cargoCapacity)
            if (fuelClass.equalsIgnoreCase("Gas")) {
                vehicle = new Van(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new GasMotor(fuelType, fuelEconomy, engine, gasTankSize), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Hybrid")) {
                vehicle = new Van(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new HybridMotor(fuelEconomy, gasTankSize, engine, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Plug-In")) {
                vehicle = new Van(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new PlugInHybrid(fuelEconomy, gasTankSize, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Electric")) {
                vehicle = new Van(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new ElectricMotor(300, 100), horsepower);
            } else {
                System.out.println("Skipping vehicle - invalid fuel class: " + fuelClass);
            }
        } else if (bodyType.equalsIgnoreCase("Hatchback")) {
            // Hatchback constructor: (..., powertrain, cargoCapacity, hp)
            if (fuelClass.equalsIgnoreCase("Gas")) {
                vehicle = new Hatchback(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new GasMotor(fuelType, fuelEconomy, engine, gasTankSize), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Hybrid")) {
                vehicle = new Hatchback(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new HybridMotor(fuelEconomy, gasTankSize, engine, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Plug-In")) {
                vehicle = new Hatchback(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new PlugInHybrid(fuelEconomy, gasTankSize, fuelType), horsepower);
            } else if (fuelClass.equalsIgnoreCase("Electric")) {
                vehicle = new Hatchback(vinNumber, manufacturer, model, year, odometer, color,
                        numberOfSeats, conditionReport, numberDoors, awd,
                        new ElectricMotor(300, 100), horsepower);
            } else {

            }
        }

        return vehicle; // return the vehicle made
    }
}
