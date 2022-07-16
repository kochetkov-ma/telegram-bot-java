package ru.brewcode.bot.impl.menu;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

// Обработка команд от пользователя
public class CommandHandler {

    public SendMessage handleCommand(Message message) {
        String text = message.getText();

        switch (text.toLowerCase()) {
            case "/keyboard":
                return keyboardCommand(message);
            default:
                return unsupportedCommand(message);
        }
    }

    private SendMessage keyboardCommand(Message message) {
        SendMessage output = new SendMessage();
        output.setChatId(message.getChatId());
        output.setText("Открывается клавиатура");
        output.setReplyMarkup(InlineKeyboard.getInlineKeyboard());
        return output;
    }

    private SendMessage unsupportedCommand(Message message) {
        SendMessage output = new SendMessage();
        output.setChatId(message.getChatId());
        output.setText("Неразобранная команда: " + message.getText());
        return output;
    }
}
