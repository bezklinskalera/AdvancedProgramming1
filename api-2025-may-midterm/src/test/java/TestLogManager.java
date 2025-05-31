import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import webLog.LogManager;

public class TestLogManager {
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
		LogManager logManager = new LogManager();
		try {
			assertEquals(41, logManager.readLogFile("webaccesslog.txt"), "\n> Error: Expected 41 log entries");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void logManagerEntriesUpToDateTime() {
		LogManager logManager = new LogManager();
		try {
			logManager.readLogFile("webaccesslog.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("{(INFO;200;GET)/(INFO;200;GET)/(WARNING;404;GET)}", logManager.logEntriesUpToDateTime("2025-05-10", "20:56:10")
				, "\n> Error: Expected {(INFO;200;GET)/(INFO;200;GET)/(WARNING;404;GET)} as log entries prior to \"2025-05-10\", \"20:56:10\"");
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void logManagerWriteAdditional() {
		String expectedOutput = "/home.html from IP 192.168.1.10 - Request successful.\n"
				+ "/about.html from IP 192.168.1.11 - Request successful.\n"
				+ "/missing.html from IP 192.168.1.12 - Page not found.\n"
				+ "/process-data from IP 192.168.1.13 - Internal server error.\n"
				+ "/services.html from IP 192.168.1.14 - Request successful.\n"
				+ "/admin-panel from IP 192.168.1.15 - Access forbidden.\n"
				+ "/redirect from IP 192.168.1.16 - Temporary redirection.\n"
				+ "/contact.html from IP 192.168.1.17 - Request successful.\n"
				+ "/unknown-page.html from IP 192.168.1.18 - Page not found.\n"
				+ "/new-user from IP 192.168.1.19 - New resource created.\n"
				+ "/error-page from IP 192.168.1.20 - Internal server error.\n"
				+ "/dashboard.html from IP 192.168.1.21 - Request successful.\n"
				+ "/restricted-content from IP 192.168.1.22 - Access forbidden.\n"
				+ "/cached-content from IP 192.168.1.23 - Not modified.\n"
				+ "/temporary-redirect from IP 192.168.1.24 - Temporary redirection.\n"
				+ "/submit-form from IP 192.168.1.25 - Internal server error.\n"
				+ "/profile.html from IP 192.168.1.26 - Request successful.\n"
				+ "/non-existent.html from IP 192.168.1.27 - Page not found.\n"
				+ "/settings.html from IP 192.168.1.28 - Request successful.\n"
				+ "/forwarded-page from IP 192.168.1.29 - Temporary redirection.\n"
				+ "/help.html from IP 192.168.1.30 - Request successful.\n"
				+ "/private-data from IP 192.168.1.31 - Unauthorized access attempt.\n"
				+ "/upload-file from IP 192.168.1.32 - Internal server error.\n"
				+ "/news.html from IP 192.168.1.33 - Request successful.\n"
				+ "/error-page from IP 192.168.1.34 - Page not found.\n"
				+ "/test-redirect from IP 192.168.1.35 - Temporary redirection.\n"
				+ "/forum.html from IP 192.168.1.36 - Request successful.\n"
				+ "/update-profile from IP 192.168.1.37 - Internal server error.\n"
				+ "/admin-settings from IP 192.168.1.38 - Access forbidden.\n"
				+ "/create-post from IP 192.168.1.39 - New resource created.\n"
				+ "/auto-forward from IP 192.168.1.40 - Temporary redirection.\n"
				+ "/blog.html from IP 192.168.1.41 - Request successful.\n"
				+ "/login from IP 192.168.1.42 - Internal server error.\n"
				+ "/lost-page from IP 192.168.1.43 - Page not found.\n"
				+ "/page-redirect from IP 192.168.1.44 - Temporary redirection.\n"
				+ "/resources.html from IP 192.168.1.45 - Request successful.\n"
				+ "/restricted-section from IP 192.168.1.46 - Access forbidden.\n"
				+ "/unchanged-content from IP 192.168.1.47 - Not modified.\n"
				+ "/submit-comment from IP 192.168.1.48 - Internal server error.\n"
				+ "/search.html from IP 192.168.1.49 - Request successful.\n"
				+ "/forwarded-request from IP 192.168.1.50 - Temporary redirection.\n";
		LogManager logManager = new LogManager();
        try {
            logManager.readLogFile("webaccesslog.txt");
            logManager.writeAdditionalInfo("additional_info.txt");
            String str = Files.readString(Path.of("additional_info.txt"));
            // System.out.println(str);
            assertEquals(expectedOutput, str, "\n> Error: Expected additional information to match the expected output");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
			try {
				Files.deleteIfExists(Path.of("additional_info.txt"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }

}
