package com.example.web.controller;

import com.example.web.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * @author 'Mukhtarov Sarvarbek' on 09.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final ProfileService profileService;


}
