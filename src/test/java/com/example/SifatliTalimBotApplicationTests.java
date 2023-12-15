package com.example;

import com.example.bot.dto.createDto.GroupCreateDto;
import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.bot.entity.group.GroupEntity;
import com.example.bot.exception.ApiResponse;
import com.example.web.dto.responseDto.UserResponseDto;
import com.example.web.dto.responseDto.UserStatisticsDTO;
import com.example.web.service.groupServise.GroupService;
import com.example.web.service.userService.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class SifatliTalimBotApplicationTests {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Test
    void contextLoads() {
//        System.out.println(passwordEncoder.encode(
//                "12345"
//        ));

//        List<UserResponseDto> list = userService.searchUser("", 0, 5);
//        for (UserResponseDto dto : list) {
//            System.out.println(dto.toString());
//        }

//        String s = userService.updatePaidDate(LocalDate.now().plusMonths(1), 8L);
//        System.out.println(s);

//        ApiResponse<GroupResponseDto> response = groupService.create(new GroupCreateDto("G26", "Python", LocalDate.now().plusDays(1)));
//        System.out.println("i'm create  "+response.getCode());
//
//
//        ApiResponse<GroupEntity> byId = groupService.getById(2L);
//        System.err.println("I'm get by id" + byId.getMessage() + "\n" + byId.getData().getName());
//        ApiResponse<String> stringApiResponse = groupService.startGroup(2L);
//        System.err.println(stringApiResponse.getMessage());
//
//        groupService.edit(new GroupCreateDto("G23- G26" , "Nomi o'zgardi", LocalDate.now().plusDays(1)), 1L);


//        groupService.delete(1L);
//        List<GroupResponseDto> all = groupService.getAll(0, 5);
//        for (GroupResponseDto groupResponseDto : all) {
//            System.err.println(groupResponseDto.getName());
//        }


//        ApiResponse<GroupResponseDto> byId = groupService.findById(2L);
//        System.err.println(byId.getData().getName());
    }

}
