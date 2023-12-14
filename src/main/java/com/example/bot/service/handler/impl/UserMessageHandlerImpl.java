package com.example.bot.service.handler.impl;

import com.example.bot.TalimBot;
import com.example.bot.entity.UsersEntity;
import com.example.bot.enums.UserSteps;
import com.example.bot.repository.UsersRepository;
import com.example.bot.service.UserStatusManage;
import com.example.bot.service.handler.UserMessageHandler;
import com.example.bot.util.SendObjects;
import com.example.web.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

/**
 * @author 'Mukhtarov Sarvarbek' on 12.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@Service
@RequiredArgsConstructor
public class UserMessageHandlerImpl implements UserMessageHandler {
    @Autowired
    @Lazy
    private TalimBot telegramBot;
    private final UserService userService;
    private final UsersRepository usersRepository;

    @Override
    public void handleText(Message message, User user) {
        String text = message.getText();
        SendObjects sendObjects = new SendObjects(user);
        switch (text) {
            case "/start" -> {
                UsersEntity savedUser = userService
                        .saveUserIfNotExists(
                                new UsersEntity(user.getId(), user.getUserName(), user.getFirstName(), user.getLastName(), true)
                        );
                SendMessage sendMessage = sendObjects.sendMessage();

                if (Optional.ofNullable(savedUser.getName()).isEmpty()) {
                    sendMessage.setText("""
                            Assalomu Aleykum 👋
                                                    
                            Botimizga xush kelibsiz!
                                                    
                            Iltimos ismingizni yozing!
                            """);
                    UserStatusManage.setStep(user.getId(), UserSteps.ENTERING_NAME);
                } else if (Optional.ofNullable(savedUser.getSurname()).isEmpty()) {
                    sendMessage.setText("""
                            Assalomu Aleykum 👋
                                                    
                            Botimizga xush kelibsiz!
                                                    
                            Iltimos familyangizni yozing!
                            """);

                    UserStatusManage.setStep(user.getId(), UserSteps.ENTERING_SURNAME);
                } else if (Optional.ofNullable(savedUser.getPhone()).isEmpty()) {
                    sendMessage.setText("""
                            Assalomu Aleykum 👋
                                                    
                            Botimizga xush kelibsiz!
                                                    
                            Iltimos raqamingizni yuboring!
                            """);
                    UserStatusManage.setStep(user.getId(), UserSteps.ENTERING_PHONE);
                }
                telegramBot.send(sendMessage);
            }
            default -> {
                UserSteps step = UserStatusManage.getStep(user.getId());
                switch (step) {
                    case ENTERING_NAME, ENTERING_SURNAME -> fillingUserData(message, user, step, sendObjects);
                }

            }
        }
    }

    private void fillingUserData(Message message, User user, UserSteps step, SendObjects sendObjects) {
        String text = message.getText();
        switch (step) {
            case ENTERING_NAME -> {
                usersRepository.updateName(user.getId(), text);
                SendMessage sendMessage = sendObjects.sendMessage();
                sendMessage.setText("Iltimos familyangizni kiriting!");
                telegramBot.send(sendMessage);
                UserStatusManage.setStep(user.getId(), UserSteps.ENTERING_SURNAME);
            }
            case ENTERING_SURNAME -> {
                usersRepository.updateSurname(user.getId(), text);
                SendMessage sendMessage = sendObjects.sendMessage();
                sendMessage.setText("Iltimos raqamingizni yuboring!");
                telegramBot.send(sendMessage);
                UserStatusManage.setStep(user.getId(), UserSteps.ENTERING_PHONE);
            }
        }
    }
}
