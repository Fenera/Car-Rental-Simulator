package edu.augie.finalProgram.taye.Vehicles;

public class Truck extends Vehicle {

    // arbitrary values
    private double bedLength;
    private double maxPayLoad;

    public Truck(int VIN, String manufacturer, String model, int year, int odometer, String color,
                 int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
                 Powertrain powertrain, double bedLength, double maxPayLoad, int horsePower){

        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower);

        this.bedLength = bedLength;
        this.maxPayLoad = maxPayLoad;
    }


    public double getBedLength() {
        return bedLength;
    }

    public void setBedLength(double bedLength) {
        this.bedLength = bedLength;
    }

    public double getMaxPayLoad() {
        return maxPayLoad;
    }

    public void setMaxPayLoad(double maxPayLoad) {
        this.maxPayLoad = maxPayLoad;
    }

    public boolean isOverLoaded(double weight){
        return weight > maxPayLoad;
    }
}
