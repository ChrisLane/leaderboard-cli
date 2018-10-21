package com.chrislane.leaderboardcli.commands;

public class CommandTuple {
    String command;
    CommandHandler commandHandler;

    public CommandTuple(String command, CommandHandler commandHandler) {
        this.command = command;
        this.commandHandler = commandHandler;
    }

    public String getCommand() {
        return command;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}
