package com.example.bot.service.handler.impl;

import com.example.bot.TalimBot;
import com.example.bot.entity.UsersEntity;
import com.example.bot.service.handler.UserMessageHandler;
import com.example.bot.util.SendObjects;
import com.example.web.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * @author 'Mukhtarov Sarvarbek' on 12.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@Service
@RequiredArgsConstructor
public class UserMessageHandlerImpl implements UserMessageHandler {
    private final TalimBot telegramBot;
    private final UserService userService;

    @Override
    public void handleText(Message message, User user) {
        String text = message.getText();
        SendObjects sendObjects = new SendObjects(user);
        switch (text) {
            case "/start" -> {
                userService
                        .saveUserIfNotExists(
                                new UsersEntity(user.getId(), user.getUserName(), user.getFirstName(), user.getLastName(), true)
                        );
                SendMessage sendMessage = sendObjects.sendMessage();
                sendMessage.setText("Assalomu Aleykum");

                telegramBot.send(sendMessage);
            }
        }
    }
}
