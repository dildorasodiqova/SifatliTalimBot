package com.example.bot.service.handler.impl;

import com.example.bot.TalimBot;
import com.example.bot.dto.createDto.GroupUsersCreateDto;
import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.bot.entity.UsersEntity;
import com.example.bot.enums.UserSteps;
import com.example.bot.exception.ApiResponse;
import com.example.bot.repository.UsersRepository;
import com.example.bot.service.UserStatusManage;
import com.example.bot.service.groupUsersService.GroupUsersService;
import com.example.bot.service.handler.UserMessageHandler;
import com.example.bot.util.KeyboardButtonUtil;
import com.example.bot.util.SendObjects;
import com.example.web.service.groupServise.GroupService;
import com.example.web.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
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
    private final GroupService groupService;
    private final GroupUsersService groupUsersService;

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
                    sendMessage.setReplyMarkup(KeyboardButtonUtil.MY_PHONE_BUTTON());
                    UserStatusManage.setStep(user.getId(), UserSteps.ENTERING_PHONE);
                } else if (Optional.ofNullable(savedUser.getGroupId()).isEmpty()) {
                    sendMessage.setText("""
                            Assalomu Aleykum 👋
                                                    
                            Botimizga xush kelibsiz!
                                                    
                            <b>Iltimos guruhingizni yozing!</b>
                            """);
                    sendMessage.setParseMode(ParseMode.HTML);
                    UserStatusManage.setStep(user.getId(), UserSteps.ENTERING_GROUP);
                } else {
                    sendMessage.setText("Assalomu Aleykum");
                }

                telegramBot.send(sendMessage);
            }
            default -> {
                UserSteps step = UserStatusManage.getStep(user.getId());
                switch (step) {
                    case ENTERING_NAME, ENTERING_SURNAME -> fillingUserData(message, user, step, sendObjects);
                    case ENTERING_GROUP -> {
                        SendMessage sendMessage = sendObjects.sendMessage();
                        ApiResponse<GroupResponseDto> groupRes = groupService.findByName(text);
                        if (groupRes.getIsSuccess()) {
                            usersRepository.updateGroup(user.getId(), groupRes.getData().getGroupId());
                            groupUsersService.add(new GroupUsersCreateDto(user.getId(), groupRes.getData().getGroupId()));
                            sendMessage.setText("Muvaffaqiyatli tanlandi!");
                            telegramBot.send(sendMessage);
                            UserStatusManage.remove(user.getId());
                            return;
                        }
                        sendMessage.setText("Bunday guruh topilmadi!");
                        telegramBot.send(sendMessage);
                    }
                }

            }
        }
    }

    @Override
    public void handleContact(Message message, User user) {
        UserSteps step = UserStatusManage.getStep(user.getId());
        SendObjects sendObjects = new SendObjects(user);
        switch (step) {
            case ENTERING_PHONE -> fillingUserData(message, user, step, sendObjects);
        }
    }

    private void fillingUserData(Message message, User user, UserSteps step, SendObjects sendObjects) {
        String text = message.getText();
        SendMessage sendMessage = sendObjects.sendMessage();
        switch (step) {
            case ENTERING_NAME -> {
                usersRepository.updateName(user.getId(), text);
                sendMessage.setText("Iltimos familyangizni kiriting!");
                telegramBot.send(sendMessage);
                UserStatusManage.setStep(user.getId(), UserSteps.ENTERING_SURNAME);
            }
            case ENTERING_SURNAME -> {
                usersRepository.updateSurname(user.getId(), text);
                sendMessage.setText("Iltimos raqamingizni yuboring!");
                sendMessage.setReplyMarkup(KeyboardButtonUtil.MY_PHONE_BUTTON());
                telegramBot.send(sendMessage);
                UserStatusManage.setStep(user.getId(), UserSteps.ENTERING_PHONE);
            }
            case ENTERING_PHONE -> {
                Contact contact = message.getContact();
                usersRepository.updatePhone(user.getId(), contact.getPhoneNumber());
                sendMessage.setText("Guruhingizni kiriting!");
                telegramBot.send(sendMessage);
                UserStatusManage.setStep(user.getId(), UserSteps.ENTERING_GROUP);
            }
        }
    }
}
