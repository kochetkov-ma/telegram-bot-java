package ru.brewcode.bot.impl.group;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.polls.StopPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.brewcode.bot.BotConstants.BOT_NAME;
import static ru.brewcode.bot.BotConstants.BOT_TOKEN;

public class BrewcodeJavaGroupBot extends TelegramLongPollingBot {

    // Запоминаем очки пользователей в виде 'username: score'
    private final Map<String, Integer> scores = new HashMap<>();

    // Запоминаем созданные голосования в виде 'id_голосования: message'
    // В Message хранится вся нужная нам информация о чате и об ответах
    private final Map<String, Message> createdPools = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        try {

            // Если пришел ответ на какое-то голосование
            if (update.hasPollAnswer()) {

                // Ищем в созданных голосованиях сообщение Message
                Message currentMessage = createdPools.get(update.getPollAnswer().getPollId());
                if (currentMessage == null)
                    return;

                // Получаем правильный ответ
                int correctId = currentMessage.getPoll().getCorrectOptionId();

                // Чат ID, в который отправлять ответ
                String chatId = currentMessage.getChatId().toString();

                // ID сообщения с голосованием, которое нужно будет закрыть после правильного ответа
                int messageId = currentMessage.getMessageId();

                // Получаем имя пользователя, который ответил правильно
                String userName = userName(update.getPollAnswer().getUser());

                // Проверяем правильность ответа
                if (correctId == update.getPollAnswer().getOptionIds().get(0)) {

                    // Получаем очки пользователя
                    int userScore = scores.getOrDefault(userName, 0);

                    // Устанавливаем очки пользователю
                    scores.put(userName, ++userScore);

                    // Формируем красивое сообщение
                    String text = "\uD83D\uDCA5 Ваш счет \uD83D\uDCA5 \n\n";
                    for (Map.Entry<String, Integer> userAndScore : scores.entrySet()) {
                        text += "▶  " + userAndScore.getKey() + " - " + userAndScore.getValue() + "\n";
                    }
                    // Показываем очки
                    SendMessage message = new SendMessage(chatId, text);
                    execute(message);

                    // Останавливаем голосование
                    StopPoll stopPoll = new StopPoll(chatId, messageId);
                    execute(stopPoll);
                }
            }

            // Запускаем голосование
            if (update.hasMessage()) {
                if ("/poll".equals(update.getMessage().getText())) {
                    SendPoll output = new SendPoll();
                    output.setChatId(update.getMessage().getChatId());
                    output.setQuestion("Високосный ли сейчас год?");
                    output.setAllowMultipleAnswers(false);
                    output.setOptions(List.of("Да", "Нет"));
                    output.setCorrectOptionId(1);
                    output.setType("quiz");
                    // НЕ анонимное!
                    output.setIsAnonymous(false);
                    Message message = execute(output);
                    createdPools.put(message.getPoll().getId(), message);

                }
            }

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String userName(User user) {
        // Если пользователь скрыл свой НИК, то используем его цифровой ID
        if (user.getUserName() == null)
            return user.getId() + "." + user.getFirstName();
        else
            return user.getUserName();
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
