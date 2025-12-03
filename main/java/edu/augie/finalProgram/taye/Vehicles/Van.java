package edu.augie.finalProgram.taye.Vehicles;

public class Van extends Vehicle{

    private double cargoSpace;
    private double towingCapacity;

    public Van(int VIN, String manufacturer, String model, int year, int odometer, String color,
                 int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
                 Powertrain powertrain, int horsePower, double bedLength, double maxPayLoad, double cargoSpace, double towingCapacity) {

        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower);
        this.cargoSpace = cargoSpace;
        this.towingCapacity = towingCapacity;
    }

    public double getCargoSpace() {
        return cargoSpace;
    }

    public void setCargoSpace(double cargoSpace) {
        this.cargoSpace = cargoSpace;
    }

    public double getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(double towingCapacity) {
        this.towingCapacity = towingCapacity;
    }
}
