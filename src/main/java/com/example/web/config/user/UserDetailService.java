package com.example.web.config.user;

import com.example.web.entity.profile.ProfileEntity;
import com.example.web.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author 'Mukhtarov Sarvarbek' on 11.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@RequiredArgsConstructor
@Slf4j
public class UserDetailService implements UserDetailsService {
    private final ProfileRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<ProfileEntity> userByUsername = userRepository.findByUserNameAndVisibleIsTrue(username);

        if (userByUsername.isEmpty()) {
            log.error("Could not find user with that username: {}", username);
            throw new UsernameNotFoundException("Invalid credentials!");
        }
        ProfileEntity user = userByUsername.get();

        Set<GrantedAuthority> grantedAuthorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new SecurityUser(user.getId(), user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}
