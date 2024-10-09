package com.cardiosense.cardiosense.controller.training;

import com.cardiosense.cardiosense.model.training.EquivalencesEntity;
import com.cardiosense.cardiosense.model.training.RecommendationsEntity;
import com.cardiosense.cardiosense.model.training.TrainingDataEntity;
import com.cardiosense.cardiosense.model.training.TrainingPlanEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/training")
public class TrainingController {


    @GetMapping("/generate-plan/{userId}")
    public void getTrainingPlanFromMicroservice(@PathVariable String userId) {


    }

    @GetMapping("/equivalences/{userId}")
    public ResponseEntity<EquivalencesEntity> getEquivalences(@PathVariable String userId) {
        // En este método se debe retornar las equivalencias de ejercicios para el usuario
        return null;
    }

    @GetMapping("/recommendations/{userId}")
    public ResponseEntity<RecommendationsEntity> getRecommendations(@PathVariable String userId) {
        // En este método se debe retornar las recomendaciones de ejercicios para el usuario
        return null;
    }

    @GetMapping("/plan/{userId}")
    public ResponseEntity<TrainingPlanEntity> getTrainingPlan(@PathVariable String userId) {
        // En este método se debe retornar el plan de entrenamiento para el usuario
        return null;
    }

    @GetMapping("/data/{userId}")
    public ResponseEntity<TrainingDataEntity> getTrainingData(@PathVariable String userId) {
        // En este método se debe retornar los datos de entrenamiento para el usuario
        return null;
    }
}
