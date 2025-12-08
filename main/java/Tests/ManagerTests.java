package Tests;

import edu.augie.finalProgram.taye.Log.LogEntry;
import edu.augie.finalProgram.taye.Log.LogManager;
import edu.augie.finalProgram.taye.Log.LogType;
import edu.augie.finalProgram.taye.Staff.Employee;
import edu.augie.finalProgram.taye.Staff.Manager;

import java.time.LocalDateTime;

public class ManagerTests {
    public static void main(String[] args) {
        // Create a LogManager object
        LogManager logManager = new LogManager();

        // Create a Manager object
        Manager manager = new Manager(
                "Alice Smith",
                "alice.smith@example.com",
                "555-1234",
                "123 Main Street",
                200,
                logManager
        );

        // Display manager info
        System.out.println(manager.getInformation());

        // Create Employee objects
        Employee emp1 = new Employee("John Doe", "john@example.com", "555-5678", "456 Oak St", 101, true);
        Employee emp2 = new Employee("Jane Roe", "jane@example.com", "555-8765", "789 Pine St", 102, true);

        // Add employees to manager
        manager.addEmployee(emp1);
        manager.addEmployee(emp2);

        // Display all active employees
        System.out.println("All active employees:");
        manager.displayAllEmployees();

        // Check if specific employee exists
        System.out.println("Contains staffID 101? " + manager.containsStaffID(101));
        System.out.println("Contains staffID 999? " + manager.containsStaffID(999));

        // View info for a specific employee
        System.out.println("Viewing employee 102 info:");
        manager.viewEmployeeInfo(102);

        // Remove an employee
        manager.removeEmployee(101);
        System.out.println("After removing employee 101:");
        manager.displayAllEmployees();

        // Display log entries
        System.out.println("Log entries:");
        logManager.displayLogs();
    }
}
