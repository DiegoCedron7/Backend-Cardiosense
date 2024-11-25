package com.cardiosense.cardiosense.repository.Rook;

import com.cardiosense.cardiosense.model.Rook.PhysicalActivity;
import com.cardiosense.cardiosense.model.Rook.PhysicalSummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalSummaryRepository extends MongoRepository<PhysicalSummary, String> {

    @Query(value = "{ 'userId': ?0, 'physicalHealth.summary.physicalSummary.metadata.datetime': { $regex: ?1 } }",
            sort = "{ 'physicalHealth.summary.physicalSummary.metadata.datetime': -1 }")
    List<PhysicalSummary> findByUserIdOrderByDatetimeString(String userId, String dateRegex);


}
