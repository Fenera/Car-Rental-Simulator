package edu.augie.finalProgram.taye.Vehicle;

public abstract class Vehicle {

    // vehicle attributes
    private Powertrain powertrain;
    private int VIN;
    private String manufacturer;
    private String model;
    private int year;
    private int odometer; // in miles
    private String color;
    private int seatingCapacity;
    private String conditionReport;
    private int numberOfDoors;
    private boolean allWheelDrive;
    private int horsePower;
    private boolean isAvailable; // all vehicles are initially available so subclasses of Vehicle will have true in their super(..., true) method

    // constructor
    public Vehicle(int VIN, String manufacturer, String model, int year, int odometer, String color,
                   int seatingCapacity, String conditionReport, int numberOfDoors, boolean allWheelDrive,
                   Powertrain powertrain, int horsePower, boolean isAvailable){
        this.VIN = VIN;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.odometer = odometer;
        this.color = color;
        this.seatingCapacity = seatingCapacity;
        this.conditionReport = conditionReport;
        this.numberOfDoors = numberOfDoors;
        this.allWheelDrive = allWheelDrive;
        this.powertrain = powertrain;
        this.horsePower = horsePower;
        this.isAvailable = isAvailable;
    }

    // default constructor
    public Vehicle(){
        this.VIN = 0;
        this.manufacturer = "Unknown";
        this.model = "Unknown";
        this.year = 0;
        this.odometer = 0;
        this.color = "Unknown";
        this.seatingCapacity = 0;
        this.conditionReport = "Unknown";
        this.numberOfDoors = 0;
        this.allWheelDrive = false;
        this.horsePower = 0;
        System.out.println("Vehicle created with unknown parameters.");
    }

    // getter method
    public Powertrain getPowertrain(){
        /*
        * Vehicle car1 = new Sedan(...., new GasMotor());
        * car1.getPowertrain().getRange();
        * */
        return powertrain; // this returns the engine/motor and fuel info

    }

    // getter and setter methods
    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public void setPowertrain(Powertrain powertrain) {
        this.powertrain = powertrain;
    }

    public double getRange(){
        return powertrain.getRange();
    }

    public boolean isAllWheelDrive() {
        return allWheelDrive;
    }

    public void setAllWheelDrive(boolean allWheelDrive) {
        this.allWheelDrive = allWheelDrive;
    }

    public int getVIN() {
        return VIN;
    }

    public void setVIN(int VIN) {
        this.VIN = VIN;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int driven) {
        this.odometer += driven;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public String getConditionReport() {
        return conditionReport;
    }

    public void setConditionReport(String conditionReport) {
        this.conditionReport = conditionReport;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    // custom toString() method for when vehicle is printed
    @Override
    // the trim() and replaceAll() is because when the strings are read in from the text file,
    // they are read in raw form so color for example would be "Red" and not Red
    public String toString() {
        return String.format("""
            +────────────────────────────────────────
            │────────────────────────────────────────
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
            +────────────────────────────────────────
           \s""",
                VIN, manufacturer.trim().replaceAll("\"", ""),
                model.trim().replaceAll("\"", ""), year,
                color.trim().replaceAll("\"", ""), odometer,
                horsePower, seatingCapacity, numberOfDoors,
                allWheelDrive ? "Yes" : "No", // true -> yes, else no
                powertrain.getMotor(),
                isAvailable ? "Yes" : "No"
        );
    }
    // abstract method that subclasses must implement
    public abstract String getBodyType();
}

