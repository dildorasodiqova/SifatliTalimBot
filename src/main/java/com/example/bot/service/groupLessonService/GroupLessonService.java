package com.example.bot.service.groupLessonService;

import com.example.bot.dto.createDto.GroupLessonCreateDto;
import com.example.bot.dto.responseDto.GroupLessonResponseDto;

public interface GroupLessonService {
    GroupLessonResponseDto add(GroupLessonCreateDto dto);

    GroupLessonResponseDto getById(Long groupId);
}
