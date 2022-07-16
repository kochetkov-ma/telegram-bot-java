package ru.brewcode.bot.impl.photo;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.brewcode.bot.model.BotMessage;
import ru.brewcode.bot.impl.keyboard.Keyboard;

import java.io.File;

public class CommandHandler {

    public BotMessage handleCommand(Message message) {
        String text = message.getText();

        switch (text.toLowerCase()) {
            case "/keyboard":
                return keyboardCommand(message);
            // Добавляем команду с клавиатуры
            case "дай картинку":
                // Добавляем метод обработки
                return imageCommand(message);
            default:
                return unsupportedCommand(message);
        }
    }

    // Появляется новый тип SendPhoto, который не укладывается в нашу структуру CommandHandler.
    // Создаем новый класс BotMessage который будет переносить все виды сообщений.
    private BotMessage imageCommand(Message message) {
        // Объект SendPhoto
        SendPhoto output = new SendPhoto();

        // Ищем файл с картинкой
        File image = new File("img.png");

        // Крепим картинку, ID и сообщение к картинке
        output.setPhoto(new InputFile(image));
        output.setChatId(message.getChatId());
        output.setCaption("Это просил?");

        return new BotMessage(output);
    }

    private BotMessage keyboardCommand(Message message) {
        SendMessage output = new SendMessage();
        output.setChatId(message.getChatId());
        output.setText("Открывается клавиатура");
        output.setReplyMarkup(Keyboard.getKeyboard());
        return new BotMessage(output);
    }

    private BotMessage unsupportedCommand(Message message) {
        return null;
    }
}
