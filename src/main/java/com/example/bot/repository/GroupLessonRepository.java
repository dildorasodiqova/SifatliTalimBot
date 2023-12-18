package com.example.bot.repository;

import com.example.bot.entity.group.GroupLessonsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GroupLessonRepository extends JpaRepository<GroupLessonsEntity, Long> {

    Page<GroupLessonsEntity> findAllByGroupIdAndVisibleIsTrue(Long groupId, Pageable pageable);
    List<GroupLessonsEntity> findAllByGroupIdAndOrderNumberAndVisibleIsTrue(Long groupId, Integer orderNumber);

    @Modifying
    @Transactional
    @Query("update GroupLessonsEntity set visible = false where id = ?1")
    void updateVisibleIsFalse(String lessonId);
}
