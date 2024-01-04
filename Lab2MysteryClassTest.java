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
import java112.labs1.MysteryClassOne;

public class Lab2MysteryClassTest {

    private static MysteryClassOne mysteryClass;

    @BeforeClass
    public static void initialSetUp() {

        mysteryClass = new MysteryClassOne();

    }

    @AfterClass
    public static void tearDown() {
        mysteryClass = null;
    }

    @Test
    public void classExists() {
        assertNotNull(mysteryClass);
    }

    @Test
    public void methodOneReturnTest() throws NoSuchMethodException {

        Method method = MysteryClassOne.class.getMethod("mysteryMethodOne");

        if (int.class != method.getReturnType()) {
            fail("\t****** The class must have a method named 'mysteryMethodOne' that returns an int.\n");
        }

    }

    @Test
    public void methodOneExistsTest() throws NoSuchMethodException {

        Method method = MysteryClassOne.class.getMethod("mysteryMethodOne");

        if (method == null) {
            fail("\t****** The class must have a method named 'mysteryMethodOne'\n");
        }

    }

    @Test
    public void runMethodOneTest() {

        int result = mysteryClass.mysteryMethodOne();

        if (result != 1) {
            fail("\t****** The mysteryMethodOne method must return the value of 1.\n");
        }

    }




}
