package com.example.bot.service.groupLessonService;

import com.example.bot.dto.createDto.GroupLessonCreateDto;
import com.example.bot.dto.responseDto.GroupLessonResponseDto;
import com.example.bot.entity.group.GroupLessonsEntity;
import com.example.bot.exception.ApiResponse;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface GroupLessonService {
    GroupLessonResponseDto add(GroupLessonCreateDto dto);

    PageImpl<GroupLessonResponseDto> getByGroupId(Long groupId, int page, int size);

    List<GroupLessonsEntity> getByGroupId(Long groupId, Integer currentOrder);
}
