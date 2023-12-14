package com.example.bot.service.groupUsersService;

import com.example.bot.dto.createDto.GroupUsersCreateDto;
import com.example.bot.dto.responseDto.GroupUsersResponseDto;
import com.example.bot.entity.group.GroupUsersEntity;
import com.example.bot.repository.GroupUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupUsersServiceImpl implements GroupUsersService{
    private final GroupUsersRepository groupUsersRepository;
    @Override
    public GroupUsersResponseDto add(GroupUsersCreateDto dto) {
        // todo
        return null;
    }

    @Override
    public GroupUsersResponseDto getById(Long groupId) {
        // todo
//        return groupUsersRepository.findById(groupId).orElse(null);
        return null;
    }

    @Override
    public List<GroupUsersResponseDto> usersOfGroup(Long groupId) {
        List<User>
        List<GroupUsersEntity> allByGroupId = groupUsersRepository.findAllByGroupId(groupId);
        for (GroupUsersEntity users : allByGroupId) {
            users.getUser()
        }
    }

    @Override
    public String addUserToGroup(Long groupId, Long userId) {
        return null;
    }

    @Override
    public String deleteUserOfGroup(Long groupId, Long userId) {
        return null;
    }
}
