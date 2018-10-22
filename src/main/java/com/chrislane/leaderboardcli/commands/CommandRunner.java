package com.chrislane.leaderboardcli.commands;

import java.util.*;

public class CommandRunner {
    private final List<CommandTuple> commands = new ArrayList<>();

    /**
     * Register a new command.
     */
    public void registerCommand(String command, CommandHandler commandHandler) {
        commands.add(new CommandTuple(command, commandHandler));
    }

    /**
     * Run a command.
     *
     * @param userInput The command string and arguments.
     * @return Whether the command was known or not.
     */
    public boolean runCommand(String userInput) {
        String[] input = userInput.split(" ");
        String[] args = Arrays.copyOfRange(input, 1, input.length);

        getCommandHandler(input[0]).ifPresent(commandHandler -> commandHandler.runCommand(args));
        return getCommandHandler(input[0]).isPresent();
    }

    /**
     * Get the command handler for a command string.
     */
    private Optional<CommandHandler> getCommandHandler(String command) {
        return commands.stream()
                .filter(commandTuple -> commandTuple.getCommand().equals(command))
                .findAny()
                .map(CommandTuple::getCommandHandler);
    }
}
