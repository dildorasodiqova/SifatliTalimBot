package com.example.bot.controller;

import com.example.bot.dto.createDto.GroupUsersCreateDto;
import com.example.bot.dto.responseDto.GroupUsersResponseDto;
import com.example.bot.exception.ApiResponse;
import com.example.bot.service.groupUsersService.GroupUsersService;
import com.example.web.dto.responseDto.UserOfGroupMapResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users-of-group")
public class GroupUsersController {
    private final GroupUsersService groupUsersService;
    @PostMapping()
    public String add(@RequestBody GroupUsersCreateDto dto){
         groupUsersService.add(dto);
         return "";
    }


    @GetMapping("/{groupId}")
    public String usersOfGroup(@PathVariable Long groupId, Model model){
        List<UserOfGroupMapResponse> list = groupUsersService.usersOfGroup(groupId);
        model.addAttribute("groupUsersList", list);
        return "";
    }
}
