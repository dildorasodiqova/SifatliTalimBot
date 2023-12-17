package com.example.web.controller;

import com.example.bot.service.NotificationService;
import com.example.web.dto.request.NotificationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Admin on 16.12.2023
 * @project sifatli_talim_bot
 * @package com.example.web.controller
 * @contact @sarvargo
 */
@Controller
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("")
    public String notification(@RequestParam(value = "groupId", required = false) Long groupId,
                               Model model) {
        model.addAttribute("groupId", groupId);
        model.addAttribute("notificationDTO", new NotificationRequestDTO());
        return "notification/index";
    }

    @PostMapping("")
    public String notification(@ModelAttribute NotificationRequestDTO request) {
        notificationService.sendNotification(request, request.getGroupId());
        return "redirect:/notification";
    }
}
