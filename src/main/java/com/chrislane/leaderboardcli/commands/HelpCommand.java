package com.chrislane.leaderboardcli.commands;

public class HelpCommand extends CommandHandler {
    @Override
    void doCommandLogic() {
        System.out.println("Find usage instructions at https://github.com/ChrisLane/leaderboard-cli");
    }

    @Override
    int checkCommand() {
        return 0;
    }
}
