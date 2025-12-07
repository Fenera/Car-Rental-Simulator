package edu.augie.finalProgram.taye.Vehicle;

// this class implements PowerTrain so it must have its methods
public class ElectricMotor implements Powertrain{
    // the values for the following 2 variables are arbitrary for the context of this program
    private int maxRange;
    private int batteryHealth;


    // constructor
    public ElectricMotor(int maxRange, int batteryHealth){
        this.maxRange = maxRange;
        this.batteryHealth = batteryHealth;
    }

    // returns the fuel type
    public String getFuelType(){
        return "Electric";
    }

    // implementation of Powertrain methods
    @Override
    public double getRange(){
        return Math.round((double)(batteryHealth * maxRange));
    }

    @Override
    public String getMotor(){
        return "Electric Motor";
    }

    // getter and setter methods
    public void setPredictedRange(int predictedRange){
        this.maxRange = predictedRange;
    }

    public int getPredictedRange(){
        return maxRange;
    }

    public void setBatteryHealth(int batteryHealth){
        this.batteryHealth = batteryHealth;
    }

    public int getBatteryHealth(){
        return batteryHealth;
    }

}
