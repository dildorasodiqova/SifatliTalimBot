package com.example.bot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 'Mukhtarov Sarvarbek' on 11.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "telegram")
public class BotVariables {
    private String username;

    private String token;
}
