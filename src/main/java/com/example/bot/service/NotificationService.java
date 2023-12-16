package com.example.bot.service;

import com.example.bot.TalimBot;
import com.example.web.dto.request.NotificationRequestDTO;
import com.example.bot.exception.ApiResponse;
import com.example.web.dto.responseDto.UserResponseDto;
import com.example.web.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Admin on 15.12.2023
 * @project sifatli_talim_bot
 * @package com.example.bot.service
 * @contact @sarvargo
 */
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final TalimBot telegramBot;
    private final UserService userService;
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public ApiResponse<?> sendNotification(NotificationRequestDTO notificationDTO, Long groupId) {
        List<UserResponseDto> users;
        if (Optional.ofNullable(groupId).isPresent()) {
            // todo get group of users
            users = new ArrayList<>();
        } else {
            users = userService.findAll();
        }

        if (Optional.ofNullable(notificationDTO.getMedia()).isEmpty() || notificationDTO.getMedia().isEmpty()) {
            SendMessage sendMessage = sendMessage(notificationDTO.getText());
            for (UserResponseDto user : users) {
                sendMessage.setChatId(user.getUserId());
                sendMessage.setText(sendMessage.getText().replace("username", user.getName()));
                telegramBot.send(user);
            }
        } else {

        }
        return new ApiResponse<>(true, 200, "", null);
    }

    private SendMessage sendMessage(String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setProtectContent(true);
        return sendMessage;
    }

}
