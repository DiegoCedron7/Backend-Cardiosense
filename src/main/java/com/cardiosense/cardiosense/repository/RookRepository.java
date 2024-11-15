package com.cardiosense.cardiosense.repository;

import com.cardiosense.cardiosense.model.Rook.PhysicalActivity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RookRepository extends MongoRepository<PhysicalActivity, String> {
}
