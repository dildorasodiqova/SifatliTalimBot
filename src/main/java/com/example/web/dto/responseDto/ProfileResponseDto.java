package com.example.web.dto.responseDto;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProfileResponseDto {
    private Long id;
    private String fullName;
    private String login;
    private String password;
    private LocalDateTime createdDate;
    private String role;
}
