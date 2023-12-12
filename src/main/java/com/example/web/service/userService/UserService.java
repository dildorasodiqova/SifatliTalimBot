package com.example.web.service.userService;

import com.example.web.dto.responseDto.UserResponseDto;
import com.example.web.dto.responseDto.UserStatisticsDTO;

import java.util.List;

public interface UserService{

    List<UserResponseDto> getAll(Integer page, Integer siza, String query);

    UserResponseDto getById(Long userId);
    List<UserStatisticsDTO> statistic();

    List<UserResponseDto> nonPayingUsers(int page , int size);

    Boolean updateActive(Boolean trueOrFalse, Long userId);

    Boolean updateActiveAll(Boolean trueOrFalse);
}
