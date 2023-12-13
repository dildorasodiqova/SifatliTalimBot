package com.example.bot.repository;

import com.example.bot.entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    Boolean existsAllByPhone(String phone);

    @Query("select COUNT(u) from UsersEntity u where YEAR(u.createdDate) = :year AND MONTH(u.createdDate) = :month AND DAY(u.createdDate) = :day")
    Integer countAllByCreatedDate(@Param("year") int year, @Param("month") int month, @Param("day") int day);


    @Modifying
    @Transactional
    @Query("UPDATE UsersEntity u SET u.paidUntil = :newPaidDate WHERE u.userId = :userId")
    int updatePaidDate(@Param("newPaidDate") LocalDate newPaidDate, @Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE UsersEntity u SET u.isActive = :isActive WHERE u.userId = :userId")
    void updateUserActivityById(@Param("userId") Long userId, @Param("isActive") boolean isActive);

    @Modifying
    @Transactional
    @Query("UPDATE UsersEntity u SET u.isActive = :isActive")
    void updateAllUsersActivity(@Param("isActive") boolean isActive);

    Page<UsersEntity> findAllByIsActiveTrue(PageRequest pageRequest);

    @Transactional
    @Query("SELECT u FROM UsersEntity u WHERE " +
            "LOWER(u.name) LIKE LOWER(CONCAT('%', :word, '%')) OR " +
            "LOWER(u.surname) LIKE LOWER(CONCAT('%', :word, '%')) OR " +
            "LOWER(u.phone) LIKE LOWER(CONCAT('%', :word, '%'))")
    Page<UsersEntity> searchUsers(@Param("word") String word, Pageable pageable);


    @Query("SELECT u from UsersEntity u ORDER BY u.paidUntil ASC")
    Page<UsersEntity> findAllByPaidUntil(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update UsersEntity u set u.isActive = ?1")
    int updateActiveALL(Boolean trueOrFalse);

    @Transactional
    @Modifying
    @Query("update UsersEntity u set u.isActive = :isActive where u.userId = :userId")
    int updateActive(@Param("isActive") Boolean trueOrFalse,@Param("userId") Long userId);

}