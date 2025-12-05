package utilities;

import client.Client;
import datastructures.LinkedList;
import staff.Employee;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.sql.SQLOutput;
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

    public LinkedList<LogEntry> getAllLogs(){
        return entries;
    }

    public void clearLogs(){
        entries = new LinkedList<>(); // assign entries to new LL object
    }

    public void viewLogsByEmployee(int staffID){
        // Find logs with certain employee
        for(Iterator<LogEntry> it = entries.items(); it.hasNext(); ){ // iterate through LL
            LogEntry l = it.next(); // get log
            if(l.getEmployee().getStaffID() == staffID){ // employee match?
                System.out.println(l);
            }
        }
    }

    public void viewLogsByClient(int clientID){
        // Find logs with certain clients
        for(Iterator<LogEntry> it = entries.items(); it.hasNext(); ){ // iterate through LL
            LogEntry l = it.next(); // get log
            if(l.getCustomer().getClientID() == clientID){
                System.out.println(l);
            }
        }
    }




}
