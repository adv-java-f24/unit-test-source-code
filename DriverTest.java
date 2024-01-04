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


public class DriverTest {

    private static Driver driver;


    @BeforeClass
    public static void initialSetUp() {

        driver = new Driver();
    }


    @AfterClass
    public static void tearDown() {
        driver = null;
    }


    @Test
    public void testClassExists() {

        if (driver == null) {
            fail("The Driver class does not exist.");
        }
    }


    @Test
    public void testInstanceVariablesCount() {

        Field[] fields = Driver.class.getDeclaredFields();
        int instanceVariableCount = fields.length;

        if (instanceVariableCount != 0) {
            fail("The Driver class must not have any instance variables or constants.");
        }
    }


    @Test
    public void testMethodOneReturn() throws NoSuchMethodException {

        Method method = Driver.class.getMethod("main", String[].class);

        if (void.class != method.getReturnType()) {
            fail("main method is malformed and must have a void return type.");
        }

    }


    @Test
    public void testMethodOneExists() throws NoSuchMethodException {

        Method method = Driver.class.getMethod("main", String[].class);

        if (method == null) {
            fail("main method is required in the class.");
        }
    }
}
