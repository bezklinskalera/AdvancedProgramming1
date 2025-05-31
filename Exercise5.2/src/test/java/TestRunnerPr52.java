
//--------------------------------------------------------------------------

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import java.util.concurrent.TimeUnit;

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

import wordIndex.*;

//--------------------------------------------------------------------------

public class TestRunnerPr52 {
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	private static final String[] inputData = {
			"How much wood would a woodchuck chuck if a woodchuck could chuck wood?", 
			"He would chuck, he would, as much as he could, and chuck as much wood as a woodchuck would if a woodchuck could chuck wood."
	};
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestCounterIndex {
		private static final String delimiters = "[ .,:;\\-\\!\\?]+";
		private CounterIndex cp1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of CounterIndex JUnit Test");
		}
		@AfterAll
		public void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of CounterIndex JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			cp1 = new CounterIndex();
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void counterIndexCtorTest1() throws Exception {
			try {
				assertTrue(((Object)cp1 instanceof Index), "\n> Error: CounterIndex implements Index:");
				assertTrue(((Object)cp1 instanceof AbstractIndex), "\n> Error: CounterIndex extends AbstractIndex:");
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentIndex(pw);
				}
				assertEquals(normalize(""),
							 normalize(loadFile("outputData.txt")),
							 "\n> Error: presentIndex():");
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void counterIndexPresentIndexPWTest1() throws Exception {
			try {
				for (String x : inputData) {
					cp1.addSentence(x);
				}
				cp1.solve(delimiters);
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentIndex(pw);
				}
				assertEquals(normalize("a 4 and 1 as 4 chuck 5 could 3 he 3 how 1 if 2 much 3 wood 4 woodchuck 4 would 4"),
							 normalize(loadFile("outputData.txt")),
							 "\n> Error: presentIndex():");
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void counterIndexPresentIndexCsTest1() {
			for (String x : inputData) {
				cp1.addSentence(x);
			}
			cp1.solve(delimiters);
			SysOutCapture sysOutCapture = new SysOutCapture();
			String salida = "";
			try {
				sysOutCapture.sysOutCapture();
				cp1.presentIndexOnConsole();
			} finally {
				salida = sysOutCapture.sysOutRelease();
			}
			assertEquals(normalize("a 4 and 1 as 4 chuck 5 could 3 he 3 how 1 if 2 much 3 wood 4 woodchuck 4 would 4"),
						 normalize(salida),
						 "\n> Error: presentIndex():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestLineIndex {
		private static final String delimiters = "[ .,:;\\-\\!\\?]+";
		private LineIndex cp1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of LineIndex JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of LineIndex JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			cp1 = new LineIndex();
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void lineIndexCtorTest1() throws Exception {
			try {
				assertAll("lineIndexCtorTest1", 
					() -> assertTrue(((Object)cp1 instanceof Index), "\n> Error: LineIndex implements Index:"),
					() -> assertTrue(((Object)cp1 instanceof AbstractIndex), "\n> Error: LineIndex extends AbstractIndex:"));
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentIndex(pw);
				}
				assertEquals(normalize(""),
							 normalize(loadFile("outputData.txt")),
							 "\n> Error: presentIndex():");
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void lineIndexPresentIndexPWTest1() throws Exception {
			try {
				for (String x : inputData) {
					cp1.addSentence(x);
				}
				cp1.solve(delimiters);
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentIndex(pw);
				}
				assertEquals(normalize("a <1,2> and <2> as <2> chuck <1,2> could <1,2> he <2> how <1> if <1,2> much <1,2> wood <1,2> woodchuck <1,2> would <1,2>"),
							 normalize(loadFile("outputData.txt")),
							 "\n> Error: presentIndex():");
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void lineIndexPresentIndexCsTest1() {
			for (String x : inputData) {
				cp1.addSentence(x);
			}
			cp1.solve(delimiters);
			SysOutCapture sysOutCapture = new SysOutCapture();
			String salida = "";
			try {
				sysOutCapture.sysOutCapture();
				cp1.presentIndexOnConsole();
			} finally {
				salida = sysOutCapture.sysOutRelease();
			}
			assertEquals(normalize("a <1,2> and <2> as <2> chuck <1,2> could <1,2> he <2> how <1> if <1,2> much <1,2> wood <1,2> woodchuck <1,2> would <1,2>"),
						 normalize(salida),
						 "\n> Error: presentIndex():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestPositionInLineIndex {
		private static final String delimiters = "[ .,:;\\-\\!\\?]+";
		private PositionInLineIndex cp1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of PositionInLineIndex JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of PositionInLineIndex JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			cp1 = new PositionInLineIndex();
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void positionInLineIndexCtorTest1() throws Exception {
			try {
				assertAll("lineIndexCtorTest1", 
					() -> assertTrue(((Object)cp1 instanceof Index), "\n> Error: PositionInLineIndex implements Index:"),
					() -> assertTrue(((Object)cp1 instanceof AbstractIndex), "\n> Error: PositionInLineIndex extends AbstractIndex:"));
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentIndex(pw);
				}
				assertEquals(normalize(""),
							 normalize(loadFile("outputData.txt")),
							 "\n> Error: presentIndex():");
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void positionInLineIndexPresentIndexPWTest1() throws Exception {
			try {
				for (String x : inputData) {
					cp1.addSentence(x);
				}
				cp1.solve(delimiters);
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentIndex(pw);
				}
				assertEquals(normalize("a 1 <5,9> 2 <17,21> and 2 <11> as 2 <6,8,13,16> chuck 1 <7,12> 2 <3,12,24> could 1 <11> 2 <10,23> he 2 <1,4,9> how 1 <1> if 1 <8> 2 <20> much 1 <2> 2 <7,14> wood 1 <3,13> 2 <15,25> woodchuck 1 <6,10> 2 <18,22> would 1 <4> 2 <2,5,19>"),
							 normalize(loadFile("outputData.txt")),
							 "\n> Error: presentIndex():");
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void positionInLineIndexPresentIndexCsTest1() {
			for (String x : inputData) {
				cp1.addSentence(x);
			}
			cp1.solve(delimiters);
			SysOutCapture sysOutCapture = new SysOutCapture();
			String salida = "";
			try {
				sysOutCapture.sysOutCapture();
				cp1.presentIndexOnConsole();
			} finally {
				salida = sysOutCapture.sysOutRelease();
			}
			assertEquals(normalize("a 1 <5,9> 2 <17,21> and 2 <11> as 2 <6,8,13,16> chuck 1 <7,12> 2 <3,12,24> could 1 <11> 2 <10,23> he 2 <1,4,9> how 1 <1> if 1 <8> 2 <20> much 1 <2> 2 <7,14> wood 1 <3,13> 2 <15,25> woodchuck 1 <6,10> 2 <18,22> would 1 <4> 2 <2,5,19>"),
						 normalize(salida),
						 "\n> Error: presentIndex():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------
	@Suite
	@SelectClasses({ JUnitTestCounterIndex.class ,
				JUnitTestLineIndex.class ,
				JUnitTestPositionInLineIndex.class
				})
				public static class JUnitTestSuite { /*empty*/ }
	//----------------------------------------------------------------------
	//--TestRunner-----------------------------------------------------
	//----------------------------------------------------------------------
	public static void main(String[] args) {
		final LauncherDiscoveryRequest request = 
				LauncherDiscoveryRequestBuilder.request()
				.selectors(
						selectClass(JUnitTestCounterIndex.class),
						selectClass(JUnitTestLineIndex.class),
						selectClass(JUnitTestPositionInLineIndex.class))
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

//	public static Result result = null;
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
//				System.out.println(">>> Error: "+ac+" tests where not executed due to errors in other methods");
//			}
//			if (fc > 0) {
//				System.out.println(">>> Error: "+fc+" tests tests failed due to errors in methods");
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
			java.util.List<String> lista = new java.util.ArrayList<>(java.util.List.of(listaStr.split(delims)));
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
		try (java.io.PrintWriter pw = new java.io.PrintWriter(filename)) {
			pw.println(inputData);
		}
	}
	private static void createFile(String filename, String[] inputData) throws Exception {
		try (java.io.PrintWriter pw = new java.io.PrintWriter(filename)) {
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
