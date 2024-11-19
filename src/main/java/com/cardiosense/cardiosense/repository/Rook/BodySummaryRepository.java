package com.cardiosense.cardiosense.repository.Rook;

import com.cardiosense.cardiosense.model.Rook.BodySummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodySummaryRepository extends MongoRepository<BodySummary, String> {
}
