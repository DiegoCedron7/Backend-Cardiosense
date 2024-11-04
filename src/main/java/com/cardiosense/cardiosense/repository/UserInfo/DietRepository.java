package com.cardiosense.cardiosense.repository.UserInfo;

import com.cardiosense.cardiosense.model.UserInfo.Diet;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.cardiosense.cardiosense.model.UserEntity;

public interface DietRepository extends MongoRepository<Diet, String> {

    Diet findByUser(UserEntity user);
}
