package com.example.bot;

import com.example.bot.config.BotVariables;
import com.example.bot.config.TelegramAppConfig;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author 'Mukhtarov Sarvarbek' on 05.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@Component
public class TalimBot extends TelegramLongPollingBot {
    private final BotVariables variables;

    @SneakyThrows
    public TalimBot(BotVariables variables, TelegramBotsApi app) {
        super(variables.getToken());
        this.variables = variables;
        app.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
    }

    @Override
    public String getBotUsername() {
        return variables.getUsername();
    }
}
