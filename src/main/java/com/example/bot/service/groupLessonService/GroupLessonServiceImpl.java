package com.example.bot.service.groupLessonService;

import com.example.bot.TalimBot;
import com.example.bot.dto.createDto.GroupLessonCreateDto;
import com.example.bot.dto.responseDto.GroupLessonResponseDto;
import com.example.bot.entity.group.GroupEntity;
import com.example.bot.entity.group.GroupLessonsEntity;
import com.example.bot.enums.TextType;
import com.example.bot.exception.ApiResponse;
import com.example.bot.repository.GroupLessonRepository;
import com.example.bot.util.MediaUtil;
import com.example.web.service.groupServise.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupLessonServiceImpl implements GroupLessonService {
    private final GroupLessonRepository groupLessonRepository;
    private final TalimBot telegramBot;
    private Long DEV_ID = 1748183791L;

    @Override
    public GroupLessonResponseDto add(GroupLessonCreateDto dto) {
        GroupLessonsEntity groupLessonsEntity = parse(dto);
        if (dto.getFile() != null && !dto.getFile().isEmpty()) {
            String mediaId = getMediaId(dto.getFile());
            groupLessonsEntity.setMediaId(mediaId);
            groupLessonsEntity.setMediaType(MediaUtil.detectType(dto.getFile()));

        }

        groupLessonRepository.save(groupLessonsEntity);
        return parse(groupLessonsEntity);
    }

    @Override
    public PageImpl<GroupLessonResponseDto> getByGroupId(Long groupId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<GroupLessonsEntity> lessonsEntities = groupLessonRepository.findAllByGroupId(groupId, pageable);
        return new PageImpl<>(
                lessonsEntities.stream().map(this::parse).toList(),
                pageable, lessonsEntities.getTotalElements()
        );
    }

    @Override
    public List<GroupLessonsEntity> getByGroupId(Long groupId, Integer currentOrder) {
        return groupLessonRepository.findAllByGroupIdAndOrderNumber(groupId, currentOrder);
    }

    @SneakyThrows
    private String getMediaId(MultipartFile file) {
        return MediaUtil.mediaUpload(file, telegramBot, DEV_ID);
    }


    public ApiResponse<GroupLessonResponseDto> getById(Long groupId) {
        Optional<GroupLessonsEntity> byId = groupLessonRepository.findById(groupId);
        return byId.map(entity -> new ApiResponse<>(true, 200, "Successfully", parse(entity))).orElseGet(() -> new ApiResponse<>(false, 400, "GroupLesson not found"));
    }

    private GroupLessonsEntity parse(GroupLessonCreateDto dto) {
        GroupLessonsEntity entity = new GroupLessonsEntity();
        entity.setGroupId(dto.getGroupId());
        entity.setText(dto.getText());
        entity.setSendTime(dto.getSendTime());
        entity.setSendDay(dto.getSendDay());
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setGroupId(dto.getGroupId());
        entity.setTextType(TextType.HTML);
        return entity;
    }

    private GroupLessonResponseDto parse(GroupLessonsEntity entity) {
        return new GroupLessonResponseDto(entity.getGroupId(),
                entity.getMediaId(),
                entity.getText(),
                entity.getOrderNumber(),
                entity.getSendTime(),
                entity.getSendDay(),
                entity.getMediaType()
        );
    }


}
