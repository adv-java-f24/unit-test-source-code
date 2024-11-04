package java112.tests;


import java.io.*;
import java.lang.reflect.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.net.*;

import java112.analyzer.*;

import org.junit.AfterClass;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class TokenLocationSearchAnalyzerProcessTokenTest {

    private static TokenLocationSearchAnalyzer analyzer;
    private static Properties properties;
    private static PrintWriter out;


    @BeforeClass
    public static void initialSetUp() throws FileNotFoundException, Exception {
        properties = new Properties();
        properties.setProperty("output.directory", "output/");
        properties.setProperty("output.file.token.search.locations", "test_locations.txt");
        properties.setProperty("classpath.search.tokens", "/test_keywords.txt");

        out = new PrintWriter("config/test_keywords.txt");

        out.println("the");
        out.println("and");
        out.println("if");

        out.close();
        analyzer = new TokenLocationSearchAnalyzer(properties);
    }


    @AfterClass
    public static void tearDown() {
        analyzer = null;

        String searchTokensClasspath = properties.getProperty("classpath.search.tokens");
        URL searchTokensURL = TokenLocationSearchAnalyzerOutputTest.class.getResource(searchTokensClasspath);
        String searchTokensFilePath = searchTokensURL.getPath();

        File file = new File(searchTokensFilePath);

        file.delete();
        out.close();
    }


    @Test
    public void foundLocationsMapCreationTest() {
        Map testMap = analyzer.getFoundLocations();
        assertNotNull(testMap);
    }


    @Test
    public void foundLocationsMapHasThreeKeysTest() {
        Map testMap = analyzer.getFoundLocations();
        int size = testMap.size();
        assertEquals(3, size);
    }


    @Test
    public void classExists() {
        assertNotNull(analyzer);
    }


    @Test
    public void instanceVariablesCountTest() {

        Field[] fields = TokenLocationSearchAnalyzer.class.getDeclaredFields();
        int instanceVariableCount = 0;

        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (!Modifier.isFinal(modifiers)) {
                instanceVariableCount += 1;
            }
        }

        if (instanceVariableCount != 3) {
            fail("\t****** TokenLocationSearchAnalyzer must have 3 and only 3 instance variables.\n");
        }
    }


    @Test
    public void processTokenReturnVoidTest() throws NoSuchMethodException {
        Method method = TokenLocationSearchAnalyzer.class.getMethod("processToken", String.class);
        assertEquals(void.class, method.getReturnType());
    }


    @Test
    public void processTokenExistsTest() throws NoSuchMethodException {
        Method method = TokenLocationSearchAnalyzer.class.getMethod("processToken", String.class);
        assertNotNull(method);
    }


    @Test
    public void constructorExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = TokenLocationSearchAnalyzer.class.getConstructor();
        assertNotNull(constructor);
    }


    @Test
    public void constructorOneParameterExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = TokenLocationSearchAnalyzer.class.getConstructor(Properties.class);
        assertNotNull(constructor);
    }


    @Test
    public void setMethodForKeywordsMapNotCreatedTest() {

        Method method = null;
        try {
            method = TokenLocationSearchAnalyzer.class.getMethod("setFoundLocationsMap", Set.class);
        } catch (java.lang.NoSuchMethodException nsme) {
            // no op, we want this to fail
        }

        assertNull(method);
    }


    @Test
    public void implementAnalyzerInterfaceTest() {
        Class[] interfaces = TokenLocationSearchAnalyzer.class.getInterfaces();
        assertEquals(interfaces[0], TokenAnalyzer.class);
    }


    @Test
    public void implementOneInterfaceOnlyTest() {
        Class[] interfaces = TokenLocationSearchAnalyzer.class.getInterfaces();
        int size = interfaces.length;
        assertEquals(1, size);
    }
}
