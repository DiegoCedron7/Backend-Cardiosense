package com.cardiosense.cardiosense.repository.training;

import com.cardiosense.cardiosense.model.training.UserInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfoEntity, String> {
    Optional<UserInfoEntity> findByUser_Id(String userId);
}
