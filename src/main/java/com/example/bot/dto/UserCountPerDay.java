package com.example.bot.dto;

/**
 * @author Admin on 17.12.2023
 * @project sifatli_talim_bot
 * @package com.example.bot.dto
 * @contact @sarvargo
 */

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserCountPerDay {
    private Long count;
    private LocalDate date;

    public UserCountPerDay(Long count, LocalDate date) {
        this.count = count;
        this.date = date;
    }

    // Getters and setters
}
