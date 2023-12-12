package com.example.bot.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupResponseDto {
    private Long groupId;
    private String name;
    private String description;
    private Integer countOfStudents;
    private String imageId; // id is telegram media id
    private LocalDate startDate;
}
