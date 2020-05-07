package su.spiridonov.discordbot.responses;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.spiridonov.discordbot.responses.commands.BotCommand;
import su.spiridonov.discordbot.responses.commands.impl.DynamicCommand;
import su.spiridonov.discordbot.responses.commands.impl.StaticCommand;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;

public class ResponsesHelper {
    private String KNOWLEDGE_BASE_PATH;
    private ResponsesHelper responsesHelper;
    Map<String, BotCommand> knowledgeBase;

    private static Logger logger = LoggerFactory.getLogger(ResponsesHelper.class);


    /**
     * @param clientMsg - original Discord message from client
     * @return response - string with bot's response or null if bot have no valid response
     */
    public String returnResponse(Message clientMsg) {
        String response = null;
        String msg = clientMsg.getContent().get();
        String cmd = msg.split(" ")[0]; // Split for spaces (for commands with params)

        if (cmd.contains("!all")) { //Base command for listing all available commands
            response = knowledgeBase.keySet().toString();
        } else if (knowledgeBase.keySet().toString().contains(cmd)) {
            BotCommand botCommand = knowledgeBase.get(cmd);
            if (botCommand != null)
                response = botCommand.returnResult(clientMsg); // Responses from knowledge base
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

    private ResponsesHelper initBotKnowledgeBase() {
        try {
            // Fill knowledge base from file
            knowledgeBase = readKnowledgeBaseFile(KNOWLEDGE_BASE_PATH);
        } catch (Exception e) {
            logger.error("Database wasn't loaded correctly " + e);
            System.exit(-1);
        }

        return responsesHelper;
    }

    /**
     * @param path - valid path for Bot's Knowledge base. Example of Knowledge base: src/main/resources/base.properties
     */
    public ResponsesHelper(String path) {
        KNOWLEDGE_BASE_PATH = path;
        responsesHelper = initBotKnowledgeBase();
    }


    protected static Map<String, BotCommand> readKnowledgeBaseFile(String fileName) throws Exception {
        FileInputStream fileInputStream = null;
        Properties properties = null;
        Map<String, BotCommand> knowledgeBase = new HashMap<>();
        try {
            fileInputStream = new FileInputStream(fileName);
            properties = new Properties();
            properties.load(fileInputStream);
            properties.setProperty("!ping", "Pong"); //Base "!ping" command
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                if (value.startsWith("!")) {
                    knowledgeBase.put(key, new DynamicCommand(value.substring(1, value.length())));
                } else {
                    knowledgeBase.put(key, new StaticCommand(value));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                fileInputStream.close();
        }
        return knowledgeBase;
    }
}
