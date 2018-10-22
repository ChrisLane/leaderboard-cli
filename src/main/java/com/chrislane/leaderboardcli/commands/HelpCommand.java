package com.chrislane.leaderboardcli.commands;

public class HelpCommand extends CommandHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    void doCommandLogic() {
        System.out.println("Find usage instructions at https://github.com/ChrisLane/leaderboard-cli");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean checkCommand() {
        return true;
    }
}
