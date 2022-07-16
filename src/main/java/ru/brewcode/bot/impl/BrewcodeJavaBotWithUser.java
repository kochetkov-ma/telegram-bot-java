package ru.brewcode.bot.impl;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static ru.brewcode.bot.BotConstants.BOT_NAME;
import static ru.brewcode.bot.BotConstants.BOT_TOKEN;

public class BrewcodeJavaBotWithUser extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        boolean hasAnyMessageFromUser = update.hasMessage();
        Message anyMessageFromUser = update.getMessage();

        String text = anyMessageFromUser.getText();
        String user = update.getMessage().getChat().getUserName();
        long chatIdWithUser = anyMessageFromUser.getChatId();

        System.out.printf("Новое сообщение '%s : %s' %n", user, text);

        SendMessage message = new SendMessage();
        message.setChatId(chatIdWithUser);
        message.setText(text);

        try {
            execute(message);
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
