package utilities;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Source Accessed: https://www.baeldung.com/java-csv-file-array
* Date: 12/3/2025
* */

public class ReadCSV {
    private String path;
    private static final String COMMA_DELIMITER = ",";

    public ReadCSV(String path){
        this.path = path;
    }

    public List<String[]> readAll() throws IOException {
        /*
        * Opens the file and reads each line
        * Splits each line by comma and stores in Array
        * Converts array to list and adds it to outer list
        * */
        List<String[]> records = new ArrayList<>(); // create a list that contains lists (inner list are for lines, outer is for all lines)
        try (BufferedReader br = new BufferedReader(new FileReader(path))) { // use BufferReader to read file
            String line;
            while((line = br.readLine()) != null){ // go to the end
                String[] values = line.split(COMMA_DELIMITER); // split values by comma to form array
                records.add(values);
            }
            // cleaning data
            for(String[] arr: records){ // iterate by rows (arrays)
                for(int i = 0; i < arr.length; i++){
                    if(arr[i] != null) {
                        arr[i] = arr[i].trim(); // remove whitespace
                    }
                }
            }
        } catch (FileNotFoundException e){
            System.out.println(e.getStackTrace());
        }
        return records;
    }
}
