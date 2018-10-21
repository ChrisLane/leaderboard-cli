package com.chrislane.leaderboardcli.commands;

import com.chrislane.leaderboardcli.Player;
import com.chrislane.leaderboardcli.language.LanguageHandler;

import java.util.List;

public class ScoreCommand extends CommandHandler {
    private final List<Player> history;
    private final List<Player> players;
    private String name;
    private int score;

    public ScoreCommand(List<Player> players, List<Player> history) {
        this.players = players;
        this.history = history;
    }

    @Override
    void doCommandLogic() {
        players.stream()
                .filter(player -> player.getName().equals(name))
                .findAny()
                .ifPresentOrElse(this::setExistingPlayerScore, this::addNewPlayer);
    }

    @Override
    int checkCommand() {
        if (args.length != 2) {
            System.out.println("Incorrect number of arguments, expected: <name> <score>");
            return 1;
        }
        name = args[0];

        try {
            score = Integer.valueOf(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Score must be an integer");
            return 1;
        }

        return 0;
    }

    private void addNewPlayer() {
        // Add to current high scores
        Player player = new Player(name, score);
        players.add(player);
        printRankMessage(getRank(player));

        // Add to history
        history.add(player.copy());
    }

    private void setExistingPlayerScore(Player player) {
        // Change current high score
        int prevRank = getRank(player);
        player.setScore(score);
        int newRank = getRank(player);
        printRankMessage(prevRank, newRank);

        // Add to history
        history.add(player.copy());
    }

    private int getRank(Player player) {
        players.sort(Player::compareTo);
        return players.indexOf(player) + 1;
    }

    private void printRankMessage(int rank) {
        printRankMessage(-1, rank);
    }

    private void printRankMessage(int prevRank, int newRank) {
        if (prevRank == -1) {
            printPlayerAddedMessage(newRank);
        } else if (newRank < prevRank) {
            printRankIncreaseMessage(prevRank, newRank);
        } else if (newRank > prevRank) {
            printRankDecreaseMessage(prevRank, newRank);
        } else {
            printRankUnchangedMessage(newRank);
        }
    }

    private void printRankIncreaseMessage(int prevRank, int newRank) {
        List<Player> overtaken = players.subList(newRank, prevRank);
        StringBuilder losersString = new StringBuilder(overtaken.get(0).getName());
        for (int i = 1; i < overtaken.size(); i++) {
            losersString.append(" and ").append(overtaken.get(i).getName());
        }
        String result = LanguageHandler.getInstance().getEntry("rank-climb");
        result = String.format(result, history.size() + 1, name, prevRank, newRank, losersString);

        System.out.println(result);
    }

    private void printRankDecreaseMessage(int prevRank, int newRank) {
        List<Player> overtakenBy = players.subList(prevRank - 1, newRank - 1);
        StringBuilder winnersString = new StringBuilder(overtakenBy.get(0).getName());
        for (int i = 1; i < overtakenBy.size(); i++) {
            winnersString.append(" and ").append(overtakenBy.get(i).getName());
        }

        String result = LanguageHandler.getInstance().getEntry("rank-drop");
        result = String.format(result, history.size() + 1, name, prevRank, newRank, winnersString);

        System.out.println(result);
    }

    private void printRankUnchangedMessage(int rank) {
        String result = LanguageHandler.getInstance().getEntry("rank-unchanged");
        result = String.format(result, history.size() + 1, name, rank);

        System.out.println(result);
    }

    private void printPlayerAddedMessage(int rank) {
        String result = LanguageHandler.getInstance().getEntry("add");
        result = String.format(result, history.size() + 1, name, rank);

        System.out.println(result);

    }
}
