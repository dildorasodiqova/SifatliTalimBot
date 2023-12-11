package com.example.bot.controller;

import com.example.bot.dto.createDto.GroupUsersCreateDto;
import com.example.bot.dto.responseDto.GroupLessonResponseDto;
import com.example.bot.dto.responseDto.GroupUsersResponseDto;
import com.example.bot.dto.responseDto.UserResponseDto;
import com.example.bot.service.groupUsersService.GroupUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GroupUsersController {
    private final GroupUsersService groupUsersService;
    @PostMapping()
    public String add(@RequestBody GroupUsersCreateDto dto, Model model){
        GroupUsersResponseDto add = groupUsersService.add(dto);
        model.addAttribute("groupUsers", add);
        return "";
    }

    @GetMapping("/by-id/{groupId}")
    public String getById(@PathVariable Long groupId, Model model){
        GroupUsersResponseDto byId = groupUsersService.getById(groupId);
        model.addAttribute("groupUsers", byId);
        return "";
    }

    @GetMapping("/users-of-group/{groupId}")
    public String usersOfGroup(@PathVariable Long groupId, Model model){
        List<GroupUsersResponseDto> dtoList = groupUsersService.usersOfGroup(groupId);
        model.addAttribute("groupUsersList", dtoList);
        return "";
    }
}
