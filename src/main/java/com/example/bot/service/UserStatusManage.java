package com.example.bot.service;

import com.example.bot.enums.UserSteps;

import java.util.HashMap;
import java.util.Map;

import static com.example.bot.enums.UserSteps.DEFAULT_STEP;

/**
 * @author 'Mukhtarov Sarvarbek' on 13.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
public class UserStatusManage {
    public static Map<Long, UserSteps> USER_STEPS = new HashMap<>();

    public static void setStep(Long userId, UserSteps step) {
        USER_STEPS.put(userId, step);
    }

    public static UserSteps getStep(Long userId) {
        return USER_STEPS.getOrDefault(userId, DEFAULT_STEP);
    }

    public static void remove(Long id) {
        USER_STEPS.remove(id);
    }
}
