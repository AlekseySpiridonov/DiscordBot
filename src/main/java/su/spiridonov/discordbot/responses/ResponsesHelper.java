package su.spiridonov.discordbot.responses;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import su.spiridonov.discordbot.Main;
import su.spiridonov.discordbot.responses.utils.PropertiesUtil;

import java.util.Properties;
import java.util.function.Supplier;

public class ResponsesHelper {
    private ResponsesHelper responsesHelper;
    Properties knowledgeBase;

    public String returnResponse(Message clientMsg) {
        String response = null;
        if (clientMsg.getContent().map("!ping"::equals).orElse(false)) {
            response = getClientUsername(clientMsg) + " pong"; // Test response
        } else if (clientMsg.getContent().map(knowledgeBase.keySet().toString()::contains).orElse(false)) {
            response = knowledgeBase.getProperty(clientMsg.getContent().get()); // Responses from knowledge base
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

    private ResponsesHelper initResponse() {
            try {
                // Fill knowledge base from file
                knowledgeBase = PropertiesUtil.readPropertiesFile(Main.returnPathForKnowledgeBase());
            } catch (Exception e) {
                e.printStackTrace();
            }

        return responsesHelper;
    }

    public ResponsesHelper() {
        responsesHelper = initResponse();
    }
}
