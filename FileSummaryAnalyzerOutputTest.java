package java112.tests;


import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

import java112.analyzer.FileSummaryAnalyzer;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class FileSummaryAnalyzerOutputTest {

    private static FileSummaryAnalyzer report;
    private static BufferedReader testOutput;
    private static String outputFilePath;
    private static String inputFilePath;
    private static List<String> outputFileContents;
    private static Properties properties;
    private static String testOutputFilePath;


    @BeforeClass
    public static void initialSetUp() throws java.io.FileNotFoundException, java.io.IOException {

        inputFilePath = "inputFile";
        outputFileContents = new ArrayList<String>();

        // The following commented out code is for projects 2-4.
        //properties = new Properties();
        //properties.setProperty("application.name", "TestApplicationName");
        //properties.setProperty("author", "TestName");
        //properties.setProperty("output.dir", "output/");
        //properties.setProperty("author.email.address", "eknapp@matcmadison.edu");
        //properties.setProperty("output.file.summary", "testing_summary_output.txt");

        testOutputFilePath = "output/testing_summary_output";

        report = new FileSummaryAnalyzer();
        report.processToken("one");
        report.processToken("one");
        report.processToken("two");
        report.processToken("three");
        report.processToken("three");
        report.processToken("four");
        report.processToken("five");
        report.processToken("six");
        report.processToken("seven");
        report.processToken("eight");

        report.generateOutputFile(inputFilePath, testOutputFilePath);

        //testOutputFilePath = properties.getProperty("output.dir") + properties.getProperty("output.file.summary");
        testOutput = new BufferedReader(new FileReader(testOutputFilePath));

        while (testOutput.ready()) {
            outputFileContents.add(testOutput.readLine());
        }
    }


    @AfterClass
    public static void tearDown() {

        File file = new File(testOutputFilePath);
        file.delete();

        report = null;
    }


    @Test
    public void testClassExists() {
        assertNotNull(report);
    }


    @Test
    public void testGenerateOutputFileExists() throws NoSuchMethodException {
        Method method = FileSummaryAnalyzer.class.getMethod("generateOutputFile", String.class, String.class);

        if (method == null) {
            fail("\t****** The FileSummaryAnalyzer class must have a 'generateOutputFile()' method.");
        }
    }


    @Test
    public void testInputFileNameInReport() {

        String reportLineFour = outputFileContents.get(3);

        String[] lineArray = reportLineFour.split("\\W");

        String inputFileFromReport = lineArray[lineArray.length - 1];

        if (!inputFilePath.equals(inputFileFromReport)) {
            fail("\t****** The input file path is not correctly written to the output file.\n"
                + "		   Incorrect output line: " + reportLineFour + "\n");
        }
    }


    //@Test
    //public void testApplicationNameFromProperties() {
    //
    //    String reportLineApplicationName = outputFileContents.get(0);
    //
    //    if (reportLineApplicationName.indexOf(properties.getProperty("application.name")) == -1) {
    //        fail("\nApplication Name must come from the properties file: \""
    //                + reportLineApplicationName + "\"\n");
    //    }
    //}


    //@Test
    //public void testAuthorFromProperties() {
    //
    //    String reportLineAuthor = outputFileContents.get(1);
    //
    //    if (reportLineAuthor.indexOf(properties.getProperty("author")) == -1) {
    //        fail("\nAuthor must come from the properties file: \"" + reportLineAuthor + "\"\n");
    //    }
    //}


    //@Test
    //public void testEmailFromProperties() {
    //
    //    String reportLineEmail = outputFileContents.get(2);
    //
    //    if (reportLineEmail.indexOf(properties.getProperty("author.email.address")) == -1) {
    //        fail("\nEmail must come from the properties file: \"" + reportLineEmail + "\"\n");
    //    }
    //}

    @Test
    public void testOututFileLineCount() {
        int lineCount = outputFileContents.size();

        if (lineCount != 9) {
            fail("\t****** Output file does not have 9 lines.\n");
        }
    }


    @Test
    public void testTokenCountInReport() {

        String reportLineNine = outputFileContents.get(8);
        String[] lineArray = reportLineNine.split("\\W");
        String tokenCountString = lineArray[lineArray.length - 1];
        int tokenCount = Integer.parseInt(tokenCountString);

        if (tokenCount != 10) {
            fail("\t****** The analyzer is not counting the tokens correctly. ");
        }

        assertEquals(10, tokenCount);
    }
}
