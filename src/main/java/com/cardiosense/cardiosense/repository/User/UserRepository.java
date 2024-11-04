package com.cardiosense.cardiosense.repository.User;


import com.cardiosense.cardiosense.model.User.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

}
