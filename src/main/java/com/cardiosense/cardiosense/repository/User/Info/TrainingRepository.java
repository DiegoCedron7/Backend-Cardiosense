package com.cardiosense.cardiosense.repository.User.Info;

import com.cardiosense.cardiosense.model.User.Info.Training;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.cardiosense.cardiosense.model.User.UserEntity;

public interface TrainingRepository extends MongoRepository<Training, String> {

    Training findByUser(UserEntity user);
}
