package com.example.web.service;

import com.example.bot.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 'Mukhtarov Sarvarbek' on 09.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository userRepository;

}
