package com.cardiosense.cardiosense.controller.Diet;

import com.cardiosense.cardiosense.model.Diet.DietPlan;
import com.cardiosense.cardiosense.model.Training.TrainingPlan;
import com.cardiosense.cardiosense.service.GeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/diet")
public class DietController {
    private final GeneratorService generatorService;

    @PostMapping("/generate/{id}")
    public ResponseEntity<String> generateDiet(@PathVariable String id) {
        String message = generatorService.generateDiet(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public DietPlan getDietPlan(@PathVariable String id) {
        return generatorService.getDietPlan(id);
    }
}
