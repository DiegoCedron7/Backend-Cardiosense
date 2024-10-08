package com.cardiosense.cardiosense.model.training;

import com.cardiosense.cardiosense.model.UserEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Data
@Document(collection = "recommendations")
public class RecommendationsEntity {
    @Id
    private String id;

    @DBRef
    private UserEntity user;

    private List<String> recommendations;

    private Date createdAt;
}
