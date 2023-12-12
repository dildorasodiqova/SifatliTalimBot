package com.example.web.service.userService;

import com.example.bot.entity.UsersEntity;
import com.example.bot.exception.DataNotFoundException;
import com.example.bot.repository.UsersRepository;
import com.example.web.dto.responseDto.UserResponseDto;
import com.example.web.dto.responseDto.UserStatisticsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


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
    public List<UserStatisticsDTO> statistic() {
        List<UserStatisticsDTO> list = new ArrayList<>();
        for (int i = 30; i >= 1; i--) {
            LocalDateTime localDateTime = LocalDateTime.now().minusDays(i);
            int year = localDateTime.getYear();
            int month = localDateTime.getMonthValue();
            int day = localDateTime.getDayOfMonth();

            Integer count = userRepository.countAllByCreatedDate(year, month, day);
            String time = localDateTime.getDayOfMonth() + "/" + localDateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
            list.add(new UserStatisticsDTO(count.longValue(), time));
        }
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        Integer count = userRepository.countAllByCreatedDate(year, month, day);
        String time = now.getDayOfMonth() + "/" + now.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());

        list.add(new UserStatisticsDTO(count.longValue(),time ));
        return list;
    }

    @Override
    public List<UserResponseDto> nonPayingUsers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<UsersEntity> users = userRepository.findAllByPaidUntil(pageRequest);
        List<UsersEntity> content = users.getContent();
        return parse(content);
    }

    @Override
    public Boolean updateActive(Boolean trueOrFalse, Long userId) {
        return userRepository.updateActive(trueOrFalse, userId);
    }

    @Override
    public Boolean updateActiveAll(Boolean trueOrFalse) {
        return userRepository.updateActiveALL(trueOrFalse);
    }

    private UserResponseDto parse(UsersEntity entity) {
        return new UserResponseDto(entity.getUserId(), entity.getName(), entity.getSurname(), entity.getPhone(), entity.getPaidUntil(), entity.getIsActive());
    }

    private List<UserResponseDto> parse(List<UsersEntity> entities) {
        return entities.stream().map(this::parse).toList();
    }

}
