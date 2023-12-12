package com.example.web.dto.responseDto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResponseDto {
    private Long userId;
    private String name;
    private String surname;
    private String phone;
    private LocalDate paidUntil;
    private Boolean isActive = false;
}