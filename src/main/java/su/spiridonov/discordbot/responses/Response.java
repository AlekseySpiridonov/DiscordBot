package su.spiridonov.discordbot.responses;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;

import java.util.function.Supplier;

public class Response {
    private static Response response = initResponse();

    public static String returnResponse(Message clientMsg) {
        String response = null;
        if (clientMsg.getContent().map("!ping"::equals).orElse(false)) {
            response = getClientUsername(clientMsg) + " pong";
        }
        if (clientMsg.getContent().map("!pong"::equals).orElse(false)) {
            response = getClientUsername(clientMsg) + " ping";
        }
        return response;
    }

    private static String getClientUsername(Message clientMsg) {
        return clientMsg.getAuthor().orElseGet(new Supplier<User>() {
            @Override
            public User get() {
                return this.get();
            }
        }).getUsername();
    }

    private static Response initResponse() {
        if (response != null) {
            response = new Response();
        }
        return response;
    }

}
