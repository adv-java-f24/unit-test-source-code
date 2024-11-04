package java112.tests;


import java.io.*;
import java.lang.reflect.*;
import java.lang.reflect.Method;
import java.util.*;

import java112.analyzer.*;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TokenLengthsAnalyzerProcessTokenTest {

    private static TokenLengthsAnalyzer analyzer;
    private static Properties properties;


    @BeforeClass
    public static void initialSetUp() throws FileNotFoundException, Exception {
        properties = new Properties();
        properties.setProperty("output.directory", "output/");
        properties.setProperty("output.file.token.lengths", "test_token_lengths.txt");

        analyzer = new TokenLengthsAnalyzer(properties);
    }


    @AfterClass
    public static void tearDown() {
        analyzer = null;

        File file = new File(properties.getProperty("output.file.token.lengths"));
        file.delete();
    }


    @Test
    public void testTokenLengthsMapCreation() {
        Map testMap = analyzer.getTokenLengths();
        assertNotNull(testMap);
    }


    @Test
    public void testKeywordsMapHasZeroKeys() {
        Map testMap = analyzer.getTokenLengths();
        int size = testMap.size();
        assertEquals(0, size);
    }


    @Test
    public void testClassExists() {
        assertNotNull(analyzer);
    }


    @Test
    public void testInstanceVariablesCount() {
        Field[] fields = TokenLengthsAnalyzer.class.getDeclaredFields();
        int instanceVariableCount = 0;

        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (!Modifier.isFinal(modifiers)) {
                instanceVariableCount += 1;
            }
        }

        if (instanceVariableCount != 2) {
            fail("\t****** TokenLengthsAnalyzer class must have two and only two instance variables.\n");
        }
    }


    @Test
    public void testProcessTokenReturnVoid() throws NoSuchMethodException {
        Method method = TokenLengthsAnalyzer.class.getMethod("processToken", String.class);
        assertEquals(void.class, method.getReturnType());
    }


    @Test
    public void testProcessTokenExists() throws NoSuchMethodException {
        Method method = TokenLengthsAnalyzer.class.getMethod("processToken", String.class);
        assertNotNull(method);
    }


    @Test
    public void testConstructorExists() throws java.lang.NoSuchMethodException {
        Constructor constructor = TokenLengthsAnalyzer.class.getConstructor();
        assertNotNull(constructor);
    }


    @Test
    public void testConstructorOneParameterExists() throws java.lang.NoSuchMethodException {
        Constructor constructor = TokenLengthsAnalyzer.class.getConstructor(Properties.class);
        assertNotNull(constructor);
    }


    @Test
    public void testSetMethodForTokenSizesNotCreated() {

        Method method = null;
        try {
            method = TokenLengthsAnalyzer.class.getMethod("setTokenSizes", Set.class);
        } catch (java.lang.NoSuchMethodException nsme) {
            // no op, we want this to fail
        }

        assertNull(method);
    }


    @Test
    public void testImplementAnalyzerInterface() {
        Class[] interfaces = TokenLengthsAnalyzer.class.getInterfaces();
        assertEquals(interfaces[0], TokenAnalyzer.class);
    }


    @Test
    public void testImplementOneInterfaceOnly() {
        Class[] interfaces = TokenLengthsAnalyzer.class.getInterfaces();
        int size = interfaces.length;
        assertEquals(1, size);
    }
}
