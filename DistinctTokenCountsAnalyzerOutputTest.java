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
import java112.analyzer.DistinctTokenCountsAnalyzer;

public class DistinctTokenCountsAnalyzerOutputTest {

    private static DistinctTokenCountsAnalyzer analyzer;
    private static BufferedReader testOutput;
    private static String testOutputFilePath;
    private static String inputFilePath;
    private static List<String> outputFileContents;
    private static Properties properties;

    @BeforeClass
    public static void initialSetUp()
            throws java.io.FileNotFoundException,
            java.io.IOException {

        inputFilePath = "inputFile";
        outputFileContents = new ArrayList<String>();

        properties = new Properties();
        properties.setProperty("output.directory", "output/");
        properties.setProperty("output.file.distinct.counts", "test_token_count.txt");

        analyzer = new DistinctTokenCountsAnalyzer(properties);
        analyzer.processToken("One");
        analyzer.processToken("1");
        analyzer.processToken("one");
        analyzer.processToken("one");
        analyzer.processToken("two");
        analyzer.processToken("three");
        analyzer.processToken("three");
        analyzer.processToken("four");
        analyzer.processToken("five");
        analyzer.processToken("five");
        analyzer.processToken("six");
        analyzer.processToken("seven");
        analyzer.processToken("seven");
        analyzer.processToken("seven");
        analyzer.processToken("seven");
        analyzer.processToken("eight");
        analyzer.processToken("eight");
        analyzer.processToken("eight");

        testOutputFilePath = properties.getProperty("output.directory")
                + properties.getProperty("output.file.distinct.counts");

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

        analyzer = null;
    }


    @Test
    public void classExists() {
        assertNotNull(analyzer);
    }

    @Test
    public void testWriteOutputFileExists() throws NoSuchMethodException {
        Method method = DistinctTokenCountsAnalyzer.class.getMethod ("generateOutputFile", String.class);
        assertNotNull(method);
    }

    @Test
    public void testOutputLineOne() {
        assertEquals("1\t1", outputFileContents.get(0));
    }


    @Test
    public void testOutputLineTwo() {
        assertEquals("One\t1", outputFileContents.get(1));
    }

    @Test
    public void testOutputLineThree() {
        assertEquals("eight\t3", outputFileContents.get(2));
    }

    @Test
    public void testOutputLineFour() {
        assertEquals("five\t2", outputFileContents.get(3));
    }

    @Test
    public void testOutputLineFive() {
        assertEquals("four\t1", outputFileContents.get(4));
    }

    @Test
    public void testOutputLineSix() {
        assertEquals("one\t2", outputFileContents.get(5));
    }

    @Test
    public void testOutputLineSeven() {
        assertEquals("seven\t4", outputFileContents.get(6));
    }

    @Test
    public void testOutputLineEight() {
        assertEquals("six\t1", outputFileContents.get(7));
    }

    @Test
    public void testOutputLineNine() {
        assertEquals("three\t2", outputFileContents.get(8));
    }

    @Test
    public void testOutputLineTen() {
        assertEquals("two\t1", outputFileContents.get(9));
    }



}
