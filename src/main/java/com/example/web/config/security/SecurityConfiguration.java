package com.example.web.config.security;

import com.example.web.config.user.UserDetailService;
import com.example.web.repository.ProfileRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // security not finished
        http.authorizeHttpRequests(a -> a
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/owl_carousel/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin((form) -> form
                        .loginPage("/auth/go/login")
                        .loginProcessingUrl("/auth/login")
                        .failureUrl("/auth/go/login?error=true")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                );
        http
                .logout((logout) -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/go/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService myUserDetailsService(ProfileRepository userRepository) {
        return new UserDetailService(userRepository);
    }
}
