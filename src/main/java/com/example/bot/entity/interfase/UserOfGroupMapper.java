package com.example.bot.entity.interfase;

import java.time.LocalDate;

public interface UserOfGroupMapper {
    Long getUserId();
    String getName();
    String getSurname();
    String getPhoneNumber();
    LocalDate getPaidUntil();
    Boolean getIsActive();
}
