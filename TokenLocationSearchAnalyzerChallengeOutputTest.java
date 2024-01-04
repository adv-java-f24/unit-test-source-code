package java112.tests;


import java112.analyzer.TokenLocationSearchAnalyzer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;


public class TokenLocationSearchAnalyzerChallengeOutputTest {

    private static TokenLocationSearchAnalyzer analyzer;
    private static BufferedReader testOutput;
    private static String testOutputFilePath;
    private static String inputFilePath;
    private static List<String> outputFileContents;
    private static Properties properties;
    private static PrintWriter out;


    @BeforeClass
    public static void initialSetUp()
    throws FileNotFoundException,
    IOException {

        inputFilePath = "inputFile";
        outputFileContents = new ArrayList<String>();

        properties = new Properties();
        properties.setProperty("output.directory", "output/");
        properties.setProperty("output.file.token.search.locations", "testing_token_locations.txt");
        properties.setProperty("classpath.search.tokens", "/search_locations_for_tests.txt");

        out = new PrintWriter("config/search_locations_for_tests.txt");

        out.println("");
        out.println("notfound");
        out.println("test");
        out.println("testword1");
        out.println("testword22");
        out.println("testword333");
        out.println("testword4444");
        out.println("testword55555");
        out.println("testword666666");

        out.close();

        String[] tokens = {
            "test", "test", "test", "test", "test", "test", "test", "test", "test", "test",
            "test", "test", "test", "test", "test", "test", "test", "test", "test", "test",
            "testword1", "testword1", "testword1", "testword1", "testword1", "testword1",
            "testword1", "testword1", "testword1", "testword1", "testword1", "testword1",
            "testword1", "testword1", "testword1", "testword1", "testword1", "testword1",
            "testword1", "testword1", "testword22", "testword22", "testword22", "testword22",
            "testword22", "testword22", "testword22", "testword22", "testword22", "testword22",
            "testword22", "testword22", "testword22", "testword22", "testword22", "testword22",
            "testword22", "testword22", "testword22", "testword22", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666",
            "test", "test", "test", "test", "test", "test", "test", "test", "test", "test",
            "test", "test", "test", "test", "test", "test", "test", "test", "test", "test",
            "testword1", "testword1", "testword1", "testword1", "testword1", "testword1",
            "testword1", "testword1", "testword1", "testword1", "testword1", "testword1",
            "testword1", "testword1", "testword1", "testword1", "testword1", "testword1",
            "testword1", "testword1", "testword22", "testword22", "testword22", "testword22",
            "testword22", "testword22", "testword22", "testword22", "testword22", "testword22",
            "testword22", "testword22", "testword22", "testword22", "testword22", "testword22",
            "testword22", "testword22", "testword22", "testword22", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666",
            "test", "test", "test", "test", "test", "test", "test", "test", "test", "test",
            "test", "test", "test", "test", "test", "test", "test", "test", "test", "test",
            "testword1", "testword1", "testword1", "testword1", "testword1", "testword1",
            "testword1", "testword1", "testword1", "testword1", "testword1", "testword1",
            "testword1", "testword1", "testword1", "testword1", "testword1", "testword1",
            "testword1", "testword1", "testword22", "testword22", "testword22", "testword22",
            "testword22", "testword22", "testword22", "testword22", "testword22", "testword22",
            "testword22", "testword22", "testword22", "testword22", "testword22", "testword22",
            "testword22", "testword22", "testword22", "testword22", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666",
            "test", "test", "test", "test", "test", "test", "test", "test", "test", "test",
            "test", "test", "test", "test", "test", "test", "test", "test", "test", "test",
            "testword1", "testword1", "testword1", "testword1", "testword1", "testword1",
            "testword1", "testword1", "testword1", "testword1", "testword1", "testword1",
            "testword1", "testword1", "testword1", "testword1", "testword1", "testword1",
            "testword1", "testword1", "testword22", "testword22", "testword22", "testword22",
            "testword22", "testword22", "testword22", "testword22", "testword22", "testword22",
            "testword22", "testword22", "testword22", "testword22", "testword22", "testword22",
            "testword22", "testword22", "testword22", "testword22", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword333",
            "testword333", "testword333", "testword333", "testword333", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword4444",
            "testword4444", "testword4444", "testword4444", "testword4444", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword55555",
            "testword55555", "testword55555", "testword55555", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666", "testword666666",
            "testword666666", "testword666666", "testword666666"
        };

        analyzer = new TokenLocationSearchAnalyzer(properties);

        for (String token : tokens) {
            analyzer.processToken(token);
        }

        testOutputFilePath = properties.getProperty("output.directory")
                + properties.getProperty("output.file.token.search.locations");

        analyzer.generateOutputFile(inputFilePath);

        testOutput = new BufferedReader(new FileReader(testOutputFilePath));

        while (testOutput.ready()) {
            outputFileContents.add(testOutput.readLine());
        }
    }


    @AfterClass
    public static void tearDown() {

        File file = new File(testOutputFilePath);

        String searchTokensClasspath = properties.getProperty("classpath.search.tokens");
        URL searchTokensURL = TokenLocationSearchAnalyzerChallengeOutputTest.class.getResource(searchTokensClasspath);
        String searchTokensFilePath = searchTokensURL.getPath();

        File keywordFile = new File(searchTokensFilePath);

        if (keywordFile.exists()) {
            keywordFile.delete();
        }

        File testFile = new File(testOutputFilePath);

        if (testFile.exists()) {
            testFile.delete();
        }

        analyzer = null;
    }

    @Test
    public void testForNoCommaBeforeRightBracket() {

        for (String outputLine : outputFileContents) {
            if (outputLine.endsWith(",]")) {
                fail("\nOutput line has comma before the file right bracket: \"" + outputLine);
            }
        }
    }

    @Test
    public void testEntryWithNoOccurences() {
        assertEquals("[]", outputFileContents.get(1));
    }


    @Test
    public void testForBlankLineAfterEntry() {
        assertEquals("", outputFileContents.get(2));
    }


    @Test
    public void testForRightBracket() {

        String lineNine = outputFileContents.get(8);

        if (!lineNine.endsWith("]")) {
            fail("\tThe last output line for a keyword occurrence list must be a right square bracket: "
                    + "\n\t\tIncorrect output: \"" + lineNine + "\"\n");
        }
    }

    @Test
    public void testForLeadingSpace() {

        String lineNine = outputFileContents.get(8);

        if (lineNine.startsWith(" ")) {
            fail("\tOutput lines must not start with a space: "
                    + "\n\t\tIncorrect output: \"" + lineNine + "\"\n");
        }
    }

    @Test
    public void testForNoTrailingSpaces() {

        for (String outputLine : outputFileContents) {

            if (outputLine.endsWith(" ")) {
                fail("\nOutput line has trailing spaces: \"" + outputLine + "\"\n");
            }
        }
    }


    @Test
    public void testForSpaceAfterCommas() {

        int indexOfFirstSpace = outputFileContents.get(4).indexOf(" ");

        if (indexOfFirstSpace != 3) {
            fail("\tCommas must be followed by a space except at end of line: "
                    + "\n\t\tIncorrect output: \"" + outputFileContents.get(4) + "\"\n");
        }
    }
}
