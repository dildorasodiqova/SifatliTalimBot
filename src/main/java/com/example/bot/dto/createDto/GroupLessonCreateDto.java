package com.example.bot.dto.createDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupLessonCreateDto {
    private Long groupId;

    private String text;

    private Integer orderNumber; // grupa kontentlari orasida nechanchi bolib yuborilishi

    private LocalTime sendTime; // 10:00

    private DayOfWeek sendDay; // MONDAY
}
