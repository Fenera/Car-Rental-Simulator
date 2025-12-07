package edu.augie.finalProgram.taye.Utilities;

import edu.augie.finalProgram.taye.DataStructures.LinkedList;

import java.util.Iterator;


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


    public LinkedList<LogEntry> getAllLogs(){
        return entries;
    }

    public void displayLogs(){
        // use iterator to display the log
        for(Iterator<LogEntry> it = entries.items(); it.hasNext(); ){ // iterate through LL
            LogEntry l = it.next(); // grab log entry
            System.out.println(l); // LogEntry has custom toString()
        }
    }

    public void addEntry(LogEntry entry){
        entries.append(entry);
    }

    public void clearLogs(){
        entries = new LinkedList<>(); // assign entries to new LL object
    }

}
