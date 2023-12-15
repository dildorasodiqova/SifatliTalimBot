package com.example.bot.service.handler;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * @author 'Mukhtarov Sarvarbek' on 12.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
public interface UserMessageHandler {
    void handleText(Message message, User user);

    void handleContact(Message message, User user);
}
