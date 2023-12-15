package com.example.bot.service.groupUsersService;

import com.example.bot.dto.createDto.GroupUsersCreateDto;
import com.example.bot.dto.responseDto.GroupLessonResponseDto;
import com.example.bot.dto.responseDto.GroupUsersResponseDto;
import com.example.bot.entity.group.GroupLessonsEntity;
import com.example.bot.entity.group.GroupUsersEntity;
import com.example.bot.entity.interfase.UserOfGroupMapper;
import com.example.bot.exception.ApiResponse;
import com.example.bot.repository.GroupUsersRepository;
import com.example.web.dto.responseDto.UserOfGroupMapResponse;
import com.example.web.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupUsersServiceImpl implements GroupUsersService{
    private final GroupUsersRepository groupUsersRepository;
    private final UserService userService;
    @Override
    public void add(GroupUsersCreateDto dto) {
        GroupUsersEntity save = groupUsersRepository.save(new GroupUsersEntity(dto.getGroupId(), dto.getUserId()));
    }



    @Override
    public List<UserOfGroupMapResponse> usersOfGroup(Long groupId) {
        List<UserOfGroupMapResponse> list = new ArrayList<>();
        List<UserOfGroupMapper> mapper = groupUsersRepository.mapper(groupId);
        for (UserOfGroupMapper u: mapper) {
            list.add(new UserOfGroupMapResponse(u.getUserId(), u.getName(), u.getSurname(), u.getPhoneNumber()));
        }
        return list;
    }




    @Override
    public String deleteUserOfGroup(Long groupId, Long userId) {
       groupUsersRepository.deleteUserFromGroup(groupId, userId);
       return "Successfully deleted";
    }

    private GroupUsersResponseDto parse(GroupUsersEntity entity) {
        Integer counted = groupUsersRepository.countAllByGroupId(entity.getGroupId());
        return new GroupUsersResponseDto(entity.getGroupId(), entity.getUserId(), entity.getUser().getName() + entity.getUser().getSurname(),counted);
    }
}
