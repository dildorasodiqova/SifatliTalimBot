package com.example.bot.repository;

import com.example.bot.entity.UsersEntity;
import com.example.bot.entity.group.GroupUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupUsersRepository extends JpaRepository<GroupUsersEntity, Long> {
    Integer countAllByGroupId(Long groupId);
    List<GroupUsersEntity> findAllByGroupId(Long groupId);

}
