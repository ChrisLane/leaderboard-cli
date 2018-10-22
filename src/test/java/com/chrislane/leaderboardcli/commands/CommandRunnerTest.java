package com.chrislane.leaderboardcli.commands;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CommandRunnerTest {
    CommandRunner runner = new CommandRunner();

    @Test(groups = "CommandRunner.run")
    void registerAndRunCommandTest() {
        runner.registerCommand("command", new CommandHandler() {
            @Override
            void doCommandLogic() {

            }

            @Override
            boolean checkCommand() {
                return true;
            }
        });

        assertTrue(runner.runCommand("command"));
    }

    @Test(groups = "CommandRunner.run")
    void runUnregisteredCommandTest() {
        assertFalse(runner.runCommand("notACommand"));
    }
}
