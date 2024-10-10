package com.cardiosense.cardiosense.controller.training;

import com.cardiosense.cardiosense.model.training.EquivalencesEntity;
import com.cardiosense.cardiosense.model.training.RecommendationsEntity;
import com.cardiosense.cardiosense.model.training.TrainingDataEntity;
import com.cardiosense.cardiosense.model.training.TrainingPlanEntity;
import com.cardiosense.cardiosense.service.training.TrainingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/training")
@AllArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;

    @GetMapping("/generate-plan/{userId}")
    public ResponseEntity<?> getTrainingPlanFromMicroservice(@PathVariable String userId) {
        trainingService.generateCompleteTrainingPlan(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/equivalences/{userId}")
    public ResponseEntity<EquivalencesEntity> getEquivalences(@PathVariable String userId) {
        return ResponseEntity.ok(trainingService.getEquivalences(userId));
    }

    @GetMapping("/recommendations/{userId}")
    public ResponseEntity<RecommendationsEntity> getRecommendations(@PathVariable String userId) {
        return ResponseEntity.ok(trainingService.getRecommendations(userId));
    }

    @GetMapping("/plan/{userId}")
    public ResponseEntity<TrainingPlanEntity> getTrainingPlan(@PathVariable String userId) {
        return ResponseEntity.ok(trainingService.getTrainingPlan(userId));
    }

    @GetMapping("/data/{userId}")
    public ResponseEntity<TrainingDataEntity> getTrainingData(@PathVariable String userId) {
        return ResponseEntity.ok(trainingService.getTrainingData(userId));
    }
}
