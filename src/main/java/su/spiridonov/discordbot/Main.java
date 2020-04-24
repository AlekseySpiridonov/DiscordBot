package su.spiridonov.discordbot;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import su.spiridonov.discordbot.responses.Response;

import javax.security.auth.login.LoginException;


public class Main {

    public static void main(String[] args) throws LoginException {
        final String TOKEN = args[0]; // Secret token for BOT
        final DiscordClient client = DiscordClientBuilder.create(TOKEN).build();

        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(ready -> System.out.println("Logged in as " + ready.getSelf().getUsername()));

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    Message message = event.getMessage();
                    System.out.println("Message: \n" + message);

                    String res = Response.returnResponse(message);
                    if (res != null) {
                        message.getChannel().block().createMessage(res).subscribe();
                    }
                });

        client.login().block();
    }

}