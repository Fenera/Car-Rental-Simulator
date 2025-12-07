package edu.augie.finalProgram.taye.Vehicle;

public class GasMotor implements Powertrain{

    // attributes
    private String fuelType; // gas/diesel
    private double fuelEfficiency; // mpg
    private String motor; // i.e 1.2L I-4
    private double tankSize; // i.e 15.1 L

    // constructor
    public GasMotor(String fuelType, double fuelEfficiency,
                    String motor, double tankSize){
        // initialize attributes
        this.fuelType = fuelType;
        this.fuelEfficiency = fuelEfficiency;
        this.motor = motor;
        this.tankSize = tankSize;
    }

    // getter and setter methods
    public double getTankSize() {
        return tankSize;
    }

    public void setTankSize(double tankSize) {
        this.tankSize = tankSize;
    }

    // implement Powertrain methods
    @Override
    public double getRange(){
        double range = fuelEfficiency * tankSize;
        return Math.round(range);
    }
    @Override
    public String getMotor(){
        return motor.trim().replaceAll("\"", "");
    }

    // getter and setter methods
    public String getFuelType() {
        return fuelType.trim().replaceAll("\"", "");
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setFuelEfficiency(double fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

}
