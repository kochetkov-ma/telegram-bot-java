package ru.brewcode.bot.impl.keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class CommandHandler {

    public SendMessage handleCommand(Message message) {
        String text = message.getText();

        // Преобразуем в нижний регистр и будем работать с командами в нижнем регистре
        switch (text.toLowerCase()) {
            case "/keyboard":
                // На каждую команду свой private метод
                return keyboardCommand(message);
            default:
                // Если не нашлось команд, то обработать отдельно
                return unsupportedCommand(message);
        }
    }

    private SendMessage keyboardCommand(Message message) {
        SendMessage output = new SendMessage();

        // ID
        output.setChatId(message.getChatId());

        // Сообщение
        output.setText("Открывается клавиатура");

        // Добавляем ранее созданную клавиатуру
        output.setReplyMarkup(Keyboard.getKeyboard());

        return output;
    }

    // Если не нашлось команд, то обработать отдельно
    private SendMessage unsupportedCommand(Message message) {
        return null;
    }
}
