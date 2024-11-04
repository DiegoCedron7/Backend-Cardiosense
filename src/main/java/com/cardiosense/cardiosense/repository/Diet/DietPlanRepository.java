package com.cardiosense.cardiosense.repository.Diet;

import com.cardiosense.cardiosense.model.User.Info.Diet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DietPlanRepository extends MongoRepository<Diet, String> {
}
