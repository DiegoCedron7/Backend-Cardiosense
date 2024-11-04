package com.cardiosense.cardiosense.controller.Training;

import com.cardiosense.cardiosense.model.Training.TrainingPlan;
import com.cardiosense.cardiosense.service.GeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/training")
@AllArgsConstructor
public class TrainingController {
    private final GeneratorService generatorService;

    @PostMapping("/generate/{id}")
    public ResponseEntity<String> generateTraining(@PathVariable String id) {
        String message = generatorService.generateTraining(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public TrainingPlan getTrainingPlan(@PathVariable String id) {
        return generatorService.getTrainingPlan(id);
    }
}
