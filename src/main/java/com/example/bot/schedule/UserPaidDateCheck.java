package com.example.bot.schedule;

import com.example.web.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Admin on 16.12.2023
 * @project sifatli_talim_bot
 * @package com.example.bot.schedule
 * @contact @sarvargo
 */
@Component
@RequiredArgsConstructor
public class UserPaidDateCheck {
    private final UserService userService;
    // Run the task every day at 1 AM
    @Scheduled(cron = "0 0 1 * * *")
    public void runDailyTask() {
        userService.checkUserPaidDate();
        System.out.println("Daily task executed at: " + System.currentTimeMillis());
    }
}
