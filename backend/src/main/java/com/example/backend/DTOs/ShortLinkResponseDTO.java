package com.example.backend.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShortLinkResponseDTO {

    private Long id;
    private String originalUrl;
    private String shortCode;
    private Long clicks = 0L ;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt ;
}
