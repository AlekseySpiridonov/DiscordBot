package su.spiridonov.discordbot.responses.commands.impl;

import discord4j.core.object.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.spiridonov.discordbot.responses.commands.BotCommand;
import su.spiridonov.discordbot.responses.commands.BotCommandsHelper;

public class DynamicCommand extends BotCommand {
    private static Logger logger = LoggerFactory.getLogger(DynamicCommand.class);

    public DynamicCommand(String result) {
        super(result);
    }

    @Override
    public String returnResult(Message clientMsg) {
        return BotCommandsHelper.runShellCommand(result);
    }


}
