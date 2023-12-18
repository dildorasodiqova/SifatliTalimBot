package com.example.bot.schedule;

import com.example.bot.TalimBot;
import com.example.bot.dto.responseDto.GroupLessonResponseDto;
import com.example.bot.entity.group.GroupEntity;
import com.example.bot.entity.group.GroupLessonsEntity;
import com.example.bot.entity.group.SendGroupLessonHistoryEntity;
import com.example.bot.repository.GroupRepository;
import com.example.bot.repository.SendGroupLessonHistoryRepository;
import com.example.bot.service.groupLessonService.GroupLessonService;
import com.example.bot.service.groupUsersService.GroupUsersService;
import com.example.web.dto.responseDto.UserResponseDto;
import com.example.web.service.groupServise.GroupService;
import com.example.web.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.*;
import java.util.IllegalFormatCodePointException;
import java.util.List;

/**
 * @author Admin on 16.12.2023
 * @project sifatli_talim_bot
 * @package com.example.bot.schedule
 * @contact @sarvargo
 */
@Component
@RequiredArgsConstructor
public class GroupLessonSendSchedule {
    private final GroupService groupService;
    private final GroupLessonService groupLessonService;
    private final TalimBot telegramBot;
    private final GroupRepository groupRepository;
    private final GroupUsersService groupUsersService;
    private final SendGroupLessonHistoryRepository sendGroupLessonHistoryRepository;

    // Run the task every hour
    @Scheduled(cron = "0 0 * * * *")
    public void runDailyTask() {
        List<GroupEntity> allGroups = groupService.getAll();
        LocalTime timeNow = LocalTime.now(ZoneOffset.ofHours(5));
        LocalDate dateNow = LocalDate.now(ZoneOffset.ofHours(5));
        for (GroupEntity group : allGroups) {
            List<GroupLessonsEntity> lessons = groupLessonService.getByGroupId(group.getId(), group.getCurrentOrderNumber() + 1);
            if (!lessons.isEmpty()) {
                for (GroupLessonsEntity lesson : lessons) {
                    if (timeNow.isBefore(lesson.getSendTime()) && dateNow.getDayOfWeek().equals(lesson.getSendDay())) {
                        List<UserResponseDto> groupUsers = groupUsersService.usersOfGroup(group.getId());
                        send(lesson, groupUsers);
                    }
                }
                groupRepository.updateCurrentOrderNum(group.getId(), group.getCurrentOrderNumber() + 1);
            }
        }
    }

    private void send(GroupLessonsEntity lesson, List<UserResponseDto> groupOfUsers) {
        switch (lesson.getMediaType()) {
            case MESSAGE -> {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText(lesson.getText());
                sendMessage.setProtectContent(true);
                sendMessage.setParseMode(ParseMode.HTML);
                for (UserResponseDto user : groupOfUsers) {
                    sendMessage.setChatId(user.getUserId());
                    Message send = telegramBot.send(sendMessage);
                    sendGroupLessonHistoryRepository.save(new SendGroupLessonHistoryEntity(user.getUserId(), send.getMessageId(), lesson.getGroupId(), lesson.getId()));
                }
            }
            case PHOTO -> {
                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setCaption(lesson.getText());
                sendPhoto.setProtectContent(true);
                sendPhoto.setParseMode(ParseMode.HTML);
                sendPhoto.setPhoto(new InputFile(lesson.getMediaId()));
                for (UserResponseDto user : groupOfUsers) {
                    sendPhoto.setChatId(user.getUserId());
                    Message send = telegramBot.send(sendPhoto);
                    sendGroupLessonHistoryRepository
                            .save(new SendGroupLessonHistoryEntity(user.getUserId(),
                                    send.getMessageId(), lesson.getGroupId(),
                                    lesson.getId()));
                }
            }
            case VIDEO -> {
                SendVideo sendVideo = new SendVideo();
                sendVideo.setCaption(lesson.getText());
                sendVideo.setProtectContent(true);
                sendVideo.setParseMode(ParseMode.HTML);
                sendVideo.setVideo(new InputFile(lesson.getMediaId()));
                for (UserResponseDto user : groupOfUsers) {
                    sendVideo.setChatId(user.getUserId());
                    Message send = telegramBot.send(sendVideo);
                    sendGroupLessonHistoryRepository
                            .save(new SendGroupLessonHistoryEntity(user.getUserId(),
                                    send.getMessageId(), lesson.getGroupId(),
                                    lesson.getId()));
                }
            }
            case AUDIO -> {
                SendAudio sendAudio = new SendAudio();
                sendAudio.setCaption(lesson.getText());
                sendAudio.setProtectContent(true);
                sendAudio.setParseMode(ParseMode.HTML);
                sendAudio.setAudio(new InputFile(lesson.getMediaId()));
                for (UserResponseDto user : groupOfUsers) {
                    sendAudio.setChatId(user.getUserId());
                    Message send = telegramBot.send(sendAudio);
                    sendGroupLessonHistoryRepository
                            .save(new SendGroupLessonHistoryEntity(user.getUserId(),
                                    send.getMessageId(), lesson.getGroupId(),
                                    lesson.getId()));
                }
            }
            case DOCUMENT -> {
                SendDocument sendDocument = new SendDocument();
                sendDocument.setCaption(lesson.getText());
                sendDocument.setProtectContent(true);
                sendDocument.setParseMode(ParseMode.HTML);
                sendDocument.setDocument(new InputFile(lesson.getMediaId()));
                for (UserResponseDto user : groupOfUsers) {
                    sendDocument.setChatId(user.getUserId());
                    Message send = telegramBot.send(sendDocument);
                    sendGroupLessonHistoryRepository
                            .save(new SendGroupLessonHistoryEntity(user.getUserId(),
                                    send.getMessageId(), lesson.getGroupId(),
                                    lesson.getId()));
                }
            }

        }
    }
}
