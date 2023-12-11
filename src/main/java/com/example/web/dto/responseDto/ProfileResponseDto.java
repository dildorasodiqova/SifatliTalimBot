package com.example.web.dto.responseDto;

import jakarta.validation.constraints.NotBlank;
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
    private Long profileId;
    private String fullName;
    private String login;
    private String password;
    private LocalDateTime createdDate;
}
