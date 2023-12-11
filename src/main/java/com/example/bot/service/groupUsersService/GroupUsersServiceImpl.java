package com.example.bot.service.groupUsersService;

import com.example.bot.dto.createDto.GroupUsersCreateDto;
import com.example.bot.dto.responseDto.GroupUsersResponseDto;
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
        return ;
    }

    @Override
    public GroupUsersResponseDto getById(Long groupId) {
        return groupUsersRepository.findById();
    }

    @Override
    public List<GroupUsersResponseDto> usersOfGroup(Long groupId) {
        return null;
    }
}
