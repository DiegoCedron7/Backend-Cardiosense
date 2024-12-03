package com.cardiosense.cardiosense.repository.User;


import com.cardiosense.cardiosense.model.User.UserEntity;
//import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);

    // Count by subscription
    long countBySubscription(boolean subscription);
}
