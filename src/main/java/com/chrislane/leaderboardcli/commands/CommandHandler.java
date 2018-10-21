package com.chrislane.leaderboardcli.commands;

abstract class CommandHandler {
    String[] args;

    void runCommand(String[] args) {
        this.args = args;

        if (checkCommand() == 0) {
            doCommandLogic();
        }
    }

    abstract void doCommandLogic();

    abstract int checkCommand();
}
