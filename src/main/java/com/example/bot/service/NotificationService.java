package com.example.bot.service;

import com.example.bot.TalimBot;
import com.example.bot.service.groupUsersService.GroupUsersService;
import com.example.bot.util.MediaUtil;
import com.example.web.dto.request.NotificationRequestDTO;
import com.example.bot.exception.ApiResponse;
import com.example.web.dto.responseDto.UserResponseDto;
import com.example.web.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.bot.util.MediaUtil.detectFileType;
import static com.example.bot.util.MediaUtil.mediaUpload;

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
    private final GroupUsersService groupUsersService;
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private Long DEV_ID = 1748183791L;


    @SneakyThrows
    public ApiResponse<?> sendNotification(NotificationRequestDTO notificationDTO, Long groupId) {
        List<UserResponseDto> users;
        if (Optional.ofNullable(groupId).isPresent()) {
            users = groupUsersService.usersOfGroup(groupId);
        } else {
            users = userService.findAll();
        }

        if (Optional.ofNullable(notificationDTO.getMedia()).isEmpty() || notificationDTO.getMedia().isEmpty()) {
            Thread thread = new Thread(() -> {
                SendMessage sendMessage = sendMessage(notificationDTO.getText());
                for (UserResponseDto user : users) {
                    sendMessage.setChatId(user.getUserId());
                    sendMessage.setText(sendMessage.getText().replace("username", user.getName()));
                    telegramBot.send(user);
                }
            });
            thread.start();
        } else {
            Thread thread = new Thread(() -> {
                String mediaId = null;
                try {
                    mediaId = mediaUpload(notificationDTO.getMedia(), telegramBot, DEV_ID);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                if (mediaId != null) {
                    switch (detectFileType(notificationDTO.getMedia())) {
                        case "PHOTO" -> {
                            SendPhoto sendPhoto = sendPhoto(mediaId, notificationDTO.getText());
                            for (UserResponseDto user : users) {
                                sendPhoto.setChatId(user.getUserId());
                                if (sendPhoto.getCaption() != null) {
                                    sendPhoto.setCaption(sendPhoto.getCaption().replace("username", user.getName()));
                                }
                                telegramBot.send(user);
                            }
                        }
                        case "VIDEO" -> {
                            SendVideo sendVideo = sendVideo(mediaId, notificationDTO.getText());

                            for (UserResponseDto user : users) {
                                sendVideo.setChatId(user.getUserId());
                                if (sendVideo.getCaption() != null) {
                                    sendVideo.setCaption(sendVideo.getCaption().replace("username", user.getName()));
                                }
                                telegramBot.send(user);
                            }
                        }
                        case "DOCUMENT" -> {
                            SendDocument sendDocument = sendDocument(mediaId, notificationDTO.getText());
                            for (UserResponseDto user : users) {
                                sendDocument.setChatId(user.getUserId());
                                if (sendDocument.getCaption() != null) {
                                    sendDocument.setCaption(sendDocument.getCaption().replace("username", user.getName()));
                                }
                                telegramBot.send(user);
                            }
                        }
                    }
                }
            });
            thread.start();
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

    private SendPhoto sendPhoto(String mediaId, String text) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(new InputFile(mediaId));
        sendPhoto.setProtectContent(true);
        sendPhoto.setCaption(text);
        sendPhoto.setParseMode(ParseMode.HTML);
        return sendPhoto;
    }

    private SendVideo sendVideo(String media, String text) {
        SendVideo sendVideo = new SendVideo();
        sendVideo.setVideo(new InputFile(media));
        sendVideo.setCaption(text);
        sendVideo.setProtectContent(true);
        sendVideo.setParseMode(ParseMode.HTML);
        return sendVideo;
    }

    private SendDocument sendDocument(String media, String text) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setDocument(new InputFile(media));
        sendDocument.setCaption(text);
        sendDocument.setProtectContent(true);
        sendDocument.setParseMode(ParseMode.HTML);
        return sendDocument;
    }
}
