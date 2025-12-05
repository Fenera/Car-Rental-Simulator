package utilities;

import datastructures.LinkedList;

import java.io.FileNotFoundException;
import java.nio.file.Files;


/*
* getAllLogs()
* logEvent()
* printAllLogs()
* writeToFile()
* clearLogs()*/
public class LogManager {

    private LinkedList<LogEntry> entries;

    public LogManager(){
        entries = new LinkedList<>();
    }

    public void displayLog(){
        // use iterator to display the log
    }
}
