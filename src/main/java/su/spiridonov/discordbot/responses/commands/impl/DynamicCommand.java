package su.spiridonov.discordbot.responses.commands.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.spiridonov.discordbot.responses.commands.BotCommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DynamicCommand extends BotCommand {
    private static Logger logger = LoggerFactory.getLogger(DynamicCommand.class);

    public DynamicCommand(String result) {
        super(result);
    }

    @Override
    public String returnResult() {
        return runShellCommand(result);
    }

    private String runShellCommand(String cmd) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(cmd);
        String result = "";
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
                logger.debug(line);
            }
            process.waitFor();
        } catch (Exception e) {
            logger.error("Error with Dynamic command execution: " + e);
        }
        return result;
    }

}
