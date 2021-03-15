package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static PolyProcessor processor = null;

    @BeforeClass
    public static void setUp() {
        processor = new PolyProcessor();
    }

    @Test
    public void testConst() {
        // String str = "100\n";
        // ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes());
        // System.setIn(bis);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        processor.processExpress("100");
        processor.printDerivative();
        assertEquals("0\n", bos.toString());
    }

    @Test
    public void testWithX() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        processor.processExpress("100 * x");
        processor.printDerivative();
        assertEquals("100\n", bos.toString());
    }

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}
