package com.chrislane.leaderboardcli.language;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class LanguageHandler {
    private final static LanguageHandler instance = new LanguageHandler();
    private List<LanguageEntry> language = new ArrayList<>();

    public static LanguageHandler getInstance() {
        return instance;
    }

    public LanguageHandler() {
    }

    public LanguageHandler(List<LanguageEntry> language) {
        this.language = language;
    }

    public String getEntry(String key) {
        Optional<LanguageEntry> entry = language.stream()
                .filter(languageEntry -> languageEntry.getKey().equals(key))
                .findAny();

        return entry.isPresent() ? entry.get().getValue() : key;
    }

    public void loadLanguageFromFile() {
        try (Stream<String> stream = Files.lines(Paths.get(getClass().getResource("/en-gb.lang").toURI()))) {
            stream.forEach(this::addLineToLanguage);
        } catch (URISyntaxException | IOException e) {
            System.err.println("Failed to load language file");
            e.printStackTrace();
        }
    }

    public void addLineToLanguage(String line) {
        String[] lineElements = line.split("=", 2);
        if (lineElements.length == 2) {
            language.add(new LanguageEntry(lineElements[0], lineElements[1]));
        } else {
            System.err.printf("No value for %s", lineElements[0]);
        }
    }
}
