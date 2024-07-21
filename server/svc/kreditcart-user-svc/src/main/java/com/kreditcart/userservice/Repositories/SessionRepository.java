package com.kreditcart.userservice.Repositories;

import com.kreditcart.userservice.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByTokenAndUser_Id(String toke, Long userId);
}
