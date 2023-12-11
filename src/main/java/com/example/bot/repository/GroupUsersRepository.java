package com.example.bot.repository;

import com.example.bot.entity.group.GroupUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupUsersRepository extends JpaRepository<GroupUsersEntity, Long> {
    Integer countAllByGroupId(Long groupId);

}
