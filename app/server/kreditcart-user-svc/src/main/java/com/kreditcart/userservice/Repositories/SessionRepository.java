package com.kreditcart.userservice.Repositories;

import com.kreditcart.userservice.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    Optional<Session> findByTokenAndUser_Id(String toke, UUID userId);
}
