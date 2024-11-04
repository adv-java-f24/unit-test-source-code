package java112.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

import java.lang.reflect.*;

import java.io.*;
import java.util.*;
import java112.analyzer.TokenLengthsAnalyzer;

public class TokenLengthsAnalyzerOutputTest {

    private static TokenLengthsAnalyzer analyzer;
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
        properties.setProperty("output.file.token.lengths", "testing_token_lengths.txt");


        analyzer = new TokenLengthsAnalyzer(properties);

        analyzer.processToken("the");
        analyzer.processToken("one");
        analyzer.processToken("if");
        analyzer.processToken("three");
        analyzer.processToken("and");
        analyzer.processToken("four");
        analyzer.processToken("five");
        analyzer.processToken("six");
        analyzer.processToken("if");
        analyzer.processToken("the");

        testOutputFilePath = properties.getProperty("output.directory")
                + properties.getProperty("output.file.token.lengths");

        if (properties.getProperty("output.directory") == null) {
            fail("output.directory is null");
        }

        if (properties.getProperty("output.file.token.lengths") == null) {
            fail("output.file.token.lengths is null");
        }

        analyzer.generateOutputFile(inputFilePath);

        testOutput = new BufferedReader(new FileReader(testOutputFilePath));

        while (testOutput.ready()) {
            outputFileContents.add(testOutput.readLine());
        }
    }


    @AfterClass
    public static void tearDown() {

        File file = new File(testOutputFilePath);
        file.delete();

        File nullFile = new File("output/null");

        if (nullFile.exists()) {
            nullFile.delete();
        }

        analyzer = null;
    }


    @Test
    public void testClassExists() {
        assertNotNull(analyzer);
    }

    @Test
    public void testWriteOutputFileExists() throws NoSuchMethodException {
        Method method = TokenLengthsAnalyzer.class.getMethod ("generateOutputFile",
                String.class);
        assertNotNull(method);
    }

    @Test
    public void testOutputLineOne() {
        assertEquals("2	2", outputFileContents.get(0));
    }

    @Test
    public void testOutputLineTwo() {
        assertEquals("3	5", outputFileContents.get(1));
    }

    @Test
    public void testOutputLineFour() {
        assertEquals("4	2", outputFileContents.get(2));
    }

    @Test
    public void testOutputLineFive() {
        assertEquals("5	1", outputFileContents.get(3));
    }



}
