package com.chrislane.leaderboardcli.language;

class LanguageEntry {
    /**
     * The value that used to find a language text.
     */
    private final String key;
    private final String value;

    public LanguageEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Get the identifying information (key) for the language key/value pair
     */
    public String getKey() {
        return key;
    }

    /**
     * Get the value for the language key/value pair
     */
    public String getValue() {
        return value;
    }
}
