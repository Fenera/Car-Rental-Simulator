package edu.augie.finalProgram.taye;

public abstract class Vehicle {
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

    public Powertrain getPowertrain(){
        /*
        * Vehicle car1 = new Sedan(...., new GasMotor());
        * car1.getPowertrain().getRange();
        * */
        return powertrain; // this returns the engine/motor that the vehicles use

    }

    public boolean isSporty(){
        return horsePower > 300;
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

    @Override
    public String toString() {
        return String.format("""
            
            ========================================
            VIN:          %d
            Vehicle:      %s %s
            Year:         %d
            Color:        %s
            Mileage:      %,d miles
            Horsepower:   %d HP
            Seats:        %d
            Doors:        %d
            AWD:          %s
            Powertrain:   %s
            Available:    %s
            ========================================
            """,
                VIN, manufacturer, model, year, color, odometer,
                horsePower, seatingCapacity, numberOfDoors,
                allWheelDrive ? "Yes" : "No",
                powertrain.getMotor(),
                isAvailable ? "Yes" : "No"
        );
    }
    public abstract String getBodyType();
}

