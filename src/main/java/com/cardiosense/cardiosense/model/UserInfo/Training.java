package com.cardiosense.cardiosense.model.UserInfo;

import com.cardiosense.cardiosense.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "training_info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Training {

    @Id
    private String id;

    @DBRef
    private UserEntity user;

    // Todos los campos relacionados a entrenamiento.

    private int kilometrosRecorridos;
}
