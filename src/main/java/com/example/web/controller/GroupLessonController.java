package com.example.web.controller;

import com.example.bot.dto.createDto.GroupLessonCreateDto;
import com.example.bot.dto.responseDto.GroupLessonResponseDto;
import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.bot.exception.ApiResponse;
import com.example.bot.service.groupLessonService.GroupLessonService;
import com.example.web.service.groupServise.GroupService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Admin on 17.12.2023
 * @project sifatli_talim_bot
 * @package com.example.web.controller
 * @contact @sarvargo
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/group-lesson")
public class GroupLessonController {
    private final GroupLessonService groupLessonService;
    private final GroupService groupService;

    @PostMapping("")
    public String add(@ModelAttribute GroupLessonCreateDto dto) {
        groupLessonService.add(dto);
        return "redirect:/group-lesson?groupId=%d".formatted(dto.getGroupId());
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long groupId,@RequestParam String lessonId) {
        groupLessonService.delete(lessonId);
        return "redirect:/group-lesson?groupId=%d".formatted(groupId);
    }

    @GetMapping("")
    public String getByGroupId(
            @RequestParam Long groupId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "30") int size,
            Model model
    ) {
        PageImpl<GroupLessonResponseDto> lessons = groupLessonService.getByGroupId(groupId, page, size);
        ApiResponse<GroupResponseDto> group = groupService.findById(groupId);
        model.addAttribute("group", group.getData());
        model.addAttribute("lessonsList", lessons);
        GroupLessonCreateDto lesson = new GroupLessonCreateDto();
        lesson.setGroupId(groupId);
        model.addAttribute("addGroupLessonDTO", lesson);

        return "groupLesson/index";
    }
}
