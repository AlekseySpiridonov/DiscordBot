FROM openjdk:8-alpine
COPY target/DiscordBot-*-jar-with-dependencies.jar /tmp/DiscordBot.jar
COPY tools/start-bot.sh /tmp/start.sh
WORKDIR /tmp/
RUN ls -la /tmp/

ENTRYPOINT ["/bin/sh", "/tmp/start.sh"]