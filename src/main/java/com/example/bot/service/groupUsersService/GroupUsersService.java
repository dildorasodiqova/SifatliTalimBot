package com.example.bot.service.groupUsersService;

import com.example.bot.dto.createDto.GroupUsersCreateDto;
import com.example.bot.dto.responseDto.GroupUsersResponseDto;

import java.util.List;

public interface GroupUsersService {
    GroupUsersResponseDto add(GroupUsersCreateDto dto);

    GroupUsersResponseDto getById(Long groupId);

    List<GroupUsersResponseDto> usersOfGroup(Long groupId);

    String addUserToGroup(Long groupId, Long userId);
    String deleteUserOfGroup(Long groupId, Long userId);
}
