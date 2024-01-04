package java112.tests;


import java.io.*;
import java.lang.reflect.*;
import java.lang.reflect.Method;
import java.util.*;

import java112.analyzer.FileAnalysis;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class FileAnalysisTest {

    private static FileAnalysis analyzeFile;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @BeforeClass
    public static void initialSetUp() {
        analyzeFile = new FileAnalysis();
    }


    @AfterClass
    public static void tearDown() {
        analyzeFile = null;
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
    public void testClassExists() {
        assertNotNull(analyzeFile);
    }


    @Test
    public void testInstanceVariablesExist() {
        Field[] fields = FileAnalysis.class.getDeclaredFields();

        if (fields == null) {
            fail("The FileAnalysis class must have 2 instance variables and at least one constant.");
        }
    }


    @Test
    public void testInstanceVariablesCount() {
        Field[] fields = FileAnalysis.class.getDeclaredFields();
        int instanceVariableCount = 0;

        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (!Modifier.isFinal(modifiers)) {
                instanceVariableCount += 1;
            }
        }

        if (instanceVariableCount != 2) {
            fail("The FileAnalysis class must have exactly 2 instance variables.");
        }
    }


    @Test
    public void testAtLeastOneConstantExists() {
        Field[] fields = FileAnalysis.class.getDeclaredFields();
        int constantCount = 0;

        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isFinal(modifiers)) {
                constantCount += 1;
            }
        }

        if (constantCount < 1) {
            fail("The FileAnalysis class must have at least one constant.");
        }
    }


    @Test
    public void testAnalyzeMethodOneStringArrayParameterExists() throws NoSuchMethodException {
        Method method = FileAnalysis.class.getMethod("analyze", String[].class);

        if (method == null) {
            fail("The FileAnalysis class must have a method named 'analyze' that has a String array parameter");
        }
    }


    @Test
    public void testAnalyzeMethodReturnVoid() throws NoSuchMethodException {
        Method method = FileAnalysis.class.getMethod("analyze", String[].class);

        if (void.class != method.getReturnType()) {
            fail("The FileAnalysis class must have a method named 'analyze' that has a return type of void.");
        }
    }


    @Test
    public void testMethodCountGreaterThanSix() {
        Method[] methods = FileAnalysis.class.getDeclaredMethods();

        if (methods.length < 6) {
            fail("The FileAnalysis class must have at least 6 methods");
        }
    }


    @Test
    public void testWriteOutputFileMethodExists() throws NoSuchMethodException {
        Method method = FileAnalysis.class.getDeclaredMethod("writeOutputFiles", String.class);

        if (method == null) {
            fail("The FileAnalysis class must have a method named 'writeOutputFiles'.");
        }
    }

    @Test
    public void testForEmptyConstructor() throws NoSuchMethodException {
        Constructor constructor = FileAnalysis.class.getConstructor();

        if (constructor == null) {
            fail("The FileAnalysis class must have a no-parameter constructor.");
        }
    }


    /*     @Test
     * public void testImplementOneInterfaceOnly() {
     * Class[] interfaces = FileAnalysis.class.getInterfaces();
     * int size = interfaces.length;
     * assertEquals(1, size);
     *
}
 */
}
