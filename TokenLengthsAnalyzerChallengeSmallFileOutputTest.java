package java112.tests;

import java112.analyzer.TokenLengthsAnalyzer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;
/** This unit test is for the histogram extra challenge */
public class TokenLengthsAnalyzerChallengeSmallFileOutputTest {

    private static TokenLengthsAnalyzer analyzer;
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
        properties.setProperty("output.file.token.lengths.histogram", "testing_token_lengths_histogram.txt");


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
                + properties.getProperty("output.file.token.lengths.histogram");

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
    public void testHistogramLineOneTokenLength() {
        assertTrue(outputFileContents.get(0).startsWith("2"));
    }

    @Test
    public void testHistogramLineTwoTokenLength() {
        assertTrue(outputFileContents.get(1).startsWith("3"));
    }

    @Test
    public void testHistogramLineThreeTokenLength() {
        assertTrue(outputFileContents.get(2).startsWith("4"));
    }

    @Test
    public void testHistogramLineFourTokenLength() {
        assertTrue(outputFileContents.get(3).startsWith("5"));
    }

    @Test
    public void testHistogramLineOneStars() {
        double count = TestUtilities.countStars(outputFileContents.get(0));
        assertEquals(32.0, count, 4.0);
    }

    @Test
    public void testHistogramLineTwoStars() {
        double count = TestUtilities.countStars(outputFileContents.get(1));
        assertEquals(80.0, count, 4.0);

    }

    @Test
    public void testHistogramLineThreeStars() {
        double count = TestUtilities.countStars(outputFileContents.get(2));
        assertEquals(32.0, count, 4.0);
    }

    @Test
    public void testHistogramLineFourStars() {
        double count = TestUtilities.countStars(outputFileContents.get(3));
        assertEquals(16.0, count, 4.0);
    }
}
