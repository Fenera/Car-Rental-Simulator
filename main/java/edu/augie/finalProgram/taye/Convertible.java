package edu.augie.finalProgram.taye;

public class Convertible extends Vehicle{

    private boolean softTop;
    private boolean rollBars;

    public Convertible(int VIN, String manufacturer, String model, int year, int odometer, String color,
                 int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
                 Powertrain powertrain, int horsePower) {

        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);

        this.softTop = softTop;
        this.rollBars = rollBars;
    }

    public boolean isRollBars() {
        return rollBars;
    }

    public void setRollBars(boolean rollBars) {
        this.rollBars = rollBars;
    }

    public boolean isSoftTop() {
        return softTop;
    }

    public void setSoftTop(boolean softTop) {
        this.softTop = softTop;
    }
}
