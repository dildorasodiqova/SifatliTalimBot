package com.example.web.controller;

import com.example.bot.dto.createDto.GroupCreateDto;
import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.bot.exception.ApiResponse;
import com.example.bot.service.groupUsersService.GroupUsersService;
import com.example.web.dto.responseDto.UserOfGroupMapResponse;
import com.example.web.dto.responseDto.UserResponseDto;
import com.example.web.service.groupServise.GroupService;
import com.example.web.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final GroupUsersService groupUsersService;

    @GetMapping("")
    public String getAllGroups(@RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(value = "searchValue", required = false, defaultValue = "") String query,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               Model model) {
        CookieUtil.setCookieValue("page", request, response, String.valueOf(page));
        CookieUtil.setCookieValue("searchValue", request, response, query);
        model.addAttribute("groupList", groupService.getAll(query, page, 30));
        model.addAttribute("searchValue", query);
        model.addAttribute("addDTO", new GroupCreateDto());

        return "group/index";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute GroupCreateDto dto, Model model) {
        ApiResponse<GroupResponseDto> response = groupService.create(dto);
        model.addAttribute("group", response.getData());
        return "redirect:/groups";
    }

    @GetMapping("/info/{groupId}")
    public String getById(@PathVariable Long groupId,
                          Model model) {
        ApiResponse<GroupResponseDto> byId = groupService.findById(groupId);
        List<UserResponseDto> userOfGroupMapResponses = groupUsersService.usersOfGroup(groupId);
        model.addAttribute("group", byId.getData());
        model.addAttribute("userList", userOfGroupMapResponses);
        return "group/info";
    }

    @GetMapping("/delete/{groupId}")
    public String delete(@PathVariable Long groupId,
                         @CookieValue(value = "page", defaultValue = "0") int page,
                         @CookieValue(value = "searchValue", defaultValue = "") String searchValue) {
        groupService.delete(groupId);
        return "redirect:/groups?searchValue=%s&page=%d".formatted(searchValue, page);
    }
}
