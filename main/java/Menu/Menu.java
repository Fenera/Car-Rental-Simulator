package Menu;

import fleet.Fleet;
import rental.RentalManager;
import utilities.Logger;
import java.util.Scanner;

public class Menu {

    private RentalManager manager;
    private Fleet fleet;
    private Logger logger;
    private Scanner input;

    public Menu(RentalManager manager, Fleet fleet, Logger logger){
        this.manager = manager;
        this.fleet = fleet;
        this.logger = logger;
        input = new Scanner(System.in);
    }

    public void start(){
        System.out.println("Welcome to Rent-A-Car!");
    }
}
