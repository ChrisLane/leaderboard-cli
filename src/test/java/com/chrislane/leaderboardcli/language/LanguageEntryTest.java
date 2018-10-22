package com.chrislane.leaderboardcli.language;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LanguageEntryTest {
    private static String key = "key";
    private static String value = "value";
    private static LanguageEntry entry;

    @BeforeMethod
    void init() {
        entry = new LanguageEntry(key, value);
    }

    @Test
    void getKeyTest() {
        assertEquals(entry.getKey(), key);
    }

    @Test
    void getValueTest() {
        assertEquals(entry.getValue(), value);
    }
}
