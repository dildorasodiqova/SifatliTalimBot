package com.example.bot;

import com.example.bot.config.BotVariables;
import com.example.bot.config.TelegramAppConfig;
import com.example.bot.service.handler.UserMessageHandler;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author 'Mukhtarov Sarvarbek' on 05.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@Component
public class TalimBot extends TelegramLongPollingBot {
    private final BotVariables variables;
    private final UserMessageHandler userMessageHandler;

    @SneakyThrows
    public TalimBot(BotVariables variables, TelegramBotsApi app, UserMessageHandler userMessageHandler) {
        super(variables.getToken());
        this.variables = variables;
        this.userMessageHandler = userMessageHandler;
        app.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
        if (update.hasMessage()) {
            Message message = update.getMessage();
            User user = message.getFrom();

            if (message.hasText()) {
                userMessageHandler.handleText(message, user);
            }

        }
    }

    @Override
    public String getBotUsername() {
        return variables.getUsername();
    }

    public Message send(Object obj) {
        try {
            if (obj instanceof SendMessage) {
                return execute((SendMessage) obj);
            } else if (obj instanceof SendPhoto) {
                return execute((SendPhoto) obj);
            } else if (obj instanceof SendVideo) {
                return execute((SendVideo) obj);
            } else if (obj instanceof SendLocation) {
                return execute((SendLocation) obj);
            } else if (obj instanceof SendContact) {
                return execute((SendContact) obj);
            } else if (obj instanceof EditMessageText) {
                execute((EditMessageText) obj);
            } else if (obj instanceof SendDocument) {
                return execute((SendDocument) obj);
            } else if (obj instanceof SendVideoNote) {
                return execute((SendVideoNote) obj);
            } else if (obj instanceof SendSticker) {
                return execute((SendSticker) obj);
            } else if (obj instanceof SendPoll) {
                return execute((SendPoll) obj);
            }
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
