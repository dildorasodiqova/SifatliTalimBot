package com.example.web.entity.profile;

import com.example.web.entity.base.BaseLongEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
}
