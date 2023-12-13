package com.example.bot.repository;

import com.example.bot.entity.group.GroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

   Page<GroupEntity> findAllByActiveTrue(PageRequest pageRequest);
   Boolean existsAllByName(String name);
   @Modifying
   @Transactional
   @Query("UPDATE GroupEntity g SET g.active = :false WHERE g.id = :groupId")
   void delete(@Param("groupId") Long groupId);

}