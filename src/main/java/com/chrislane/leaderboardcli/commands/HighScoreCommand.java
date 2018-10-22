package com.chrislane.leaderboardcli.commands;

import com.chrislane.leaderboardcli.Player;
import com.chrislane.leaderboardcli.language.LanguageHandler;

import java.util.List;
import java.util.Optional;

public class HighScoreCommand extends CommandHandler {
    private final List<Player> history;
    private String name;

    public HighScoreCommand(List<Player> history) {
        this.history = history;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void doCommandLogic() {
        Player winner;
        int play;

        if (args.length == 0) { // Show the highest score
            Optional<Player> winnerOpt = history.stream().max(Player::compareTo);
            if (!winnerOpt.isPresent()) {
                return;
            }
            winner = winnerOpt.get();
            play = history.indexOf(winner) + 1;
        } else { // Show the highest score for a player
            name = args[0];
            // Find the maximum score for the given player
            Optional<Player> player = history.stream()
                    .filter(p -> p.getName().equals(name))
                    .max(Player::compareTo);

            if (!player.isPresent()) {
                System.out.println("There does not exist a player by that name");
                return;
            }

            winner = player.get();
            play = history.indexOf(winner) + 1;
        }

        // Format the score into the high score string
        String result = LanguageHandler.getInstance().getEntry("high-score");
        result = String.format(result, winner.getName(), winner.getScore(), play);
        System.out.println(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean checkCommand() {
        if (history.size() == 0) {
            System.out.println("No scores have been entered yet");
            return false;
        }

        if (args.length > 1) {
            System.out.println("Too many arguments, expected: [play-number]");
            return false;
        }
        return true;
    }
}
