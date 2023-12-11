package com.example.web.service.groupServise;

import com.example.bot.dto.createDto.GroupCreateDto;
import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.bot.entity.UsersEntity;
import com.example.bot.entity.group.GroupEntity;
import com.example.bot.exception.DataNotFoundException;
import com.example.bot.repository.GroupRepository;
import com.example.bot.repository.GroupUsersRepository;
import com.example.web.dto.responseDto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public GroupEntity getById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(()-> new DataNotFoundException("This group not found !!!"));
    }

    @Override
    public GroupResponseDto findById(Long groupId) {
        GroupEntity groupEntity = groupRepository.findById(groupId).orElseThrow(() -> new DataNotFoundException("This group not found !!!"));
        return parse(groupEntity);
    }

    private GroupResponseDto parse(GroupEntity group){
//        return new GroupResponseDto(group);
        return null;
    }
    private List<GroupResponseDto> parse(List<GroupEntity> group){
        List<GroupResponseDto> list = new ArrayList<>();
        for (GroupEntity groupEntity : group) {
            Integer counted = groupUsersRepository.countAllByGroupId(groupEntity.getId());
            list.add(new GroupResponseDto(groupEntity.getId(),groupEntity.getName(), groupEntity.getDescription(),counted,  groupEntity.getStartDate()));
        }
        return list;
    }
    private GroupEntity parse(GroupCreateDto group){
//        return new GroupEntity(group);
        return null;
        // todo
    }
}
