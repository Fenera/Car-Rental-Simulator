package edu.augie.finalProgram.taye;

/*
 * Source used: https://www.kleinhonda.com/wbp-understanding-hybrids-versus-plug-in-hybrids
 * Accessed on 12/1/2025
 * "PHEVs have an average electric-only range of 10 to 50 miles"
 * */
public class PlugInHybrid implements Powertrain{
    private String fuelType;
    private double fuelEfficiency;
    private static double rangeAdded = 30.0;
    private double tankSize;
    private String motor;

    public PlugInHybrid(double fuelEfficiency, double tankSize, String fuelType){
        this.fuelEfficiency = fuelEfficiency;
        this.tankSize = tankSize;
        this.fuelType = fuelType;
    }

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

    @Override
    public double getRange(){
        double range = fuelEfficiency * tankSize + rangeAdded;
        return Math.round(range);
    }
    @Override
    public String getMotor(){
        return motor;
    }

}
