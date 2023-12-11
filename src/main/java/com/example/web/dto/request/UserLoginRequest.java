package com.example.web.dto.request;

import lombok.*;

/**
 * @author 'Mukhtarov Sarvarbek' on 11.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLoginRequest {
    private String username;
    private String password;
}
