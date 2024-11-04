package com.cardiosense.cardiosense.controller.Diet;

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

    @GetMapping("/{userId}")
    public String generateDiet(@PathVariable String userId) {
        generatorService.generateDiet(userId);
        return "Diet generated";
    }
}
