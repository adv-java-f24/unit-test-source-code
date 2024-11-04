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

public class DistinctTokensAnalyzerProcessTokenTest {

    private static DistinctTokensAnalyzer analyzer;
    private static Properties properties;

    @BeforeClass
    public static void initialSetUp() {

        // The following commented-out lines are for projects 2-4
        properties = new Properties();
        properties.setProperty("output.dir", "output/");
        properties.setProperty("output.file.unique", "test_unique_tokens.txt");

        analyzer = new DistinctTokensAnalyzer(properties);
    }

    @AfterClass
    public static void tearDown() {
        analyzer = null;
    }

    @Test
    public void uniqueTokensSetCreationTest() {
        Set testSet = analyzer.getDistinctTokens();

        if (testSet == null) {
            fail("\t****** The DistinctTokensAnalyzer class must have a Set named 'distinctTokens'.\n");
        }
    }

    @Test
    public void uniqueTokensSetEmptyTest() {
        Set testSet = analyzer.getDistinctTokens();
        int size = testSet.size();

        if (size != 0) {
            fail("\t****** The distinctTokens Set must start with no elements.\n");
        }
    }

    @Test
    public void classExists() {
        assertNotNull(analyzer);
    }

    @Test
    public void processTokenReturnVoidTest() throws NoSuchMethodException {
        Method method = DistinctTokensAnalyzer.class.getMethod("processToken", String.class);

        if (void.class != method.getReturnType()) {
            fail("\t****** The 'processToken()' method must have a return type of void.\n");
        }
    }

    @Test
    public void processTokenExistsTest() throws NoSuchMethodException {
        Method method = DistinctTokensAnalyzer.class.getMethod("processToken", String.class);

        if (method == null) {
            fail("\t****** The DistinctTokensAnalyzer class must have a method named 'processToken.\n'");
        }
    }

    @Test
    public void constructorExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = DistinctTokensAnalyzer.class.getConstructor();

        if (constructor == null) {
            fail("\t****** The DistinctTokensAnalyzer must have a zero-parameter constructor.\n");
        }
    }

    // The following method is for projects 2-4
    @Test
    public void constructorOneParameterExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = DistinctTokensAnalyzer.class.getConstructor(Properties.class);
        assertNotNull(constructor);
    }

    @Test
    public void setMethodForTokensListNotCreatedTest() {

        Method method = null;
        try {
            method = DistinctTokensAnalyzer.class.getMethod("setDistinctTokens", Set.class);
            fail("\t****** The DistinctTokensAnalyzer class must not have a 'setDistinctTokens' method.\n");
        } catch (java.lang.NoSuchMethodException nsme) {
            //no op
        }
    }

    @Test
    public void instanceVariablesCountTest() {

        Field[] fields = DistinctTokensAnalyzer.class.getDeclaredFields();
        int instanceVariableCount = 0;

        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (!Modifier.isFinal(modifiers)) {
                instanceVariableCount += 1;
            }
        }

        if (instanceVariableCount != 2) {
            fail("\t****** The DistinctTokensAnalyzer class must have two instance variable.\n");
        }
    }


    @Test
    public void implementAnalyzerInterfaceTest() {
        Class[] interfaces = DistinctTokensAnalyzer.class.getInterfaces();

        if (interfaces[0] != TokenAnalyzer.class) {
            fail("\t****** The DistinctTokensAnalyzer class must only implement the TokenAnalyzer interface.\n");
        }
    }

    @Test
    public void implementOneInterfaceOnlyTest() {
        Class[] interfaces = DistinctTokensAnalyzer.class.getInterfaces();
        int size = interfaces.length;

        if (size != 1) {
            fail("\t****** The DistinctTokensAnalyzer class must implement only one interface.\n");
        }
    }

}
