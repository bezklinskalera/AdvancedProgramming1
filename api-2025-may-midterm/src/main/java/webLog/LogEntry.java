package webLog;

import java.util.Objects;

public class LogEntry {

    private String date;
    private String time;
    private String severity;
    private int statusCode;
    private String method;
    private String additionalInfo;


    public LogEntry(String date, String time, String severity, int statusCode, String method, String additionalInfo) {

        if (date == null || time == null || severity == null || method == null || additionalInfo == null) {
            throw new WebLogException();
        }

        this.date = date;
        this.time = time;
        this.severity = severity;
        this.statusCode = statusCode;
        this.method = method;
        this.additionalInfo = additionalInfo;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getSeverity() {
        return severity;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMethod() {
        return method;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LogEntry logEntry = (LogEntry) o;
        return statusCode == logEntry.statusCode && Objects.equals(date, logEntry.date) && Objects.equals(time, logEntry.time) && Objects.equals(severity, logEntry.severity) && Objects.equals(method, logEntry.method) && Objects.equals(additionalInfo, logEntry.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time, severity, statusCode, method, additionalInfo);
    }

    @Override
    public String toString() {
        return  date + " " +
                time + " " +
                severity + " " +
                "[" + statusCode +
                "] " + method + " /" +
                 additionalInfo;
    }
}
