package fleet;
import datastructures.BinarySearchTree;
import datastructures.LinkedList;
import edu.augie.finalProgram.taye.Vehicles.*;
import utilities.ReadCSV;
import utilities.Logger;

import java.io.IOException;
import java.util.List;

// this stores all the cars
// get the cars the csv file (use the CSVLoader.java to get the values and format...)
// this is the engine -> BST is initialized here (get car, add car, remove car)
// more -> add updates to logger(car ... was checked out at 12:00 pm by ...)
// user should be able to search by specific criteria (i.e mpg > 20) -> return linkedlist of all cars that meet this criteria



public class Fleet {
    private BinarySearchTree<String, Vehicle> vehicleByVinBST; // this is to store all the cars on the fleet
    private LinkedList<Vehicle> availableVehicleLL; // this is to store all the cars that are available

    public Fleet(){
        this.vehicleByVinBST = new BinarySearchTree();
    }

    public void loadFromFile(String file) throws IOException {
        ReadCSV reader = new ReadCSV(file);
        List<String[]> vehicles = reader.readAll();

        for(String[] r: vehicles){ // iterate through the rows
            // get data from file and store in variables
            String manufacturer = r[1];
            String model = r[2];
            String engine = r[3];
            int horsepower = Integer.valueOf(r[4]);
            int topSpeedMPG = (int) (Integer.valueOf(r[5]) * 0.621371); // convert kmh to mph and convert to int
            double zeroSixty = Double.valueOf(r[6]);
            int priceMSRP = Integer.valueOf(r[7]);
            String powertrain = r[8];
            int numberOfSeats = Integer.valueOf(r[9]);
            String vehicleClass = r[10]; // SUV, Sedan..
            double fuelEconomy = Double.valueOf(r[11]);
            double gasTankSize = Double.valueOf(r[12]);
            int vinNumber = Integer.valueOf(r[13]);

            vehicleByVinBST.insert(Integer.valueOf(r[r.length - 1])); // VIN is the last column in the array

            /*
            * TODO:
            *  Add vehicles by vin to BST
            * Add Vehicle objects to LL
            * */
        }
    }

    public void getVehicleByVin(){

    }
    // or Manager object
    public void addNewVehicle(int managerID){

    }

    public void removeVehicleFromLot(int managerID) {

    }
}
