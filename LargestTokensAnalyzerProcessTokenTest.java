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
import java.lang.reflect.*;
import java112.analyzer.*;

public class LargestTokensAnalyzerProcessTokenTest {

    private static LargestTokensAnalyzer analyzer;
    private static Properties properties;

    @BeforeClass
    public static void initialSetUp() {
        properties = new Properties();
        properties.setProperty("output.directory", "output/");
        properties.setProperty("output.file.largest.words", "test_bigwords.txt");
        properties.setProperty("largest.words.minimum.length", "4");

        analyzer = new LargestTokensAnalyzer(properties);
    }

    @AfterClass
    public static void tearDown() {
        analyzer = null;
    }

    @Test
    public void testBigWordsSetCreation() {
        Set testSet = analyzer.getLargestTokens();
        assertNotNull(testSet);
    }

    @Test
    public void testBigWordsSetEmpty() {
        Set testSet = analyzer.getLargestTokens();
        int size = testSet.size();
        assertEquals(0, size);
    }

    @Test
    public void testClassExists() {
        assertNotNull(analyzer);
    }

    @Test
    public void testInstanceVariablesCount() {
        Field[] fields = LargestTokensAnalyzer.class.getDeclaredFields();
        int instanceVariablecount = 0;

        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (!Modifier.isFinal(modifiers)) {
                instanceVariablecount += 1;
            }
        }

        if (instanceVariablecount != 3) {
            fail("\t****** The LargestTokensAnalyzer class must have 3 instance variables.\n");
        }
    }

    @Test
    public void testProcessTokenReturnVoid() throws NoSuchMethodException {
        Method method = LargestTokensAnalyzer.class.getMethod("processToken", String.class);

        if (void.class != method.getReturnType()) {
            fail("\t****** The processToken method must have a void return.\n");
        }
    }

    @Test
    public void testProcessTokenExists() throws NoSuchMethodException {
        Method method = LargestTokensAnalyzer.class.getMethod("processToken", String.class);
        assertNotNull(method);
    }

    @Test
    public void testConstructorExists() throws java.lang.NoSuchMethodException {
        Constructor constructor = LargestTokensAnalyzer.class.getConstructor();
        assertNotNull(constructor);
    }

    @Test
    public void testConstructorOneParameterExists() throws java.lang.NoSuchMethodException {
        Constructor constructor = LargestTokensAnalyzer.class.getConstructor(Properties.class);
        assertNotNull(constructor);
    }

    @Test
    public void testSetMethodForBigWordsSetNotCreated() {

        Method method = null;
        try {
            method = LargestTokensAnalyzer.class.getMethod("setBigWordsSet", Set.class);
        } catch (java.lang.NoSuchMethodException nsme) {
            //no op, we want this to fail
        }

        assertNull(method);
    }

    @Test
    public void testImplementAnalyzerInterface() {
        Class[] interfaces = LargestTokensAnalyzer.class.getInterfaces();
        assertEquals(interfaces[0], TokenAnalyzer.class);
    }

    @Test
    public void testImplementOneInterfaceOnly() {
        Class[] interfaces = LargestTokensAnalyzer.class.getInterfaces();
        int size = interfaces.length;
        assertEquals(1, size);
    }

}
