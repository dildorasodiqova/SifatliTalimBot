package com.example.bot.entity.group;

import com.example.bot.entity.UsersEntity;
import com.example.web.entity.base.BaseStringEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupUsersEntity extends BaseStringEntity {
    @Column(name = "group_id")
    private Long groupId;

    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private GroupEntity group;

    @Column(name = "user_id")
    private Long userId;

    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UsersEntity user;

    public GroupUsersEntity(Long groupId, Long userId) {
        this.groupId = groupId;
        this.userId = userId;
    }
}
