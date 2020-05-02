package su.spiridonov.discordbot;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.spiridonov.discordbot.responses.ResponsesHelper;

public class DiscordHelper {
    static String TOKEN;
    static String BASE_PATH;

    private static Logger logger = LoggerFactory.getLogger(DiscordHelper.class);

    public static void startBot() {
        setupSystemProperties();
        ResponsesHelper responsesHelper = new ResponsesHelper(BASE_PATH);

        final DiscordClient client = DiscordClientBuilder.create(TOKEN).build();

        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(ready -> logger.info("Logged in as " + ready.getSelf().getUsername()));

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    Message message = event.getMessage();
                    logger.trace("Message: \n" + message);

                    String res = responsesHelper.returnResponse(message);
                    if (res != null) {
                        message.getChannel().block().createMessage(res).subscribe();
                    }
                });

        client.login().block();
    }

    private static void setupSystemProperties() {
        //@TODO: Need parse it by some library as key\value params
        TOKEN = System.getProperty("token");
        BASE_PATH = System.getProperty("base");
    }
}
