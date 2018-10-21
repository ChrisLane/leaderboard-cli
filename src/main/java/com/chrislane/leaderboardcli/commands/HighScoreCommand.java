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

    @Override
    void doCommandLogic() {
        Player winner;
        int play;

        if (args.length == 0) {
            Optional<Player> winnerOpt = history.stream().max(Player::compareTo);
            if (!winnerOpt.isPresent()) {
                return;
            }
            winner = winnerOpt.get();
            play = history.indexOf(winner) + 1;
        } else {
            name = args[0];
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

        String result = LanguageHandler.getInstance().getEntry("high-score");
        result = String.format(result, winner.getName(), winner.getScore(), play);
        System.out.println(result);
    }

    @Override
    int checkCommand() {
        if (history.size() == 0) {
            System.out.println("No scores have been entered yet");
            return 1;
        }

        if (args.length > 1) {
            System.out.println("Too many arguments, expected: <play-number>");
            return 1;
        }
        return 0;
    }
}
