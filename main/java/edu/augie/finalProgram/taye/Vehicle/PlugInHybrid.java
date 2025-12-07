package edu.augie.finalProgram.taye.Vehicle;

public class PlugInHybrid implements Powertrain{
    // attributes
    private String fuelType;
    private double fuelEfficiency;
    private static double rangeAdded = 30.0; // arbitrary value
    private double tankSize;
    private String motor;

    // constructor
    public PlugInHybrid(double fuelEfficiency, double tankSize, String fuelType){
        this.fuelEfficiency = fuelEfficiency;
        this.tankSize = tankSize;
        this.fuelType = fuelType;
    }

   // getter and setter methods
    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }

    public void setFuelEfficiency(double fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    public static double getRangeAdded() {
        return rangeAdded;
    }

    public static void setRangeAdded(double rangeAdded) {
        PlugInHybrid.rangeAdded = rangeAdded;
    }

    public double getTankSize() {
        return tankSize;
    }

    public void setTankSize(double tankSize) {
        this.tankSize = tankSize;
    }

    // implementation of Powertrain methods
    @Override
    public double getRange(){
        double range = fuelEfficiency * tankSize + rangeAdded;
        return Math.round(range);
    }
    @Override
    public String getMotor(){
        return motor.trim().replaceAll("\"", "");
    }

}
