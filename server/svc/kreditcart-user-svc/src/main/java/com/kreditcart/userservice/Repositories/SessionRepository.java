package com.kreditcart.userservice.Repositories;

import com.kreditcart.userservice.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
