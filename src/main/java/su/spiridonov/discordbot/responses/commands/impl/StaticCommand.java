package su.spiridonov.discordbot.responses.commands.impl;

import su.spiridonov.discordbot.responses.commands.BotCommand;

public class StaticCommand extends BotCommand {

    public StaticCommand(String result) {
        super(result);
    }

    @Override
    public String returnResult() {
        return super.result;
    }


}
