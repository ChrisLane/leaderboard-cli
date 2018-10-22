package com.chrislane.leaderboardcli.commands;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CommandHandlerTest {
    private CommandHandler handler;
    private boolean hasDoneLogic = false;

    @BeforeTest
    void initAll() {
        handler = new CommandHandler() {
            @Override
            void doCommandLogic() {
                hasDoneLogic = true;
            }

            @Override
            int checkCommand() {
                if (args.length == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };
    }

    @Test
    void checkRunCommandCancelsLogic() {
        handler.runCommand(new String[]{"arg"});
        assertFalse(hasDoneLogic);
    }

    @Test
    void checkRunCommandRunsLogic() {
        handler.runCommand(new String[0]);
        assertTrue(hasDoneLogic);
    }
}
