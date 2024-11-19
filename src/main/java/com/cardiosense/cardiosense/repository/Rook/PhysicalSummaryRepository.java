package com.cardiosense.cardiosense.repository.Rook;

import com.cardiosense.cardiosense.model.Rook.PhysicalSummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalSummaryRepository extends MongoRepository<PhysicalSummary, String> {
}
