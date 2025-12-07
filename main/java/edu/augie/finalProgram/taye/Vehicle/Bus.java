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
            │Length (ft):         %.2f                        \s
            │Accessible Ramp:         %b                       \s
            +────────────────────────────────────────
           \s""",
                "Bus", super.getVIN(), super.getManufacturer().trim().replaceAll("\"", ""),
                super.getModel().trim().replaceAll("\"", ""), 2025,
                super.getColor().trim().replaceAll("\"", ""), super.getOdometer(),
                super.getHorsePower(), super.getSeatingCapacity(), super.getNumberOfDoors(),
                super.isAllWheelDrive() ? "Yes" : "No", // true -> yes, else no
                super.getPowertrain().getMotor(),
                super.isAvailable() ? "Yes" : "No",
                 length, accessibleRamp
        );
    }
}
