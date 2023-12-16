package com.example.web.service.groupServise;

import com.example.bot.dto.createDto.GroupCreateDto;
import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.bot.entity.group.GroupEntity;
import com.example.bot.exception.ApiResponse;
import com.example.bot.repository.GroupRepository;
import com.example.bot.repository.GroupUsersRepository;
import com.example.web.dto.responseDto.ProfileResponseDto;
import com.example.web.entity.profile.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupUsersRepository groupUsersRepository;

    @Override
    public ApiResponse<GroupResponseDto> create(GroupCreateDto dto) {

        if (groupRepository.existsAllByName(dto.getName())) {
            return new ApiResponse<>(false, 400, "This group name already exists.");
        } else {
            GroupEntity entity = parse(dto);
            groupRepository.save(entity);
            return new ApiResponse<>(true, 200, "Successfully", parse(entity));
        }
    }

    @Override
    public PageImpl<GroupResponseDto> getAll(String query, Integer page, Integer size) {
        Pageable pageRequest = PageRequest
                .of(
                        page,
                        size,
                        Sort.by(Sort.Direction.DESC, "createdDate")
                );
        Page<GroupEntity> roadPage = groupRepository.findAllByActiveTrue(query, pageRequest);
        List<GroupEntity> content = roadPage.getContent();
        return new PageImpl<>(parse(content), pageRequest, roadPage.getTotalElements());
    }

    @Override
    public ApiResponse<String> startGroup(Long groupId) {
        Optional<GroupEntity> groupEntity = groupRepository.findById(groupId);
        if (groupEntity.isEmpty()) {
            return new ApiResponse<>(false, 400, "Group not found");
        }
        groupEntity.get().setStartDate(LocalDate.now());
        groupEntity.get().setStarted(true);
        groupRepository.save(groupEntity.get());
        return new ApiResponse<>(true, 200, "Successfully", "Started");
    }

    @Override
    public ApiResponse<GroupResponseDto> edit(GroupCreateDto groupCreateDto, Long groupId) {
        Optional<GroupEntity> groupEntity = groupRepository.findById(groupId);
        if (groupEntity.isEmpty()) {
            return new ApiResponse<>(false, 400, "Group not found");
        }
        GroupEntity groupEntity1 = groupEntity.get();
        groupEntity1.setDescription(groupCreateDto.getDescription());
        groupEntity1.setName(groupCreateDto.getName());
        groupEntity1.setStartDate(groupCreateDto.getStartDate());
        groupEntity1.setTeacherId(groupCreateDto.getTeacherId());
        groupRepository.save(groupEntity1);
        GroupResponseDto parse = parse(groupEntity1);
        return new ApiResponse<>(true, 200, "Successfully", parse);
    }

    @Override
    public ApiResponse<GroupResponseDto> findByName(String text) {
        return groupRepository
                .findByName(text)
                .map(item -> new ApiResponse<>(true, 200, "", parse(item)))
                .orElse(new ApiResponse<>(false, 400, "Not found"));
    }

    @Override
    public ApiResponse<GroupEntity> getById(Long groupId) {
        Optional<GroupEntity> byId = groupRepository.findById(groupId);
        return byId.map(group -> new ApiResponse<>(true, 200, "Successfully", group)).orElseGet(() -> new ApiResponse<>(false, 400, "Group not found."));
    }

    @Override
    public ApiResponse<GroupResponseDto> findById(Long groupId) {
        Optional<GroupEntity> byId = groupRepository.findById(groupId);
        return byId.map(group -> new ApiResponse<>(true, 200, "Successfully", parse(group))).orElseGet(() -> new ApiResponse<>(false, 400, "This group not found"));
    }

    @Override
    public String delete(Long groupId) {
        groupRepository.delete(groupId);
        return "Successfully";
    }

    private GroupResponseDto parse(GroupEntity group) {
        Integer counted = groupUsersRepository.countAllByGroupId(group.getId());
        return new GroupResponseDto(group.getId(), group.getName(), group.getDescription(), group.getTeacherId(), counted, group.getStartDate(), null);
    }

    private List<GroupResponseDto> parse(List<GroupEntity> group) {
        List<GroupResponseDto> list = new ArrayList<>();
        for (GroupEntity groupEntity : group) {
            Integer counted = groupUsersRepository.countAllByGroupId(groupEntity.getId());
            //  shu yerda image urlni nima qilishim kk
            ProfileEntity teacher = groupEntity.getTeacher();
            list.add(new GroupResponseDto(
                    groupEntity.getId(),
                    groupEntity.getName(),
                    groupEntity.getDescription(),
                    groupEntity.getTeacherId(),
                    counted,
                    groupEntity.getStartDate(),
                    teacher == null ? null : new ProfileResponseDto(teacher.getId(), teacher.getFullName(),
                            teacher.getUserName(), null, teacher.getCreatedDate(), teacher.getRole().name()
                    )
            ));
        }
        return list;
    }

    private GroupEntity parse(GroupCreateDto group) {
        return new GroupEntity(null, group.getTeacherId(), group.getName(), group.getDescription(), group.getStartDate(), false);
    }
}
