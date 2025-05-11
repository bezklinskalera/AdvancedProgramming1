
//--------------------------------------------------------------------------

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import simpleFilesWordCounting.*;

//--------------------------------------------------------------------------

public class TestRunnerPr42 {
    //----------------------------------------------------------------------
    //--JUnitTest-----------------------------------------------------------
    //----------------------------------------------------------------------
    public static class JUnitTestWordInText {
        private WordInText wt1;
        @BeforeAll
        public static void beforeClass() {
            // Code executed before the first test method
            System.out.println("Start of WordInText JUnit Test");
        }
        @AfterAll
        public static void  afterClass() {
            // Code executed after the last test method
            System.out.println("End of WordInText JUnit Test");
        }
        @BeforeEach
        public void setUp() throws Exception {
            // Code executed before each test
            wt1 = new WordInText("Word");
        }
        @AfterEach
        public void tearDown() {
            // Code executed after each test
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordInTextCtorTest1() {
            assertEquals(normalize("WORD: 1"),
                    normalize(wt1.toString()),
                    "\n> Error: an1.toString():");
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordInTextIncrementTest2() throws Exception {
            wt1.increment();
            assertEquals(normalize("WORD: 2"),
                    normalize(wt1.toString()),
                    "\n> Error: an1.increment(); an1.toString():");
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordInTextEqualsTest1() throws Exception {
            WordInText an2 = new WordInText("Word");
            assertTrue(wt1.equals(an2), "\n> Error: an1.equals(an2): ");
            an2.increment();
            assertTrue(wt1.equals(an2), "\n> Error: an1.equals(an2): ");
            //------------------------
            WordInText an3 = new WordInText("Word");
            assertTrue(wt1.equals(an3), "\n> Error: an1.equals(an3): ");
            an3.increment();
            assertTrue(wt1.equals(an3), "\n> Error: an1.equals(an3): ");
            //------------------------
            WordInText an4 = new WordInText("another word");
            assertFalse(wt1.equals(an4), "\n> Error: an1.equals(an4): ");
            //------------------------
            assertFalse(wt1.equals(null), "\n> Error: an1.equals(null): ");
            assertFalse(wt1.equals("This is a String"), "\n> Error: an1.equals(\"This is a String\"): ");
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordInTextHashCodeTest1() throws Exception {
            int an1HashCode = wt1.hashCode();
            //------------------------
            WordInText an2 = new WordInText("Word");
            assertEquals(an1HashCode, an2.hashCode(), "\n> Error: an2.hashCode(): ");
            an2.increment();
            assertEquals(an1HashCode, an2.hashCode(), "\n> Error: an2.hashCode(): ");
            //------------------------
            WordInText an3 = new WordInText("Word");
            assertEquals(an1HashCode, an3.hashCode(), "\n> Error: an3.hashCode(): ");
            an3.increment();
            assertEquals(an1HashCode, an3.hashCode(), "\n> Error: an3.hashCode(): ");
            //------------------------
            WordInText an4 = new WordInText("another word");
            assertNotEquals(an1HashCode, an4.hashCode(), "\n> Error: an4.hashCode(): ");
        }
        //------------------------------------------------------------------
    }
    //----------------------------------------------------------------------
    //--JUnitTest-----------------------------------------------------------
    //----------------------------------------------------------------------
    public static class JUnitTestMainWordInText {
        @BeforeAll
        public static void beforeClass() {
            // Code executed before the first test method
            System.out.println("Start of MainWordInText JUnit Test");
        }
        @AfterAll
        public static void  afterClass() {
            // Code executed after the last test method
            System.out.println("End of MainWordInText JUnit Test");
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
        public void MainWordInTextMainTest1() {
            String output = "";
            SysOutCapture sysOutCapture = new SysOutCapture();
            try {
                sysOutCapture.sysOutCapture();
                MainWordInText.main(new String[]{});
            } finally {
                output = sysOutCapture.sysOutRelease();
            }
            assertEquals(normalize("Word 1 = WOOD : 2 Word 2 = WOOD : 1 The words are equal"),
                    normalize(output),
                    "\n> Error: MainWordInText.main():");
        }
        //------------------------------------------------------------------
    }
    //----------------------------------------------------------------------
    //--JUnitTest-----------------------------------------------------------
    //----------------------------------------------------------------------
    public static class JUnitTestWordCounter {
        private static final String[] inputData = {
                "How much wood would a woodchuck chuck",
                "if a woodchuck could chuck wood?",
                "He would chuck, he would, as much as he could,",
                "and chuck as much wood as a woodchuck would",
                "if a woodchuck could chuck wood."
        };
        private static final String delimiters = "[ .,\\?]+";
        private WordCounter cp1;
        @BeforeAll
        public static void beforeClass() {
            // Code executed before the first test method
            System.out.println("Start of WordCounter JUnit Test");
        }
        @AfterAll
        public static void  afterClass() {
            // Code executed after the last test method
            System.out.println("End of WordCounter JUnit Test");
        }
        @BeforeEach
        public void setUp() {
            // Code executed before each test
            cp1 = new WordCounter();
        }
        @AfterEach
        public void tearDown() {
            // Code executed after each test
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterCtorTest1() {
            assertEquals(normalize("[]"),
                    normalize(cp1.toString()),
                    "\n> Error: cp1.toString():");
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterCtorTest2() {
            WordCounter cp2 = new WordCounter();
            assertEquals(normalize("[]"),
                    normalize(cp2.toString()),
                    "\n> Error: cp1.toString():");
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterincludeAllTest1() {
            cp1.includeAll(inputData, delimiters);
            assertEquals(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - A : 4 - WOODCHUCK : 4 - CHUCK : 5 - IF : 2 - COULD : 3 - HE : 3 - AS : 4 - AND : 1 ]"),
                    normalize(cp1.toString()),
                    "\n> Error: includeAll() ; toString():");
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterincludeAllFromFileTest1() throws Exception {
            try {
                createFile("inputData.txt", inputData);
                cp1.includeAllFromFile("inputData.txt", delimiters);
                assertEquals(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - A : 4 - WOODCHUCK : 4 - CHUCK : 5 - IF : 2 - COULD : 3 - HE : 3 - AS : 4 - AND : 1 ]"),
                        normalize(cp1.toString()),
                        "\n> Error: includeAllFromFile() ; toString():");
            } finally {
                deleteFile("inputData.txt");
            }
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterFindTest1() {
            cp1.includeAll(inputData, delimiters);
            precond(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - A : 4 - WOODCHUCK : 4 - CHUCK : 5 - IF : 2 - COULD : 3 - HE : 3 - AS : 4 - AND : 1 ]"),
                    normalize(cp1.toString()));
            assertAll("WordCounterFindTest1",
                    () -> assertEquals(new WordInText("WOOD"), cp1.find("wood"), "\n> Error: cp1.find(wood):"),
                    () -> assertEquals(new WordInText("WOODCHUCK"), cp1.find("woodchuck"), "\n> Error: cp1.find(woodchuck):"),
                    () -> assertEquals(new WordInText("COULD"), cp1.find("could"), "\n> Error: cp1.find(could):"));
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterFindTest2() {
            cp1.includeAll(inputData, delimiters);
            precond(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - A : 4 - WOODCHUCK : 4 - CHUCK : 5 - IF : 2 - COULD : 3 - HE : 3 - AS : 4 - AND : 1 ]"),
                    normalize(cp1.toString()));
            Exception exception = assertThrows(NoSuchElementException.class,
                    () -> cp1.find("xxx"));
            assertEquals("Not found word xxx", exception.getMessage(), "\n> Error: find(xxx): exception.getMessage():");
//			try {
//				cp1.includeAll(inputData, delimiters);
//				precond(normalize("[GUERRA: 5, TENIA: 2, UNA: 2, JARRA: 3, Y: 1, PARRA: 7, PERRA: 6, PERO: 1, LA: 10, DE: 8, ROMPIO: 1, PEGO: 1, CON: 3, PORRA: 3, A: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, SI: 1, NO: 2, HUBIERA: 2, ROTO: 1]"),
//						normalize(cp1.toString()));
//				WordInText pal = cp1.find("xxx");
//				fail("\n> Error: find(xxx): No exception thrown");
//			} catch (java.util.NoSuchElementException e) {
//				//assertEquals("\n> Error: find(xxx): exception.getMessage():", "Not found word xxx", e.getMessage());
//			} catch (Exception e) {
//				fail("\n> Error: find(xxx): the exception thrown was not NoSuchElementException");
//			}
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterFindTest3() {
            Exception exception = assertThrows(NoSuchElementException.class,
                    () -> cp1.find("xxx"));
            assertEquals("Not found word xxx", exception.getMessage(), "\n> Error: find(xxx): exception.getMessage():");
//			try {
//				WordInText pal = cp1.find("xxx");
//				fail("\n> Error: find(xxx): No exception thrown");
//			} catch (java.util.NoSuchElementException e) {
//				//assertEquals("\n> Error: find(xxx): exception.getMessage():", "Not found word xxx", e.getMessage());
//			} catch (Exception e) {
//				fail("\n> Error: find(xxx): the exception thrown was not NoSuchElementException");
//			}
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterPresentWordsPWTest1() throws Exception {
            try {
                cp1.includeAll(inputData, delimiters);
                precond(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - A : 4 - WOODCHUCK : 4 - CHUCK : 5 - IF : 2 - COULD : 3 - HE : 3 - AS : 4 - AND : 1 ]"),
                        normalize(cp1.toString()));
                try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
                    cp1.presentWords(pw);
                }
                assertEquals(normalize("HOW: 1 MUCH: 3 WOOD: 4 WOULD: 4 A: 4 WOODCHUCK: 4 CHUCK: 5 IF: 2 COULD: 3 HE: 3 AS: 4 AND: 1"),
                        normalize(loadFile("outputData.txt")),
                        "\n> Error: presentWords():");

            } finally {
                deleteFile("outputData.txt");
            }
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterPresentWordsFichTest1() throws Exception {
            try {
                cp1.includeAll(inputData, delimiters);
                precond(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - A : 4 - WOODCHUCK : 4 - CHUCK : 5 - IF : 2 - COULD : 3 - HE : 3 - AS : 4 - AND : 1 ]"),
                        normalize(cp1.toString()));
                cp1.presentWords("outputData.txt");
                assertEquals(normalize("HOW: 1 MUCH: 3 WOOD: 4 WOULD: 4 A: 4 WOODCHUCK: 4 CHUCK: 5 IF: 2 COULD: 3 HE: 3 AS: 4 AND: 1"),
                        normalize(loadFile("outputData.txt")),
                        "\n> Error: presentWords():");

            } finally {
                deleteFile("outputData.txt");
            }
        }
        //------------------------------------------------------------------
    }
    //----------------------------------------------------------------------
    //--JUnitTest-----------------------------------------------------------
    //----------------------------------------------------------------------
    public static class JUnitTestMainWordCounter {
        @BeforeAll
        public static void beforeClass() {
            // Code executed before the first test method
            System.out.println("Start of MainWordCounter JUnit Test");
        }
        @AfterAll
        public static void  afterClass() {
            // Code executed after the last test method
            System.out.println("End of MainWordCounter JUnit Test");
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
        public void MainWordCounterMainTest1() {
            String output = "";
            SysOutCapture sysOutCapture = new SysOutCapture();
            try {
                sysOutCapture.sysOutCapture();
                MainWordCounter.main(new String[]{});
            } finally {
                output = sysOutCapture.sysOutRelease();
            }
            assertEquals(normalize("[ THIS : 2 - IS : 2 - THE : 3 - FIRST : 1 - SENTENCE : 1 - OF : 1 - EXAMPLE : 1 - AND : 1 - SECOND : 1 - ONE : 1 ]"),
                    normalize(output),
                    "\n> Error: MainWordCounter.main():");
        }
        //------------------------------------------------------------------
    }
    //----------------------------------------------------------------------
    //--JUnitTest-----------------------------------------------------------
    //----------------------------------------------------------------------
    public static class JUnitTestWordCounterSig {
        private static final String[] inputData = {
                "How much wood would a woodchuck chuck",
                "if a woodchuck could chuck wood?",
                "He would chuck, he would, as much as he could,",
                "and chuck as much wood as a woodchuck would",
                "if a woodchuck could chuck wood."
        };
        private static final String delimiters = "[ .,\\?]+";
        private static final String[] noSig = {
                "If", "A", "as", "AND"
        };
        private static final String[] noSigInputData = {
                "If   A as   AND"
        };
        private WordCounterSig cp1;
        @BeforeAll
        public static void beforeClass() {
            // Code executed before the first test method
            System.out.println("Start of WordCounterSig JUnit Test");
        }
        @AfterAll
        public static void  afterClass() {
            // Code executed after the last test method
            System.out.println("End of WordCounterSig JUnit Test");
        }
        @BeforeEach
        public void setUp() {
            // Code executed before each test
            cp1 = new WordCounterSig();
            cp1.readNonSigArray(noSig);
        }
        @AfterEach
        public void tearDown() {
            // Code executed after each test
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterSigCtorTest1() {
            assertTrue(((Object)cp1 instanceof WordCounter), "\n> Error: WordCounterSig extends WordCounter:");
            assertEquals(normalize("[]"),
                    normalize(cp1.toString()),
                    "\n> Error: cp1.toString():");
            cp1.includeAll(inputData, delimiters);
            assertEquals(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - WOODCHUCK : 4 - CHUCK : 5 - COULD : 3 - HE : 3 ]"),
                    normalize(cp1.toString()),
                    "\n> Error: includeAll() ; toString():");
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterSigCtorTest2() {
            WordCounterSig cp2 = new WordCounterSig();
            cp2.readNonSigArray(noSig);
            assertEquals(normalize("[]"),
                    normalize(cp2.toString()),
                    "\n> Error: cp2.toString():");
            cp2.includeAll(inputData, delimiters);
            assertEquals(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - WOODCHUCK : 4 - CHUCK : 5 - COULD : 3 - HE : 3 ]"),
                    normalize(cp2.toString()),
                    "\n> Error: includeAll() ; toString():");
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterSigCtorTest3() throws Exception {
            try {
                createFile("noSigInputData.txt", noSigInputData);
                WordCounterSig cp2 = new WordCounterSig();
                cp2.readNonSigFile("noSigInputData.txt", delimiters);
                assertEquals(normalize("[]"),
                        normalize(cp2.toString()),
                        "\n> Error: cp2.toString():");
                cp2.includeAll(inputData, delimiters);
                assertEquals(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - WOODCHUCK : 4 - CHUCK : 5 - COULD : 3 - HE : 3 ]"),
                        normalize(cp2.toString()),
                        "\n> Error: includeAll() ; toString():");
            } finally {
                deleteFile("noSigInputData.txt");
            }
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterSigCtorTest4() throws Exception {
            try {
                createFile("noSigInputData.txt", noSigInputData);
                WordCounterSig cp2 = new WordCounterSig();
                cp2.readNonSigFile("noSigInputData.txt", delimiters);
                assertEquals(normalize("[]"),
                        normalize(cp2.toString()), "\n> Error: cp2.toString():");
                cp2.includeAll(inputData, delimiters);
                assertEquals(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - WOODCHUCK : 4 - CHUCK : 5 - COULD : 3 - HE : 3 ]"),
                        normalize(cp2.toString()),
                        "\n> Error: includeAll() ; toString():");
            } finally {
                deleteFile("noSigInputData.txt");
            }
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterSigincludeAllTest1() {
            cp1.includeAll(inputData, delimiters);
            assertEquals(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - WOODCHUCK : 4 - CHUCK : 5 - COULD : 3 - HE : 3 ]"),
                    normalize(cp1.toString()),
                    "\n> Error: includeAll() ; toString():");
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterSigincludeAllFromFileTest1() throws Exception {
            try {
                createFile("inputData.txt", inputData);
                cp1.includeAllFromFile("inputData.txt", delimiters);
                assertEquals(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - WOODCHUCK : 4 - CHUCK : 5 - COULD : 3 - HE : 3 ]"),
                        normalize(cp1.toString()),
                        "\n> Error: includeAllFromFile() ; toString():");
            } finally {
                deleteFile("inputData.txt");
            }
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterSigFindTest1() {
            cp1.includeAll(inputData, delimiters);
            precond(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - WOODCHUCK : 4 - CHUCK : 5 - COULD : 3 - HE : 3 ]"),
                    normalize(cp1.toString()));
            assertAll("WordCounterSigFindTest1",
                    () -> assertEquals(new WordInText("WOOD"), cp1.find("wood"), "\n> Error: cp1.find(wood):"),
                    () -> assertEquals(new WordInText("WOODCHUCK"), cp1.find("woodchuck"), "\n> Error: cp1.find(woodchuck):"),
                    () -> assertEquals(new WordInText("COULD"), cp1.find("could"), "\n> Error: cp1.find(could):"));
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterSigFindTest2() {
            cp1.includeAll(inputData, delimiters);
            precond(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - WOODCHUCK : 4 - CHUCK : 5 - COULD : 3 - HE : 3 ]"),
                    normalize(cp1.toString()));
            Exception exception = assertThrows(NoSuchElementException.class,
                    () -> cp1.find("xxx"));
            assertEquals("Not found word xxx", exception.getMessage(), "Error: find(xxx): exception.getMessage():");
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterSigFindTest3() {
            Exception exception = assertThrows(NoSuchElementException.class,
                    () -> cp1.find("xxx"));
            assertEquals("Not found word xxx", exception.getMessage(), "Error: find(xxx): exception.getMessage():");
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterSigPresentWordsPWTest1() throws Exception {
            try {
                cp1.includeAll(inputData, delimiters);
                precond(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - WOODCHUCK : 4 - CHUCK : 5 - COULD : 3 - HE : 3 ]"),
                        normalize(cp1.toString()));
                try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
                    cp1.presentWords(pw);
                }
                assertEquals(normalize("HOW: 1 MUCH: 3 WOOD: 4 WOULD: 4 WOODCHUCK: 4 CHUCK: 5 COULD: 3 HE: 3"),
                        normalize(loadFile("outputData.txt")),
                        "\n> Error: presentWords():");

            } finally {
                deleteFile("outputData.txt");
            }
        }
        @Test
        @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
        public void WordCounterSigPresentWordsFichTest1() throws Exception {
            try {
                cp1.includeAll(inputData, delimiters);
                precond(normalize("[ HOW : 1 - MUCH : 3 - WOOD : 4 - WOULD : 4 - WOODCHUCK : 4 - CHUCK : 5 - COULD : 3 - HE : 3 ]"),
                        normalize(cp1.toString()));
                cp1.presentWords("outputData.txt");
                assertEquals(normalize("HOW: 1 MUCH: 3 WOOD: 4 WOULD: 4 WOODCHUCK: 4 CHUCK: 5 COULD: 3 HE: 3"),
                        normalize(loadFile("outputData.txt")),
                        "\n> Error: presentWords():");

            } finally {
                deleteFile("outputData.txt");
            }
        }
        //------------------------------------------------------------------
    }
    //----------------------------------------------------------------------
    //--JUnitTestSuite------------------------------------------------------
    //----------------------------------------------------------------------
    @Suite
    @SelectClasses({ JUnitTestWordCounter.class ,
            JUnitTestWordCounterSig.class ,
            JUnitTestWordInText.class ,
            JUnitTestMainWordCounter.class ,
            JUnitTestMainWordInText.class
    })
    public static class JUnitTestSuite { /*empty*/ }
    //----------------------------------------------------------------------
    //--TestRunner-----------------------------------------------------
    //----------------------------------------------------------------------
    public static void main(String[] args) {
        final LauncherDiscoveryRequest request =
                LauncherDiscoveryRequestBuilder.request()
                        .selectors(
                                selectClass(JUnitTestWordCounter.class),
                                selectClass(JUnitTestWordCounterSig.class),
                                selectClass(JUnitTestWordInText.class),
                                selectClass(JUnitTestMainWordCounter.class),
                                selectClass(JUnitTestMainWordCounter.class))
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
        //----------------------------------------------------------------------
        //--TestRunner-----------------------------------------------------
        //----------------------------------------------------------------------
//		public static Result result = null;
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
//				System.out.println(">>> Error: No se pudieron realizar "+ac+" tests debido a errores en otros metodos");
//			}
//			if (fc > 0) {
//				System.out.println(">>> Error: Fallaron "+fc+" tests debido a errores en metodos");
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
    //----------------------------------------------------------------------
    private static void precondNorm(String expectedValue, String currValue) {
        precond(normalize(expectedValue), normalize(currValue));
    }
    private static void assertEqualsNorm(String msg, String expectedValue, String currValue) {
        assertEquals(normalize(expectedValue), normalize(currValue), msg);
    }
    private static void assertArrayEqualsNorm(String msg, String[] expectedValue, String[] currValue) {
        assertEquals(expectedValue.length, currValue.length, msg);
        for (int i = 0; i < expectedValue.length; ++i) {
            assertEquals(normalize(expectedValue[i]), normalize(currValue[i]), msg);
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
}
//--------------------------------------------------------------------------
