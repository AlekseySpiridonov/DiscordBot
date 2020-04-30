package su.spiridonov.discordbot.responses;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.function.Supplier;

public class ResponsesHelper {
    private String KNOWLEDGE_BASE_PATH;
    private ResponsesHelper responsesHelper;
    Properties knowledgeBase;

    /**
     *
     * @param clientMsg - original Discord message from client
     * @return response - string with bot's response or null if bot have no valid response
     */
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
            knowledgeBase = readKnowledgeBaseFile(KNOWLEDGE_BASE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responsesHelper;
    }

    /**
     *
     * @param path - valid path for Bot's Knowledge base. Example of Knowledge base: src/main/resources/base.properties
     */
    public ResponsesHelper(String path) {
        KNOWLEDGE_BASE_PATH = path;
        responsesHelper = initResponse();
    }


    protected static Properties readKnowledgeBaseFile(String fileName) throws Exception {
        FileInputStream fileInputStream = null;
        Properties properties = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                fileInputStream.close();
        }
        return properties;
    }
}
