package com.example.web.service.userService;

import com.example.web.dto.createdDto.UserCreateDto;
import com.example.web.dto.responseDto.UserResponseDto;
import org.springframework.ui.Model;

import java.util.List;

public interface UserService{

    List<UserResponseDto> getAll(Integer page, Integer siza, String query);

    UserResponseDto getById(Long userId);

    Boolean updateActive(Boolean trueOrFalse, Long userId);

    Boolean updateActiveAll(Boolean trueOrFalse);
}
