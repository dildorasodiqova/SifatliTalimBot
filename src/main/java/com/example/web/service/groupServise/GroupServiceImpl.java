package com.example.web.service.groupServise;

import com.example.bot.dto.createDto.GroupCreateDto;
import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.bot.entity.group.GroupEntity;
import com.example.bot.exception.ApiResponse;
import com.example.bot.repository.GroupRepository;
import com.example.bot.repository.GroupUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupUsersRepository groupUsersRepository;

    @Override
    public GroupResponseDto create(GroupCreateDto dto) {
        GroupEntity entity = parse(dto);
        groupRepository.save(entity);
        return parse(entity);
    }

    @Override
    public List<GroupResponseDto> getAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<GroupEntity> roadPage = groupRepository.findAllByActiveTrue(pageRequest);
        List<GroupEntity> content = roadPage.getContent();
        return parse(content);
    }

    @Override
    public ApiResponse<GroupResponseDto> edit(GroupCreateDto groupCreateDto, Long groupId) {
        Optional<GroupEntity> groupEntity = groupRepository.findById(groupId);
        if (groupEntity.isEmpty()){
            return new ApiResponse<>(false, 400, "Group not found" );
        }
        GroupEntity groupEntity1 = groupEntity.get();
        groupEntity1.setDescription(groupCreateDto.getDescription());
        groupEntity1.setName(groupCreateDto.getName());
        groupEntity1.setImageId("");
        groupEntity1.setStartDate(groupCreateDto.getStartDate());
         groupRepository.save(groupEntity1);
        GroupResponseDto parse = parse(groupEntity1);
        return new ApiResponse<>(true, 200, "Successfully", parse);
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

    private GroupResponseDto parse(GroupEntity group){
        Integer counted = groupUsersRepository.countAllByGroupId(group.getId());
        return new GroupResponseDto(group.getId(), group.getName(), group.getDescription(), counted, "", group.getStartDate());
    }

    private List<GroupResponseDto> parse(List<GroupEntity> group){
        List<GroupResponseDto> list = new ArrayList<>();
        for (GroupEntity groupEntity : group) {
            Integer counted = groupUsersRepository.countAllByGroupId(groupEntity.getId());
          //  shu yerda image urlni nima qilishim kk
            list.add(new GroupResponseDto(groupEntity.getId(),groupEntity.getName(), groupEntity.getDescription(),counted,new String(),  groupEntity.getStartDate()));
        }
        return list;
    }
    private GroupEntity parse(GroupCreateDto group){
        return new GroupEntity(group.getName(), group.getDescription(), "", group.getStartDate());
    }
}
