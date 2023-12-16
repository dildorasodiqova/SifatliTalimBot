package com.example.bot.service.groupUsersService;

import com.example.bot.dto.createDto.GroupUsersCreateDto;
import com.example.bot.dto.responseDto.GroupUsersResponseDto;
import com.example.bot.exception.ApiResponse;
import com.example.web.dto.responseDto.UserOfGroupMapResponse;
import com.example.web.dto.responseDto.UserResponseDto;

import java.util.List;

public interface GroupUsersService {
    void add(GroupUsersCreateDto dto);


    List<UserResponseDto> usersOfGroup(Long groupId);

    String deleteUserOfGroup(Long groupId, Long userId);
}
