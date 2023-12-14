package com.example.bot.controller;

import com.example.bot.dto.createDto.GroupLessonCreateDto;
import com.example.bot.dto.createDto.GroupUsersCreateDto;
import com.example.bot.dto.responseDto.GroupLessonResponseDto;
import com.example.bot.dto.responseDto.GroupUsersResponseDto;
import com.example.bot.service.groupLessonService.GroupLessonService;
import com.example.bot.service.groupLessonService.GroupLessonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class GroupLessonController {
    private final GroupLessonService groupLessonService;

    @PostMapping()
    public String add(@ModelAttribute GroupLessonCreateDto dto,
                      @RequestParam("mediaFile") MultipartFile mediaFile,
                      Model model) {
        GroupLessonResponseDto add = groupLessonService.add(dto);
        model.addAttribute("groupLesson", add);
        return "";
    }

}
