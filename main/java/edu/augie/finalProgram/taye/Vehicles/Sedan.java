package edu.augie.finalProgram.taye.Vehicles;

public class Sedan extends Vehicle {

    private double trunkVolume;

    public Sedan(int VIN, String manufacturer, String model, int year, int odometer, String color,
                 int seatingCapacity, String conditionReport, int numberOfDoors,
                 boolean allWheelDrive, Powertrain powertrain, int horsePower){

        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors,
                allWheelDrive, powertrain, horsePower, true);

        this.trunkVolume = trunkVolume;
    }

    // invokes the no-parameter constructor in GasVehicle
    public Sedan(){
        super();
    }

    public double getTrunkVolume() {
        return trunkVolume;
    }

    public void setTrunkVolume(double trunkVolume) {
        this.trunkVolume = trunkVolume;
    }
}

