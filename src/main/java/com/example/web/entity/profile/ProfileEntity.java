package com.example.web.entity.profile;

import com.example.web.entity.base.BaseLongEntity;
import com.example.web.enums.ProfileRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "profile")
@Getter
@Setter
public class ProfileEntity extends BaseLongEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(nullable = false, name = "login")
    private String userName;

    @Column(nullable = false, name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private ProfileRole role;
}
