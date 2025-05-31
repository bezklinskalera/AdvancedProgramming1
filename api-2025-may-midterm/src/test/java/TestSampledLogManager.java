import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import webLog.LogManager;
import webLog.SampledLogManager;

public class TestSampledLogManager {
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
	public void logManagerReadFileException() {
		assertThrows(FileNotFoundException.class, () -> new LogManager().readLogFile("nonexistentfile.txt"),
				"\n> Error: Expected FileNotFoundException when file does not exist");
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void logManagerReadFile() {
		SampledLogManager logManager = new SampledLogManager();
		try {
			assertEquals(28, logManager.readLogFile("webaccesslog.txt"), "\n> Error: Expected 28 log entries");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void logManagerEntriesUpToDateTime() {
		SampledLogManager logManager = new SampledLogManager();
		try {
			logManager.readLogFile("webaccesslog.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("{(INFO;200;GET)/(INFO;200;GET)/(WARNING;404;GET)/(INFO;200;GET)}", logManager.logEntriesUpToDateTime("2025-05-10", "20:57:10")
				, "\n> Error: Expected {(INFO;200;GET)/(INFO;200;GET)/(WARNING;404;GET)/(INFO;200;GET)} as log entries prior to \"2025-05-10\", \"20:57:10\"");
	}

}
