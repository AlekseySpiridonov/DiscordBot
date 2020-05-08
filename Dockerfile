FROM openjdk:11
COPY target/DiscordBot-*-jar-with-dependencies.jar /tmp/DiscordBot.jar
COPY tools/start-bot.sh /tmp/start.sh
WORKDIR /tmp/
RUN ls -la /tmp/

ENTRYPOINT ["bash", "/tmp/start.sh"]