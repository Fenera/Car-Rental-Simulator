package edu.augie.finalProgram.taye.staff;

import edu.augie.finalProgram.taye.datastructures.LinkedList;
import edu.augie.finalProgram.taye.utilities.ReadCSV;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Manager extends Staff {

    // instantiate linked lists
    LinkedList<Employee> activeEmployeeLinkedList;
    LinkedList<Employee> allTimeEmployeeLinkedList;


    // constructor
    public Manager(String name, String email, String phoneNumber, String address, int staffID) {
        super(name, email, phoneNumber, address, staffID);

        // instantiate LLs
        activeEmployeeLinkedList = new LinkedList<>();
        allTimeEmployeeLinkedList = new LinkedList<>();
    }

    // similar logic to loadCarsFromFile() in Fleet class
    public void loadEmployeeFromFile(String path) throws IOException {
        ReadCSV readCSV = new ReadCSV(path); // create ReadCSV object
        List<String[]> employees = readCSV.readAll(); // read in list of string arrays (rows of list)

        // iterate through list (same logic as method in Fleet)
        // get data and assign it to variables
        for(int i = 1; i < employees.size(); i ++){ // start at i = 1 to skip title row
            String[] rows = employees.get(i); // get the rows as a string arrays

            String temp = rows[0].trim().replaceAll("^[0-9]\"", "");
            int staffID = Integer.valueOf(temp);
            String firstName = rows[1];
            String lastName = rows[2];
            String email = rows[3];
            String phoneNumber = rows[4];
            String address = rows[5];
            String name = firstName + " " + lastName;

            // create employee object and add it to employeeLinkedList
            // all employees in the list are initially active

            Employee employee = new Employee(name, email, phoneNumber, address, staffID, true);
            activeEmployeeLinkedList.append(employee);
            allTimeEmployeeLinkedList.append(employee);

            // print statements below are for testing purposes

            //System.out.println("Added Employee: " + employee.getInformation());
        }

        //System.out.println("Finished loading " + activeEmployeeLinkedList.getLength() + " employees");
    }

    public void displayAllEmployees(){
        // display the information of all employees
        // iterate through employee LL using Iterator object
        for(Iterator<Employee> it = activeEmployeeLinkedList.items(); it.hasNext(); ){
            Employee employee = it.next();
            System.out.println(employee.getInformation()); // Employee has custom toString()
        }
    }


    // displays the information of the employee
    public void viewEmployeeInfo(int staffID) {

        // iterate through employee LL
        for(Iterator<Employee> it = activeEmployeeLinkedList.items(); it.hasNext(); ){
            Employee e = it.next(); // get employee
            if(e.getStaffID() == staffID){ // check if the id matches the argument
                System.out.println(e.getInformation()); // print their information
            }
        }
    }

    public Employee getEmployee(int staffID) {

        // iterate through employee LL
        for(Iterator<Employee> it = activeEmployeeLinkedList.items(); it.hasNext(); ){
            Employee e = it.next(); // get employee
            if(e.getStaffID() == staffID){ // check if the id matches the argument
                return e;
            }
        }

        return null; // no employee found with that id
    }

    public boolean containsStaffID(int staffID){
        // see's if linked list contains an employee with that id
        boolean contains = false;

        for(Iterator<Employee> it = activeEmployeeLinkedList.items(); it.hasNext(); ){
            Employee e = it.next(); // get employee
            if(e.getStaffID() == staffID){ // check if the id matches the argument
                contains = true;
            }
        }

        return contains;
    }

    // adds employee to LL
    public void addEmployee(Employee employee){
        activeEmployeeLinkedList.append(employee);
    }

    // removes employee from LL
    public void removeEmployee(int staffID){
        Employee employee = getEmployee(staffID);
        activeEmployeeLinkedList.delete(employee);
    }

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



