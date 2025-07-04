
//--------------------------------------------------------------------------
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import grading.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

//--------------------------------------------------------------------------

public class TestRunnerPr41Ex1{
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestStudentException {
		private StudentException se1;
		@BeforeAll
		public void beforeAll() {
			// Code executed before the first test method
			System.out.println("Start of StudentException JUnit Test");
		}
		@AfterAll
		public void afterAll() {
			// Code executed after the last test method
			System.out.println("End of StudentException JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			se1 = new StudentException("Error Message 1");
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void alumnoExceptionCtorTest1() {
			assertAll("alumnoExceptionCtorTest1",
					() -> assertTrue(((Object)se1 instanceof Exception), "\n> Error: StudentException extends Exception:"),
					() -> assertFalse(((Object)se1 instanceof RuntimeException), "\n> Error: StudentException extends RuntimeException:"),
					() -> assertEquals("Error Message 1", se1.getMessage(), "\n> Error: new StudentException(\"Error Message 1\"): getMessage():"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void alumnoExceptionCtorTest2() {
			StudentException ae2 = new StudentException();
			assertAll("alumnoExceptionCtorTest2",
					() -> assertTrue(((Object)ae2 instanceof Exception), "\n> Error: StudentException extends Exception:"),
					() -> assertFalse(((Object)ae2 instanceof RuntimeException), "\n> Error: StudentException extends RuntimeException:"),
					() -> assertEquals(null, ae2.getMessage(), "\n> Error: new StudentException(): getMessage():"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void alumnoExceptionToStringTest1() {
			precond("Error Message 1", se1.getMessage());
			assertEquals(normalize("grading.StudentException : Error Message 1"),
						 normalize(se1.toString()),
						 "\n> Error: ae1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestStudent {
		private Student an1;
		@BeforeAll
		public void beforeAll() {
			// Code executed before the first test method
			System.out.println("Start of Student JUnit Test");
		}
		@AfterAll
		public void afterAll() {
			// Code executed after the last test method
			System.out.println("End of Student JUnit Test");
		}
		@BeforeEach
		public void setUp() throws Exception {
			// Code executed before each test
			an1 = new Student("22456784F", "Gonzalez Perez, Juan", 5.5);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void alumnoCtorTest1() {
			assertAll("alumnoCtorTest1",
					() -> assertEquals("22456784F", an1.getDni(), "\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\", 5.5): DNI:"),
					() -> assertEquals("Gonzalez Perez, Juan", an1.getName(), "\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\", 5.5): Name:"),
					() -> assertEquals(5.5, an1.getGrade(), 1e-6));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void alumnoCtorTest2() throws Exception {
			Student an2 = new Student("22456784F", "Gonzalez Perez, Juan");
			assertAll("alumnoCtorTest2",
					() -> assertEquals("22456784F", an2.getDni(),"\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\"): DNI:"),
					() -> assertEquals("Gonzalez Perez, Juan", an2.getName(), "\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\"): Name:"),
					() -> assertEquals(0.0, an2.getGrade(), 1e-6, "\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\"): Calificacion:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void alumnoCtorTestX1() {
			Exception exception = assertThrows(StudentException.class, () -> new Student("22456784F", "Gonzalez Perez, Juan", -1.0));
			assertEquals(normalize("Negative grade"), normalize(exception.getMessage()), "\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\", -1.0): exception.getMessage():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void alumnoEqualsTest1() throws Exception {
			precond("22456784F", an1.getDni());
			precond("Gonzalez Perez, Juan", an1.getName());
			precond(5.5, an1.getGrade(), 1e-6);
			//------------------------
			Student an2 = new Student("22456784F", "Gonzalez Perez, Juan", 7.7);
			precond("22456784F", an2.getDni());
			precond("Gonzalez Perez, Juan", an2.getName());
			precond(7.7, an2.getGrade(), 1e-6);
			assertTrue(an1.equals(an2), "\n> Error: an1.equals(an2): ");
			//------------------------
			Student an3 = new Student("22456784f", "Gonzalez Perez, Juan", 7.7);
			precond("22456784f", an3.getDni());
			precond("Gonzalez Perez, Juan", an3.getName());
			precond(7.7, an3.getGrade(), 1e-6);
			assertTrue(an1.equals(an3), "\n> Error: an1.equals(an3): ");
			//------------------------
			Student an4 = new Student("22456784F", "gonzalez perez, juan", 5.5);
			precond("22456784F", an4.getDni());
			precond("gonzalez perez, juan", an4.getName());
			precond(5.5, an4.getGrade(), 1e-6);
			assertFalse(an1.equals(an4), "\n> Error: an1.equals(an4): ");
			//------------------------
			Student an5 = new Student("11111111A", "Gonzalez Perez, Juan", 5.5);
			precond("11111111A", an5.getDni());
			precond("Gonzalez Perez, Juan", an5.getName());
			precond(5.5, an5.getGrade(), 1e-6);
			assertFalse(an1.equals(an5), "\n> Error: an1.equals(an5): ");
			//------------------------
			assertFalse(an1.equals(null), "\n> Error: an1.equals(null): ");
			assertFalse(an1.equals("This is a String"), "\n> Error: an1.equals(\"This is a String\"): ");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void alumnoHashCodeTest1() throws Exception {
			precond("22456784F", an1.getDni());
			precond("Gonzalez Perez, Juan", an1.getName());
			precond(5.5, an1.getGrade(), 1e-6);
			int an1HashCode = an1.hashCode();
			//------------------------
			Student an2 = new Student("22456784F", "Gonzalez Perez, Juan", 7.7);
			precond("22456784F", an2.getDni());
			precond("Gonzalez Perez, Juan", an2.getName());
			precond(7.7, an2.getGrade(), 1e-6);
			assertEquals(an1HashCode, an2.hashCode(), "\n> Error: an2.hashCode(): ");
			//------------------------
			Student an3 = new Student("22456784f", "Gonzalez Perez, Juan", 7.7);
			precond("22456784f", an3.getDni());
			precond("Gonzalez Perez, Juan", an3.getName());
			precond(7.7, an3.getGrade(), 1e-6);
			assertEquals(an1HashCode, an3.hashCode(), "\n> Error: an3.hashCode(): ");
			//------------------------
			Student an4 = new Student("22456784F", "gonzalez perez, juan", 5.5);
			precond("22456784F", an4.getDni());
			precond("gonzalez perez, juan", an4.getName());
			precond(5.5, an4.getGrade(), 1e-6);
			assertNotEquals(an1HashCode, an4.hashCode(), "\n> Error: an4.hashCode(): ");
			//------------------------
			Student an5 = new Student("11111111A", "Gonzalez Perez, Juan", 5.5);
			precond("11111111A", an5.getDni());
			precond("Gonzalez Perez, Juan", an5.getName());
			precond(5.5, an5.getGrade(), 1e-6);
			assertNotEquals(an1HashCode, an5.hashCode(), "\n> Error: an5.hashCode(): ");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void alumnoToStringTest1() throws StudentException {
			precond("22456784F", an1.getDni());
			precond("Gonzalez Perez, Juan", an1.getName());
			precond(5.5, an1.getGrade(), 1e-6);
			assertEquals(normalize("Gonzalez Perez, Juan (22456784F)"),
						 normalize(an1.toString()),
						 "\n> Error: an1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	private static final String nmAsg = "Algebra";
	private static final String[] inputData = {
		"25653443S;Garcia Gomez, Juan;8.1",
		"23322443K;Lopez Turo, Manuel;4.3",
		"24433522M;Merlo Martinez, Juana;5.3",
		"53553421D;Santana Medina, Petra;-7.1",
		"55343442L,Godoy Molina, Marina;6.3",
		"34242432J;Fernandez Vara, Pedro;2.k",
		"42424312G;Lopez Gama, Luisa;7.1"
	};
	private static final java.util.List<Student> inputValues = createStudents();
	private static final java.util.List<String> inputErrors = createErrors();
	private static java.util.List<Student> createStudents() {
		ArrayList<Student> alumnos = new ArrayList<>();
		try {
			alumnos.add(new Student("25653443S", "Garcia Gomez, Juan", 8.1));
			alumnos.add(new Student("23322443K", "Lopez Turo, Manuel", 4.3));
			alumnos.add(new Student("24433522M", "Merlo Martinez, Juana", 5.3));
			alumnos.add(new Student("42424312G", "Lopez Gama, Luisa", 7.1));
		} catch (Exception e) {
			fail("\n> Error: createStudents: unexpected exception thrown");
		}
		return alumnos;
	}
	private static java.util.List<String> createErrors() {
		ArrayList<String> errores = new ArrayList<>();
		try {
			errores.add("ERROR. Negative grade: 53553421D;Santana Medina, Petra;-7.1");
			errores.add("ERROR. Missing data: 55343442L,Godoy Molina, Marina;6.3");
			errores.add("ERROR. Non numerical grade: 34242432J;Fernandez Vara, Pedro;2.k");
		} catch (Exception e) {
			fail("\n> Error: createErrors: unexpected exception thrown");
		}
		return errores;
	}
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestSubject {
		private Subject sbj1;
		@BeforeAll
		public void beforeAll() {
			// Code executed before the first test method
			System.out.println("Start of Subject JUnit Test");
		}
		@AfterAll
		public void afterAll() {
			// Code executed after the last test method
			System.out.println("End of Subject JUnit Test");
		}
		@BeforeEach
		public void setUp() throws StudentException {
			// Code executed before each test
			sbj1 = new Subject(nmAsg, inputData);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void subjectCtorTest1() {
			if (inputValues.equals(sbj1.getStudents())) {
				System.out.println("Igualess");
			} else {
				System.out.println("Distintoss");
			}
			assertEquals(inputValues, sbj1.getStudents(), "\n> Error: new Subject(): sbj1.getStudents():");
			assertEqualsNorm("\n> Error: new Subject(): sbj1.getErrors():", inputErrors, sbj1.getErrors());
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void subjectCalcAverageTest1() throws Exception {
			assertEquals(6.20, sbj1.getAverage(), 1e-6, "\n> Error: sbj1.getAverage():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void subjectCalcAverageTest2() throws StudentException {
			String[] data = { "xxx" };
			Subject asg2 = new Subject(nmAsg, data);
			Exception exception = assertThrows(StudentException.class,() -> asg2.getAverage());
			assertEquals(normalize("No students"), normalize(exception.getMessage()));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void subjectGetGradeTest1() throws Exception {
			assertAll("subjectGetGradeTest1",
					() -> assertEquals(8.1, sbj1.getGrade(inputValues.get(0)), 1e-6, "\n> Error: sbj1.getGrade(Garcia Gomez, Juan):"),
					() -> assertEquals(4.3, sbj1.getGrade(inputValues.get(1)), 1e-6, "\n> Error: sbj1.getGrade(Lopez Turo, Manuel):"),
					() -> assertEquals(5.3, sbj1.getGrade(inputValues.get(2)), 1e-6, "\n> Error: sbj1.getGrade(Merlo Martinez, Juana):"),
					() -> assertEquals(7.1, sbj1.getGrade(inputValues.get(3)), 1e-6, "\n> Error: sbj1.getGrade(Lopez Gama, Luisa):"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void subjectGetGradeTest2() {
			Exception exception = assertThrows(StudentException.class, 
					() -> {
						    Student an2 = new Student("34242432J", "Fernandez Vara, Pedro");
							sbj1.getGrade(an2);
						  });
			assertEquals("Student Fernandez Vara, Pedro (34242432J) has not been found", exception.getMessage());
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void subjectToStringTest1() {
			assertEquals(normalize("Algebra: { [Garcia Gomez, Juan (25653443S), Lopez Turo, Manuel (23322443K), Merlo Martinez, Juana (24433522M), Lopez Gama, Luisa (42424312G)], [ERROR. Negative grade: 53553421D;Santana Medina, Petra;-7.1, ERROR. Missing data: 55343442L,Godoy Molina, Marina;6.3, ERROR. Non numerical grade: 34242432J;Fernandez Vara, Pedro;2.k] }"),
						 normalize(sbj1.toString()),
						 "\n> Error: asgsbj1String():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestMainStudent {
		@BeforeAll
		public void beforeAll() {
			// Code executed before the first test method
			System.out.println("Start of MainStudent JUnit Test");
		}
		@AfterAll
		public void afterAll() {
			// Code executed after the last test method
			System.out.println("End of MainStudent JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void pruebaStudentMainTest1() {
			String output = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				MainStudent.main(new String[]{});
			} finally {
				output = sysOutCapture.sysOutRelease();
			}
			// remove StackTrace from output, as it may vary among environment and version
			int idx = output.indexOf("at ");
			if (idx >= 0) {
				output = output.substring(0, idx);
			}
			assertEquals(normalize("DNI : 22456784 F , Name : Gonzalez Perez , Juan , Grade : 5.5 DNI : 33456777 S , Name : Gonzalez Perez , Juan , Grade : 3.4 Students Gonzalez Perez , Juan ( 22456784 F ) and Gonzalez Perez , Juan ( 33456777 S ) are not equal grading . StudentException : Negative grade"),
						 normalize(output),
						 "\n> Error: MainStudent.main():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestMainSubject {
		@BeforeAll
		public void beforeAll() {
			// Code executed before the first test method
			System.out.println("Start of MainSubject JUnit Test");
		}
		@AfterAll
		public void afterAll() {
			// Code executed after the last test method
			System.out.println("End of MainSubject JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void pruebaSubjectMainTest1() {
			String salida = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				MainSubject.main(new String[]{});
			} finally {
				salida = sysOutCapture.sysOutRelease();
			}
			assertEquals(normalize("Grade of Lopez Turo , Manuel ( 23322443 k ) : 4.3 Student Fernandez Vara , Pedro ( 34242432 J ) has not been found Average 6.20 Students . . . Garcia Gomez , Juan ( 25653443 S ) : 8.1 Lopez Turo , Manuel ( 23322443 K ) : 4.3 Merlo Martinez , Juana ( 24433522 M ) : 5.3 Lopez Gama , Luisa ( 42424312 G ) : 7.1 Errors . . . ERROR . Negative grade : 53553421 D ; Santana Medina , Petra ; -7.1 ERROR . Missing data : 55343442 L , Godoy Molina , Marina ; 6.3 ERROR . Non numerical grade : 34242432 J ; Fernandez Vara , Pedro ; 2 . k Subject . . . Algebra : { [ Garcia Gomez , Juan ( 25653443 S ) , Lopez Turo , Manuel ( 23322443 K ) , Merlo Martinez , Juana ( 24433522 M ) , Lopez Gama , Luisa ( 42424312 G ) ] , [ ERROR . Negative grade : 53553421 D ; Santana Medina , Petra ; -7.1 , ERROR . Missing data : 55343442 L , Godoy Molina , Marina ; 6.3 , ERROR . Non numerical grade : 34242432 J ; Fernandez Vara , Pedro ; 2 . k ] }"),
						 normalize(salida),
						 "\n> Error: MainSubject.main():");
		} 
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------
	@Suite
	@SelectClasses({ JUnitTestStudentException.class ,
				JUnitTestStudent.class ,
				JUnitTestSubject.class ,
				JUnitTestMainStudent.class ,
				JUnitTestMainSubject.class
				})
				public static class JUnitTestSuite { /*empty*/ }
	//----------------------------------------------------------------------
	//--TestRunner-----------------------------------------------------
	//----------------------------------------------------------------------
	public static void main(String[] args) {
		final LauncherDiscoveryRequest request = 
				LauncherDiscoveryRequestBuilder.request()
				.selectors(
						selectClass(JUnitTestStudentException.class),
						selectClass(JUnitTestStudent.class),
						selectClass(JUnitTestSubject.class),
						selectClass(JUnitTestMainStudent.class),
						selectClass(JUnitTestMainSubject.class))
				.build();

		final Launcher launcher = LauncherFactory.create();
		final SummaryGeneratingListener listener = new SummaryGeneratingListener();

		launcher.registerTestExecutionListeners(listener);
		launcher.execute(request);

		TestExecutionSummary summary = listener.getSummary();

//		summary.printTo(new PrintWriter(System.out, true));

	    		long abortedCount = summary.getTestsAbortedCount();
	    		long succeededCount = summary.getTestsFoundCount();
	    		long foundCount = summary.getTestsSucceededCount();
	    		long skippedCount = summary.getTestsSkippedCount();
	    		long failedCount = summary.getTestsFailedCount();
	    		long startedCount = summary.getTestsStartedCount();
	    		if (failedCount > 0) {
	    			System.out.println(">>> ------");
	    			summary.getFailures().forEach(failure -> System.out.println("failure - " + failure.getException()));
	    		}
	    		if ((abortedCount > 0)||(failedCount > 0)||(skippedCount > 0)) {
	    			System.out.println(">>> ------");
	    			if (skippedCount > 0) {
	    				System.out.println(">>> Error: Some tests ("+skippedCount+") were ignored");
	    			}
	    			if (abortedCount > 0) {
	    				System.out.println(">>> Error: ("+abortedCount+") tests could not be run due to errors in other methods");
	    			}
	    			if (failedCount > 0) {
	    				System.out.println(">>> Error: ("+failedCount+") tests failed due to errors in methods");
	    			}
	    		}
	    		if (succeededCount == foundCount) {
	    			System.out.print(">>> JUnit Test Succeeded");
	    		} else {
	    			System.out.print(">>> Error: JUnit Test Failed");
	    		}
	    		System.out.println(" (Tests: "+foundCount+", Errors: "+failedCount+", ErrorPrecond: "+abortedCount+", Ignored: "+skippedCount+")");

//	    public static Result result = null;
//		result = JUnitCore.runClasses(JUnitTestSuite.class);
//		int rc = result.getRunCount();
//		int fc = result.getFailureCount();
//		int ac = 0;//result.getAssumptionFailureCount();
//		int ic = result.getIgnoreCount();
//		if (fc > 0) {
//			System.out.println(">>> ------");
//		}
//		for (Failure failure : result.getFailures()) {
//			System.out.println(failure.toString());
//		}
//		if ((ac > 0)||(fc > 0)) {
//			System.out.println(">>> ------");
//			if (ac > 0) {
//				System.out.println(">>> Error: " + ac + "tests could not be executed due to errors in other methods");
//			}
//			if (fc > 0) {
//				System.out.println(">>> Error: "+fc+" tests failed due to errors in methods");
//			}
//		}
//		if (result.wasSuccessful()) {
//			System.out.print(">>> JUnit Test Succeeded");
//		} else {
//			System.out.print(">>> Error: JUnit Test Failed");
//		}
//		System.out.println(" ("+rc+", "+fc+", "+ac+", "+ic+")");
	}
	//----------------------------------------------------------------------
	//-- Utils -------------------------------------------------------------
	//----------------------------------------------------------------------
	private static char normalizeUnicode(char ch) {
		switch (ch) {
		case '\n':
		case '\r':
		case '\t':
		case '\f':
			ch = ' ';
			break;
		case '\u20AC':
			ch = 'E';
			break;
		case '\u00A1':
			ch = '!';
			break;
		case '\u00AA':
			ch = 'a';
			break;
		case '\u00BA':
			ch = 'o';
			break;
		case '\u00BF':
			ch = '?';
			break;
		case '\u00C0':
		case '\u00C1':
		case '\u00C2':
		case '\u00C3':
		case '\u00C4':
		case '\u00C5':
		case '\u00C6':
			ch = 'A';
			break;
		case '\u00C7':
			ch = 'C';
			break;
		case '\u00C8':
		case '\u00C9':
		case '\u00CA':
		case '\u00CB':
			ch = 'E';
			break;
		case '\u00CC':
		case '\u00CD':
		case '\u00CE':
		case '\u00CF':
			ch = 'I';
			break;
		case '\u00D0':
			ch = 'D';
			break;
		case '\u00D1':
			ch = 'N';
			break;
		case '\u00D2':
		case '\u00D3':
		case '\u00D4':
		case '\u00D5':
		case '\u00D6':
			ch = 'O';
			break;
		case '\u00D9':
		case '\u00DA':
		case '\u00DB':
		case '\u00DC':
			ch = 'U';
			break;
		case '\u00DD':
			ch = 'Y';
			break;
		case '\u00E0':
		case '\u00E1':
		case '\u00E2':
		case '\u00E3':
		case '\u00E4':
		case '\u00E5':
		case '\u00E6':
			ch = 'a';
			break;
		case '\u00E7':
			ch = 'c';
			break;
		case '\u00E8':
		case '\u00E9':
		case '\u00EA':
		case '\u00EB':
			ch = 'e';
			break;
		case '\u00EC':
		case '\u00ED':
		case '\u00EE':
		case '\u00EF':
			ch = 'i';
			break;
		case '\u00F0':
			ch = 'd';
			break;
		case '\u00F1':
			ch = 'n';
			break;
		case '\u00F2':
		case '\u00F3':
		case '\u00F4':
		case '\u00F5':
		case '\u00F6':
			ch = 'o';
			break;
		case '\u00F9':
		case '\u00FA':
		case '\u00FB':
		case '\u00FC':
			ch = 'u';
			break;
		case '\u00FD':
		case '\u00FF':
			ch = 'y';
			break;
		}
		return ch;
	}
    //------------------------------------------------------------------
    //private static java.util.regex.Pattern float_pattern = java.util.regex.Pattern.compile("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)([eE][+-]?[0-9]+)?");
    private static java.util.regex.Pattern float_pattern = java.util.regex.Pattern.compile("[+-]?(([0-9]+[.][0-9]+([eE][+-]?[0-9]+)?)|([0-9]+[eE][+-]?[0-9]+))");
	private static String normalize_real_numbers(CharSequence csq) {
		String res = "";
		try {
			StringBuilder sbres = new StringBuilder(csq.length());
			java.util.regex.Matcher matcher = float_pattern.matcher(csq);
			int idx = 0;
			while (matcher.find()) {
				int inicio = matcher.start();
				int fin = matcher.end();
				String num1 = csq.subSequence(inicio, fin).toString();
				String formato = "%.6f";
				if (num1.contains("e") || num1.contains("E")) {
					formato = "%.6e";
				}
				double num2 = Double.parseDouble(num1);
				String num3 = String.format(java.util.Locale.UK, formato, num2);
				sbres.append(csq.subSequence(idx, inicio));
				sbres.append(num3);
				idx = fin;
			}
			sbres.append(csq.subSequence(idx, csq.length()));
			res = sbres.toString();
		} catch (Throwable e) {
			res = csq.toString();
		}
		return res;
	}
	//----------------------------------------------------------------------
	private static String normalize(String s1) {
		int sz = s1 == null ? 16 : s1.length() == 0 ? 16 : 2*s1.length();
		StringBuilder sb = new StringBuilder(sz);
		sb.append(' ');
		if (s1 != null) {
			for (int i = 0; i < s1.length(); ++i) {
				char ch = normalizeUnicode(s1.charAt(i));
				char sbLastChar = sb.charAt(sb.length()-1);
				if (Character.isLetter(ch)) {
					if ( ! Character.isLetter(sbLastChar)) {
						if ( ! Character.isSpaceChar(sbLastChar)) {
							sb.append(' ');
						}
					}
					sb.append(ch);
				} else if (Character.isDigit(ch)) {
					if ((i >= 2)
						&& (s1.charAt(i-1) == '.')
						&& Character.isDigit(s1.charAt(i-2))) {
						sb.setLength(sb.length()-2); // "9 ."
						sb.append('.');
					} else if ((i >= 2)
							   && ((s1.charAt(i-1) == 'e')||(s1.charAt(i-1) == 'E'))
							   && Character.isDigit(s1.charAt(i-2))) {
						sb.setLength(sb.length()-2); // "9 e"
						sb.append('e');
					} else if ((i >= 3)
							   && (s1.charAt(i-1) == '+')
							   && ((s1.charAt(i-2) == 'e')||(s1.charAt(i-2) == 'E'))
							   && Character.isDigit(s1.charAt(i-3))) {
						sb.setLength(sb.length()-4); // "9 e +"
						sb.append('e');
					} else if ((i >= 3)
							   && (s1.charAt(i-1) == '-')
							   && ((s1.charAt(i-2) == 'e')||(s1.charAt(i-2) == 'E'))
							   && Character.isDigit(s1.charAt(i-3))) {
						sb.setLength(sb.length()-4); // "9 e -"
						sb.append("e-");
					} else if ( (sbLastChar != '-') && ! Character.isDigit(sbLastChar)) {
						if ( ! Character.isSpaceChar(sbLastChar)) {
							sb.append(' ');
						}
					}
					sb.append(ch);
				} else if (Character.isSpaceChar(ch)) {
					if ( ! Character.isSpaceChar(sbLastChar)) {
						sb.append(' ');
					}
				} else {
					if ( ! Character.isSpaceChar(sbLastChar)) {
						sb.append(' ');
					}
					sb.append(ch);
				}
			}
		}
		if (Character.isSpaceChar(sb.charAt(sb.length()-1))) {
			sb.setLength(sb.length()-1);
		}
		if ((sb.length() > 0) && Character.isSpaceChar(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		return normalize_real_numbers(sb);
	}
	//------------------------------------------------------------------
	private static String normalizeListStr(String listaStr, String delims, String prefix, String suffix) {
		listaStr = listaStr.trim();
		String res = listaStr;
		try {
			if (prefix.length() > 0 && listaStr.startsWith(prefix)) {
				listaStr = listaStr.substring(prefix.length());
			}
			if (suffix.length() > 0 && listaStr.endsWith(suffix)) {
				listaStr = listaStr.substring(0, listaStr.length()-suffix.length());
			}
			listaStr = listaStr.trim();
			java.util.List<String> lista = new ArrayList<>(java.util.List.of(listaStr.split(delims)));
			lista.sort(null);
			res = lista.toString();
		} catch (Throwable e) {
			// ignorar
		}
		return res;
	}
	//----------------------------------------------------------------------
	private static final String precondMessage = "\n> Warning: the test could not be executed due to previous errors";
	//----------------------------------------------------------------------
	private static void precond(boolean expectedValue, boolean currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(char expectedValue, char currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(short expectedValue, short currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(int expectedValue, int currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(long expectedValue, long currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(float expectedValue, float currValue, float delta) {
		assumeTrue(Math.abs(expectedValue - currValue) <= delta, precondMessage);
	}
	private static void precond(double expectedValue, double currValue, double delta) {
		assumeTrue(Math.abs(expectedValue - currValue) <= delta, precondMessage);
	}
	private static void precond(Object expectedValue, Object currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			assumeTrue(expectedValue.equals(currValue), precondMessage);
		}
	}
	//------------------------------------------------------------------
	private static void precond(int[] expectedValue, int[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i]);
			}
		}
	}
	private static void precond(double[] expectedValue, double[] currValue, double delta) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i], delta);
			}
		}
	}
	private static <T> void precond(T[] expectedValue, T[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i]);
			}
		}
	}
	//----------------------------------------------------------------------
	private static void precondNorm(String expectedValue, String currValue) {
		precond(normalize(expectedValue), normalize(currValue));
	}
	private static void precondNorm(String[] expectedValue, String[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precondNorm(expectedValue[i], currValue[i]);
			}
		}
	}
	private static void assertEqualsNorm(String msg, String expectedValue, String currValue) {
		assertEquals(msg, normalize(expectedValue), normalize(currValue));
	}
	private static void assertEqualsNorm(String msg, java.util.List<String> expectedValue, java.util.List<String> currValue) {
		assertEquals(expectedValue.size(), currValue.size(), msg);
		for (int i = 0; i < expectedValue.size(); ++i) {
			assertEquals(normalize(expectedValue.get(i)), normalize(currValue.get(i)), msg);
		}
	}
	private static void assertArrayEqualsNorm(String msg, String[] expectedValue, String[] currValue) {
		assertEquals(expectedValue.length, currValue.length, msg);
		for (int i = 0; i < expectedValue.length; ++i) {
			assertEquals(msg, normalize(expectedValue[i]), normalize(currValue[i]));
		}
	}
	//----------------------------------------------------------------------
	private static void deleteFile(String filename) {
		new java.io.File(filename).delete();
	}
	private static void createFile(String filename, String inputData) throws Exception {
		try (PrintWriter pw = new PrintWriter(filename)) {
			pw.println(inputData);
		}
	}
	private static void createFile(String filename, String[] inputData) throws Exception {
		try (PrintWriter pw = new PrintWriter(filename)) {
			for (String linea : inputData) {
				pw.println(linea);
			}
		}
	}
	private static String loadFile(String filename) throws Exception {
		java.util.StringJoiner sj = new java.util.StringJoiner("\n");
		try (java.util.Scanner sc = new java.util.Scanner(new java.io.File(filename))) {
			while (sc.hasNextLine()) {
				sj.add(sc.nextLine());
			}
		}
		return sj.toString();
	}
	//----------------------------------------------------------------------
	//------------------------------------------------------------------
	private static Object getMemberObject(Object obj, Class objClass, Class memberClass, String memberName) {
		//--------------------------
		// OBJ puede ser NULL en caso de variable STATIC
		// OBJCLASS puede ser NULL si OBJ no es NULL
		// MEMBERCLASS no puede ser NULL
		// MEMBERNAME puede ser NULL, si solo hay una unica variable de ese tipo
		//--------------------------
		String memberId = (memberName == null ? "" : memberName)+":"+(memberClass == null ? "" : memberClass.getName());
		Object res = null;
		int idx = -1;
		try {
			if ((objClass == null)&&(obj != null)) {
				objClass = obj.getClass();
			}
			if ((objClass != null)&&(memberClass != null)) {
				int cnt = 0;
				int aux = -1;
				java.lang.reflect.Field[] objFields = objClass.getDeclaredFields();
				for (int i = 0; i < objFields.length; ++i) {
					if (memberClass.equals(objFields[i].getType())) {
						if ((memberName != null)&&(memberName.equalsIgnoreCase(objFields[i].getName()))) {
							idx = i;
						} else {
							aux = i;
						}
						++cnt;
					}
				}
				if ((idx < 0)&&(cnt == 1)) {
					idx = aux;	// si solo tiene una variable de ese tipo, no importa el nombre
				}
				if (idx >= 0) {
					objFields[idx].setAccessible(true);
					res = objFields[idx].get(obj);
				}
			}
		} catch (Throwable e) {
			fail("\n> Unexpected Error. getMemberObject["+memberId+"]: " + e);
		}
		if (idx < 0) {
			fail("\n> Error: no ha sido posible encontrar la variable ["+memberId+"]");
		}
		if (res == null) {
			fail("\n> Error: la variable ["+memberId+"] no se ha creado correctamente");
		}
		return res;
	} 
	//----------------------------------------------------------------------
	//----------------------------------------------------------------------
	private static class SysOutCapture {
		private SysOutErrCapture systemout;
		private SysOutErrCapture systemerr;
		public SysOutCapture() {
			systemout = new SysOutErrCapture(false);
			systemerr = new SysOutErrCapture(true);
		}
		public void sysOutCapture() throws RuntimeException {
			try {
				systemout.sysOutCapture();
			} finally {
				systemerr.sysOutCapture();
			}
		}
		public String sysOutRelease() throws RuntimeException {
			String s1 = "";
			String s2 = "";
			try {
				s1 = systemout.sysOutRelease();
			} finally {
				s2 = systemerr.sysOutRelease();
			}
			return s1 + " " + s2 ;
		}
		//--------------------------
		private static class SysOutErrCapture {
			//--------------------------------
			private java.io.PrintStream sysoutOld;
			private java.io.PrintStream sysoutstr;
			private java.io.ByteArrayOutputStream baos;
			private final boolean systemErr;
			private int estado;
			//--------------------------------
			public SysOutErrCapture(boolean syserr) {
				sysoutstr = null ;
				baos = null;
				sysoutOld = null ;
				estado = 0;
				systemErr = syserr;
			}
			//--------------------------------
			public void sysOutCapture() throws RuntimeException {
				if (estado != 0) {
					throw new RuntimeException("sysOutCapture:BadState");
				} else {
					estado = 1;
					try {
						if (systemErr) {
							sysoutOld = System.err;
						} else {
							sysoutOld = System.out;
						}
						baos = new java.io.ByteArrayOutputStream();
						sysoutstr = new java.io.PrintStream(baos);
						if (systemErr) {
							System.setErr(sysoutstr);
						} else {
							System.setOut(sysoutstr);
						}
					} catch (Throwable e) {
						throw new RuntimeException("sysOutCapture:InternalError");
					}
				}
			}
			//--------------------------------
			public String sysOutRelease() throws RuntimeException {
				String result = "";
				if (estado != 1) {
					throw new RuntimeException("sysOutRelease:BadState");
				} else {
					estado = 0;
					try {
						if (sysoutstr != null) {
							sysoutstr.flush();
						}
						if (baos != null) {
							baos.flush();
							result = new String(baos.toByteArray()); //java.nio.charset.StandardCharsets.ISO_8859_1);
						}
					} catch (Throwable e) {
						throw new RuntimeException("sysOutRelease:InternalError1");
					} finally {
						try {
							if (systemErr) {
								System.setErr(sysoutOld);
							} else {
								System.setOut(sysoutOld);
							}
							if (sysoutstr != null) {
								sysoutstr.close();
								sysoutstr = null;
							}
							if (baos != null) {
								baos.close();
								baos = null;
							}
						} catch (Throwable e) {
							throw new RuntimeException("sysOutRelease:InternalError2");
						}
					}
				}
				return result;
			}
			//--------------------------------
		}
	}
	//----------------------------------------------------------------------
	//--TestRunner-End---------------------------------------------------
	//----------------------------------------------------------------------
}
//--------------------------------------------------------------------------
