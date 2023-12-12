package com.example.bot.util;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * @author 'Mukhtarov Sarvarbek' on 12.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
public class SendObjects {
    private User user;

    public SendObjects(User user) {
        this.user = user;
    }

    public SendMessage sendMessage() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(user.getId());
        return sendMessage;
    }
}
