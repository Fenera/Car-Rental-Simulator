package edu.augie.finalProgram.taye.Utilities;

import java.time.LocalDateTime;

// this is the update logger
// writes to a text file in Output folder (below main folder)
// write any update (car rented out, car returned, edu.augie.finalProgram.taye.Staff sign-in, edu.augie.finalProgram.taye.Client sign-in)



/*
* to do : format time in cleaner format and as String: i.e. 12/5/2025 12:43 pm
* Different types of log constructors (to log rentals, to log sign-ins, to log actions) -> different parameters
*
* */
public class LogEntry {
    /*
    * Attributes:
    * Title (i.e. New Rental, Check-in)
    * Message
    *Time
    * Who: Client or edu.augie.finalProgram.taye.Staff updated the log -> name, customerID, staffID, phone number
    * output file: .txt file
    * */

    private LocalDateTime time;
    private String message;
    private LogType type;

    public LogEntry(LocalDateTime time, String message, LogType type) {
        this.time = time;
        this.message = message;
        this.type = type;
    }

    // getter & setter methods

    public String getTime() {
        // converts LocalDateTime object to String time with correct format
        return String.format("%d/%d/%d @ %d:%d:%d", time.getMonth(), time.getDayOfMonth(), time.getYear(),
                time.getHour(), time.getMinute(), time.getSecond());
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "time=" + getTime() +
                ", message='" + message + '\'' +
                ", type=" + type +
                '}';
    }

}
