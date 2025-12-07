package edu.augie.finalProgram.taye.Vehicle;


public class HybridMotor implements Powertrain{
    // attributes
    private String fuelType;
    private double fuelEfficiency;
    private static double electricRange = 1.5; // a hybrid battery typically adds 1-2 miles per gallon
    private double tankSize;
    private String motor;

    // constructor
    public HybridMotor(double fuelEfficiency, double tankSize, String motor, String fuelType){
        this.fuelEfficiency = fuelEfficiency;
        this.tankSize = tankSize;
        this.motor = motor;
        this.fuelType = fuelType;
    }

    // getter and setter methods
    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    // implementation of Powertrain methods
    @Override
    public double getRange(){
        double range = (fuelEfficiency * tankSize) + (tankSize * electricRange);
        return Math.round(range);
    }
    @Override
    public String getMotor(){
        return motor.trim().replaceAll("\"", "");
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }

    public void setFuelEfficiency(double fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    public static double getElectricRange() {
        return electricRange;
    }

    public static void setElectricRange(double electricRange) {
        HybridMotor.electricRange = electricRange;
    }

    public double getTankSize() {
        return tankSize;
    }

    public void setTankSize(double tankSize) {
        this.tankSize = tankSize;
    }
}
