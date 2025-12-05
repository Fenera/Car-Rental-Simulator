package edu.augie.finalProgram.taye;

public class GasMotor implements Powertrain{
    private String fuelType; // gas/diesel
    private double fuelEfficiency; // mpg
    private String engine; // i.e 1.2L I-4
    private double tankSize; // i.e 15.1 L

    public GasMotor(String fuelType, double fuelEfficiency,
                    String engine, double tankSize){
        this.fuelType = fuelType;
        this.fuelEfficiency = fuelEfficiency;
        this.engine = engine;
        this.tankSize = tankSize;
    }

    public double getTankSize() {
        return tankSize;
    }

    public void setTankSize(double tankSize) {
        this.tankSize = tankSize;
    }

    @Override
    public double getRange(){
        double range = fuelEfficiency * tankSize;
        return Math.round(range);
    }
    @Override
    public String getMotor(){
        return engine;
    }

    // invokes the no-parameter constructor in Vehicle.java
    public GasMotor() {
        super();
    }

    public String getFuelType() {
        return fuelType;
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
