package com.example.backend.controller;

import com.example.backend.DTOs.ShortLinkRequestDTO;
import com.example.backend.DTOs.ShortLinkResponseDTO;
import com.example.backend.entity.ShortLink;
import com.example.backend.service.ShortLinkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shortlink")
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    @PostMapping("/createlink")
    public ResponseEntity<ShortLinkResponseDTO> createShortLink(@Valid @RequestBody ShortLinkRequestDTO shortLinkRequestDTO){
        ShortLinkResponseDTO  shortLinkResponseDTO = shortLinkService.createShortLink(shortLinkRequestDTO);
        return ResponseEntity.ok(shortLinkResponseDTO);
    }


    @GetMapping()
    public ResponseEntity<List<ShortLinkResponseDTO>> getAllShortLinks() {
        List<ShortLinkResponseDTO> links = shortLinkService.getMyLinks();
        return  ResponseEntity.ok(links);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteShortLink(@PathVariable Long id) {
        shortLinkService.deleteLink(id);
        return ResponseEntity.ok().build();
    }
}
