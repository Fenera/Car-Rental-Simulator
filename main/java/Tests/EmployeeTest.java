package Tests;

import edu.augie.finalProgram.taye.Staff.Employee;

public class EmployeeTest {
    public static void main(String[] args) {
        // Create an Employee object
        Employee emp = new Employee(
                "Jane Doe",
                "jane.doe@example.com",
                "555-9876",
                "456 Oak Street",
                101,
                true
        );

        // Print the employee info using getInformation()
        System.out.println("Employee Information:");
        System.out.println(emp.getInformation());

        // Print the employee using toString()
        System.out.println("Employee toString():");
        System.out.println(emp);

        // Test getter/setter for isActive
        System.out.println("Is Active? " + emp.isActive());
        emp.setActive(false);
        System.out.println("Is Active after setActive(false)? " + emp.isActive());
    }
}
