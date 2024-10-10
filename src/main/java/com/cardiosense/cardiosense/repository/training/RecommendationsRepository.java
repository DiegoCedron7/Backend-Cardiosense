package com.cardiosense.cardiosense.repository.training;

import com.cardiosense.cardiosense.model.training.RecommendationsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationsRepository extends MongoRepository<RecommendationsEntity, String> {

    // findByUser_Id
    RecommendationsEntity findByUser_Id(String userId);
}
