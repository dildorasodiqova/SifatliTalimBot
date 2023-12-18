package com.example.web.controller;

import com.example.web.dto.createdDto.ProfileCreatedDto;
import com.example.web.dto.responseDto.ProfileResponseDto;
import com.example.web.service.profileService.ProfileService;
import com.example.web.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/add")
    public String create(@ModelAttribute ProfileCreatedDto dto) {
        profileService.save(dto);
        return "redirect:/profile";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ProfileCreatedDto dto,
                         @RequestParam Long teacherId,
                         @CookieValue(value = "page", defaultValue = "0") int page,
                         @CookieValue(value = "searchValue", defaultValue = "") String searchValue) {
        profileService.update(dto, teacherId);
        return "redirect:/profile?page=%d&searchValue=%s".formatted(page, searchValue);
    }

    @GetMapping("")
    public String getAll(@RequestParam(value = "searchValue", defaultValue = "") String query,
                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @RequestParam(value = "size", defaultValue = "30") Integer size,
                         HttpServletResponse response,
                         HttpServletRequest request,
                         Model model) {
        CookieUtil.setCookieValue("page", request, response, String.valueOf(page));
        CookieUtil.setCookieValue("searchValue", request, response, String.valueOf(query));
        PageImpl<ProfileResponseDto> all = profileService.getAll(query, page, size);
        model.addAttribute("profileList", all);
        model.addAttribute("addDTO", new ProfileCreatedDto());
        model.addAttribute("searchValue", query);
        return "profile/index";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("profileId") Long profileId,
                         @CookieValue(value = "page", defaultValue = "0") int page,
                         @CookieValue(value = "searchValue", defaultValue = "") String searchValue) {
        profileService.delete(profileId);
        return "redirect:/profile?page=%d&searchValue=%s".formatted(page, searchValue);
    }
}
