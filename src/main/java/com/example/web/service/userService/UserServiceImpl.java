package com.example.web.service.userService;

import com.example.bot.entity.UsersEntity;
import com.example.bot.exception.DataNotFoundException;
import com.example.bot.repository.UsersRepository;
import com.example.web.dto.responseDto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository userRepository;

    @Override
    public List<UserResponseDto> getAll(Integer page, Integer size, String query) {
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

    @Override
    public void saveUserIfNotExists(UsersEntity user) {
        if (!userRepository.existsById(user.getUserId())) userRepository.save(user);
    }

    private UserResponseDto parse(UsersEntity entity) {
        return new UserResponseDto(entity.getUserId(), entity.getName(), entity.getSurname(), entity.getPhone(), entity.getPaidUntil(), entity.getIsActive());
    }

    private List<UserResponseDto> parse(List<UsersEntity> entities) {
        return entities.stream().map(this::parse).toList();
    }

}
