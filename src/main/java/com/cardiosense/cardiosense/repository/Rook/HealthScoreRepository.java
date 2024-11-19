package com.cardiosense.cardiosense.repository.Rook;

import com.cardiosense.cardiosense.model.Rook.HealthScore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthScoreRepository extends MongoRepository<HealthScore, String> {
}
