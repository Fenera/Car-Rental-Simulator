package edu.augie.finalProgram.taye;


public class HybridMotor implements Powertrain{
    private String fuelType;
    private double fuelEfficiency;
    private static double electricRange = 1.5; // a hybrid battery typically adds 1-2 miles per gallon
    private double tankSize;
    private String motor;

    public HybridMotor(double fuelEfficiency, double tankSize, String motor, String fuelType){
        this.fuelEfficiency = fuelEfficiency;
        this.tankSize = tankSize;
        this.motor = motor;
        this.fuelType = fuelType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public double getRange(){
        double range = (fuelEfficiency * tankSize) + (tankSize * electricRange);
        return Math.round(range);
    }
    @Override
    public String getMotor(){
        return motor;
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
