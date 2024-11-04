package com.cardiosense.cardiosense.controller.Training;

import com.cardiosense.cardiosense.model.Training.TrainingPlan;
import com.cardiosense.cardiosense.service.GeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/training")
@AllArgsConstructor
public class TrainingController {
    private final GeneratorService generatorService;

    @GetMapping("/generate/{id}")
    public void generateTraining(@PathVariable String id) {
        generatorService.generateTraining(id);
    }

    @GetMapping("/{id}")
    public TrainingPlan getTrainingPlan(@PathVariable String id) {

        return generatorService.getTrainingPlan(id);
    }
}
