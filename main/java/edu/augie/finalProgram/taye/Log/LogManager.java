package edu.augie.finalProgram.taye.Log;

import edu.augie.finalProgram.taye.DataStructures.LinkedList;

import java.util.Iterator;

public class LogManager {

    // declare entries linked list
    private LinkedList<LogEntry> entries;

    // constructor
    public LogManager(){
        entries = new LinkedList<>();
    }

    // method to get all logs
    public LinkedList<LogEntry> getAllLogs(){
        return entries;
    }

    // same as above but prints logs
    public void displayLogs(){
        // use iterator to display the log
        for(Iterator<LogEntry> it = entries.items(); it.hasNext(); ){ // iterate through LL
            LogEntry l = it.next(); // grab log entry
            System.out.println(l); // LogEntry has custom toString()
        }
    }

    // adds new entry to log manager
    public void addEntry(LogEntry entry){
        entries.append(entry);
    }

    // clears logs
    public void clearLogs(){
        entries = new LinkedList<>(); // assign entries to new LL object
    }

}
