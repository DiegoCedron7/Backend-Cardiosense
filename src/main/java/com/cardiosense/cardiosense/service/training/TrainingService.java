package com.cardiosense.cardiosense.service.training;

import com.cardiosense.cardiosense.model.training.*;
import com.cardiosense.cardiosense.repository.training.EquivalencesRepository;
import com.cardiosense.cardiosense.repository.training.RecommendationsRepository;
import com.cardiosense.cardiosense.repository.training.TrainingDataRepository;
import com.cardiosense.cardiosense.repository.training.TrainingPlanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class TrainingService {
    private final EquivalencesRepository equivalencesRepository;
    private final RecommendationsRepository recommendationsRepository;
    private final TrainingDataRepository trainingDataRepository;
    private final TrainingPlanRepository trainingPlanRepository;
    private final RestTemplate restTemplate;
    private final UserInfoService userInfoService;

    public void generateTrainingPlan(String userId) {
        UserInfoEntity userInfo = userInfoService.getUserInfo(userId);

        // Make the request
        String url = "http://127.0.0.1:5000/generar_plan";
        TrainingPlanEntity trainingPlan = restTemplate.postForObject(url, userInfo, TrainingPlanEntity.class);

    }

    public void saveEquivalence(EquivalencesEntity equivalence) {
        equivalencesRepository.save(equivalence);
    }

    public void saveRecommendation(RecommendationsEntity recommendation) {
        recommendationsRepository.save(recommendation);
    }

    public void saveTrainingData(TrainingDataEntity trainingData) {
        trainingDataRepository.save(trainingData);
    }

    public void saveTrainingPlan(TrainingPlanEntity trainingPlan) {
        trainingPlanRepository.save(trainingPlan);
    }


}
