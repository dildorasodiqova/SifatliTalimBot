package com.example.bot.service.groupLessonService;

import com.example.bot.dto.createDto.GroupLessonCreateDto;
import com.example.bot.dto.responseDto.GroupLessonResponseDto;
import com.example.bot.entity.group.GroupEntity;
import com.example.bot.entity.group.GroupLessonsEntity;
import com.example.bot.exception.DataNotFoundException;
import com.example.bot.repository.GroupLessonRepository;
import com.example.web.service.groupServise.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupLessonServiceImpl implements GroupLessonService {
    private final GroupLessonRepository groupLessonRepository;
    private final GroupService groupService;
    @Override
    public GroupLessonResponseDto add(GroupLessonCreateDto dto) {
        GroupLessonsEntity groupLessonsEntity = parse(dto);
        groupLessonRepository.save(groupLessonsEntity);
        return parse(groupLessonsEntity);
    }

    @Override
    public GroupLessonResponseDto getById(Long groupId) {
        GroupLessonsEntity groupLessonNotFound = groupLessonRepository.findById(groupId).orElseThrow(() -> new DataNotFoundException("GroupLesson not found"));
        return parse(groupLessonNotFound);
    }

    private GroupLessonsEntity parse(GroupLessonCreateDto dto){
        GroupEntity group = groupService.getById(dto.getGroupId());
        return new GroupLessonsEntity(1L, group, );
    }

    private GroupLessonResponseDto parse(GroupLessonsEntity entity){
        return new GroupLessonResponseDto(entity.getGroupId(),
                entity.getMediaId(),
                entity.getText(),
                entity.getOrderNumber(),
                entity.getSendTime(),
                entity.getSendDay());
    }
}
