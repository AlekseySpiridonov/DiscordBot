package su.spiridonov.discordbot.responses.commands.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.spiridonov.discordbot.responses.commands.BotCommand;
import su.spiridonov.discordbot.responses.commands.BotCommandsHelper;

public class ParameterizedCommand extends BotCommand {
    private static Logger logger = LoggerFactory.getLogger(ParameterizedCommand.class);
    private static String MESSAGE_FOR_HACKERS = "YOU ARE HACKER. YOU ACTIVITY HAS BEEN RECORDED.";

    public ParameterizedCommand(String result) {
        super(result);
        logger.warn("\n" +
                "** WARNING! *********************************************************\n" +
                "You are using potential unsafe Parametrized Commands!!! \n" +
                "Please verify all commands from user before run scripts with it. \n" +
                "Validate function @simpleSecurityValidations(String clientMsg) \n" +
                "before deploying to Prod environment! \n" +
                "*********************************************************************");
    }


    @Override
    public String returnResult(String clientMsg) {
        String filteredMsg = simpleSecurityValidations(clientMsg);
        if (!filteredMsg.equals(MESSAGE_FOR_HACKERS)) {
            return BotCommandsHelper.runShellCommand(result, filteredMsg);
        } else {
            return MESSAGE_FOR_HACKERS;
        }
    }

    private String simpleSecurityValidations(String clientMsg) {
        if (clientMsg.contains(";") || clientMsg.contains("&&") ||
                clientMsg.contains("|") || clientMsg.contains("\"") ||
                clientMsg.contains("'") || clientMsg.contains("`")) {
            logger.error("Hack attempt during using parametrized command: \n" + clientMsg);
            clientMsg = MESSAGE_FOR_HACKERS;
        } else {
            clientMsg = clientMsg.split(" ")[1]; // Save only user's parameters
            clientMsg = "\"" + clientMsg + "\""; // Additional security by adding quotes for user's params
        }
        return clientMsg;
    }

    public String returnSystemSecurityError() {
        return MESSAGE_FOR_HACKERS;
    }
}
