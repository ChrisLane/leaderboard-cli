package com.chrislane.leaderboardcli.language;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class LanguageHandler {
    private final static LanguageHandler instance = new LanguageHandler();
    private List<LanguageEntry> language = new ArrayList<>();

    public static LanguageHandler getInstance() {
        return instance;
    }

    private LanguageHandler() {
    }

    /**
     * Do not use this, it is for testing purposes only. Instead use {@link #getInstance()}
     *
     * @param language The language store list.
     */
    public LanguageHandler(List<LanguageEntry> language) {
        this.language = language;
    }

    /**
     * Find the value for a given language entry key.
     *
     * @return The entry value or the key if none was found.
     */
    public String getEntry(String key) {
        Optional<LanguageEntry> entry = language.stream()
                .filter(languageEntry -> languageEntry.getKey().equals(key))
                .findAny();

        return entry.isPresent() ? entry.get().getValue() : key;
    }

    public void loadLanguageFromFile() {
        try {
            URI fileUri = getClass().getResource("/en-gb.lang").toURI();
            try (Stream<String> stream = Files.lines(Paths.get(fileUri))) {
                stream.forEach(this::addLineToLanguage);
            } catch (FileSystemNotFoundException ignored) {
                Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                FileSystems.newFileSystem(fileUri, env);
                try (Stream<String> stream = Files.lines(Paths.get(fileUri))) {
                    stream.forEach(this::addLineToLanguage);
                }
            }
        } catch (URISyntaxException | IOException e) {
            System.err.println("Failed to load language file");
            e.printStackTrace();
        }
    }

    /**
     * Split the key/value string into key and value and then add them as an entry in the language.
     *
     * @param line The key/value string.
     */
    public void addLineToLanguage(String line) {
        String[] lineElements = line.split("=", 2);
        if (lineElements.length == 2) {
            language.add(new LanguageEntry(lineElements[0], lineElements[1]));
        } else {
            System.err.printf("No value for %s", lineElements[0]);
        }
    }
}
