package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="ShortLinks")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ShortLink {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String originalUrl;

    @Column(nullable=false , unique=true , length=10)
    private String shortCode;

    @Column(nullable=false)
    private Long clicks = 0L ;

    @Column(nullable=false , updatable=false)
    private LocalDateTime createdAt;

    @Column(nullable=false , updatable=false)
    private LocalDateTime expiresAt ;


    // LIEN AVEC ENTITE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
