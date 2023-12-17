package com.example.bot.schedule;

import com.example.bot.entity.group.GroupEntity;
import com.example.web.service.groupServise.GroupService;
import com.example.web.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    // Run the task every day at 1 AM
    @Scheduled(cron = "0 0 1 * * *")
    public void runDailyTask() {
        List<GroupEntity> allGroups = groupService.getAll();

    }
}
