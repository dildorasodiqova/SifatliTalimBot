package com.example.web.service.groupServise;

import com.example.bot.dto.createDto.GroupCreateDto;
import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.bot.entity.group.GroupEntity;
import com.example.bot.exception.ApiResponse;
import com.example.web.dto.responseDto.UserResponseDto;

import java.security.PublicKey;
import java.util.List;

public interface GroupService{
    ApiResponse<GroupEntity> getById(Long groupId);
    ApiResponse<GroupResponseDto> findById(Long groupId);
    List<GroupResponseDto> getAll(Integer page, Integer size);

    ApiResponse<GroupResponseDto> edit(GroupCreateDto groupCreateDto, Long groupId);
    GroupResponseDto create(GroupCreateDto dto);
}
