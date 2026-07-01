package com.example.backend.repository;

import com.example.backend.DTOs.ShortLinkResponseDTO;
import com.example.backend.entity.ShortLink;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {
    Optional<ShortLink> findByShortCode(String shortCode);
    List<ShortLink> findByUser(User user);
    List<ShortLink> findByExpiresAtBefore(LocalDateTime date);
}