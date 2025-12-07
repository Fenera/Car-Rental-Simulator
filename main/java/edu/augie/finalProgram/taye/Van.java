package edu.augie.finalProgram.taye;

public class Van extends Vehicle{

    private double cargoCapacity;
    private double towingCapacity;

    public Van(int VIN, String manufacturer, String model, int year, int odometer, String color,
                 int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
                 Powertrain powertrain, int horsePower, double cargoCapacity) {

        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);
        this.cargoCapacity = cargoCapacity;
        this.towingCapacity = towingCapacity;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public double getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(double towingCapacity) {
        this.towingCapacity = towingCapacity;
    }

    // implementation of abstract method
    public String getBodyType(){
        return "Van";
    }
}
