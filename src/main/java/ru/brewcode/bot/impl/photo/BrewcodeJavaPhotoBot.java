package ru.brewcode.bot.impl.photo;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.brewcode.bot.model.BotMessage;

import static ru.brewcode.bot.BotConstants.BOT_NAME;
import static ru.brewcode.bot.BotConstants.BOT_TOKEN;

public class BrewcodeJavaPhotoBot extends TelegramLongPollingBot {

    private final CommandHandler commandHandler = new CommandHandler();

    @Override
    public void onUpdateReceived(Update update) {
        BotMessage output = commandHandler.handleCommand(update.getMessage());

        try {

            // Если в BotMessage есть SendMessage, то высылаем его
            if (output.getSendMessage() != null)
                execute(output.getSendMessage());

            // Если в BotMessage есть SendPhoto, то высылаем его
            if (output.getSendPhoto() != null)
                execute(output.getSendPhoto());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
