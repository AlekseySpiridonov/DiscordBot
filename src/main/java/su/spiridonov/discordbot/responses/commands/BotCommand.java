package su.spiridonov.discordbot.responses.commands;

public abstract class BotCommand {
    protected String result;

    public BotCommand(String result) {
        this.result = result;
    }

    /**
     * @param clientMsg - original message from client (optional for command's without parameters)
     * @return (String) result of execution Bot's command
     */
    abstract public String returnResult(String clientMsg);


}
