package com.cardiosense.cardiosense.repository.Diet;

import com.cardiosense.cardiosense.model.Diet.DietPlan;
import com.cardiosense.cardiosense.model.User.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DietPlanRepository extends MongoRepository<DietPlan, String> {
    DietPlan findByUser(UserEntity user);
}
