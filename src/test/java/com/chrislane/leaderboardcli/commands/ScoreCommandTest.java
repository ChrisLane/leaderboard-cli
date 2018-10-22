package com.chrislane.leaderboardcli.commands;

import com.chrislane.leaderboardcli.Player;
import com.chrislane.leaderboardcli.language.LanguageHandler;
import org.testng.annotations.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ScoreCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final List<Player> current = new ArrayList<>();
    private final List<Player> history = new ArrayList<>();
    private final CommandRunner runner =  new CommandRunner();

    @BeforeTest(dependsOnGroups = "CommandRunner.run")
    void initAll() {
        runner.registerCommand("score", new ScoreCommand(current, history));
        LanguageHandler.getInstance().loadLanguageFromFile();
    }

    @BeforeMethod
    void init() {
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @Test(dependsOnGroups = "CommandRunner.run")
    void tooManyArgsTest() {
        runner.runCommand("score alice 1 bob");
        assertEquals(outContent.toString(), "Incorrect number of arguments, expected: <name> <score>\n");
    }

    @Test(dependsOnGroups = "CommandRunner.run")
    void scoreNotAnIntegerTest() {
        runner.runCommand("score alice bob");
        assertEquals(outContent.toString(), "Score must be an integer\n");
    }

    @Test(dependsOnGroups = "CommandRunner.run")
    void addNewPlayerTest() {
        runner.runCommand("score alice 1");
        assertEquals(outContent.toString(), "Play 1 - alice enters the leaderboard at rank 1\n");
    }

    @Test(dependsOnGroups = "CommandRunner.run", dependsOnMethods = "addNewPlayerTest")
    void setExistingPlayerRankUnchanged() {
        runner.runCommand("score alice 1");
        assertEquals(outContent.toString(), "Play 2 - alice stays at rank 1\n");
    }

    @Test(dependsOnGroups = "CommandRunner.run", dependsOnMethods = "setExistingPlayerRankUnchanged")
    void setExistingPlayerRankIncrease() {
        runner.runCommand("score bob 10");
        outContent.reset();
        runner.runCommand("score alice 20");
        assertEquals(outContent.toString(), "Play 4 - alice climbs from rank 2 to 1, above bob\n");
    }

    @Test(dependsOnGroups = "CommandRunner.run", dependsOnMethods = "setExistingPlayerRankIncrease")
    void setExistingPlayerRankDecrease() {
        runner.runCommand("score alice 1");
        assertEquals(outContent.toString(), "Play 5 - alice drops from rank 1 to 2, below bob\n");
    }

    @AfterMethod
    void destroy() {
        System.setOut(originalOut);
    }
}
