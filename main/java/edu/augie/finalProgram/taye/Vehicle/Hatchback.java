package edu.augie.finalProgram.taye.Vehicle;

public class Hatchback extends Vehicle {

    // attribute
    private double cargoCapacity;


    // constructor
    public Hatchback(int VIN, String manufacturer, String model, int year, int odometer, String color,
               int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
               Powertrain powertrain, double cargoCapacity, int horsePower) {

        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);

        this.cargoCapacity = cargoCapacity;
    }

    // default constructor (same as in Vehicle)
    public Hatchback(int VIN, String manufacturer, String model, int year, int odometer, String color,
               int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
               Powertrain powertrain, int horsePower){
        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);
    }

    // getter and setter
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    // implementation of abstract method
    @Override
    public String getBodyType(){
        return "Hatchback";
    }

    // custom toString() method for Hatchback
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
                "|Cargo Capacity: %.2f\n" +
                "+----------------------------------------\n",
                "Hatchback",
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
                cargoCapacity
        );
    }

}
