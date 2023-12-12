package com.example.bot.repository;

import com.example.bot.entity.UsersEntity;
import com.example.web.dto.responseDto.UserStatisticsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    Boolean existsAllByPhone(String phone);

    @Query("select COUNT(u) from UsersEntity u where YEAR(u.createdDate) = :year AND MONTH(u.createdDate) = :month AND DAY(u.createdDate) = :day")
    Integer countAllByCreatedDate(@Param("year") int year, @Param("month") int month, @Param("day") int day);


    Page<UsersEntity> findAllByIsActiveTrue(PageRequest pageRequest);


    @Query("SELECT u from UsersEntity u ORDER BY u.paidUntil ASC")
    Page<UsersEntity> findAllByPaidUntil(Pageable pageable);

    @Query("update UsersEntity u set u.isActive = ?1")
    Boolean updateActiveALL(Boolean trueOrFalse);

    @Query("update UsersEntity u set u.isActive = ?1 where u.userId = ?2")
    Boolean updateActive(Boolean trueOrFalse, Long userId);

}