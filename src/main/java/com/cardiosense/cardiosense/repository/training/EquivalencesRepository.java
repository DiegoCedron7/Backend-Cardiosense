package com.cardiosense.cardiosense.repository.training;

import com.cardiosense.cardiosense.model.training.EquivalencesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquivalencesRepository extends MongoRepository<EquivalencesEntity, String> {
}
