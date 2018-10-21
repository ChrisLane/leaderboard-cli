package com.chrislane.leaderboardcli.commands;

import java.util.*;

public class CommandRunner {
    private final List<CommandTuple> commands = new ArrayList<>();

    public void registerCommand(String command, CommandHandler commandHandler) {
        commands.add(new CommandTuple(command, commandHandler));
    }

    public boolean runCommand(String userInput) {
        String[] input = userInput.split(" ");
        String[] args = Arrays.copyOfRange(input, 1, input.length);

        getCommandHandler(input[0]).ifPresent(commandHandler -> commandHandler.runCommand(args));
        return getCommandHandler(input[0]).isPresent();
    }

    private Optional<CommandHandler> getCommandHandler(String command) {
        return commands.stream()
                .filter(commandTuple -> commandTuple.getCommand().equals(command))
                .findAny()
                .map(CommandTuple::getCommandHandler);
    }
}
