package edu.augie.finalProgram.taye;

public class Bus extends Vehicle{

    private double cargoCapacity;
    private int standingCapacity;
    private double length;
    private boolean accessibleRamp;

    public Bus(int VIN, String manufacturer, String model, int year, int odometer, String color,
               int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
               Powertrain powertrain, int horsePower, int standingCapacity, double length, boolean accessRamp) {

        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);
        this.cargoCapacity = cargoCapacity;
        this.standingCapacity = standingCapacity;
        this.length = length;
        this.accessibleRamp = accessRamp;
    }
    public Bus(int VIN, String manufacturer, String model, int year, int odometer, String color,
               int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
               Powertrain powertrain, int horsePower){
        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);
    }

    public int getTotalCapacity(){
        return super.getSeatingCapacity() + standingCapacity;
    }
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public int getStandingCapacity() {
        return standingCapacity;
    }

    public void setStandingCapacity(int standingCapacity) {
        this.standingCapacity = standingCapacity;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public boolean isAccessibleRamp() {
        return accessibleRamp;
    }

    public void setAccessibleRamp(boolean accessibleRamp) {
        this.accessibleRamp = accessibleRamp;
    }

    // implementation of abstract method
    public String getBodyType(){
        return "Bus";
    }
}
