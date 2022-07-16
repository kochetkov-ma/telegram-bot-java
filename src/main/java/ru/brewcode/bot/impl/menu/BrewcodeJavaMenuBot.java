package ru.brewcode.bot.impl.menu;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static ru.brewcode.bot.BotConstants.BOT_NAME;
import static ru.brewcode.bot.BotConstants.BOT_TOKEN;

public class BrewcodeJavaMenuBot extends TelegramLongPollingBot {

    // Создаем обработчик
    private final CallbackHandler callbackHandler = new CallbackHandler();
    private final CommandHandler commandHandler = new CommandHandler();

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage output;

        // Если в сообщении есть сallback-команда
        if (update.hasCallbackQuery()) {
            output = callbackHandler.handleCallback(update.getCallbackQuery());
        } else {
            output = commandHandler.handleCommand(update.getMessage());
        }

        try {
            execute(output);
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
