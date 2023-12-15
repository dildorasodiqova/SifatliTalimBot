package com.example.web.dto.createdDto;

import com.example.web.enums.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileCreatedDto {
    private String fullName;
    private String login;
    private String password;
    private String role;
}
