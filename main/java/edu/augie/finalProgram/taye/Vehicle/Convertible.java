package edu.augie.finalProgram.taye.Vehicle;

public class Convertible extends Vehicle{

    // attributes
    private boolean softTop;
    private boolean rollBars;

    // constructor
    public Convertible(int VIN, String manufacturer, String model, int year, int odometer, String color,
                 int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
                 Powertrain powertrain, int horsePower) {

        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);

        // initialize attributes
        this.softTop = softTop;
        this.rollBars = rollBars;
    }

    // getter and setter methods
    public boolean hasRollBars() {
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

    // implementation of abstract method
    public String getBodyType(){
        return "Convertible";
    }

    // custom toString() method for Convertible with added attributes
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
                "|Soft Top:     %s\n" +
                "|Roll Bars:    %s\n" +
                "+----------------------------------------\n",
                "Convertible",
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
                softTop ? "Yes" : "No",
                rollBars ? "Yes" : "No"
        );
    }
}
