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
import java.util.List;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    Boolean existsAllByPhone(String phone);

    @Query("""
            select COUNT(u) from UsersEntity u
            where YEAR(u.createdDate) = :year
            AND MONTH(u.createdDate) = :month
            AND DAY(u.createdDate) = :day and u.isDeleted = false
            """)
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

    Page<UsersEntity> findAllByIsActiveTrue(Pageable pageRequest);
    List<UsersEntity> findAllByIsActiveTrue();

    @Transactional
    @Query("""
            SELECT u FROM UsersEntity u WHERE u.isDeleted = FALSE AND
            LOWER(u.name) LIKE LOWER(CONCAT('%', :word, '%')) OR
            LOWER(u.surname) LIKE LOWER(CONCAT('%', :word, '%')) OR
            LOWER(u.phone) LIKE LOWER(CONCAT('%', :word, '%'))
            """
    )
    Page<UsersEntity> searchUsers(@Param("word") String word, Pageable pageable);


    @Query("SELECT u from UsersEntity u where u.paidUntil is not null ORDER BY u.paidUntil ASC")
    Page<UsersEntity> findAllByPaidUntil(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update UsersEntity u set u.isActive = ?1")
    int updateActiveALL(Boolean trueOrFalse);

    @Transactional
    @Modifying
    @Query("update UsersEntity u set u.isActive = :isActive where u.userId = :userId")
    int updateActive(@Param("isActive") Boolean trueOrFalse, @Param("userId") Long userId);

    boolean existsByUserIdAndIsActiveIsTrueAndIsDeletedIsFalse(Long userId);

    @Transactional
    @Modifying
    @Query("update UsersEntity u set u.name = ?2 where u.userId = ?1 ")
    void updateName(Long id, String text);

    @Transactional
    @Modifying
    @Query("update UsersEntity u set u.surname = ?2 where u.userId = ?1 ")
    void updateSurname(Long id, String text);

    @Transactional
    @Modifying
    @Query("update UsersEntity u set u.phone = ?2 where u.userId = ?1 ")
    void updatePhone(Long id, String text);

    @Query("UPDATE UsersEntity u set u.groupId = ?2 where u.userId=?1")
    @Modifying
    @Transactional
    void updateGroup(Long id, Long groupId);
}