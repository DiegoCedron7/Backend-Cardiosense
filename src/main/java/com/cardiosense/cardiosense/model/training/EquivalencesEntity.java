package com.cardiosense.cardiosense.model.training;

import com.cardiosense.cardiosense.model.UserEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "equivalences")
public class EquivalencesEntity {
    @Id
    private String id;

    @DBRef
    private UserEntity user;

    private Date createdAt;

    private Map<String, List<String>> exerciseEquivalences;

}
