package com.example.bot.repository;

import com.example.bot.entity.UsersEntity;
import com.example.bot.entity.group.GroupUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupUsersRepository extends JpaRepository<GroupUsersEntity, Long> {
    Integer countAllByGroupId(Long groupId);
    List<GroupUsersEntity> findAllByGroupId(Long groupId);

    @Modifying
    @Transactional
    @Query("DELETE FROM GroupUsersEntity ug WHERE ug.groupId = :groupId AND ug.userId = :userId")
    void deleteUserFromGroup(@Param("groupId") Long groupId, @Param("userId") Long userId);

}
