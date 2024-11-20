package com.cardiosense.cardiosense.service;

import com.cardiosense.cardiosense.model.Rook.BodySummary;
import com.cardiosense.cardiosense.model.Rook.HealthScore;
import com.cardiosense.cardiosense.model.Rook.PhysicalActivity;
import com.cardiosense.cardiosense.model.Rook.SleepSummary;
import com.cardiosense.cardiosense.model.Rook.PhysicalSummary;
import com.cardiosense.cardiosense.repository.Rook.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class RookService {

    private final PhysicalActivityRepository physicalActivityRepository;
    private final PhysicalSummaryRepository physicalSummaryRepository;
    private final HealthScoreRepository healthScoreRepository;
    private final BodySummaryRepository bodySummaryRepository;
    private final SleepSummaryRepository sleepSummaryRepository;
    private final ObjectMapper objectMapper;

    public void saveRookEntity(Object request) {
        var map = objectMapper.convertValue(request, Map.class);
        String dataStructure = (String) map.get("data_structure");

        if (dataStructure == null) {
            throw new IllegalArgumentException("The 'data_structure' field is required");
        }

        switch (dataStructure) {
            case "physical_activity":
                PhysicalActivity physicalActivity = objectMapper.convertValue(request, PhysicalActivity.class);
                savePhysicalActivity(physicalActivity);
                break;
            case "physical_summary":
                PhysicalSummary physicalSummary = objectMapper.convertValue(request, PhysicalSummary.class);
                savePhysicalSummary(physicalSummary);
                break;
            case "body_summary":
                BodySummary bodySummary = objectMapper.convertValue(request, BodySummary.class);
                saveBodySummary(bodySummary);
                break;
            case "health_score":
                HealthScore healthScore = objectMapper.convertValue(request, HealthScore.class);
                saveHealthScore(healthScore);
                break;
            case "sleep_summary":
                SleepSummary sleepSummary = objectMapper.convertValue(request, SleepSummary.class);
                saveSleepSummary(sleepSummary);
                break;
            default:
                throw new IllegalArgumentException("Unsupported data_structure: " + dataStructure);
        }
    }

    private void savePhysicalActivity(PhysicalActivity physicalActivity) {
        physicalActivityRepository.save(physicalActivity);
    }

    private void savePhysicalSummary(PhysicalSummary physicalSummary) {
        physicalSummaryRepository.save(physicalSummary);
    }

    private void saveBodySummary(BodySummary bodySummary) {
        bodySummaryRepository.save(bodySummary);
    }

    private void saveHealthScore(HealthScore healthScore) {
        healthScoreRepository.save(healthScore);
    }

    private void saveSleepSummary(SleepSummary sleepSummary) {
        sleepSummaryRepository.save(sleepSummary);
    }
}
