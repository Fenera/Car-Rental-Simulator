package edu.augie.finalProgram.taye.client;

/*
* Client is the person renting the vehicle
* This class is used to create a edu.augie.finalProgram.taye.client object
* */
public class Client {
    // edu.augie.finalProgram.taye.client id is 999 so when a edu.augie.finalProgram.taye.client is made the id starts at 1000
    private static int clientID = 999;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;

    // constructor
    public Client(String name, String phoneNumber, String email, String address) {
        clientID ++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    // getter and setter methods
    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
