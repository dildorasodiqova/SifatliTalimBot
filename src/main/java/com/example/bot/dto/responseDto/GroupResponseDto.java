package com.example.bot.dto.responseDto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GroupResponseDto {
    private Long groupId;
    private String name;
    private String description;
    private Integer countOfStudents;
    private LocalDate startDate;
}
