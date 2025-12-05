package edu.augie.finalProgram.taye;

public class ElectricMotor implements Powertrain{
    // the values for the following 3 variables are arbitrary for the context of this program
    private int maxRange;
    private int batteryHealth;


    public ElectricMotor(int maxRange, int batteryHealth){
        this.maxRange = maxRange;
        this.batteryHealth = batteryHealth;
    }

    @Override
    public double getRange(){
        return Math.round((double)(batteryHealth * maxRange));
    }

    @Override
    public String getMotor(){
        return "Electric Motor";
    }

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
