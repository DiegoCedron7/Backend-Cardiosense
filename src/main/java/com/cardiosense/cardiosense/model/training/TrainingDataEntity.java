package com.cardiosense.cardiosense.model.training;

import com.cardiosense.cardiosense.model.UserEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "training_data")
public class TrainingDataEntity {
    @Id
    private String id;

    @DBRef
    private UserEntity user;

    private Date createdAt;
    private int version;
    private String estadoActual;
    private double pesoActual;
    private double pesoPerfecto;
    private boolean progresionGradual;
    private String sexo;
    private String tipoEntrenamiento;
}
