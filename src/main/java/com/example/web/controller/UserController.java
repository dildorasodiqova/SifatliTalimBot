package com.example.web.controller;

import com.example.web.dto.responseDto.UserResponseDto;
import com.example.web.service.userService.UserService;
import com.example.web.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
                              HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {
        CookieUtil.setCookieValue("page", request, response, String.valueOf(page));
        CookieUtil.setCookieValue("searchValue", request, response, query);
        model.addAttribute("searchValue", query);
        PageImpl<UserResponseDto> allResponse = userService.searchUser(query, page, size);

        model.addAttribute("userList", allResponse);

        return "user/index";
    }

    @PostMapping("/updateStatus")
    public String updateActiveAll(@RequestParam(value = "status", defaultValue = "true") Boolean trueOrFalse, Model model,
                                  @CookieValue(value = "page", defaultValue = "0") int page,
                                  @CookieValue(value = "searchValue", defaultValue = "") String query) {
        Boolean aBoolean = userService.updateActiveAll(trueOrFalse);
        model.addAttribute("update", aBoolean);
        return "redirect:/users?page=%d&searchValue=%s".formatted(page, query);
    }

    @PostMapping("/updateActive/{userId}")
    public String updateActive(@RequestParam(value = "status", defaultValue = "true") Boolean trueOrFalse,
                               @PathVariable("userId") Long userId, Model model,
                               @CookieValue(value = "page", defaultValue = "0") int page,
                               @CookieValue(value = "searchValue", defaultValue = "") String query) {
        Boolean aBoolean = userService.updateActive(trueOrFalse, userId);
        model.addAttribute("update", aBoolean);
        return "redirect:/users?page=%d&searchValue=%s".formatted(page, query);
    }

    @PostMapping("/update-paid")
    public String updatePaid(@RequestParam LocalDate paidUntil, @RequestParam Long userId,
                             @CookieValue(value = "page", defaultValue = "0") int page,
                             @CookieValue(value = "searchValue", defaultValue = "") String query) {
        userService.updatePaidDate(paidUntil, userId);
        return "redirect:/users?page=%d&searchValue=%s".formatted(page, query);
    }

}
