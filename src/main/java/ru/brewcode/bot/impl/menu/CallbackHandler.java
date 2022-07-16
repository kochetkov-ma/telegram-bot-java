package ru.brewcode.bot.impl.menu;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

// Обработка команд от встроенной в чат клавиатуры
public class CallbackHandler {

    public SendMessage handleCallback(CallbackQuery callback) {
        String text = callback.getData();

        switch (text.toLowerCase()) {
            case "day":
                return dateCallback(callback);
            default:
                return unsupportedCallback(callback);
        }
    }

    private SendMessage dateCallback(CallbackQuery callback) {
        SendMessage output = new SendMessage();
        // Получаем chatId из callback
        output.setChatId(callback.getMessage().getChatId());
        // Получаем текущий день
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        // Печатаем на русском
        String day = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ru"));
        // Добавляем в ответ
        output.setText("Сегодня " + day);
        return output;
    }

    private SendMessage unsupportedCallback(CallbackQuery callback) {
        SendMessage output = new SendMessage();
        output.setChatId(callback.getMessage().getChatId());
        output.setText("Неразобранная команда: " + callback.getData());
        return output;
    }
}
