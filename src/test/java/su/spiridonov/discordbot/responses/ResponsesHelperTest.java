package su.spiridonov.discordbot.responses;

import discord4j.core.ServiceMediator;
import discord4j.core.object.data.stored.MessageBean;
import discord4j.core.object.data.stored.UserBean;
import discord4j.core.object.entity.Message;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponsesHelperTest {
    String KNOWLEDGE_BASE = "src/main/resources/base.properties";
    String USERNAME = "test";

    @Test
    public void validateNullResponse() {
        Message message = createDiscordTestMessage("zzz");
        ResponsesHelper responsesHelper = new ResponsesHelper(KNOWLEDGE_BASE);
        assertEquals(null, responsesHelper.returnResponse(message));
    }

    @Test
    public void validatePingResponse() {
        Message message = createDiscordTestMessage("!ping");
        ResponsesHelper responsesHelper = new ResponsesHelper(KNOWLEDGE_BASE);
        assertEquals("Pong", responsesHelper.returnResponse(message));
    }

    private Message createDiscordTestMessage(String msg) {
        ResponsesHelper responsesHelper = new ResponsesHelper(KNOWLEDGE_BASE);
        UserBean userBean = new UserBean();
        userBean.setUsername(USERNAME);
        MessageBean testMessageBean = new MessageBean();
        ServiceMediator nullMediator = new ServiceMediator(null, null, null, null, null, null, null);
        Message testMsg = new Message(nullMediator, testMessageBean);
        testMessageBean.setContent(msg);
        testMessageBean.setAuthor(userBean);
        return testMsg;
    }
}