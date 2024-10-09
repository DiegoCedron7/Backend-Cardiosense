package com.cardiosense.cardiosense.repository.training;

import com.cardiosense.cardiosense.model.training.TrainingDataEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingDataRepository extends MongoRepository<TrainingDataEntity, String> {
}
