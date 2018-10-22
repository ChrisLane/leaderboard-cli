package com.chrislane.leaderboardcli.commands;

import com.chrislane.leaderboardcli.Player;
import com.chrislane.leaderboardcli.language.LanguageHandler;
import org.testng.annotations.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class BoardCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private CommandRunner runner = new CommandRunner();
    private List<Player> current = new ArrayList<>();
    private List<Player> history = new ArrayList<>();

    @BeforeTest(dependsOnGroups = "CommandRunner.run")
    void initAll() {
        runner.registerCommand("board", new BoardCommand(current, history));
        LanguageHandler.getInstance().loadLanguageFromFile();
    }

    @BeforeMethod
    void init() {
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @Test(dependsOnGroups = "CommandRunner.run")
    void playNotAnIntegerTest() {
        runner.runCommand("board alice");
        assertEquals(outContent.toString(), "Play number must be an integer\n");
    }

    @Test(dependsOnGroups = "CommandRunner.run")
    void playDoesNotExistTest() {
        history.clear();
        runner.runCommand("board 10");
        assertEquals(outContent.toString(), "Play count does not exist\n");
    }

    @Test(dependsOnGroups = "CommandRunner.run")
    void tooManyArgsTest() {
        runner.runCommand("board 1 alice");
        assertEquals(outContent.toString(), "Too many arguments, expected: [play-number]\n");
    }

    @AfterMethod
    void destroy() {
        System.setOut(originalOut);
    }
}
