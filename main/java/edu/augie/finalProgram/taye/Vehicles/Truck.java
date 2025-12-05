package edu.augie.finalProgram.taye.Vehicles;

public class Truck extends Vehicle {

    // arbitrary values
    private double bedLength;
    private double towingCapacity;

    public Truck(int VIN, String manufacturer, String model, int year, int odometer, String color,
                 int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
                 Powertrain powertrain, double bedLength, double towingCapacity, int horsePower){

        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);

        this.bedLength = bedLength;
        this.towingCapacity = towingCapacity;
    }


    public double getBedLength() {
        return bedLength;
    }

    public void setBedLength(double bedLength) {
        this.bedLength = bedLength;
    }

    public double getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(double towingCapacity) {
        this.towingCapacity = towingCapacity;
    }

    public boolean isOverLoaded(double weight){
        return weight > towingCapacity;
    }
}
