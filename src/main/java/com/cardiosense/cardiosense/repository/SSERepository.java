package com.cardiosense.cardiosense.repository;

import com.cardiosense.cardiosense.model.SSEEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SSERepository extends MongoRepository<SSEEntity, String>{
}
