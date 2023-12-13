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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


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
        return userRepository
                .findById(userId)
                .map(this::parse)
                .orElse(null);
    }

    @Override
    public List<UserStatisticsDTO> statistic() {
        List<UserStatisticsDTO> list = new ArrayList<>();
        for (int i = 30; i >= 0; i--) {
            LocalDateTime localDateTime = LocalDateTime.now().minusDays(i);
            int year = localDateTime.getYear();
            int month = localDateTime.getMonthValue();
            int day = localDateTime.getDayOfMonth();

            Integer count = userRepository.countAllByCreatedDate(year, month, day);
            String time = localDateTime.getDayOfMonth() + "/" + localDateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
            list.add(new UserStatisticsDTO(count.longValue(), time));
        }
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
        int i = userRepository.updateActive(trueOrFalse, userId);
        return i > 1;
    }

    @Override
    public Boolean updateActiveAll(Boolean trueOrFalse) {
        int i = userRepository.updateActiveALL(trueOrFalse);
        return i > 0;
    }

    @Override
    public List<UserResponseDto> searchUser(String word, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<UsersEntity> usersEntities = userRepository.searchUsers(word, pageRequest);
        return parse(usersEntities.getContent());
    }

    @Override
    public Boolean changeOneUserActive(Boolean trueOrFalse, Long userId) {
        userRepository.updateUserActivityById(userId, trueOrFalse);
        return true;
    }

    @Override
    public Boolean changeActiveOfUsers(Boolean trueOrFalse) {
        userRepository.updateAllUsersActivity(trueOrFalse);
        return true;
    }

    @Override
    public String updatePaidDate(LocalDate localDate, Long userId) {
        int i = userRepository.updatePaidDate(localDate, userId);
        return i > 0 ? "Successfully" : "Something went wrong";
    }

    @Override
    public Boolean checkIsActive(Long userId) {
        return userRepository.existsByUserIdAndIsActiveIsTrueAndIsDeletedIsFalse(userId);
    }

    @Override
    public UsersEntity saveUserIfNotExists(UsersEntity user) {
        Optional<UsersEntity> byId = userRepository.findById(user.getUserId());
        return byId.orElse(userRepository.save(user));
    }

    private UserResponseDto parse(UsersEntity entity) {
        return new UserResponseDto(entity.getUserId(), entity.getName(), entity.getSurname(), entity.getPhone(), entity.getPaidUntil(), entity.getIsActive());
    }

    private List<UserResponseDto> parse(List<UsersEntity> entities) {
        return entities.stream().map(this::parse).toList();
    }

}
