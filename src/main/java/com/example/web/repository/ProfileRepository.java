package com.example.web.repository;

import com.example.web.entity.profile.ProfileEntity;
import com.example.web.enums.ProfileRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    Optional<ProfileEntity> findByUserNameAndVisibleIsTrue(String userName);

    @Query("from ProfileEntity where lower(fullName) like concat('%',lower(?1) ,'%') and visible = true ")
    Page<ProfileEntity> findAllByActiveTrue(String query, Pageable pageRequest);

    List<ProfileEntity> findAllByVisibleIsTrueAndRole(ProfileRole profileRole);
}