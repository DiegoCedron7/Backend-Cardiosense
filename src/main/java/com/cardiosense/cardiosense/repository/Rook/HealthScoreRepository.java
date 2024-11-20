package com.cardiosense.cardiosense.repository.Rook;

import com.cardiosense.cardiosense.model.Rook.HealthScore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthScoreRepository extends MongoRepository<HealthScore, String> {

    @Query(value = "{ 'userId': ?0 }", sort = "{ 'healthScoreData.metadata.datetimeString': -1 }")
    List<HealthScore> findByUserIdOrderByDatetimeString(String userId);
}



