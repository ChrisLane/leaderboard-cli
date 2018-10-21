package com.chrislane.leaderboardcli.language;

class LanguageEntry {
    private final String key;
    private final String value;

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
