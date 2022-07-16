package ru.brewcode.bot.model;

import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

public class BotMessage {

    private SendMessage sendMessage;
    private SendPhoto sendPhoto;
    private SendAudio sendAudio;
    private SendPoll pool;

    public BotMessage() {
    }

    public BotMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    public BotMessage(SendPhoto sendPhoto) {
        this.sendPhoto = sendPhoto;
    }

    public BotMessage(SendPoll pool) {
        this.pool = pool;
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public SendPhoto getSendPhoto() {
        return sendPhoto;
    }

    public SendPoll getPool() {
        return pool;
    }
}
