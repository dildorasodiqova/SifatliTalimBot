package com.example.bot.repository;

import com.example.bot.entity.group.GroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

   Page<GroupEntity> findAllByActiveTrue(PageRequest pageRequest);

}