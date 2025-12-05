package edu.augie.finalProgram.taye.Vehicles;

public class Hatchback extends Vehicle {

    private double cargoCapacity;

    public Hatchback(int VIN, String manufacturer, String model, int year, int odometer, String color,
               int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
               Powertrain powertrain, double cargoCapacity, int horsePower) {

        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);

        this.cargoCapacity = cargoCapacity;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }
}
