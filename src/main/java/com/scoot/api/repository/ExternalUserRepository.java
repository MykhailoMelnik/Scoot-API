package com.scoot.api.repository;

import com.scoot.api.entity.ExternalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExternalUserRepository extends JpaRepository<ExternalUser, Long> {
    Optional<ExternalUser> findByApiKey(String apiKey);
}

