Java Telegram Bot Samples
---

## Resources

- [MonsterDeveloper](https://monsterdeveloper.gitbooks.io/writing-telegram-bots-on-java/content/chapter1.html)
- [Telegram bot на Java](https://habr.com/ru/sandbox/165353/)
- [TelegramBots by rubenlagus](https://github.com/rubenlagus/TelegramBots/wiki/Getting-Started)

## Structure

- [BrewcodeJavaBotWithUser](src/main/java/ru/brewcode/bot/impl/BrewcodeJavaBotWithUser.java) Just sent message on any
  update
- [BrewcodeJavaMenuBot](src/main/java/ru/brewcode/bot/impl/menu/BrewcodeJavaMenuBot.java) Keyboard-menu in user chat
  input
- [BrewcodeJavaPhotoBot](src/main/java/ru/brewcode/bot/impl/photo/BrewcodeJavaPhotoBot.java) Sent media files to chat
- [BrewcodeJavaKeyboardBot](src/main/java/ru/brewcode/bot/impl/keyboard/BrewcodeJavaKeyboardBot.java)
  Inline-Keyboard-menu in chat as message
- [BrewcodeJavaPoolBot](src/main/java/ru/brewcode/bot/impl/pool/BrewcodeJavaPoolBot.java) Create poll/quiz
- [BrewcodeJavaGroupBot](src/main/java/ru/brewcode/bot/impl/group/BrewcodeJavaGroupBot.java) Quiz game with user score
- [Main](src/main/java/ru/brewcode/bot/Main.java) Start current bot impl
- [BotConstants](src/main/java/ru/brewcode/bot/BotConstants.java) Load token from gradle.properties (in .gitignore)