package com.chrislane.leaderboardcli.commands;

abstract class CommandHandler {
    String[] args;

    /**
     * Runs {@link #doCommandLogic()} if {@link #checkCommand()} returns true.
     *
     * @param args Command arguments.
     */
    void runCommand(String[] args) {
        this.args = args;

        if (checkCommand()) {
            doCommandLogic();
        }
    }

    /**
     * Runs the command actions.
     */
    abstract void doCommandLogic();

    /**
     * Checks the command format and conditions for the command to run.
     *
     * @return Returns True if command and environment is correct, else False.
     */
    abstract boolean checkCommand();
}
