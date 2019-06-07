package com.matevitsky.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public final class MD5UtilTest {


    @Test
    public void encryptPassword() {
        String expected = "81dc9bdb52d04dc20036dbd8313ed055";
        assertEquals(expected, MD5Util.encryptPassword("1234"));
    }
}
