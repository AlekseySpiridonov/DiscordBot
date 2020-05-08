package su.spiridonov.discordbot.responses;

import discord4j.core.ServiceMediator;
import discord4j.core.object.data.stored.MessageBean;
import discord4j.core.object.data.stored.UserBean;
import discord4j.core.object.entity.Message;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.spiridonov.discordbot.responses.commands.impl.ParameterizedCommand;

import static org.junit.Assert.*;

public class ResponsesHelperTest {
    private static Logger logger = LoggerFactory.getLogger(ResponsesHelperTest.class);
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

    @Test
    public void validatePingResponseWithBlanParams() {
        Message message = createDiscordTestMessage("!ping  ");
        ResponsesHelper responsesHelper = new ResponsesHelper(KNOWLEDGE_BASE);
        assertEquals("Pong", responsesHelper.returnResponse(message));
    }

    @Test
    public void validateParametrizedCommand() {
        Message message = createDiscordTestMessage("!ns ya.ru");
        ResponsesHelper responsesHelper = new ResponsesHelper(KNOWLEDGE_BASE);
        assertTrue(responsesHelper.returnResponse(message).contains("Address:"));
    }

    @Test
    public void validateSecurityOfParametrizedCommand() {
        Message message = createDiscordTestMessage("!ns ya.ru; uptime");
        ResponsesHelper responsesHelper = new ResponsesHelper(KNOWLEDGE_BASE);
        ParameterizedCommand parameterizedCommand = new ParameterizedCommand("ns");
        assertTrue(responsesHelper.returnResponse(message).equals(parameterizedCommand.returnSystemSecurityError()));
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