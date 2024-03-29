package com.chrislane.leaderboardcli;

import javax.annotation.Nonnull;

public class Player implements Comparable<Player> {
    private final String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Compare players by their scores. Players with higher scores > players with lower scores.
     */
    @Override
    public int compareTo(@Nonnull Player player) {
        return Integer.compare(score, player.getScore());
    }

    @Override
    public String toString() {
        return name + ": " + score;
    }

    public Player copy() {
        return new Player(name, score);
    }
}
