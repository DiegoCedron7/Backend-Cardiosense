package com.cardiosense.cardiosense.repository.Rook;

import com.cardiosense.cardiosense.model.Rook.SleepSummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SleepSummaryRepository extends MongoRepository<SleepSummary, String> {
    @Query(value = "{ 'userId': ?0, 'sleepHealth.summary.sleepSummary.metadata.datetimeString': { $regex: ?1 } }",
            sort = "{ 'sleepHealth.summary.sleepSummary.metadata.datetimeString': -1 }")
    List<SleepSummary> findByUserIdOrderByDatetimeString(String userId, String dateRegex);
}
