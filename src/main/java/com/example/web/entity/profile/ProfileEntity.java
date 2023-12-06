package com.example.web.entity.profile;

import com.example.web.entity.base.BaseLongEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 'Mukhtarov Sarvarbek' on 06.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@Entity
@Table(name = "profile")
@Getter
@Setter
public class ProfileEntity extends BaseLongEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(nullable = false, name = "login")
    private String login;

    @Column(nullable = false, name = "password")
    private String password;
}
