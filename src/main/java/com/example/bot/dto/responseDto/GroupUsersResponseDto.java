package com.example.bot.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupUsersResponseDto {
    private Long groupId;
    private Long userId;
    private String fullNameOfUser;
    private Integer countOfStudents;
}
