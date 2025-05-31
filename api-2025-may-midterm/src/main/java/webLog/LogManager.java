package webLog;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogManager {

    protected List<LogEntry> list;

    public LogManager() {
        this.list = new ArrayList<>();
    }

    public int readLodFile(String fileName) throws FileNotFoundException {
        int count = 0;
        Scanner sc = new Scanner(new File(fileName));

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+", 7);
            if (parts.length < 7) {
                continue; // skip malformed lines
            }

            String date = parts[0];
            String time = parts[1];
            String severity = parts[2];

            String statusCodeString = parts[3];
            if (!statusCodeString.startsWith("[") || !statusCodeString.endsWith("]")) {
                continue;
            }

            int statusCode = Integer.parseInt(statusCodeString.substring(1, statusCodeString.length() - 1));
            String method = parts[4];
            String additionalInfo = parts[6];

            LogEntry entry = new LogEntry(date, time, severity, statusCode, method, additionalInfo);
            list.add(entry);
            count++;
        }

        sc.close();
        return count;
    }


    public String logEntriesUpToDateTime(String date, String time) {

        StringBuilder sb = new StringBuilder();
        sb.append("{");


        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDate().compareTo(date) < 0 ||
                    (list.get(i).getDate().equals(date) && list.get(i).getTime().compareTo(time) < 0)) {
                        sb.append("/");
                        sb.append("(");
                        sb.append(list.get(i).getSeverity());
                        sb.append(";");
                        sb.append(list.get(i).getStatusCode());
                        sb.append(";");
                        sb.append(list.get(i).getMethod());
                        sb.append(")");

            }
        }
        sb.append("}");
        return sb.toString();
    }



    public void writeAdditionalInfo(String fileName) throws FileNotFoundException  {
        PrintWriter writer = new PrintWriter(fileName);

        for (LogEntry logEntry : list) {
            String additionalInfo = logEntry.getAdditionalInfo();
            if (additionalInfo.length() > 20){
            writer.println(additionalInfo.substring(0, 20));
            }else {
                writer.println(additionalInfo);
            }
        }
        writer.close();
    }



}



