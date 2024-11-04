package java112.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

// import java.lang.reflect.Method;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import java112.analyzer.*;

public class FileSummaryAnalyzerTest {

    private static FileSummaryAnalyzer analyzer;
    private static Properties properties;

    @BeforeClass
    public static void initialSetUp() {

        // The following lines are commented out project 1
        //properties = new Properties();
        //properties.setProperty("author", "Eric Knapp");
        //properties.setProperty("output.dir", "output/");
        //properties.setProperty("email", "eknapp@matcmadison.edu");
        //properties.setProperty("output.file.summary", "test_summary.txt");

        analyzer = new FileSummaryAnalyzer();
    }

    @AfterClass
    public static void tearDown() {
        analyzer = null;
    }

    @Test
    public void classExists() {
        assertNotNull(analyzer);
    }

    @Test
    public void processTokenReturnVoidTest() throws NoSuchMethodException {
        Method method = FileSummaryAnalyzer.class.getMethod("processToken", String.class);

        if (void.class != method.getReturnType()) {
            fail("\t****** The 'processToken()' method must have a void return.\n");
        }
    }

    @Test
    public void processTokenExistsTest() throws NoSuchMethodException {
        Method method = FileSummaryAnalyzer.class.getMethod("processToken", String.class);

        if (method == null) {
            fail("\t****** The FileSummaryAnalyzer class must have a one-parameter method named 'processToken'.\n");
        }
    }

    @Test
    public void processTokenInitialCount() {

        int initialCount = analyzer.getTotalTokensCount();

        if (initialCount != 0) {
            fail("\t****** The FileSummaryAnalyzer class's totalTokensCount variable must be initialized to zero.\n");
        }
    }

    @Test
    public void processTokenAddTokenTest() {
        analyzer.processToken("test");

        if (analyzer.getTotalTokensCount() != 1) {
            fail("\t****** The FileSummaryAnalyzer class is not counting tokens properly.\n");
        }
    }

    @Test
    public void instanceVariablesCountTest() {

        Field[] fields = FileSummaryAnalyzer.class.getDeclaredFields();
        int instanceVariableCount = 0;

        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (!Modifier.isFinal(modifiers)) {
                instanceVariableCount += 1;
            }
        }

        if (instanceVariableCount != 2) {
            fail("\t****** The FileSummaryAnalyzer class must have two instance variables.\n");
        }
    }


    @Test
    public void setMethodForTokensCountNotCreatedTest() {
        Method method = null;

        try {
            method = FileSummaryAnalyzer.class.getMethod("setTotalTokensCount", int.class);
            fail("\t****** The FileSummaryAnalyzer class can't have a setTotalTokensCount() method.\n");
        } catch (NoSuchMethodException noMethod) {
            // nothing to do here.
        }
    }

    @Test
    public void implementAnalyzerInterfaceTest() {
        Class[] interfaces = FileSummaryAnalyzer.class.getInterfaces();

        if (interfaces[0] != TokenAnalyzer.class) {
            fail("\t****** The FileSummaryAnalyzer must implement the TokenAnalyzer interface.\n");
        }
    }

    @Test
    public void implementOneInterfaceOnlyTest() {
        Class[] interfaces = FileSummaryAnalyzer.class.getInterfaces();
        int size = interfaces.length;

        if (size != 1) {
            fail("\t****** The FileSummaryAnalyzer class must only implement one interface.\n");
        }

        assertEquals(1, size);
    }

    @Test
    public void constructorExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = FileSummaryAnalyzer.class.getConstructor();
        assertNotNull(constructor);
    }

    // The following is for projects 2 through 4.
    //@Test
    //public void constructorOneParameterExistsTest() throws java.lang.NoSuchMethodException {
    //    Constructor constructor = FileSummaryAnalyzer.class.getConstructor(Properties.class);
    //    assertNotNull(constructor);
    //}


}
