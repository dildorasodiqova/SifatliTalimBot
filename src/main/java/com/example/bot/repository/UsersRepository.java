package com.example.bot.repository;

import com.example.bot.entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    Boolean existsAllByPhone(String phone);

    Page<UsersEntity> findAllByIsActiveTrue(PageRequest pageRequest);

    @Query("update UsersEntity u set u.isActive = ?1")
    Boolean updateActiveALL(Boolean trueOrFalse);

    @Query("update UsersEntity u set u.isActive = ?1 where u.userId = ?2")
    Boolean updateActive(Boolean trueOrFalse, Long userId);

}