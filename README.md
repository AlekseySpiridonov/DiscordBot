# Simple DiscordBot

### How to build
```mvn package```

### Release versions
Binary versions avalible on [GitHub Packages](https://github.com/AlekseySpiridonov/discordbot/packages)  
Download "DiscordBot-*-jar-with-dependencies.jar" for start standalone application.

### How to start
1. Get API Token for DiscordBot
2. Create and save Knowledge Base for bot. Example of knowledge base: ```src/main/resources/base.properties```
3. Run Jar by command
```java -Dtoken="$TOKEN$" -Dbase="$PATH$/base.properties" -jar DiscordBot-*-jar-with-dependencies.jar ```
