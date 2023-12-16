package com.example.web.controller;

import com.example.bot.dto.createDto.GroupCreateDto;
import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.bot.exception.ApiResponse;
import com.example.web.dto.createdDto.ProfileCreatedDto;
import com.example.web.dto.responseDto.ProfileResponseDto;
import com.example.web.service.profileService.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
                         @ModelAttribute Long teacherId) {
        profileService.update(dto, teacherId);
        return "redirect:/profile";
    }

    @PostMapping("/getAll")
    public String getAll(@ModelAttribute String query ,
                         @ModelAttribute Integer page,
                         @ModelAttribute Integer size,
                         Model model) {
        PageImpl<ProfileResponseDto> all = profileService.getAll(query, page, size);
        model.addAttribute("profileList", all);
        return "";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Long teacherId) {
        profileService.delete(teacherId);
        return "";
    }
}
