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
@Document(collection = "training_plans")
public class TrainingPlanEntity {

    @Id
    private String id;
    private Map<String, DayPlan> planEntrenamiento;
    private Date createdAt;

    @DBRef
    private UserEntity user;

    @Data
    public static class DayPlan {
        private List<Ejercicio> calentamiento;
        private List<Ejercicio> entrenamiento;
        private List<String> musculos;
    }

    @Data
    public static class Ejercicio {
        private String nombre;
        private String duracion;
        private int repeticiones;
        private int series;
    }
}
