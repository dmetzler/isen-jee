package org.isen.strcalc;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {

    private StringCalculator sc;

    @Before
    public void doBefore() {
        sc = new StringCalculator();
    }

    @Test
    public void anEmptyStringReturn0() throws Exception {
        assertEquals(0, sc.add(""));
    }

    @Test
    public void aSingleNumberReturnsTheNumber() throws Exception {
        assertEquals(1, sc.add("1"));
    }

    @Test
    public void numberSeparatedByACommaAreSummed() throws Exception {
        assertEquals(3, sc.add("1,2"));
    }

    @Test
    public void itCanSumAnArbitraryNumber() throws Exception {
        assertEquals(6, sc.add("1,2,3"));
    }

    @Test
    public void valuesCanBeSeparatedByANewline() throws Exception {
        assertEquals(6, sc.add("1\n2,3"));
    }

    @Test
    public void userCanDefineItsDelimiter() throws Exception {
        assertEquals(5, sc.add("//#\n2#3"));
    }

    @Test
    public void userCanDefineItsDelimiterWithRegexInside() throws Exception {
        assertEquals(5, sc.add("//?\n2?3"));
    }
}
