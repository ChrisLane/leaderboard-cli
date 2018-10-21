package com.chrislane.leaderboardcli.commands;

class CommandTuple {
    private final String command;
    private final CommandHandler commandHandler;

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
