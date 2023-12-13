package com.example.web.service.userService;

import com.example.bot.exception.ApiResponse;
import com.example.web.dto.responseDto.UserResponseDto;
import com.example.web.dto.responseDto.UserStatisticsDTO;

import java.time.LocalDate;
import java.util.List;

public interface UserService{

    List<UserResponseDto> getAll(Integer page, Integer siza, String query);

    ApiResponse<UserResponseDto> getById(Long userId);
    List<UserStatisticsDTO> statistic();

    List<UserResponseDto> nonPayingUsers(int page , int size);

    Boolean updateActive(Boolean trueOrFalse, Long userId);

    Boolean updateActiveAll(Boolean trueOrFalse);

    List<UserResponseDto> searchUser(String word, int page, int size);

    Boolean changeOneUserActive(Boolean trueOrFalse, Long userId);
    Boolean changeActiveOfUsers(Boolean trueOrFalse);

    String updatePaidDate(LocalDate localDate, Long userId);


}
