package com.example.bot.dto.responseDto;

import com.example.web.dto.responseDto.ProfileResponseDto;
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
    private Long teacherId;
    private Integer countOfStudents;
    private LocalDate startDate;
    private ProfileResponseDto teacher;
}
