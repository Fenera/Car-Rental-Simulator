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
            │Sporty:         %s                     \s
            +────────────────────────────────────────
           \s""",
                "Coupe", super.getVIN(), super.getManufacturer().trim().replaceAll("\"", ""),
                super.getModel().trim().replaceAll("\"", ""), 2025,
                super.getColor().trim().replaceAll("\"", ""), super.getOdometer(),
                super.getHorsePower(), super.getSeatingCapacity(), super.getNumberOfDoors(),
                super.isAllWheelDrive() ? "Yes" : "No", // true -> yes, else no
                super.getPowertrain().getMotor(),
                super.isAvailable() ? "Yes" : "No",
                super.getHorsePower() > 300? "Yes" : "No"
        );
    }

}
