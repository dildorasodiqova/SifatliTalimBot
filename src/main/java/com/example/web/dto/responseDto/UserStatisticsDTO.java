package com.example.web.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 'Mukhtarov Sarvarbek' on 12.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserStatisticsDTO {

    private Long count = 0L;

    private String date; // 12-dekabr

    // { count: 10, date: 12-dekabr } like this
}
