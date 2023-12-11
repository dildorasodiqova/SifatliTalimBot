package com.example.web.controller;


import com.example.web.service.profileService.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final ProfileService profileService;


}
