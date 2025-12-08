package edu.augie.finalProgram.taye.Menu;

import edu.augie.finalProgram.taye.Client.Client;
import edu.augie.finalProgram.taye.Vehicle.Vehicle;
import edu.augie.finalProgram.taye.Fleet.Fleet;
import edu.augie.finalProgram.taye.Fleet.VehicleMaker;
import edu.augie.finalProgram.taye.Rental.Rental;
import edu.augie.finalProgram.taye.Rental.RentalManager;
import edu.augie.finalProgram.taye.Staff.Employee;
import edu.augie.finalProgram.taye.Staff.Manager;
import edu.augie.finalProgram.taye.Log.LogManager;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    // declare variables
    private RentalManager rentalManager;
    private Fleet fleet;
    private LogManager logManager;
    private Scanner input;
    private Manager manager;

    // constructor
    public Menu(RentalManager rentalManager, Fleet fleet, LogManager logManager, Manager manager){

        // initialize variables
        this.rentalManager = rentalManager;
        this.fleet = fleet;
        this.logManager = logManager;
        this.manager = manager;
        input = new Scanner(System.in);
    }

    // this method runs everything (called in main)
    public void start(){
        while(true){
            mainMenu();
        }
    }

    public void mainMenu(){
        while(true) {
            // this is to catch if the user enters an incorrect datatype for a variable (i.e. string for int variable)
            try {
                System.out.println("\n\n" +
                        "============================================\n" +
                        "           WELCOME TO RENT-A-CAR!\n" +
                        "============================================\n\n");
                System.out.printf("%nSelect user type: %n1) %s" +
                                "%n2) %s" +
                                "%n3) %s%n",
                        "Customer", "Staff", "Exit");

                System.out.print("Enter choice number: ");

                int choice = input.nextInt(); // get their choice

                // this allows all the inputs to work correctly and not collide
                // returns an empty string
                input.nextLine();


                switch (choice) { // pass user's choice into switch

                    // based on the user's input, the program will show the correct menu by calling their methods
                    case 1:
                        customerMenu();
                        return; // breaks out of loop
                    case 2:
                        staffMenu();
                        return;
                    case 3:
                        exit();
                        return;
                    default:
                        pause(); // this pause method shows a msg if the user enters wrong input and cycles back to top of while loop
                }
            } catch (InputMismatchException e) {
                pause(); // allow user to re-enter their choice
            }
        }



    }

    private void customerMenu() {
        try { // try-catch to catch InputMismatchException
            while (true) {
                System.out.println("\n\n" +
                        "====================================\n" +
                        "          CUSTOMER MENU\n" +
                        "====================================\n\n");

                System.out.printf("%n1) %s" +
                                "%n2) %s" +
                                "%n3) %s" +
                                "%n4) %s" +
                                "%n5) %s" +
                                "%n6) %s" +
                                "%n7) %s" +
                                "%n8) %s%n",
                        "View all available vehicles", "Search vehicles by VIN",
                        "Filter vehicles by type (SUV, Sedan, Truck)",
                        "Filter vehicles by price range",
                        "Filter vehicles by horsepower, mpg, or gas type",
                        "View vehicle details",
                        "Rent a vehicle", "Return to main menu");

                System.out.print("\n\nEnter choice number: ");
                int choice = input.nextInt(); // get user's choice for customer options
                input.nextLine();

                switch (choice) {
                    case 1: // View all available vehicles
                        fleet.showAvailableCars(); // void method that prints out all vehicles that are available
                        System.out.println("\nPress enter to continue...");
                        input.nextLine();
                        break; // breaks out of switch

                    case 2: // Filter vehicles by type (SUV, Sedan, Truck)
                        System.out.println("Search vehicles by vin\n");
                        System.out.print("Enter the vehicles VIN: ");
                        int vin = input.nextInt(); // gets vin from user
                        input.nextLine();

                        Vehicle v = fleet.getVehicleByVin(vin); // method that returns Vehicle object from the vin #
                        if (v != null) { // checks if the method returned null (not in bst)
                            System.out.println(v); // prints vehicle details (custom toString() in vehicle)
                        } else {
                            System.out.println("Vehicle not found.");
                        }
                        System.out.println("\nPress enter to continue...");
                        input.nextLine();
                        break;

                    case 3: // Filter vehicles by price range
                        boolean validBodyType = false; // stores if the user entered a correct body type
                        while (!validBodyType) { // loop until they do
                            System.out.println("Filter by body type\n");
                            System.out.print("Enter the body type (options=SUV, Sedan, Convertible, Coupe, Truck, Van, Hatchback, Bus): ");
                            String bodyTypeChoice = input.nextLine().trim(); // get their choice

                            switch (bodyTypeChoice.toLowerCase()) { // convert to lowercse to avoid case mismatch
                                case "suv":
                                case "bus":
                                case "sedan":
                                case "convertible":
                                case "coupe":
                                case "truck":
                                case "van":
                                case "hatchback": // if their choice is one of the following, use method that prints vehicles by body type
                                    int count = fleet.filterByBodyType(bodyTypeChoice);
                                    validBodyType = true; // set to true -> break out of loop
                                    if(count == 0){
                                        System.out.println("\n\nNo Vehicles found");
                                    }
                                    break; // break out of case
                                default:
                                    System.out.println("Invalid body type. Please try again."); // continue to loop again
                            }
                        }
                        System.out.println("\nPress enter to continue...");
                        input.nextLine();
                        break; // breaks out of case switch

                    case 4: // Filter vehicles by horsepower, mpg, or gas type
                        boolean validRange = false; // same logic as above but for range
                        while (!validRange) {
                            System.out.println("\n\nFilter vehicles by their daily rate (in $)\n");
                            System.out.print("Lower Limit: ");
                            double lowerLimit = input.nextDouble();
                            System.out.print("Upper Limit: ");
                            double upperLimit = input.nextDouble();
                            input.nextLine();

                            // 40 is arbitrary

                            // the user entered too low of a value
                            if (lowerLimit <= 40 || upperLimit <= 40) {
                                System.out.println("Range too low (minimum $40)");

                                // the user mixed up lower and upper
                            } else if (lowerLimit > upperLimit) {
                                System.out.println("Lower limit cannot be greater than upper limit");
                            } else {
                                // the filterByPriceChange method prints the number of vehicles within that price range and returns a count
                                int count = fleet.filterByPriceRange(lowerLimit, upperLimit);
                                if (count == 0) {
                                    System.out.println("No cars found within that range");
                                }
                                validRange = true; // break out of loop
                            }
                        }
                        System.out.println("\nPress enter to continue...");
                        input.nextLine();
                        break;

                    case 5: // View vehicle details
                        boolean validFilter = false;
                        while (!validFilter) { // loop runs until the user enters a valid filter
                            System.out.printf("\n\nFilter by:\n1) Horsepower\n2) Fuel Economy (mpg)\n3) Gas Type (Petrol, Diesel, Electric..)\n");
                            System.out.print("\nEnter choice number: ");
                            int filterChoice = input.nextInt(); // get choice
                            input.nextLine();

                            // switch-case to perform action based on user's decision
                            switch (filterChoice) {
                                case 1: // filter by hp
                                    System.out.println("\n\nFilter by horsepower");
                                    // get max and min hp
                                    System.out.print("Minimum: ");
                                    int minHp = input.nextInt();
                                    System.out.print("Maximum: ");
                                    int maxHp = input.nextInt();
                                    input.nextLine();

                                    // see if the min and max are valid values
                                    if (minHp >= 0 && maxHp >= 0 && minHp <= maxHp) {
                                        // get the count and print the vehicles with hp in that range
                                        int count = fleet.filterByHorsePower(minHp, maxHp);
                                        if (count == 0) { // no vehicles within hp range
                                            System.out.println("\n\nNo cars found within that range");
                                        }
                                        validFilter = true; // set to true bc filter was valid -> breaks out of loop
                                    } else {
                                        System.out.println("\n\nInvalid range");
                                    }
                                    break; // breaks out of switch case

                                case 2: // filter by fuel economy
                                    System.out.println("\n\nFilter by fuel economy (mpg)");
                                    System.out.print("Minimum: ");
                                    // get min and max mpg
                                    int minMpg = input.nextInt();
                                    System.out.print("Maximum: ");
                                    int maxMpg = input.nextInt();
                                    input.nextLine();

                                    // ensure valid range
                                    if (minMpg >= 0 && maxMpg >= 0 && minMpg <= maxMpg) {
                                        // print vehicles within that range
                                        int count = fleet.filterByMPG(minMpg, maxMpg);
                                        if (count == 0) {
                                            System.out.println("\n\nNo cars found within that range");
                                        }
                                        validFilter = true; // break out of loop
                                    } else {
                                        System.out.println("\n\nInvalid range");
                                    }
                                    break; // break out of switch case

                                case 3: // filter by fuel type
                                    System.out.println("\n\nFilter by fuel type");
                                    System.out.print("Enter type (diesel, petrol, electric, hybrid, pih): ");
                                    String fuelType = input.nextLine().trim(); // get the user's fuel type

                                    // determine valid input
                                    switch (fuelType.toLowerCase()) {
                                        case "diesel":
                                        case "petrol":
                                        case "electric":
                                        case "hybrid":
                                        case "pih":
                                            fleet.filterByFuelType(fuelType); // print vehicles with that fuel type
                                            validFilter = true; // break out of loop
                                            break; // break out of switch case
                                        default:
                                            System.out.println("\n\nInvalid fuel type");
                                    }
                                    break; // breaks out of switch case

                                default:
                                    System.out.println("\n\nInvalid choice");
                            }
                        }
                        System.out.println("\n\nPress enter to continue...");
                        input.nextLine();
                        break;

                    case 6: // Show details about vehicle
                        System.out.println("\n\nView vehicle details");
                        System.out.print("\n\nEnter the VIN: ");
                        int vinNumber = input.nextInt(); // get vin
                        input.nextLine();

                        Vehicle vehicle = fleet.getVehicleByVin(vinNumber); // get vehicle with vin

                        // see if vehicle is valid
                        if (vehicle != null) {
                            if (vehicle.isAvailable()) { // see if it available currently
                                System.out.println(vehicle); // print the vehicle
                            } else {
                                System.out.println("\n\nThat vehicle is currently not available.");
                            }
                        } else {
                            System.out.println("\n\nVehicle not found.");
                        }
                        System.out.println("\nPress enter to continue...");
                        input.nextLine();
                        break;

                    case 7: // Rent a vehicle

                        // get user's information
                        System.out.println("\n\nEnter some personal information:\n");
                        System.out.print("Enter name: ");
                        String name = input.nextLine().trim();

                        System.out.print("\n\nEnter phone number: ");
                        String phoneNumber = input.nextLine().trim();

                        System.out.print("\n\nEnter email: ");
                        String email = input.nextLine().trim();

                        System.out.print("\n\nEnter address: ");
                        String address = input.nextLine().trim();

                        // create client object with user's information
                        Client client = new Client(name, phoneNumber, email, address);

                        // get the vin of their choice
                        System.out.print("\n\nEnter the VIN of the car you chose: ");
                        int vinNumber2 = input.nextInt();
                        input.nextLine();

                        System.out.print("\n\nHow many days would you like to rent the car: ");
                        int daysToRent = input.nextInt();
                        input.nextLine();

                        // get vehicle from vin (null if not valid)
                        Vehicle vehicleToRent = fleet.getVehicleByVin(vinNumber2);

                        // get employee object (doesn't matter what employee bc we are on client side)
                        Employee employee = manager.getEmployee(1002);

                        // make rental object with information given
                        Rental rental = new Rental(vehicleToRent, client, employee, LocalDateTime.now(),
                                LocalDateTime.now().plusDays(daysToRent), fleet.getRateByVin(vinNumber2));

                        // vehicle is valid
                        if (!(vehicleToRent == null)) {
                            // vehicle is valid but not available to rent
                            if(!vehicleToRent.isAvailable()) {
                                System.out.println("The vehicle is not available, try again..");
                                System.out.println("\nPress enter to continue...");
                                input.nextLine();
                            } else{
                                rentalManager.rentVehicle(rental); // rent the vehicle
                                System.out.println("Rented: \n");
                                System.out.println(vehicleToRent);
                                System.out.println("\n\nThank you for renting with us!");
                                System.out.println("\nPress enter to continue...");
                                input.nextLine();
                                break;
                            }
                        } else{ // vehicle is not valid
                            System.out.println("The vehicle is not in inventory, try again");
                            System.out.println("\nPress enter to continue...");
                            input.nextLine();
                        }
                    case 8:
                        return; // this returns to where the method customerMenu() was called => mainMenu()

                    default:
                        System.out.println("\n\nInvalid choice. Please try again."); // restarts loop
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("\n\nInvalid input type. Please enter a number.");
            input.nextLine();
            customerMenu();
        }
    }

    private void staffMenu(){
        // menu for staff: employee or manager
        try {
            while (true) {
                System.out.println("\n\n" +
                        "====================================\n" +
                        "          STAFF MENU\n" +
                        "====================================\n\n");

                System.out.println("Enter your Staff ID (id = 0000 for Manager & id = 1000 - 1008 for Employee)");
                System.out.print("Enter here: ");
                int staffID = input.nextInt(); // get staffID from user
                input.nextLine();

                if(manager.getStaffID() == staffID){ // the user is a manager
                    while(true) {
                        System.out.println("\n\n" +
                                "====================================\n" +
                                "           MANAGER MENU\n" +
                                "====================================\n\n");

                        System.out.printf("%n1) %s" +
                                        "%n2) %s" +
                                        "%n3) %s" +
                                        "%n4) %s" +
                                        "%n5) %s" +
                                        "%n6) %s" +
                                        "%n7) %s" +
                                        "%n8) %s%n",
                                "View entire fleet", "View logs",
                                "Add new vehicle to fleet",
                                "Remove vehicle from fleet",
                                "View all employees",
                                "Add employee",
                                "Remove employee", "Return to main menu");

                        System.out.print("\n\nEnter choice number: ");
                        int choice = input.nextInt(); // get the user's choice
                        input.nextLine();

                        switch (choice) {
                            case 1:
                                System.out.println("View Entire Fleet: \n\n");
                                fleet.showInventory(); // prints all vehicles on fleet (available & unavailable)
                                System.out.println("\nPress enter to continue...");
                                input.nextLine();
                                break;
                            case 2: // prints logs
                                System.out.println("\n\nUpdate Logs: \n");
                                logManager.displayLogs();
                                System.out.println("\nPress enter to continue...");
                                input.nextLine();
                                break;

                            case 3: // add new vehicle to fleet

                        try{
                                while (true) {
                                    // get information about the vehicle
                                    System.out.println("\n\nAdd new vehicle to fleet\n");
                                    System.out.print("Enter the VIN of the vehicle: ");
                                    int vin = input.nextInt();
                                    input.nextLine();

                                // see if vehicle is already in the fleet
                                // returns null if the vehicle is not in the lot
                                if(fleet.getVehicleByVin(vin) == null) {
                                    System.out.println();
                                    System.out.print("Enter the manufacturer: ");
                                    String manufacturer = input.nextLine().trim();

                                    System.out.println();
                                    System.out.print("Enter the model: ");
                                    String model = input.nextLine().trim();

                                    System.out.println();
                                    System.out.print("Enter the body type (SUV, Sedan...): ");
                                    String bodyType = input.nextLine().trim();

                                    System.out.println();
                                    System.out.print("Enter the horsepower: ");
                                    int horsepower = input.nextInt();
                                    input.nextLine();

                                    System.out.println();
                                    System.out.print("Enter the number of miles: ");
                                    int odometer = input.nextInt();
                                    input.nextLine();

                                    System.out.println();
                                    System.out.print("Enter the color: ");
                                    String color = input.nextLine().trim();

                                    System.out.println();
                                    System.out.print("Enter the number of seats: ");
                                    int seats = input.nextInt();
                                    input.nextLine();

                                    System.out.println();
                                    System.out.print("Enter the conditionReport: ");
                                    String conditionReport = input.nextLine().trim();

                                    System.out.println();
                                    System.out.print("Enter the number of doors: ");
                                    int doors = input.nextInt();
                                    input.nextLine();

                                    System.out.println();
                                    System.out.print("Is it awd? (true or false): ");
                                    boolean awd = Boolean.parseBoolean(input.nextLine().toLowerCase());

                                    System.out.println();
                                    System.out.print("Enter the fuel type (petrol, diesel, hybrid, pih, electric): ");
                                    String fuelType = input.nextLine().trim();

                                    System.out.println();
                                    System.out.print("Enter the fuel economy in mpg: ");
                                    double fuelEconomy = input.nextDouble();
                                    input.nextLine();

                                    System.out.println();
                                    System.out.print("Enter the engine (i.e. 2.0 L i4): ");
                                    String engine = input.nextLine().trim();

                                    // we planned on doing something with trip range estimates but we scrapped it
                                    // 20 is arbitrary value
                                    double tankSize = 20.0;

                                    System.out.println();
                                    System.out.print("Enter the daily rate ($): ");
                                    double dailyRate = input.nextDouble();


                                        // create variable fuelClass for gas/electric/hybrid/plug-in distinction
                                        String fuelClass = fuelType.toLowerCase().equals("petrol") || fuelType.toLowerCase().equals("diesel") ? "Gas" :
                                                (fuelType.toLowerCase().equals("hybrid") ? "Hybrid" : (fuelType.toLowerCase().equals("Plug-In") ? "Plug-In" : "Electric"));

                                        VehicleMaker vMaker = new VehicleMaker(); // instantiate vehicle maker object


                                        // create vehicle
                                        Vehicle vehicle = vMaker.make(bodyType, fuelClass, vin, manufacturer,
                                                model, 2025, odometer, color, doors, seats, conditionReport,
                                                awd, fuelType, fuelEconomy, engine, tankSize, horsepower);


                                        // add the vehicle to the fleet
                                        fleet.addNewVehicle(staffID, vehicle, dailyRate);

                                        System.out.println("\nNew Vehicle Added: \n\n");
                                        System.out.println(vehicle);
                                        break;
                                    } else{
                                        System.out.println("\nVehicle is already present in the fleet\n\n");
                                    }
                                }
                        } catch(InputMismatchException e){
                            continue; // loop again
                        }
                                System.out.println("\nPress enter to continue...");
                                input.nextLine();
                                break; // breaks out of switch-case

                            case 4: // Remove vehicle from fleet
                                while (true) { // loop until the vin the user enter is valid
                                    System.out.println("Remove the vehicle from the fleet\n");
                                    System.out.print("Enter the VIN of the vehicle: ");
                                    int vin = input.nextInt();
                                    input.nextLine();

                                    Vehicle vehicle = fleet.getVehicleByVin(vin); // get vehicle object with that vin (null if DNE)

                                    if (vehicle != null) {
                                        fleet.removeVehicleFromLot(staffID, vin);
                                        System.out.println("Vehicle Removed: "); // print the vehicle that was removed
                                        System.out.print(vehicle + "\n");
                                        System.out.println("\nPress enter to continue...");
                                        input.nextLine();
                                        break; // breaks out of loop
                                    } else {
                                        System.out.println("No such vehicle with that VIN\n\n");
                                    }
                                }
                                System.out.println("\nPress enter to continue...");
                                input.nextLine();
                                break; // breaks out of switch-case

                            case 5:
                                manager.displayAllEmployees();
                                input.nextLine();
                                break;
                            case 6:
                                while (true) {
                                    // get informtion about the employee
                                    System.out.println("Add a new employee: \n\n");
                                    System.out.println("\nEnter their information");
                                    System.out.print("Enter name: ");
                                    String name = input.nextLine().trim();

                                    System.out.print("\n\nEnter phone number: ");
                                    String phoneNumber = input.nextLine().trim();

                                    System.out.print("\n\nEnter email: ");
                                    String email = input.nextLine().trim();

                                    System.out.print("\n\nEnter address: ");
                                    String address = input.nextLine().trim();

                                    System.out.print("\n\nEnter staffID: ");
                                    int staffID2 = input.nextInt();
                                    input.nextLine();
                                    // see if the staffID the manager chose is already in the system
                                    if (!manager.containsStaffID(staffID2)) { // not in system
                                        Employee employee = new Employee(name, email, phoneNumber,
                                                address, staffID2, true); // create employee object
                                        manager.addEmployee(employee); // add the employee
                                        System.out.println("Employee Added: ");
                                        System.out.print(employee);
                                        input.nextLine();
                                        break; // break out of loop
                                    } else { // in system
                                        System.out.println("StaffID already in system");
                                    }
                                }
                                System.out.println("\nPress enter to continue...");
                                input.nextLine();
                                break; // break out of switch

                            case 7:
                                while (true) {
                                    System.out.println("Remove an employee: \n\n");
                                    System.out.println();
                                    System.out.print("Enter the employee's staffID: ");
                                    int staffID3 = input.nextInt(); // get their staffID
                                    input.nextLine();

                                    // check if their id is in the system in the first place
                                    if (manager.containsStaffID(staffID3)) {
                                        Employee employee = manager.getEmployee(staffID3); // store instance of this employee
                                        employee.setActive(false); // set active to false
                                        manager.removeEmployee(staffID3); // remove employee
                                        System.out.println("Employee Removed: ");
                                        System.out.print(employee); // print out details about the removed employee
                                        break; // break out of loop

                                    } else { // manager gave invalid staffID
                                        System.out.println("No such employee with that staffID");
                                    }
                                }
                                System.out.println("\nPress enter to continue...");
                                input.nextLine();
                                break;
                            case 8:
                                return;
                            default:
                                pause();
                        }
                    }
                }
                else if(manager.containsStaffID(staffID)){ // the user is an employee
                    while(true) {
                        System.out.println("\n\n" +
                                "====================================\n" +
                                "           EMPLOYEE MENU\n" +
                                "====================================\n\n");

                        System.out.printf("%n1) %s" +
                                        "%n2) %s" +
                                        "%n3) %s" +
                                        "%n4) %s" +
                                        "%n5) %s" +
                                        "%n6) %s" +
                                        "%n7) %s%n",
                                "View fleet", "View rented vehicles",
                                "Rent a vehicle",
                                "Process return",
                                "View rental history",
                                "View rentals by VIN", "Return to main menu");
                        System.out.print("\n\nEnter choice number: ");
                        int choice = input.nextInt(); // get the user's choice
                        input.nextLine();

                        switch (choice) {
                            case 1:
                                System.out.println("View Entire Fleet: \n\n");
                                fleet.showInventory(); // prints all vehicles on fleet (available & unavailable)
                                System.out.println("\nPress enter to continue...");
                                input.nextLine();
                                break;
                            case 2: // view rented vehicles
                                System.out.println("Rented vehicles: \n\n");
                                rentalManager.printRentedVehicles();
                                System.out.println("\nPress enter to continue...");
                                input.nextLine();
                                break;
                            case 3: // Rent a vehicle
                                while (true) {
                                    // get information about customer and rental
                                    System.out.println("Rent a vehicle\n");
                                    System.out.print("Enter the VIN of the vehicle: ");
                                    int vin = input.nextInt();
                                    input.nextLine();

                                    System.out.println("Enter customer information: ");
                                    System.out.print("Enter the name of the customer: ");
                                    String name = input.nextLine().trim();

                                    System.out.println();
                                    System.out.print("Enter customer's phone number: ");
                                    String phoneNumber = input.nextLine().trim();

                                    System.out.println();
                                    System.out.print("Enter the customer's email: ");
                                    String email = input.nextLine().trim();

                                    System.out.println();
                                    System.out.println("Enter the customer's address: ");
                                    String address = input.nextLine().trim();

                                    System.out.println();
                                    System.out.print("How many days is this rental: ");
                                    int days = input.nextInt();
                                    input.nextLine();

                                    // get vehicle with that vin
                                    Vehicle vehicle = fleet.getVehicleByVin(vin);
                                    Client client = new Client(name, phoneNumber, email, address);

                                    if (!(vehicle == null)) {
                                        if (vehicle.isAvailable()) { // is the vehicle available to rent
                                            Rental rental = new Rental(vehicle, client, manager.getEmployee(staffID),
                                                    LocalDateTime.now(), LocalDateTime.now().plusDays(4), fleet.getRateByVin(vin));
                                            rentalManager.rentVehicle(rental);
                                            System.out.println("Employee (" + staffID + ")" + " Rented out => VIN: " + vehicle.getVIN() +
                                                    " Manufacturer: " + vehicle.getManufacturer() +
                                                    " Model +" + vehicle.getModel() + " to Client " + client.getName() + "(" +
                                                    client.getClientID() + ")");
                                            break;
                                        } else {
                                            System.out.println("This vehicle is not available to rent at the moment");
                                        }
                                    } else {
                                        System.out.println("The VIN you entered is invalid");
                                    }
                                }
                                System.out.println("\nPress enter to continue...");
                                input.nextLine();
                                break; // breaks out of switch-case

                            case 4: // Process a return
                                while (true) {
                                    // get vin of rental vehicle
                                    System.out.println("Process a return\n");
                                    System.out.print("Enter the VIN of the vehicle: ");
                                    int vin = input.nextInt();
                                    input.nextLine();

                                    Vehicle vehicle = fleet.getVehicleByVin(vin); // get Vehicle obj to check validity of vin

                                    // get Rental object of vehicle with that vin
                                    Rental rental = rentalManager.findActiveRentalsByVin(vin);
                                    if (!(vehicle == null)) {
                                        if (!(rental == null)) { // rental is null (method returned null bc vehicle is not rented now)
                                            rentalManager.returnVehicle(rental);
                                            break;
                                        } else {
                                            System.out.println("The vehicle is not an active rental");
                                        }
                                    } else {
                                        System.out.println("The VIN you entered is invalid");
                                    }
                                }
                                System.out.println("\nPress enter to continue...");
                                input.nextLine();
                                break; // breaks out of switch-case

                            case 5:
                                System.out.println("Rental History: \n\n");
                                rentalManager.printRentalHistory(); // display the rental history
                                break;
                            case 6:
                                while (true) {
                                    System.out.println("View rentals by vehicle\n\n");

                                    System.out.println("Enter the vin: ");
                                    int vin = input.nextInt();
                                    input.nextLine();

                                    Vehicle vehicle = fleet.getVehicleByVin(vin);

                                    if (!(vehicle == null)) {
                                        rentalManager.printRentalByVin(vin);
                                        break; // break out of loop
                                    } else {
                                        System.out.println("No vehicle found with that VIN"); // continue with loop
                                    }
                                }
                                System.out.println("\nPress enter to continue...");
                                input.nextLine();
                                break; // breaks out of switch-case

                            case 7:
                                return;
                            default:
                                System.out.println("\n\nInvalid choice. Please try again."); // restarts loop
                        }
                    }
                    }else{ // the user is not a manager or employee
                    System.out.println("\n\nIncorrect Staff ID");
                    System.out.println("\nPress enter to continue...");
                    input.nextLine();

                }
            }
        } catch(InputMismatchException e){
            pause();
        }
    }


    public void exit(){
        System.out.println("\n\nThank you for using RENT-A-CAR!");
        System.exit(0);
    }

    public void clearConsole(){

    }

    private void pause(){ // this method pauses to program until the user is ready to continue (pressing enter)
        System.out.println("\nYou entered invalid input. Press enter to continue\n");
        System.out.println("Press enter to continue...");
        input.nextLine();
    }
}
