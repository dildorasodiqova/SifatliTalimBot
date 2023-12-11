package com.example.web.repository;

import com.example.web.entity.profile.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    Optional<ProfileEntity> findByUserNameAndVisibleIsTrue(String userName);
}