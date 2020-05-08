package su.spiridonov.discordbot.responses.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BotCommandsHelper {
    private static Logger logger = LoggerFactory.getLogger(BotCommandsHelper.class);

    public static String runShellCommand(String cmd) {
        return runShellCommand(cmd);
    }

    public static String runShellCommand(String... cmd) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(cmd);
        String result = "";
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
                logger.debug(line);
            }
            process.waitFor();
        } catch (Exception e) {
            logger.error("Error with Dynamic command execution: " + e);
        }
        return result;
    }
}
