package com.example.backend.scheduled;

import com.example.backend.entity.ShortLink;
import com.example.backend.repository.ShortLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final ShortLinkRepository shortLinkRepository;

    @Scheduled(fixedRate = 3600000)
    public void deleteafterday(){
        List<ShortLink> shortLinks = shortLinkRepository.findByExpiresAtBefore(LocalDateTime.now());
        shortLinkRepository.deleteAll(shortLinks);
    }

}
