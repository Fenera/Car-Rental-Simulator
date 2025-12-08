package Tests;

import edu.augie.finalProgram.taye.Log.ReadCSV;

import java.io.IOException;
import java.util.List;

public class ReadCSVTest {
    public static void main(String[] args) {
        // Replace this path with the path to your test CSV file
        String filePath = "test_vehicles.csv";

        ReadCSV reader = new ReadCSV(filePath);

        try {
            List<String[]> records = reader.readAll();

            // Print the records to verify
            System.out.println("CSV Contents:");
            for (int i = 0; i < records.size(); i++) {
                String[] row = records.get(i);
                System.out.print("Row " + i + ": ");
                for (String value : row) {
                    System.out.print(value + " | ");
                }
                System.out.println();
            }

            System.out.println("\nTotal rows read: " + records.size());
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
