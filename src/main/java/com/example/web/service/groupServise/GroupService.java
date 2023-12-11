package com.example.web.service.groupServise;

import com.example.bot.dto.createDto.GroupCreateDto;
import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.bot.entity.group.GroupEntity;
import com.example.web.dto.responseDto.UserResponseDto;

import java.security.PublicKey;
import java.util.List;

public interface GroupService{
    GroupEntity getById(Long groupId);
    GroupResponseDto findById(Long groupId);
    List<GroupResponseDto> getAll(Integer page, Integer size)
    GroupResponseDto create(GroupCreateDto dto);
}
