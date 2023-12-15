package com.example.bot.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;
import java.util.List;

/**
 * @author Admin on 15.12.2023
 * @project sifatli_talim_bot
 * @package com.example.bot.util
 * @contact @sarvargo
 */
public class KeyboardButtonUtil {
    public static KeyboardRow ROW_MY_PHONE() {
        KeyboardButton MY_LOCATIONS_BUTTON = new KeyboardButton("Telefon raqam yuborish ", true, false, null, null, null, null);
        return getRow(MY_LOCATIONS_BUTTON);
    }

    public static ReplyKeyboardMarkup MY_PHONE_BUTTON() {
        return getRowList(ROW_MY_PHONE());
    }

    private static KeyboardRow getRow(KeyboardButton... keyboardButtons) {
        return new KeyboardRow(Arrays.asList(keyboardButtons));
    }

    private static ReplyKeyboardMarkup getRowList(KeyboardRow... rows) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(Arrays.asList(rows));
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        return replyKeyboardMarkup;
    }

    private static ReplyKeyboardMarkup getRowList(List<KeyboardRow> rows) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }
}
