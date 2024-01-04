package java112.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import java.lang.reflect.Method;
import java.io.*;
import java.util.*;
import java112.labs1.LabThree;

public class LabThreeTest {

    private static LabThree lab;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeClass
    public static void initialSetUp() {
        lab = new LabThree();
    }

    @AfterClass
    public static void tearDown() {
        lab = null;
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void classExists() {
        assertNotNull(lab);
    }

    @Test
    public void mainMethodVoidReturnTest() throws NoSuchMethodException {
        Method method = LabThree.class.getMethod("main", String[].class);

        if (void.class != method.getReturnType()) {
            fail("\t****** The class must have a main method with a void return type.\n");
        }
    }

    @Test
    public void mainMethodExistsTest() throws NoSuchMethodException {
        Method method = LabThree.class.getMethod("main", String[].class);

        if (method == null) {
            fail("\t****** The class must have a method named 'main'.\n");
        }
    }

    @Test
    public void runMethodVoidReturnTest() throws NoSuchMethodException {
        Method method = LabThree.class.getMethod("run", String.class);

        if (void.class != method.getReturnType()) {
            fail("\t****** The class must have a method named 'run' that has a void return type.\n");
        }
    }

    @Test
    public void runMethodWithOneStringParamExistsTest() throws NoSuchMethodException {
        Method method = LabThree.class.getMethod("run", String.class);

        if (method == null) {
            fail("\t****** The class must have a method named 'run' with one String parameter.\n");
        }
    }

    @Test
    public void runMethodSystemOutPrintlnWithInputTest() {
        lab.run("test");

        if (!outContent.toString().equals("input: test\n")) {
            fail("\t****** Expected: input:test \n"
                + "\t****** Actual: " + outContent + "\n"
                + "\t****** The class must output the string \"input: test\" "
                + "The string \"input: \" is hard coded and \"test\" is data that was passed "
                + "to the class from the command line.\n");
        }
    }

    @Test
    public void mainMethodSystemOutPrintlnNoArgumentsInputTest() {
        LabThree.main(new String[0]);

        if (!outContent.toString().equals("Please enter one argument on the command line\n")) {
            fail("\t****** Expected: Please enter one argument on the command line \n"
                + "\t****** Actual: " + outContent + "\n"
                + "\t****** The class must output this string if there are no arguments on the command:\n"
                + "\t****** Please enter one argument on the command line");
        }
    }

    @Test
    public void mainMethodOneArgumentInputCallRunMethodTest() {
        String[] testArray = {"test"};
        LabThree.main(testArray);

        if (!outContent.toString().equals("input: test\n")) {
            fail("\t****** Expected: input:test \n"
                + "\t****** Actual: " + outContent + "\n"
                + "\t****** The class must output the string \"input: test\" "
                + "The string \"input: \" is hard coded and \"test\" is data that was passed "
                + "to the class from the command line.\n");
        }
    }

    @Test
    public void mainMethodSystemOutPrintlnTooManyArgumentsTest() {
        String[] testArray = {"test", "another test"};
        LabThree.main(testArray);

        if (!outContent.toString().equals("Please enter one argument on the command line\n")) {
            fail("\t****** Expected: Please enter one argument on the command line \n"
                    + "\t****** Actual: " + outContent
                    + "\n\t****** The class must output this string if there is more than one argument on the command:\n"
                    + "\t****** Please enter one argument on the command line");
        }
        assertEquals("Please enter one argument on the command line\n", outContent.toString());
    }

}
