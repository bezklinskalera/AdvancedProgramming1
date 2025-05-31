




import webLog.LogEntry;
import webLog.LogManager;
import webLog.WebLogException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainWebLog {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println(System.getProperty("user.dir"));



        LogEntry logEntry1 = new LogEntry("2023-10-01", "12:00:00", "INFO", 200, "GET", "Sample info");
        System.out.println(logEntry1.getDate() + " " +
                logEntry1.getTime() + " " +
                logEntry1.getSeverity() + " " +
                "[" + logEntry1.getStatusCode() +
                "] " + logEntry1.getMethod() + " /" +
                logEntry1.getAdditionalInfo());

        try {
            LogEntry logEntry2 = new LogEntry(null, "12:00:00", "INFO", 200, "GET", "Sample info");
        }catch (WebLogException e){
            System.out.println("Not all parameters were passed");
        }

        try {
            LogEntry logEntry3 = new LogEntry("2023-10-01", "12:00:00", "INFO", 200, null, "Sample info");
        }catch (WebLogException e){
            System.out.println("Not all parameters were passed");
        }

        try {
            LogEntry logEntry4 = new LogEntry("2023-10-01", "12:00:00", "INFO", 200, "GET", null);
        }catch (WebLogException e){
            System.out.println("Not all parameters were passed");
        }

        LogManager logManager = new LogManager();

        try {
            logManager.readLodFile("log.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        try {
            System.out.println(logManager.readLodFile(
                    "D:\\test\\webaccesslog.txt"));

            System.out.println(logManager.logEntriesUpToDateTime("2025-05-10", "20:56:10"));

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        try {
            logManager.writeAdditionalInfo("additional_info.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }







    }

}

// Import requires packages and classes

// Define public class MainWebLog

// Define public main method

// Create a log entry object with sample data "2023-10-01", "12:00:00", "INFO", 200, "GET", "Sample info"
// Print the log entry details using the getter methods

// Try to create log entry objects with invalid data null, "12:00:00", "INFO", 200, "GET", "Sample info"
// Catch the WebLogException and print the error message

// Try to create log entry objects with invalid data null, "12:00:00", "INFO", 200, "GET", "Sample info"
// Catch the WebLogException and print the error message

// Try to create log entry objects with invalid data "2023-10-01", "12:00:00", "INFO", 200, null, "Sample info"
// Catch the WebLogException and print the error message

// Try to create log entry objects with invalid data "2023-10-01", "12:00:00", "INFO", 200, "GET", null
// Catch the WebLogException and print the error message

// Create a LogManager object
// Try to read the non existing log file "log.txt" and print the number of log entries read
// Catch the FileNotFoundException and print the error message

// Try to read the existing log file "webaccesslog.txt" and print the number of log entries read


// Try to write additional information to the file "additional_info.txt"
// Catch the FileNotFoundException and print the error message
