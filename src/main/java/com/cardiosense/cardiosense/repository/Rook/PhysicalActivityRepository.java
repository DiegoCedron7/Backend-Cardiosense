package com.cardiosense.cardiosense.repository.Rook;

import com.cardiosense.cardiosense.model.Rook.PhysicalActivity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalActivityRepository extends MongoRepository<PhysicalActivity, String> {

    @Query(value = "{ 'userId': ?0 , 'physicalHealth.events.activityEvent.metadata.datetimeString': { $regex: ?1 } }",
            sort = "{ 'physicalHealth.events.activityEvent.metadata.datetimeString': -1 }")
    List<PhysicalActivity> findByUserIdOrderByDatetimeString(String userId, String date);
}



