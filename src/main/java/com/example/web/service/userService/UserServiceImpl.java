package com.example.web.service.userService;

import com.example.bot.TalimBot;
import com.example.bot.entity.UsersEntity;
import com.example.bot.exception.ApiResponse;
import com.example.bot.repository.UsersRepository;
import com.example.bot.repository.manager.UserRepositoryCustom;
import com.example.web.dto.responseDto.UserResponseDto;
import com.example.web.dto.responseDto.UserStatisticsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository userRepository;

    private final UserRepositoryCustom userRepositoryCustom;
    @Lazy
    @Autowired
    private TalimBot telegramBot;

    @Override
    public PageImpl<UserResponseDto> getAll(Integer page, Integer size, String query) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<UsersEntity> roadPage = userRepository.findAllByIsActiveTrue(pageRequest);
        List<UsersEntity> content = roadPage.getContent();
        return new PageImpl<>(parse(content), pageRequest, roadPage.getTotalElements());
    }


    @Override
    public ApiResponse<UserResponseDto> getById(Long userId) {
        Optional<UsersEntity> byId = userRepository.findById(userId);
        return byId.map(entity -> new ApiResponse<>(true, 200, "Successfully", parse(entity))).orElseGet(() -> new ApiResponse<>(false, 400, "User not found "));
    }

    @Override
    public List<UserStatisticsDTO> statistic() {
        return userRepositoryCustom.getUsersCountLast30Days()
                .stream()
                .map(item -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM");
                    return new UserStatisticsDTO(item.getCount(), item.getDate().format(formatter));
                })
                .toList();
    }

    @Override
    public PageImpl<UserResponseDto> nonPayingUsers(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<UsersEntity> users = userRepository.findAllByPaidUntil(pageRequest);
        List<UsersEntity> content = users.getContent();
        return new PageImpl<>(parse(content), pageRequest, users.getTotalElements());
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
    public PageImpl<UserResponseDto> searchUser(String word, int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<UsersEntity> usersEntities = userRepository.searchUsers(word, pageRequest);
        return new PageImpl<>(parse(usersEntities.getContent()), pageRequest, usersEntities.getTotalElements());
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
    public List<UserResponseDto> findAll() {
        return userRepository.findAllByIsActiveTrue().stream().map(this::parse).toList();
    }

    @Override
    public void checkUserPaidDate() {
        List<UserResponseDto> all = findAll();
        for (UserResponseDto user : all) {
            if (user.getPaidUntil() != null) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText("""
                        Assalomu Aleykum
                                                
                        Tolov sanasiga %d kun qoldi!!
                        Tolov qilishni unutmang.
                        """);
                sendMessage.setChatId(user.getUserId());
                sendMessage.setProtectContent(true);
                if (LocalDate.now().isBefore(user.getPaidUntil())) {
                    long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), user.getPaidUntil());
                    if (daysBetween == 14 || daysBetween == 7 || daysBetween == 3 || daysBetween == 1) {
                        sendMessage.setText(sendMessage.getText().formatted(daysBetween));
                        telegramBot.send(sendMessage);
                    }
                }
            }
        }
    }

    @Override
    public void deleteUserFromGroup(Long groupId, Long userId) {
        userRepository.deleteUserFromGroup(groupId,userId);
    }

    @Override
    public UsersEntity saveUserIfNotExists(UsersEntity user) {
        Optional<UsersEntity> byId = userRepository.findById(user.getUserId());
        return byId.orElseGet(() -> userRepository.save(user));
    }

    private UserResponseDto parse(UsersEntity entity) {
        return new UserResponseDto(entity.getUserId(), entity.getName(), entity.getSurname(), entity.getPhone(), entity.getPaidUntil(), entity.getIsActive());
    }

    private List<UserResponseDto> parse(List<UsersEntity> entities) {
        return entities.stream().map(this::parse).toList();
    }

}
