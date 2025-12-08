package Tests;
import edu.augie.finalProgram.taye.Log.LogEntry;
import edu.augie.finalProgram.taye.Log.LogManager;
import edu.augie.finalProgram.taye.Log.LogType;

import java.time.LocalDateTime;

public class LogManagerTest {
    public static void main(String[] args) {
        LogManager logManager = new LogManager();

        // Create some log entries
        LogEntry entry1 = new LogEntry(LocalDateTime.of(2025, 12, 7, 10, 0, 0),
                "Car added to fleet", LogType.CAR_ADDED);
        LogEntry entry2 = new LogEntry(LocalDateTime.of(2025, 12, 7, 11, 30, 15),
                "Car removed from fleet", LogType.CAR_REMOVED);

        // Add entries to LogManager
        logManager.addEntry(entry1);
        logManager.addEntry(entry2);

        // Display logs
        System.out.println("All logs:");
        logManager.displayLogs();

        // Clear logs
        logManager.clearLogs();
        System.out.println("\nAfter clearing logs:");
        logManager.displayLogs(); // Should display nothing
    }
}
