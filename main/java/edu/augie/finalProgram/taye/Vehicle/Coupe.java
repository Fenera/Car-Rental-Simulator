package edu.augie.finalProgram.taye.Vehicle;

public class Coupe extends Vehicle{

    // same constructor as vehicle
    public Coupe(int VIN, String manufacturer, String model, int year, int odometer, String color,
                 int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
                 Powertrain powertrain, int horsePower) {

        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);
    }

    // implementation of abstract method
    public String getBodyType(){
        return "Coupe";
    }

    // custom toString() method for Coupe
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
                "|Sporty:       %s\n" +
                "+----------------------------------------\n",
                "Coupe",
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
                super.getHorsePower() > 300 ? "Yes" : "No"
        );
    }
}
