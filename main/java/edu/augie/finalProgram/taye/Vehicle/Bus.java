package edu.augie.finalProgram.taye.Vehicle;

public class Bus extends Vehicle{

    // attributes
    private double length;
    private boolean accessibleRamp;

    // constructor
    public Bus(int VIN, String manufacturer, String model, int year, int odometer, String color,
               int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
               Powertrain powertrain, int horsePower, double length, boolean accessRamp) {

        // pass to Vehicle constructor
        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);

        // initialize attributes
        this.length = length;
        this.accessibleRamp = accessRamp;
    }

    // default constructor with no Bus attributes
    public Bus(int VIN, String manufacturer, String model, int year, int odometer, String color,
               int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
               Powertrain powertrain, int horsePower){
        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);
    }

    // getter and setter methods
    public int getTotalCapacity(){return super.getSeatingCapacity();}

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public boolean isAccessibleRamp() {
        return accessibleRamp;
    }

    public void setAccessibleRamp(boolean accessibleRamp) {
        this.accessibleRamp = accessibleRamp;
    }

    // implementation of abstract method
    public String getBodyType(){
        return "Bus";
    }

    // custom toString() method for BUS with added attributes
    @Override
    public String toString() {
        return String.format(
                "+----------------------------------------\n" +
                "|----------------------------------------\n" +
                "|Body Type:    %s\n" +
                "|VIN:          %d\n" +
                "|Vehicle:      %s %s\n" +
                "|Year:         %d\n" +
                "|Color:        %s\n" +
                "|Mileage:      %,d miles\n" +
                "|Horsepower:   %d HP\n" +
                "|Seats:        %d\n" +
                "|Doors:        %d\n" +
                "|AWD:          %s\n" +
                "|Powertrain:   %s\n" +
                "|Available:    %s\n" +
                "|Length (ft):  %.2f\n" +
                "|Accessible Ramp: %b\n" +
                "+----------------------------------------\n",
                "Bus",
                super.getVIN(),
                super.getManufacturer().trim().replaceAll("\"", ""),
                super.getModel().trim().replaceAll("\"", ""),
                2025,
                super.getColor().trim().replaceAll("\"", ""),
                super.getOdometer(),
                super.getHorsePower(),
                super.getSeatingCapacity(),
                super.getNumberOfDoors(),
                super.isAllWheelDrive() ? "Yes" : "No",
                super.getPowertrain().getMotor(),
                super.isAvailable() ? "Yes" : "No",
                length,
                accessibleRamp
        );
    }
}
