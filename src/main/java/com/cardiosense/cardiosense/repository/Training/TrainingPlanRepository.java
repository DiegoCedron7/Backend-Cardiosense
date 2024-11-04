package com.cardiosense.cardiosense.repository.Training;

import com.cardiosense.cardiosense.model.Training.TrainingPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainingPlanRepository extends MongoRepository<TrainingPlan, String> {
}
