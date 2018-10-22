package com.chrislane.leaderboardcli;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PlayerTest {
    private static int score = 10;
    private static String name = "Alice";
    private static Player player;

    @BeforeMethod
    void init() {
        player = new Player(name, score);
    }

    @Test
    void getNameTest() {
        assertEquals(player.getName(), name);
    }

    @Test
    void getScoreTest() {
        assertEquals(player.getScore(), score);
    }

    @Test(dependsOnMethods = "getScoreTest")
    void setScoreTest() {
        player.setScore(20);
        assertEquals(player.getScore(), 20);
    }
}
