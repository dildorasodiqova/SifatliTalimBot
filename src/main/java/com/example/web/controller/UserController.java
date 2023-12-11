package com.example.web.controller;

import com.example.web.dto.createdDto.UserCreateDto;
import com.example.web.dto.responseDto.UserResponseDto;
import com.example.web.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public String getAllUsers(@RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "30") int size,
                              Model model) {
        // TODO get All users by page
        return "user/index";
    }

    @PostMapping("")
    public String create(@RequestBody UserCreateDto dto, Model model){
        UserResponseDto  add = userService.create(dto);
        model.addAttribute("user", add);
        return "";
    }

    @PutMapping ("/updateActiveAll")
    public String updateActiveAll(@RequestParam Boolean trueOrFalse, Model model){
        Boolean aBoolean = userService.updateActiveAll(trueOrFalse);
        model.addAttribute("update", aBoolean);
        return "";
    }
    @PutMapping ("/updateActive/{userId}")
    public String updateActive(@RequestParam Boolean trueOrFalse, @RequestParam Long userId,  Model model){
        Boolean aBoolean = userService.updateActive(trueOrFalse, userId);
        model.addAttribute("update", aBoolean);
        return "";
    }

    @GetMapping("/byId/{userId}")
    public String getById(@PathVariable Long userId, Model model){
        UserResponseDto byId = userService.getById(userId);
        model.addAttribute("user", byId);
        return "";
    }
}
