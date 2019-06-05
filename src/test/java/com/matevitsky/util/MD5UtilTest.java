package com.matevitsky.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MD5Util.class)
public class MD5UtilTest {

    @Before
    public void setUp() {
        PowerMockito.mockStatic(MD5Util.class);
        PowerMockito.when(MD5Util.encryptPassword("1234")).thenReturn("81dc9bdb52d04dc20036dbd8313ed055");
    }

    @Test
    public void encryptPassword() {
        String expected = "81dc9bdb52d04dc20036dbd8313ed055";
        assertEquals(expected, MD5Util.encryptPassword("1234"));
    }
}
