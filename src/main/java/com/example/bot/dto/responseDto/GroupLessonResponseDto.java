package com.example.bot.dto.responseDto;

import com.example.bot.entity.group.GroupEntity;
import com.example.bot.enums.LessonMediaType;
import com.example.bot.enums.TextType;
import jakarta.persistence.*;
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
public class GroupLessonResponseDto {
    private Long groupId;

    private String mediaId;

    private String text;

    private Integer orderNumber; // grupa kontentlari orasida nechanchi bolib yuborilishi

    private LocalTime sendTime; // 10:00

    private DayOfWeek sendDay; // MONDAY
}
