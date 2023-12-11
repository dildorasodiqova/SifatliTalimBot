package com.example.web.config.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author 'Mukhtarov Sarvarbek' on 11.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */

@Getter
@Setter
public class SecurityUser extends User {

    public SecurityUser(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, true, true, true, true, authorities);
        this.username = username;
        this.id = id;
    }

    private String username;
    private Long id;

}
