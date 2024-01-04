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
import java112.analyzer.FileAnalysis;

public class FileAnalysisMethodsTest {

    private static FileAnalysis analyzeFile;

    @BeforeClass
    public static void initialSetUp() {
        analyzeFile = new FileAnalysis();
    }

    @AfterClass
    public static void tearDown() {
        analyzeFile = null;
    }

    @Test
    public void testClassExists() {
        assertNotNull(analyzeFile);
    }

    @Test
    public void testInstanceVariablesExist() {
        Field[] fields = FileAnalysis.class.getDeclaredFields();
        assertNotNull(fields);
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

      if (instanceVariableCount != 1) {
          fail("\t****** The FileAnalysis class must have only one instance variable.\n");
      }
    }

    @Test
    public void testRunAnalyzeOneStringArrayParamExists() throws NoSuchMethodException {
        Method method = FileAnalysis.class.getMethod("analyze", String[].class);

        if (method == null) {
            fail("\t****** This class must have a method name 'analyze' with one parameter of an array of Strings.\n");
        }

    }

    @Test
    public void testRunAnalysisMethodReturnVoid() throws NoSuchMethodException {
        Method method = FileAnalysis.class.getMethod("analyze", String[].class);

        if (void.class != method.getReturnType()) {
            fail("\t****** The 'analyze' method must have a void return type\n");
        }
    }

    @Test
    public void testMethodCountGreaterThanSix() {
        Method[] methods = FileAnalysis.class.getDeclaredMethods();

        if (methods.length < 6) {
            fail("\t****** The FileAnalysis class must have at least 6 methods.\n");
        }

    }

    @Test
    public void testWriteAllOutputFileMethod() throws java.lang.NoSuchMethodException {
        Method method = FileAnalysis.class.getDeclaredMethod("writeOutputFiles", String.class);

        if (method == null) {
            fail("\t****** The FileAnalysis class must have a method named writeOutputFiles\n");
        }
    }

    @Test
    public void testImplementOneInterfaceOnly() {
        Class[] interfaces = FileAnalysis.class.getInterfaces();
        int size = interfaces.length;

        if (size != 1) {
            fail("\t****** The FileAnalysis class must implement one interface.\n");
        }
    }

}
