package ru.brewcode.bot.impl.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {

    // Обрати внимание, что static
    public static ReplyKeyboardMarkup getKeyboard() {

        // Создаем объект клавиатуры
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true); // Подгоняем размер
        keyboard.setOneTimeKeyboard(false); // Клавиатура остается

        // Список рядов
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        // Создаем ряд кнопок
        KeyboardRow keyboardRow = new KeyboardRow();
        // Из одной кнопки
        keyboardRow.add(new KeyboardButton("Дай картинку"));
        // Добавляем ряд в список рядов
        keyboardRows.add(keyboardRow);

        // Наполняем клавиатуру кнопками
        keyboard.setKeyboard(keyboardRows);

        return keyboard;
    }
}
