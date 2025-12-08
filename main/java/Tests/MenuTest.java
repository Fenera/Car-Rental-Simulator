package Tests;

import java.io.IOException;

import edu.augie.finalProgram.taye.Menu.Menu;
import edu.augie.finalProgram.taye.Fleet.Fleet;
import edu.augie.finalProgram.taye.Rental.RentalManager;
import edu.augie.finalProgram.taye.Staff.Manager;
import edu.augie.finalProgram.taye.Log.LogManager;

public class MenuTest {
    public static void main(String[] args) throws IOException {

        // create LogManager object
        LogManager log = new LogManager();

        // create manager object
        Manager manager = new Manager("Saruultugs Oyuntsetseg", "so@hotmail.com", "605-555-555", "2001 Summit Ave", 0000, log);

        // load all employees from file
        manager.loadEmployeeFromFile("main/Resources/Employee Data.csv");


        // create Fleet object
        Fleet f = new Fleet(manager, log);

        // load cars from file
        f.loadCarsFromFile("main/Resources/carsData2025.csv");

        // create RentalManager object
        RentalManager r = new RentalManager(log, f);

        // create Menu object
        Menu menu = new Menu(r, f, log, manager);

        // start program
        menu.start();

    }
}
