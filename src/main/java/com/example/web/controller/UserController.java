package com.example.web.controller;

import com.example.web.dto.responseDto.UserResponseDto;
import com.example.web.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public String getAllUsers(@RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "30") int size,
                              @RequestParam(value = "searchValue", required = false, defaultValue = "") String query,
                              Model model) {

        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("searchValue", query);

        List<UserResponseDto> allResponse = userService.getAll(page, size, query);

        model.addAttribute("userList", allResponse);
        return "user/index";
    }

    @PutMapping("/updateActiveAll")
    public String updateActiveAll(@RequestParam Boolean trueOrFalse, Model model) {
        Boolean aBoolean = userService.updateActiveAll(trueOrFalse);
        model.addAttribute("update", aBoolean);
        return "";
    }

    @PutMapping("/updateActive/{userId}")
    public String updateActive(@RequestParam Boolean trueOrFalse, @RequestParam Long userId, Model model) {
        Boolean aBoolean = userService.updateActive(trueOrFalse, userId);
        model.addAttribute("update", aBoolean);
        return "";
    }

}
