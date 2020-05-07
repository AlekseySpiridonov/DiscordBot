package su.spiridonov.discordbot.responses.commands.impl;

import discord4j.core.object.entity.Message;
import su.spiridonov.discordbot.responses.commands.BotCommand;

public class StaticCommand extends BotCommand {

    public StaticCommand(String result) {
        super(result);
    }

    @Override
    public String returnResult(Message clientMsg) {
        return super.result;
    }


}
