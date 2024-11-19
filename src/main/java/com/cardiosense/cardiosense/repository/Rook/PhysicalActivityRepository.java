package com.cardiosense.cardiosense.repository.Rook;

import com.cardiosense.cardiosense.model.Rook.PhysicalActivity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalActivityRepository extends MongoRepository<PhysicalActivity, String> {
}
