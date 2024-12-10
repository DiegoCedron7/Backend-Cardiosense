package com.cardiosense.cardiosense.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class KeepAliveService {

    private final RestTemplate restTemplate;

    //@Scheduled(fixedRate = 600000)
    public void sendKeepAlive() {
        String url = "https://backend-cardiosense.onrender.com/rook/ping";
        try {
            restTemplate.getForObject(url, String.class);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println("KeepAlive ping at: " + dtf.format(now));
        } catch (Exception e) {
            System.err.println("Error during KeepAlive ping: " + e.getMessage());
        }
    }
}
