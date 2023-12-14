package com.example.bot.service.groupLessonService;

import com.example.bot.dto.createDto.GroupLessonCreateDto;
import com.example.bot.dto.responseDto.GroupLessonResponseDto;
import com.example.bot.entity.group.GroupEntity;
import com.example.bot.entity.group.GroupLessonsEntity;
import com.example.bot.exception.ApiResponse;
import com.example.bot.repository.GroupLessonRepository;
import com.example.web.service.groupServise.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public ApiResponse<GroupLessonResponseDto> getById(Long groupId) {
        Optional<GroupLessonsEntity> byId = groupLessonRepository.findById(groupId);
        return byId.map(entity -> new ApiResponse<>(true, 200, "Successfully", parse(entity))).orElseGet(() -> new ApiResponse<>(false, 400, "GroupLesson not found"));
    }

    private GroupLessonsEntity parse(GroupLessonCreateDto dto) {
        ApiResponse<GroupEntity> byId = groupService.getById(dto.getGroupId());
        GroupEntity data = byId.getData();
        //todo write parse
        return new GroupLessonsEntity();
    }

    private GroupLessonResponseDto parse(GroupLessonsEntity entity) {
        return new GroupLessonResponseDto(entity.getGroupId(),
                entity.getMediaId(),
                entity.getText(),
                entity.getOrderNumber(),
                entity.getSendTime(),
                entity.getSendDay());
    }
}
