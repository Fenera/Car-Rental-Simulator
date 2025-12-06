package staff;

import datastructures.LinkedList;
import utilities.ReadCSV;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Manager extends Staff {

    LinkedList<Employee> employeeLinkedList;

    public Manager(String name, String email, String phoneNumber, String address, int staffID) {
        super(name, email, phoneNumber, address, staffID);

        employeeLinkedList = new LinkedList<>();
    }

    public void loadEmployeeFromFile(String path) throws IOException {
        ReadCSV readCSV = new ReadCSV(path); // create ReadCSV object
        List<String[]> employees = readCSV.readAll(); // read in list of string arrays (rows of list)

        // iterate through list (same logic as method in Fleet)
        // get data and assign it to variables
        for(String[] rows: employees){
            int staffID = Integer.valueOf(rows[2]);
            String firstName = rows[3];
            String lastName = rows[4];
            String email = rows[5];
            String phoneNumber = rows[6];
            String address = rows[7];
            String name = firstName + " " + lastName;

            // create employee object and add it to employeeLinkedList
            // all employees in the list are initially active
            employeeLinkedList.append(new Employee(name, email, phoneNumber, address, staffID, true));
        }
    }

    public void displayAllEmployees(){
        // display the information of all employees
        // iterate through employee LL using Iterator object
        for(Iterator<Employee> it = employeeLinkedList.items(); it.hasNext(); ){
            Employee employee = it.next();
            System.out.println(employee); // Employee has custom toString()
        }
    }


    // displays the information of the employee
    public void viewEmployeeInfo(int staffID) {

        // iterate through employee LL
        for(Iterator<Employee> it = employeeLinkedList.items(); it.hasNext(); ){
            Employee e = it.next(); // get employee
            if(e.getStaffID() == staffID){ // check if the id matches the argument
                System.out.println(e); // print their information
            }
        }
    }

    public void viewPaymentLogs() {}

    @Override
    public String getInformation() {
        return "Manager Information:\n" +
                "ID: " + getStaffID() + "\n" +
                "Name: " + getName() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Phone: " + getPhoneNumber() + "\n" +
                "Address: " + getAddress() + "\n";
    }
}



