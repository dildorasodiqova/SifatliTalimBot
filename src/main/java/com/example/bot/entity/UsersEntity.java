package com.example.bot.entity;

import com.example.bot.entity.group.GroupEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UsersEntity {
    @Id
    private Long userId;
    private String name;
    private String surname;

    private String username; // telegram username
    private String firstName; // telegram firsName
    private String lastName; // telegram lastName

    private Boolean isActive = true;
    private String phone;

    private LocalDate paidUntil; // qachongacha tolov qilgani

    private LocalDateTime createdDate = LocalDateTime.now();

    private Boolean isDeleted = false;

    @Column(name = "group_id")
    private Long groupId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private GroupEntity group;

    public UsersEntity(Long userId, String username, String firstName, String lastName, Boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
    }
}
