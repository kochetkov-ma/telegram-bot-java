package ru.brewcode.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.brewcode.bot.impl.group.BrewcodeJavaGroupBot;

public class Main {

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            LongPollingBot bot = new BrewcodeJavaGroupBot();

            botsApi.registerBot(bot);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
