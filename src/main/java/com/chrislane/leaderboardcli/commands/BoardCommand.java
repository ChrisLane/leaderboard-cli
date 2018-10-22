package com.chrislane.leaderboardcli.commands;

import com.chrislane.leaderboardcli.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BoardCommand extends CommandHandler {
    private final List<Player> players;
    private final List<Player> history;
    private int play;

    public BoardCommand(List<Player> players, List<Player> history) {
        this.players = players;
        this.history = history;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void doCommandLogic() {
        if (args.length == 0) {
            printBoard(players);
            System.out.println("Current Play - " + history.size());
        } else {
            // Recreate the board before play
            List<Player> replay = new ArrayList<>();
            for (int i = 0; i < play - 1; i++) {
                Player historyPlayer = history.get(i);
                String name = historyPlayer.getName();
                int score = historyPlayer.getScore();

                // Find existing player to set their score or add as a new player
                replay.stream()
                        .filter(player -> player.getName().equals(name))
                        .findAny()
                        .ifPresentOrElse(player -> player.setScore(score), () -> replay.add(historyPlayer));
            }

            printBoardAtPlay(replay, history.get(play - 1));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean checkCommand() {
        if (args.length > 1) {
            System.out.println("Too many arguments, expected: [play-number]");
            return false;
        } else if (args.length == 1) {
            try {
                play = Integer.valueOf(args[0]);

                if (play > history.size() || play <= 0) {
                    System.out.println("Play count does not exist");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Play number must be an integer");
                return false;
            }
        }

        return true;
    }

    /**
     * Prints the current leaderboard positions.
     *
     * @param board The leaderboard.
     */
    private void printBoard(List<Player> board) {
        for (int i = 0; i < board.size(); i++) {
            Player player = board.get(i);
            System.out.println((i + 1) + " - " + player.getName() + " " + player.getScore());
        }
    }

    /**
     * Prints the leaderboard before and after a play.
     *
     * @param board The leaderboard before the play.
     * @param addition The play.
     */
    private void printBoardAtPlay(List<Player> board, Player addition) {
        System.out.println("Before Play " + play);
        board.sort(Comparator.reverseOrder());
        printBoard(board);

        board.stream()
                .filter(player -> player.getName().equals(addition.getName()))
                .findAny()
                .ifPresentOrElse(player -> player.setScore(addition.getScore()), () -> board.add(addition));
        board.sort(Comparator.reverseOrder());

        System.out.println("\nAfter Play " + play);
        printBoard(board);
    }
}
