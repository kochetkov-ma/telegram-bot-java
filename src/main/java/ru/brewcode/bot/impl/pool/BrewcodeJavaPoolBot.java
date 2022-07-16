package ru.brewcode.bot.impl.pool;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.brewcode.bot.model.BotMessage;

import static ru.brewcode.bot.BotConstants.BOT_NAME;
import static ru.brewcode.bot.BotConstants.BOT_TOKEN;

public class BrewcodeJavaPoolBot extends TelegramLongPollingBot {

    // Создаем обработчик для админа
    private final AdminCommandHandler adminCommandHandler = new AdminCommandHandler();

    @Override
    public void onUpdateReceived(Update update) {
        BotMessage output = null;

        if (update.hasMessage())
            output = adminCommandHandler.handleCommand(update.getMessage());

        // Если нечего отправлять, то просто выходим из метода
        if (output == null)
            return;

        try {
            if (output.getPool() != null)
                execute(output.getPool());

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
