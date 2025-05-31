
//--------------------------------------------------------------------------

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import webLog.LogEntry;
import webLog.WebLogException;

//--------------------------------------------------------------------------

public class TestLogEntry {
	@BeforeAll
	static public void beforeClass() {
		// Code executed before the first test method
		System.out.println("Start of LogEntry JUnit Test");
	}
	@AfterAll
	static public void afterClass() {
		// Code executed after the last test method
		System.out.println("End of LogEntry JUnit Test");
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void logEntryCtorTest1() {
		LogEntry entry = new LogEntry("2023-10-01", "12:00:00", "INFO", 200, "GET", "Sample info");
		assertAll("logEntryCtorTest1",
			() -> assertEquals("2023-10-01", entry.getDate(), "\n> Error: Date should be 2023-10-01"),
			() -> assertEquals("12:00:00", entry.getTime(), "\n> Error: Time should be 12:00:00"),
            () -> assertEquals("INFO", entry.getSeverity(), "\n> Error: Severity should be INFO"),
            () -> assertEquals(200, entry.getStatusCode(), "\n> Error: Status code should be 200"),
            () -> assertEquals("GET", entry.getMethod(), "\n> Error: Method should be GET"),
            () -> assertEquals("Sample info", entry.getAdditionalInfo(), "\n> Error: Additional info should be Sample info"),
			() -> assertTrue(entry.equals(new LogEntry("2023-10-01", "12:00:00", "INFO", 200, "GET", "Sample info")),
						"\n> Error: LogEntry should be equal to another LogEntry with the same values"),
			() -> assertFalse(
						entry.equals(new LogEntry("2023-10-02", "12:00:00", "INFO", 200, "GET", "Sample info")),
						"\n> Error: LogEntry should not be equal to another LogEntry with different date")
		);
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void logEntryCtorTestExcept1() {
		
		assertThrows(WebLogException.class, 
		             () -> new LogEntry(null, "12:00:00", "INFO", 200, "GET", "Sample info"), 
					 "\n> Error: Expected WeblogException when date is null");
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void logEntryCtorTestExcept2() {
		
		assertThrows(WebLogException.class, 
		             () -> new LogEntry("2023-10-01", "12:00:00", "INFO", 200, null, "Sample info"), 
					 "\n> Error: Expected WeblogException when http method is null");
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void logEntryCtorTestExcept3() {
		assertThrows(RuntimeException.class, 
		             () -> new LogEntry("2023-10-01", "12:00:00", "INFO", 200, "GET", null), 
					 "\n> Error: Expected RuntimeException when additional information is null");
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void logEntryCtorTestExcept4() {
		assertThrows(RuntimeException.class, 
		             () -> new LogEntry("2023-10-01", "12:00:00", null, 200, "GET", "Sample info"), 
					 "\n> Error: Expected RuntimeException when severity is null");
	}
}
