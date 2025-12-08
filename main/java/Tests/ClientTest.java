package Tests;

import edu.augie.finalProgram.taye.Client.Client;

public class ClientTest {
    public static void main(String[] args) {

        Client c = new Client("John", "555-555-5555", "john@mail.com", "2001 S Summit Ave");
        System.out.println("ID: " + c.getClientID());
        System.out.println("Name: " + c.getName());
        System.out.println("Phone: " + c.getPhoneNumber());
        System.out.println("Email: " + c.getEmail());
        System.out.println("Address: " + c.getAddress());
        c.setName("Jane Doe");
        c.setPhoneNumber("555-9876");
        c.setEmail("jane@mail.com");
        c.setAddress("456 Avenue");
        System.out.println("Updated Name: " + c.getName());
        System.out.println("Updated Phone: " + c.getPhoneNumber());
        System.out.println("Updated Email: " + c.getEmail());
        System.out.println("Updated Address: " + c.getAddress());

    }
}
