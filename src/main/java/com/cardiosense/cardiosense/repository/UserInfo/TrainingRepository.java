package com.cardiosense.cardiosense.repository.UserInfo;

import com.cardiosense.cardiosense.model.UserInfo.Training;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.cardiosense.cardiosense.model.UserEntity;

public interface TrainingRepository extends MongoRepository<Training, String> {

    Training findByUser(UserEntity user);
}
