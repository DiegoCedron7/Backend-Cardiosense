package com.cardiosense.cardiosense.service;

import com.cardiosense.cardiosense.model.RookEvents;
import com.cardiosense.cardiosense.repository.RookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RookService {
    private final RookRepository rookRepository;

    public void saveRookEvents(RookEvents rookEvents) {

        rookRepository.save(rookEvents);
    }

    public List<RookEvents> getRookEvents() {
        return rookRepository.findAll();
    }
}
