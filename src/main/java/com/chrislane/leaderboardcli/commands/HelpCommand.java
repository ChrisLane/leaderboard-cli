package com.chrislane.leaderboardcli.commands;

public class HelpCommand extends CommandHandler {
    @Override
    void doCommandLogic() {
        System.out.println("Help info");
    }

    @Override
    int checkCommand() {
        return 0;
    }
}
