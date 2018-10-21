package com.chrislane.leaderboardcli;

import com.chrislane.leaderboardcli.commands.*;
import com.chrislane.leaderboardcli.language.LanguageHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class LeaderboardCli {
    private final static Scanner input = new Scanner(System.in);
    private final static CommandRunner runner = new CommandRunner();
    private final static List<Player> history = new ArrayList<>();
    private final static List<Player> currentState = new ArrayList<>();

    public static void main(String[] args) {
        // Read language file
        LanguageHandler.getInstance().loadLanguageFromFile();

        // Register commands
        runner.registerCommand("help", new HelpCommand());
        runner.registerCommand("score", new ScoreCommand(currentState, history));
        runner.registerCommand("highest-alltime-score", new HighScoreCommand(history));
        runner.registerCommand("board", new BoardCommand(currentState, history));

        // Read command input
        while (input.hasNextLine()) {
            // Attempt to run the command
            if (!runner.runCommand(input.nextLine())) {
                // Handle unrecognised commands
                System.out.println("Oops! I don't recognise that command, try 'help'.");
            }
        }
    }
}
