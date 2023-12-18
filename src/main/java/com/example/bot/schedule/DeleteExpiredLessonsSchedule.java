package com.example.bot.schedule;

import com.example.bot.TalimBot;
import com.example.bot.entity.group.SendGroupLessonHistoryEntity;
import com.example.bot.repository.SendGroupLessonHistoryRepository;
import com.example.web.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

/**
 * @author Admin on 16.12.2023
 * @project sifatli_talim_bot
 * @package com.example.bot.schedule
 * @contact @sarvargo
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteExpiredLessonsSchedule {
    private final SendGroupLessonHistoryRepository sendGroupLessonHistoryRepository;
    private final TalimBot telegramBot;

    // Run the task every day at 1 AM
    @Scheduled(cron = "0 0 1 * * *")
    public void runDailyTask() {
        log.info("DeleteExpiredLessonsSchedule start");
        LocalDate dateNow = LocalDate
                .now(ZoneOffset.ofHours(5))
                .minusDays(14);

        List<SendGroupLessonHistoryEntity> allMessages = sendGroupLessonHistoryRepository.findAllByCreatedDateBefore(dateNow);
        for (SendGroupLessonHistoryEntity message : allMessages) {
            DeleteMessage deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(message.getUserId());
            deleteMessage.setMessageId(message.getMessageId());
            telegramBot.send(deleteMessage);
        }
        log.info("Deleted message count={}", allMessages.size());

        int updated = sendGroupLessonHistoryRepository
                .updateVisibleIsFalse(
                        allMessages
                                .stream()
                                .map(SendGroupLessonHistoryEntity::getId)
                                .toList()
                );
    }
}
