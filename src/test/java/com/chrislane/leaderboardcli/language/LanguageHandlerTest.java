package com.chrislane.leaderboardcli.language;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class LanguageHandlerTest {
    private static LanguageHandler languageHandler;
    private List<LanguageEntry> language = new ArrayList<>();

    @BeforeMethod
    void init() {
        language.clear();
        languageHandler = new LanguageHandler(language);
    }

    @Test
    void getEntryTest() {
        language.add(new LanguageEntry("key", "value"));
        assertEquals(languageHandler.getEntry("key"), "value");
    }

    @Test(dependsOnMethods = "getEntryTest")
    void addLineToLanguageTest() {
        languageHandler.addLineToLanguage("key=value");
        assertEquals(languageHandler.getEntry("key"), "value");
    }

    @Test(dependsOnMethods = "addLineToLanguageTest")
    void loadLanguageFromFileTest() {
        languageHandler.loadLanguageFromFile();
        assertEquals(languageHandler.getEntry("key1"), "value1");
        assertEquals(languageHandler.getEntry("key2"), "value2");
        assertEquals(languageHandler.getEntry("key3"), "value3");
    }
}
