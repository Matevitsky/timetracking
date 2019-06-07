package com.matevitsky.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public final class TimeParserTest {

    @Test
    public void testParseIntegerTimeToString() {
        String actual = TimeParser.parseIntegerToTimeString(120);
        String actual1 = TimeParser.parseIntegerToTimeString(165);
        assertEquals("02:00", actual);
        assertEquals("02:45", actual1);
    }

    @Test
    public void testParseStringToInteger() {
        Integer expected = 123;
        assertEquals(expected, TimeParser.parseStringTimeToInteger("02:03"));
    }
}
