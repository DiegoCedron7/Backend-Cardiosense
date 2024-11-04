package com.cardiosense.cardiosense.controller.Diet;

import com.cardiosense.cardiosense.model.Diet.DietPlan;
import com.cardiosense.cardiosense.model.Training.TrainingPlan;
import com.cardiosense.cardiosense.service.GeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/diet")
public class DietController {
    private final GeneratorService generatorService;

    @GetMapping("/generate/{id}")
    public void generateDiet(@PathVariable String id) {
        generatorService.generateDiet(id);
    }

    @GetMapping("/{id}")
    public DietPlan getDietPlan(@PathVariable String id) {
        return generatorService.getDietPlan(id);
    }
}
