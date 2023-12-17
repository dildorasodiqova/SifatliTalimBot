package com.example.bot.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Admin on 16.12.2023
 * @project sifatli_talim_bot
 * @package com.example.bot.schedule
 * @contact @sarvargo
 */
@Component
public class UserPaidDateCheck {

    // Run the task every day at 1 AM
    @Scheduled(cron = "0 0 1 * * *")
    public void runDailyTask() {
        // Your task logic goes here
        System.out.println("Daily task executed at: " + System.currentTimeMillis());
    }
}
