package com.example.web.controller;

import com.example.web.service.GroupService;
import com.example.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 'Mukhtarov Sarvarbek' on 09.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    @GetMapping("")
    public String getAllGroups(@RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "30") int size,
                              Model model) {
        // TODO get All groups by page
        return "group/index";
    }
}
