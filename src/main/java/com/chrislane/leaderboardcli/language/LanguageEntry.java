package com.chrislane.leaderboardcli.language;

public class LanguageEntry {
    private String key;
    private String value;

    public LanguageEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
