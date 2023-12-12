package com.example.web.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileResponseDto {
    private Long id;
    private String fullName;
    private String login;
    private String password;
    private LocalDateTime createdDate;
}
