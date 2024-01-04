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
import java112.analyzer.TokenAnalyzer;
import java112.analyzer.DistinctTokenCountsAnalyzer;

public class DistinctTokenCountsAnalyzerProcessTokenTest {

    private static DistinctTokenCountsAnalyzer analyzer;
    private static Properties properties;

    @BeforeClass
    public static void initialSetUp() {
        properties = new Properties();
        properties.setProperty("output.directory", "output/");
        properties.setProperty("output.file.distinct.counts", "test_token_count.txt");

        analyzer = new DistinctTokenCountsAnalyzer(properties);
    }

    @AfterClass
    public static void tearDown() {
        analyzer = null;
    }

    @Test
    public void testUniqueTokensSetCreation() {
        Map testMap = analyzer.getDistinctTokenCounts();
        assertNotNull(testMap);
    }

    @Test
    public void testUniqueTokensSetEmpty() {
        Map testMap = analyzer.getDistinctTokenCounts();
        int size = testMap.size();
        assertEquals(0, size);
    }

    @Test
    public void testClassExists() {
        assertNotNull(analyzer);
    }

    @Test
    public void testInstanceVariablesCount() {
        Field[] fields = DistinctTokenCountsAnalyzer.class.getDeclaredFields();
        int instanceVariableCount = 0;

        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (!Modifier.isFinal(modifiers)) {
                instanceVariableCount += 1;
            }
        }

        assertEquals(2, instanceVariableCount);
    }

    @Test
    public void testProcessTokenReturnVoid() throws NoSuchMethodException {
        Method method = DistinctTokenCountsAnalyzer.class.getMethod("processToken", String.class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void testProcessTokenExists() throws NoSuchMethodException {
        Method method = DistinctTokenCountsAnalyzer.class.getMethod("processToken", String.class);
        assertNotNull(method);
    }

    @Test
    public void testPonstructorExists() throws java.lang.NoSuchMethodException {
        Constructor constructor = DistinctTokenCountsAnalyzer.class.getConstructor();
        assertNotNull(constructor);
    }

    @Test
    public void testConstructorOneParameterExists() throws java.lang.NoSuchMethodException {
        Constructor constructor = DistinctTokenCountsAnalyzer.class.getConstructor(Properties.class);
        assertNotNull(constructor);
    }

    @Test
    public void testSetMethodForTokenCountSetNotCreated() {

        Method method = null;
        try {
            method = DistinctTokenCountsAnalyzer.class.getMethod("setDistinctTokenCounts", Map.class);
        } catch (java.lang.NoSuchMethodException nsme) {
            //no op, we want this to fail
        }

        assertNull(method);
    }

    @Test
    public void testImplementAnalyzerInterface() {
        Class[] interfaces = DistinctTokenCountsAnalyzer.class.getInterfaces();
        assertEquals(interfaces[0], TokenAnalyzer.class);
    }

    @Test
    public void testImplementOneInterfaceOnly() {
        Class[] interfaces = DistinctTokenCountsAnalyzer.class.getInterfaces();
        int size = interfaces.length;
        assertEquals(1, size);
    }

}
