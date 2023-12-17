package com.example.web.service.groupServise;

import com.example.bot.dto.createDto.GroupCreateDto;
import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.bot.entity.group.GroupEntity;
import com.example.bot.exception.ApiResponse;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface GroupService{
    ApiResponse<GroupResponseDto> create(GroupCreateDto dto);
    ApiResponse<GroupEntity> getById(Long groupId);
    ApiResponse<GroupResponseDto> findById(Long groupId);
    String delete(Long groupId);

    PageImpl<GroupResponseDto> getAll(String query, Integer page, Integer size);
    List<GroupEntity> getAll();

    ApiResponse<String> startGroup(Long groupId);
    ApiResponse<GroupResponseDto> edit(GroupCreateDto groupCreateDto, Long groupId);

    ApiResponse<GroupResponseDto> findByName(String text);
}
