package com.example;

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
        List<UserStatisticsDTO> statistic = userService.statistic();
        for (UserStatisticsDTO userStatisticsDTO : statistic) {
            System.out.println(userStatisticsDTO.toString());
        }

    }

}
