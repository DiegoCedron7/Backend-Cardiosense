package com.cardiosense.cardiosense.repository.User.Info;

import com.cardiosense.cardiosense.model.User.Info.Diet;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.cardiosense.cardiosense.model.User.UserEntity;

public interface DietRepository extends MongoRepository<Diet, String> {

    Diet findByUser(UserEntity user);
}
