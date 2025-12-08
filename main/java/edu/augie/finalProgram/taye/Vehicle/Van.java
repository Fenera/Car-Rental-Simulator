package edu.augie.finalProgram.taye.Vehicle;

public class Van extends Vehicle{

    // attributes
    private double cargoCapacity;
    private double towingCapacity;

    // constructor
    public Van(int VIN, String manufacturer, String model, int year, int odometer, String color,
                 int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
                 Powertrain powertrain, int horsePower, double cargoCapacity) {

        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);
        this.cargoCapacity = cargoCapacity;
        this.towingCapacity = towingCapacity;
    }

    // default constructor (same as Vehicle)
    public Van(int VIN, String manufacturer, String model, int year, int odometer, String color,
               int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
               Powertrain powertrain, int horsePower){
        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);
    }

    // getter and setter methods
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public double getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(double towingCapacity) {
        this.towingCapacity = towingCapacity;
    }

    // implementation of abstract method
    public String getBodyType(){
        return "Van";
    }

    // custom toString() method for Van with added attributes
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
                "|Cargo Capacity (lb): %.2f\n" +
                "|Towing Capacity (lb): %.2f\n" +
                "+----------------------------------------\n",
                "SUV",
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
                cargoCapacity,
                towingCapacity
        );
    }

}
