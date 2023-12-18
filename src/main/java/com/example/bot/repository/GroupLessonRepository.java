package com.example.bot.repository;

import com.example.bot.entity.group.GroupLessonsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupLessonRepository extends JpaRepository<GroupLessonsEntity, Long> {

    Page<GroupLessonsEntity> findAllByGroupId(Long groupId, Pageable pageable);
    List<GroupLessonsEntity> findAllByGroupIdAndOrderNumber(Long groupId, Integer orderNumber);
}
