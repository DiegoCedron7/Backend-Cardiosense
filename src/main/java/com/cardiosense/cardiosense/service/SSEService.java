package com.cardiosense.cardiosense.service;

import com.cardiosense.cardiosense.model.SSEEntity;
import com.cardiosense.cardiosense.repository.SSERepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class SSEService {
    private final SSERepository sseRepository;

    public void saveMessage(SSEEntity message) {
        sseRepository.save(message);
    }

    public void deleteAllMessages() {
        sseRepository.deleteAll();
    }
}
