package com.cardiosense.cardiosense.service;

import com.cardiosense.cardiosense.model.Rook.PhysicalActivity;
import com.cardiosense.cardiosense.repository.Rook.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RookService {
    private final PhysicalActivityRepository physicalActivityRepository;
    private final PhysicalSummaryRepository physicalSummaryRepository;
    private final HealthScoreRepository healthScoreRepository;
    private final BodySummaryRepository bodySummaryRepository;
    private final SleepSummaryRepository sleepSummaryRepository;

    public void saveRookEvents(PhysicalActivity physicalActivity) {
        physicalActivityRepository.save(physicalActivity);
    }


    public List<PhysicalActivity> getRookEvents() {
        return physicalActivityRepository.findAll();
    }
}
