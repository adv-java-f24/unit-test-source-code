package java112.tests;


import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.net.*;

import java112.analyzer.*;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TokenLocationSearchAnalyzerOutputTest {

    private static TokenLocationSearchAnalyzer analyzer;
    private static BufferedReader testOutput;
    private static String testOutputFilePath;
    private static String inputFilePath;
    private static List<String> outputFileContents;
    private static Properties properties;
    private static PrintWriter out;


    @BeforeClass
    public static void initialSetUp()
            throws java.io.FileNotFoundException,
            java.io.IOException {

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
        URL searchTokensURL = TokenLocationSearchAnalyzerOutputTest.class.getResource(searchTokensClasspath);
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
    public void testClassExists() {
        assertNotNull(analyzer);
    }


    @Test
    public void testForWriteOutputFileExists() throws NoSuchMethodException {
        Method method = TokenLocationSearchAnalyzer.class.getMethod("generateOutputFile",
                String.class);
        assertNotNull(method);
    }



    @Test
    public void testOutputLinesLessThan80Characters() {

        boolean lineTooLongFound = false;

        for (String outputLine : outputFileContents) {
            if (outputLine.length() > 80) {
                fail("\nOutput Line longer than 80 characters: \"" + outputLine);
            }
        }
    }


    @Test
    public void testFirstEntryFormedCorrectly() {

        String outputLine = outputFileContents.get(0);

        if (!outputLine.equals("notfound =")) {
            fail("\tKeyword output must be of the form \"keyword =\" with no space at the end\n\tand one space before the equal sign. "
                    + "\n\t\tIncorrect output: \"" + outputLine + "\"\n");
        }
    }

}