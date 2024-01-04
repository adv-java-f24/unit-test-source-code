package java112.tests;


import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import java112.analyzer.DistinctTokensAnalyzer;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class DistinctTokensAnalyzerOutputTest {

    private static DistinctTokensAnalyzer analyzer;
    private static BufferedReader testOutput;
    private static String testOutputFilePath;
    private static String inputFilePath;
    private static List<String> outputFileContents;


    private static Properties properties;

    @BeforeClass
    public static void initialSetUp() throws java.io.FileNotFoundException, java.io.IOException {

        inputFilePath = "inputFile";
        outputFileContents = new ArrayList<String>();

        // The following commented-out code is for projects 2-4.
        properties = new Properties();
        properties.setProperty("output.directory", "output/");
        properties.setProperty("output.file.distinct", "test_distinct_tokens.txt");
        analyzer = new DistinctTokensAnalyzer(properties);

        //analyzer.processToken("aaa");
        analyzer.processToken("One");
        analyzer.processToken("one");
        analyzer.processToken("one");
        analyzer.processToken("two");
        analyzer.processToken("three");
        analyzer.processToken("three");
        analyzer.processToken("four");
        analyzer.processToken("five");
        analyzer.processToken("six");
        analyzer.processToken("seven");
        analyzer.processToken("eight");

        testOutputFilePath = properties.getProperty("output.directory")
        + properties.getProperty("output.file.distinct");

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
    public void generateOutputFileExistsTest() throws NoSuchMethodException {
        Method method = DistinctTokensAnalyzer.class.getMethod("generateOutputFile", String.class);

        if (method == null) {
            fail("\t****** The DistinctTokensAnalyzer class must have a 'generateOutputFile' method.\n");
        }
    }

    @Test
    public void outputLineOneTest() {

        outputLineCheck("One", outputFileContents.get(0));
    }
    @Test
    public void outputLineTwoTest() {

        outputLineCheck("eight", outputFileContents.get(1));
        String expectedOutput = "eight";
    }


    @Test
    public void outputLineThreeTest() {

        outputLineCheck("five", outputFileContents.get(2));
    }


    @Test
    public void outputLineFourTest() {

        outputLineCheck("four", outputFileContents.get(3));
    }


    @Test
    public void outputLineFiveTest() {

        outputLineCheck("one", outputFileContents.get(4));
    }


    @Test
    public void outputLineSixTest() {

        outputLineCheck("seven", outputFileContents.get(5));
    }


    @Test
    public void outputLineSevenTest() {

        outputLineCheck("six", outputFileContents.get(6));
    }


    @Test
    public void outputLineEightTest() {

        outputLineCheck("three", outputFileContents.get(7));
    }


    @Test
    public void outputLineNineTest() {

        outputLineCheck("two", outputFileContents.get(8));
    }



    @Test
        public void outputFileSizeTest() {
            if (outputFileContents.size() != 9 ) {
                fail("\t****** The output file should contain 9 lines of output, but it had \n"
                    + "\t****** " + outputFileContents.size() + " lines.");
            }

        }


    public void outputLineCheck(String expectedOutput, String lineContent) {

        if (!lineContent.equals(expectedOutput)) {
            fail("\t****** The generated output is not correct.\n"
            + "\t****** Expected <" + expectedOutput + ">, recieved <" + lineContent + ">\n");
        }
    }


}
