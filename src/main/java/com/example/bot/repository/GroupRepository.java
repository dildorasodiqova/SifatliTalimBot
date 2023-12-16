package com.example.bot.repository;

import com.example.bot.entity.group.GroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    @Query("from GroupEntity where lower(name) like concat('%',lower(?1) ,'%') and visible = true ")
    Page<GroupEntity> findAllByActiveTrue(String query, Pageable pageRequest);

    Boolean existsAllByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE GroupEntity  SET visible = false WHERE id = :groupId")
    void delete(@Param("groupId") Long groupId);

    @Query("""
            FROM GroupEntity where lower(name) = ?1 and visible = true
            """)
    Optional<GroupEntity> findByName(String text);
}