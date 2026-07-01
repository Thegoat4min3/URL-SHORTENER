package com.example.backend.service;

import com.example.backend.DTOs.ShortLinkRequestDTO;
import com.example.backend.DTOs.ShortLinkResponseDTO;
import com.example.backend.entity.ShortLink;
import com.example.backend.entity.User;
import com.example.backend.repository.ShortLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.structured.GraylogExtendedLogFormatProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShortLinkService {

    private final ShortLinkRepository shortLinkRepository;

    public ShortLinkResponseDTO createShortLink(ShortLinkRequestDTO shortLinkRequestDTO) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String originalUrl = shortLinkRequestDTO.getOriginalUrl();

        String shortCode = UUID.randomUUID().toString().substring(0, 6);
        ShortLink shortLink = new ShortLink();
        shortLink.setOriginalUrl(originalUrl);
        shortLink.setShortCode(shortCode);
        shortLink.setClicks(0L);
        shortLink.setUser(currentUser);
        shortLink.setCreatedAt(LocalDateTime.now());
        shortLink.setExpiresAt(LocalDateTime.now().plusDays(shortLinkRequestDTO.getExpiresInDays()));

        ShortLink saved = shortLinkRepository.save(shortLink);

        ShortLinkResponseDTO response = new ShortLinkResponseDTO();
        response.setId(saved.getId());
        response.setOriginalUrl(saved.getOriginalUrl());
        response.setShortCode(saved.getShortCode());
        response.setClicks(saved.getClicks());
        response.setCreatedAt(saved.getCreatedAt());
        response.setExpiresAt(saved.getExpiresAt());

        return response;
    }



    public String redirect(String shortCode){

        Optional<ShortLink> shortLink = Optional.of(shortLinkRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Ce lien n'existe pas")));

        Long clicks = shortLink.get().getClicks() + 1L;
        shortLink.get().setClicks(clicks);
        shortLinkRepository.save(shortLink.get());

        return shortLink.get().getOriginalUrl();
    }

    public List<ShortLinkResponseDTO> getMyLinks(){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<ShortLink> links = shortLinkRepository.findByUser(user);

        return links.stream().map(link -> {
            ShortLinkResponseDTO response = new ShortLinkResponseDTO();
            response.setId(link.getId());
            response.setOriginalUrl(link.getOriginalUrl());
            response.setShortCode(link.getShortCode());
            response.setClicks(link.getClicks());
            response.setCreatedAt(link.getCreatedAt());
            response.setExpiresAt(link.getExpiresAt());
            return response;
        }).toList();
    }


    public void deleteLink(Long id){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ShortLink url = shortLinkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ce lien n'existe pas"));

        if(!url.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Vous n'êtes pas autorisé à supprimer ce lien");
        }

        shortLinkRepository.delete(url);
    }
}
