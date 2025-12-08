package Tests;

import edu.augie.finalProgram.taye.Log.LogEntry;
import edu.augie.finalProgram.taye.Log.LogType;

import java.time.LocalDateTime;

public class LogEntryTest {
    public static void main(String[] args) {
        // Create a log entry
        LocalDateTime now = LocalDateTime.of(2025, 12, 7, 14, 30, 45);
        LogEntry entry = new LogEntry(now, "Test log message", LogType.CAR_ADDED);

        // Test getters
        System.out.println("Time: " + entry.getTime());
        System.out.println("Message: " + entry.getMessage());
        System.out.println("Type: " + entry.getType());

        // Test setters
        entry.setMessage("Updated message");
        entry.setType(LogType.CAR_REMOVED);
        entry.setTime(LocalDateTime.of(2025, 12, 8, 9, 15, 0));

        System.out.println("\nAfter updating:");
        System.out.println("Time: " + entry.getTime());
        System.out.println("Message: " + entry.getMessage());
        System.out.println("Type: " + entry.getType());

        // Test toString()
        System.out.println("\nLogEntry toString():");
        System.out.println(entry);
    }
}
