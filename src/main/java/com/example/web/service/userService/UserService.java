package com.example.web.service.userService;

import com.example.web.dto.createdDto.UserCreateDto;
import com.example.web.dto.responseDto.UserResponseDto;

import java.util.List;

public interface UserService{

    UserResponseDto create(UserCreateDto dto);
    List<UserResponseDto> getAll(Integer page, Integer siza);

    UserResponseDto getById(Long userId);

    Boolean updateActive(Boolean trueOrFalse, Long userId);

    Boolean updateActiveAll(Boolean trueOrFalse);
}
