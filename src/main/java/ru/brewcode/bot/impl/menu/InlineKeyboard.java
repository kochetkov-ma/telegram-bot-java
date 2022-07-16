package ru.brewcode.bot.impl.menu;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboard {

    // Обрати внимание, что static
    public static ReplyKeyboard getInlineKeyboard() {

        // Создаем объект встроенной в чат клавиатуры
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();

        // Список рядов, состоящий из списка кнопок
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();

        // Создаем список кнопок
        List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

        // Из одной кнопки
        var btn = new InlineKeyboardButton("Какой сегодня день недели");

        // Устанавливаем кнопке callback-команду
        btn.setCallbackData("day");

        // Добавляем кнопку в список ряда
        keyboardRow.add(btn);

        // Добавляем ряд в список рядов
        keyboardRows.add(keyboardRow);

        // Наполняем клавиатуру кнопками
        keyboard.setKeyboard(keyboardRows);

        return keyboard;
    }
}
