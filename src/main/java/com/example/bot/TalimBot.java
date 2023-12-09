package com.example.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author 'Mukhtarov Sarvarbek' on 05.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@Component
public class TalimBot extends TelegramLongPollingBot {
    public TalimBot(){

        super("");
    }
    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return null;
    }
}
