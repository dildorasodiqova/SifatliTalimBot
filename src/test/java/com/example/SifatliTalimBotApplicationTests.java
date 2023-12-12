package com.example;

import com.example.web.dto.responseDto.UserResponseDto;
import com.example.web.dto.responseDto.UserStatisticsDTO;
import com.example.web.service.userService.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class SifatliTalimBotApplicationTests {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
//        System.out.println(passwordEncoder.encode(
//                "12345"
//        ));
        List<UserResponseDto> list = userService.nonPayingUsers(0, 5);
        for (UserResponseDto userResponseDto : list) {
            System.out.println(userResponseDto.toString());
        }

    }

}
