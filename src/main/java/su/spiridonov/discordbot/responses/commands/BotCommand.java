package su.spiridonov.discordbot.responses.commands;

public abstract class BotCommand {
    protected String result;

    public BotCommand(String result) {
        this.result = result;
    }

    abstract public String returnResult();


}
