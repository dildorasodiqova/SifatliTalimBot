package com.example.bot.repository;

import com.example.bot.entity.group.SendGroupLessonHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SendGroupLessonHistoryRepository extends JpaRepository<SendGroupLessonHistoryEntity, String> {

    @Query("from SendGroupLessonHistoryEntity where date(createdDate) <= ?1 and visible is true")
    List<SendGroupLessonHistoryEntity> findAllByCreatedDateBefore(LocalDate timeNow);
    @Modifying
    @Transactional
    @Query("update SendGroupLessonHistoryEntity set visible = false where id in (?1)")
    int updateVisibleIsFalse(List<String> ids);
}