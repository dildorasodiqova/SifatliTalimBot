package com.example.web.controller;

import com.example.bot.dto.createDto.GroupCreateDto;
import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.web.service.groupServise.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public String create(@RequestBody GroupCreateDto dto, Model model) {
        GroupResponseDto group = groupService.create(dto);
        model.addAttribute("group", group);
        return "";
    }

    @GetMapping("/byId/{groupId}")
    public String getById(@PathVariable Long groupId, Model model) {
        GroupResponseDto byId = groupService.findById(groupId);
        model.addAttribute("group", byId);
        return "";
    }
}
