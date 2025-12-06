package Menu;

import client.Client;
import edu.augie.finalProgram.taye.Vehicle;
import fleet.Fleet;
import rental.RentalManager;
import staff.Employee;
import utilities.LogEntry;
import utilities.LogManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private RentalManager rentalManager;
    private Fleet fleet;
    private LogManager logManager;
    private Scanner input;

    public Menu(RentalManager rentalManager, Fleet fleet, LogManager logManager){
        this.rentalManager = rentalManager;
        this.fleet = fleet;
        this.logManager = logManager;
        input = new Scanner(System.in);
    }

    public void start(){
        while(true){
            mainMenu();
        }
    }
    public void mainMenu(){
        while(true) {
            try {
                System.out.println("Welcome to Rent-A-Car!");

                System.out.printf("%nSelect user type: %n1) %s," +
                                "%n2) %s" +
                                "%n3) %s" +
                                "%n4) %s%n",
                        "Customer", "Employee", "Manager", "Exit");

                System.out.print("Enter choice number: ");

                int choice = input.nextInt(); // get user's choice

                switch (choice) { // determine method call
                    case 1:
                        customerMenu();
                        return;
                    case 2:
                        employeeMenu();
                        return;
                    case 3:
                        managerMenu();
                        return;
                    case 4:
                        exit();
                        return;
                    default:
                        System.out.println("You entered an invalid number. Try again");
                        pause();
                }
            } catch (InputMismatchException e) {
                pause();
            }
        }



    }

    private void customerMenu() {

        try {
            while (true) {
                // this loop is for the first input (customer menu)
                // if the user enters an invalid input, the default allows this loop to continue, the case statements break out of this loop (valid input)

                System.out.println("Customer Menu");

                System.out.printf("%n1) %s," +
                                "%n2) %s" +
                                "%n3) %s" +
                                "%n4) %s" +
                                "%n5) %s" +
                                "%n6) %s" +
                                "%n7) %s" +
                                "%n8) %s%n",
                        "View all available vehicles", "Search vehicles by vin", "Filter vehicles by type (SUV, Sedan, Truck), " +
                                "Filter vehicles by price range", "Filter vehicles by horsepower, mpg, or gas type", "View vehicle details",
                        "Rent a vehicle", "Return to main menu");

                System.out.print("Enter choice number: ");
                int choice = input.nextInt();

                switch (choice) {
                    case 1: // show all vehicles
                        fleet.showAvailableCars();
                        break;

                    case 2: // search vehicles by vin
                        System.out.println("Search vehicles by vin\n");
                        System.out.print("Enter the vehicles VIN: ");
                        int vin = input.nextInt();
                        System.out.println(fleet.getVehicleByVin(vin)); // print the vehicle with that vin (Vehicle has custom toString())
                        break;

                    case 3: // Filter vehicles by type (SUV, Sedan, Truck, etc.)
                        while (true) { // this loop is for the choice input (valid => break out of loop, invalid => continue loop until valid)
                            System.out.println("Filter by body type\n");
                            System.out.print("Enter the body type (options=SUV, Sedan, Convertible, Coupe, Truck, Van, Hatchback, Bus): ");
                            String choice2 = input.nextLine(); // get choice
                            switch (choice2) {
                                case "SUV", "Bus", "Sedan", "Convertible", "Coupe", "Truck", "Van",
                                     "Hatchback": // bodyType is valid
                                    fleet.filterByBodyType(choice2); // print
                                    break;
                                default:
                                    pause();
                                    continue;
                            }
                            break;
                        }break;
                    case 4: // Filter vehicles by price range

                        while (true) {
                            System.out.println("Filter vehicles by their daily rate (in $)\n");
                            System.out.print("Lower Limit: ");
                            double lowerLimit = input.nextDouble(); // get lower limit
                            System.out.println();
                            System.out.print("Upper Limit: ");
                            double upperLimit = input.nextDouble(); // get upper limit

                            if (lowerLimit <= 50 || upperLimit <= 50) {
                                System.out.println("Range too low");
                                pause();
                            } else {
                                int count = fleet.filterByPriceRange(lowerLimit, upperLimit); // print the vehicles with rates in the range and store the number of vehicles
                                if (count == 0) {
                                    System.out.println("No cars found within that range");
                                }
                                break; // break out of loop
                            }
                        }
                    case 5:

                        while (true) {
                            System.out.printf("Filter by: 1) %s%n" +
                                    "2) %s%n" +
                                    "3) %s%n" +
                                    "4) %s%n", "Horsepower", "Fuel Economy (mpg)", "Gas Type (Petrol, Diesel, Electric..)");

                            System.out.println();
                            System.out.print("Enter choice number: ");
                            int filterChoice = input.nextInt();

                            switch (filterChoice) {
                                case 1:
                                    while (true) {
                                        // filter by hp
                                        System.out.println("Filter by horsepower");
                                        System.out.print("Minimum: ");
                                        int minimum = input.nextInt();
                                        System.out.println("Maximum: ");
                                        int maximum = input.nextInt();

                                        // check if hp is valid
                                        if (minimum >= 0 && maximum >= 0) {
                                            int count = fleet.filterByHorsePower(minimum, maximum);
                                            if (count == 0) {
                                                System.out.println("No cars found within that range");
                                            }
                                            break;
                                        } else {
                                            System.out.println("Range too low");
                                            pause();
                                        }
                                    }
                                case 2:
                                    // filter by fuel economy
                                    while (true) {
                                        // filter by hp
                                        System.out.println("Filter by fuel economy (mpg)");
                                        System.out.print("Minimum: ");
                                        int minimum = input.nextInt();
                                        System.out.println("Maximum: ");
                                        int maximum = input.nextInt();

                                        // check if hp is valid
                                        if (minimum >= 0 && maximum >= 0) {
                                            int count = fleet.filterByMPG(minimum, maximum);
                                            if (count == 0) {
                                                System.out.println("No cars found within that range");
                                            }
                                            break;
                                        } else {
                                            System.out.println("Range too low");
                                            pause();
                                        }
                                        break;
                                    }break;
                                case 3:
                                    // filter by gas type(diesel, petrol, electric, hybrid, PIH)
                                    while(true){
                                        // get fuel type from user
                                        System.out.println("Filter by fuel type");
                                        System.out.print("Enter type (diesel, petrol, electric," +
                                                " hybrid, PIH: ");
                                        String fuelType = input.nextLine().trim();

                                        switch(fuelType.toLowerCase()){
                                            case "diesel", "petrol", "electric", "hybrid", "pih":
                                                fleet.filterByFuelType(fuelType);
                                                break; // break out of the outerLoop (no more inputs for this prompt)
                                            default:
                                                pause();

                                        }
                                    }
                                default:
                                    pause();
                            }
                        }

                    case 6:
                        while(true){
                            System.out.println("View vehicle details");
                            System.out.print("Enter the vin: ");
                            int vinNumber = input.nextInt();
                            Vehicle vehicle = fleet.getVehicleByVin(vinNumber);
                            if(vehicle.isAvailable()){
                                System.out.println(vehicle); // print info about vehicle with that vin
                                break;
                            } else{
                                System.out.println("That vehicle is currently not available: ");
                            }
                        }
                    case 7:
                        while(true){
                            System.out.println("Enter some personal information: ");
                            System.out.println("Enter name");
                            String name = input.nextLine();

                            System.out.println();
                            System.out.print("Enter phone number: ");
                            String phoneNumber = input.nextLine();

                            System.out.println();
                            System.out.print("Enter email: ");
                            String email = input.nextLine();

                            System.out.println();
                            System.out.print("Enter address: ");
                            String address = input.nextLine();

                            // create client object
                            Client client = new Client(name, phoneNumber, email, address);

                            System.out.println();
                            System.out.print("Enter the vin of the car you chose: ");
                            int vinNumber2 = input.nextInt();

                            Vehicle vehicle = fleet.getVehicleByVin(vinNumber2);

                            System.out.println();
                            System.out.print("How many days do you want to rent the car for: ");
                            int days = input.nextInt();

                            // since we are a client, the employee object doesn't really matter so just create one
                            Employee employee = new Employee("Computer", "dr@ebrown.org", "555-555-5555",
                                    "Hill Valley, CA", 9999, true);

                            if(vehicle.isAvailable()) {
                                rentalManager.rentVehicle(vinNumber2, employee, client, days);
                                System.out.println("Thank you for renting with us!");
                                break;
                            }else{
                                pause();
                            }

                        }
                    case 8:
                        return; // returns to main menu
                }
            }
        } catch(InputMismatchException e){
            customerMenu();
        }
    }

    private void employeeMenu(){
    }

    private void managerMenu(){

    }

    public void exit(){

    }

    public void clearConsole(){

    }

    private void pause(){ // this method pauses to program until the user is ready to continue (pressing enter)
        System.out.println("You entered invalid input. Press enter to continue");
        input.nextLine();
    }
}
