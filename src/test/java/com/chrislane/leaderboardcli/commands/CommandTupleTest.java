package com.chrislane.leaderboardcli.commands;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CommandTupleTest {
    private CommandTuple tuple;
    private CommandHandler handler;

    @BeforeTest
    void initAll() {
        handler = new HelpCommand();
        tuple = new CommandTuple("command", handler);
    }

    @Test
    void getCommandTest() {
        assertEquals(tuple.getCommand(), "command");
    }

    @Test
    void getCommandHandlerTest() {
        assertEquals(tuple.getCommandHandler(), handler);
    }
}
