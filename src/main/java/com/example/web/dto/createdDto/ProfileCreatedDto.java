package com.example.web.dto.createdDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileCreatedDto {
    @NotBlank()
    private String fullName;
    @NotBlank()
    private String login;
    @NotBlank()
    private String password;
}
