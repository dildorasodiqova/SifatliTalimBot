package com.example.bot.service.groupLessonService;

import com.example.bot.dto.createDto.GroupLessonCreateDto;
import com.example.bot.dto.responseDto.GroupLessonResponseDto;
import com.example.bot.exception.ApiResponse;

public interface GroupLessonService {
    GroupLessonResponseDto add(GroupLessonCreateDto dto);

    ApiResponse<GroupLessonResponseDto> getById(Long groupId);
}
