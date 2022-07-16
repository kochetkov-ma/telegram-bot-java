package ru.brewcode.bot.impl.pool;

import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.brewcode.bot.model.BotMessage;

import java.util.List;

import static ru.brewcode.bot.impl.pool.Constants.ADMIN_USERNAME;

public class AdminCommandHandler {

    public BotMessage handleCommand(Message message) {
        // Получаем пользователя, который отправил сообщение
        User user = message.getFrom();

        // Если он НЕ админ, то завершаем обработку
        if (!isAdmin(user)) return null;

        String text = message.getText();

        Command command;
        try {
            command = Command.valueOf(text.toLowerCase());
        } catch (IllegalArgumentException error) {
            command = Command.UNSUPPORTED;
        }

        switch (command) {
            case POOL:
                return pollCommand(message);
            default:
                return unsupportedCommand(message);
        }
    }

    private boolean isAdmin(User user) {
        return user.getUserName().equals(ADMIN_USERNAME);
    }

    private BotMessage pollCommand(Message message) {
        // Новый тип ответа с голосованием
        SendPoll output = new SendPoll();

        // ID чата, куда отправить сообщение
        output.setChatId(message.getChatId());

        // Вопрос
        output.setQuestion("Високосный ли сейчас год?");
        output.setAllowMultipleAnswers(false);

        // Индекс корректного ответа в списке
        output.setOptions(List.of("Да", "Нет"));

        // Индекс корректного ответа в списке
        output.setCorrectOptionId(1);

        // Тип опроса - “quiz” or “regular”
        output.setType("quiz");

        // Бонусная информация за правильный ответ
        output.setExplanation("Чтобы узнать это можно выполнить в Java 'LocalDate.now().isLeapYear()'");

        return new BotMessage(output);
    }

    private BotMessage unsupportedCommand(Message message) {
        return null;
    }

    enum Command {
        POOL("/poll"),
        UNSUPPORTED("unsupported");
        private String text;

        Command(String s) {

            this.text = s;
        }
    }
}
