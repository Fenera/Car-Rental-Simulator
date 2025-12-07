package edu.augie.finalProgram.taye.Vehicle;

public class Truck extends Vehicle {

    // attributes
    private double bedLength;
    private double towingCapacity;

    // constructor with class attributes
    public Truck(int VIN, String manufacturer, String model, int year, int odometer, String color,
                 int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
                 Powertrain powertrain, double bedLength, double towingCapacity, int horsePower){

        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);

        this.bedLength = bedLength;
        this.towingCapacity = towingCapacity;
    }

    // default constructor (same as Vehicle)
    public Truck(int VIN, String manufacturer, String model, int year, int odometer, String color,
               int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
               Powertrain powertrain, int horsePower){
        super(VIN, manufacturer, model, year, odometer, color,
                seatingCapacity, conditionReport, numberOfDoors, allWheelDrive, powertrain, horsePower, true);
    }

    // getter and setter methods
    public double getBedLength() {
        return bedLength;
    }

    public void setBedLength(double bedLength) {
        this.bedLength = bedLength;
    }

    public double getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(double towingCapacity) {
        this.towingCapacity = towingCapacity;
    }

    public boolean isOverLoaded(double weight){
        return weight > towingCapacity;
    }

    // implementation of abstract method
    public String getBodyType(){
        return "Truck";
    }

    // custom toString() method for Truck with added attributes
    @Override
    public String toString() {
        return String.format("""
            +────────────────────────────────────────
            │────────────────────────────────────────
            │Body Type:    %s                          \s
            │VIN:          %d                          \s
            │Vehicle:      %s %s                    \s
            │Year:         %d                          \s
            │Color:        %s                          \s
            │Mileage:      %,d miles                     \s
            │Horsepower:   %d HP                       \s
            │Seats:        %d                          \s
            │Doors:        %d                          \s
            │AWD:          %s                          \s
            │Powertrain:   %s                          \s
            │Available:    %s                          \s
            │Bed Length (ft):         %.2f                     \s
            │Towing Capacity (lb):         %.2f                     \s           
            +────────────────────────────────────────
           \s""",
                "Truck", super.getVIN(), super.getManufacturer().trim().replaceAll("\"", ""),
                super.getModel().trim().replaceAll("\"", ""), 2025,
                super.getColor().trim().replaceAll("\"", ""), super.getOdometer(),
                super.getHorsePower(), super.getSeatingCapacity(), super.getNumberOfDoors(),
                super.isAllWheelDrive() ? "Yes" : "No", // true -> yes, else no
                super.getPowertrain().getMotor(),
                super.isAvailable() ? "Yes" : "No",
                bedLength, towingCapacity
        );
    }
}
