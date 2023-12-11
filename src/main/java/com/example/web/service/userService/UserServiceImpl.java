package com.example.web.service.userService;

import com.example.bot.entity.UsersEntity;
import com.example.bot.exception.DataAlreadyExistsException;
import com.example.bot.exception.DataNotFoundException;
import com.example.bot.repository.UsersRepository;
import com.example.web.dto.createdDto.UserCreateDto;
import com.example.web.dto.responseDto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository userRepository;

    @Override
    public UserResponseDto create(UserCreateDto dto) {
        if (userRepository.existsAllByPhone(dto.getPhone())){
            throw new DataAlreadyExistsException("This phone number already exists !");
        }
        UsersEntity parse = parse(dto);
        userRepository.save(parse);
        return parse(parse);
    }

    @Override
    public List<UserResponseDto> getAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<UsersEntity> roadPage = userRepository.findAllByIsActiveTrue(pageRequest);
        List<UsersEntity> content = roadPage.getContent();
        return parse(content);
    }


    @Override
    public UserResponseDto getById(Long userId) {
        UsersEntity usersEntity = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User not found !!!"));
        return parse(usersEntity);
    }

    @Override
    public Boolean updateActive(Boolean trueOrFalse, Long userId) {
        return userRepository.updateActive(trueOrFalse, userId);
    }

    @Override
    public Boolean updateActiveAll(Boolean trueOrFalse) {
        return userRepository.updateActiveALL(trueOrFalse);
    }

    private UserResponseDto parse(UsersEntity entity){
        return new UserResponseDto(entity.getUserId(), entity.getName(), entity.getSurname(), entity.getPhone(), entity.getPaidUntil());
    }

    private List<UserResponseDto> parse(List<UsersEntity> entities){
        List<UserResponseDto> list = new ArrayList<>();
        for (UsersEntity entity : entities) {
            list.add(new UserResponseDto(entity.getUserId(), entity.getName(), entity.getSurname(), entity.getPhone(), entity.getPaidUntil()));
        }
      return list;
       }
    private UsersEntity parse(UserCreateDto dto){
//        return new UsersEntity(dto);
        return null;
        // todo
    }
}
