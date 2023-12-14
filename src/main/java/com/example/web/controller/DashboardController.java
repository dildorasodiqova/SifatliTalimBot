package com.example.web.controller;

import com.example.web.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 'Mukhtarov Sarvarbek' on 11.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final UserService userService;
    @GetMapping("")
    public String dashboard(Model model){
        // shu yerga
        model.addAttribute("userStatics",userService.statistic());
        return "dashboard/index";
    }
}
