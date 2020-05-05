# Simple DiscordBot

### How to build
```mvn package```

### Release versions
Binary versions avalible on [GitHub Packages](https://github.com/AlekseySpiridonov/discordbot/packages)  
Download "DiscordBot-*-jar-with-dependencies.jar" for start standalone application.

### How to configure Knowledge Base
Create and save Knowledge Base for bot.  
Example of knowledge base: ```src/main/resources/base.properties```   
Current version of bot supports two types of commands:
- static command (return pre-defined response string)
```\!command=Static response```
- dynamic command (return result after executing shell command)
```\!command=\!uptime```   
(``uptime`` will be execute as SHELL command and return result)

### How to start
1. Get API Token for DiscordBot
2. Run Jar by command
```java -Dtoken="$TOKEN$" -Dbase="$PATH$/base.properties" -jar DiscordBot-*-jar-with-dependencies.jar ```
