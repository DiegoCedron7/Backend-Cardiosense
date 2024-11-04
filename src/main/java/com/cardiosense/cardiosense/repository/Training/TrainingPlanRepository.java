package com.cardiosense.cardiosense.repository.Training;

import com.cardiosense.cardiosense.model.Training.TrainingPlan;
import com.cardiosense.cardiosense.model.User.Info.Training;
import com.cardiosense.cardiosense.model.User.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainingPlanRepository extends MongoRepository<TrainingPlan, String> {
    TrainingPlan findByUser(UserEntity user);
}
