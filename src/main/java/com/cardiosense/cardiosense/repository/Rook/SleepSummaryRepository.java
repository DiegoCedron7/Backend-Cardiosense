package com.cardiosense.cardiosense.repository.Rook;

import com.cardiosense.cardiosense.model.Rook.SleepSummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SleepSummaryRepository extends MongoRepository<SleepSummary, String> {
}
