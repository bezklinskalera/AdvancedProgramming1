
//--------------------------------------------------------------------------

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import webLog.WebLogException;

//--------------------------------------------------------------------------

public class TestWebLogException {
	@BeforeAll
	static public void beforeClass() {
		// Code executed before the first test method
		System.out.println("Start of WebLogException JUnit Test");
	}
	@AfterAll
	static public void afterClass() {
		// Code executed after the last test method
		System.out.println("End of WebLogException JUnit Test");
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
		WebLogException le1 = new WebLogException();
		Assertions.assertAll("logEntryCtorTest1",
				() -> Assertions.assertTrue(le1 instanceof RuntimeException,
						"\n> Error: WebLogException should be an unchecked exception"),
				() -> Assertions.assertEquals(null, le1.getMessage(),
						"\n> Error: new WebLogException() message should be the empty string"));
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void logEntryCtorTest2() {
		WebLogException le1 = new WebLogException("Test message");
		Assertions.assertAll("logEntryCtorTest2",
				() -> Assertions.assertTrue(le1 instanceof RuntimeException,
						"\n> Error: WebLogException should be an unchecked exception"),
				() -> Assertions.assertEquals("Test message", le1.getMessage(),
						"\n> Error: new WebLogException(message) message should be equal to getMessage()"));
	}

}