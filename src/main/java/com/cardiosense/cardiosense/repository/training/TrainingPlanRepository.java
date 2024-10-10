package com.cardiosense.cardiosense.repository.training;

import com.cardiosense.cardiosense.model.training.TrainingPlanEntity;
import org.springframework.data.repository.CrudRepository;

public interface TrainingPlanRepository extends CrudRepository<TrainingPlanEntity, String> {

    // findByUser_Id
    TrainingPlanEntity findByUser_Id(String userId);
}
