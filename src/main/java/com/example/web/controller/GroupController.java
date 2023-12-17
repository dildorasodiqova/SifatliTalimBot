package com.example.web.controller;

import com.example.bot.dto.createDto.GroupCreateDto;
import com.example.bot.dto.createDto.GroupLessonCreateDto;
import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.bot.exception.ApiResponse;
import com.example.bot.service.groupUsersService.GroupUsersService;
import com.example.web.dto.responseDto.ProfileResponseDto;
import com.example.web.dto.responseDto.UserOfGroupMapResponse;
import com.example.web.dto.responseDto.UserResponseDto;
import com.example.web.enums.ProfileRole;
import com.example.web.service.groupServise.GroupService;
import com.example.web.service.profileService.ProfileService;
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
    private final ProfileService profileService;

    @GetMapping("")
    public String getAllGroups(@RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(value = "searchValue", required = false, defaultValue = "") String query,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               Model model) {
        List<ProfileResponseDto> teacherList = profileService.getByRole(ProfileRole.ROLE_TEACHER);
        CookieUtil.setCookieValue("page", request, response, String.valueOf(page));
        CookieUtil.setCookieValue("searchValue", request, response, query);
        model.addAttribute("groupList", groupService.getAll(query, page, 30));
        model.addAttribute("searchValue", query);
        model.addAttribute("addDTO", new GroupCreateDto());
        model.addAttribute("teacherList", teacherList);

        return "group/index";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute GroupCreateDto dto) {
        if (dto.getIsUpdate()) {
            groupService.edit(dto, dto.getGroupId());
        } else {
            groupService.create(dto);
        }
        return "redirect:/groups";
    }

    @GetMapping("/info/{groupId}")
    public String getById(@PathVariable Long groupId,
                          Model model) {
        ApiResponse<GroupResponseDto> byId = groupService.findById(groupId);
        List<UserResponseDto> userOfGroupMapResponses = groupUsersService.usersOfGroup(groupId);
        model.addAttribute("group", byId.getData());
        model.addAttribute("userList", userOfGroupMapResponses);
        model.addAttribute("addGroupLessonDTO",new GroupLessonCreateDto());
        return "group/info";
    }

    @GetMapping("/delete/{groupId}")
    public String delete(@PathVariable Long groupId,
                         @CookieValue(value = "page", defaultValue = "0") int page,
                         @CookieValue(value = "searchValue", defaultValue = "") String searchValue) {
        groupService.delete(groupId);
        return "redirect:/groups?searchValue=%s&page=%d".formatted(searchValue, page);
    }

    @GetMapping("/logout")
    public String delete(@RequestParam Long groupId,
                         @RequestParam Long userId) {
        groupUsersService.deleteUserOfGroup(groupId, userId);
        return "redirect:/groups/info/%d".formatted(groupId);
    }

}
