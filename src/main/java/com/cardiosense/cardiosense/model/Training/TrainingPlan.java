package com.cardiosense.cardiosense.model.Training;

import com.cardiosense.cardiosense.model.User.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "training_plan")
public class TrainingPlan {


    @Id
    private String id;

    @DBRef
    private UserEntity user;

    private String datetime;
    private PesoPerfectoYEstadoActual pesoPerfectoYEstadoActual;
    private Map<String, DiaEntrenamiento> planEntrenamiento;
    private List<String> recomendaciones;
    private Map<String, List<String>> tablaEquivalencias;
    private String tipoEntrenamiento;

    @Data
    public static class PesoPerfectoYEstadoActual {
        private String estadoActual;
        private int pesoActual;
        private int pesoPerfecto;
        private boolean progresionGradual;
        private String sexo;
    }

    @Data
    public static class DiaEntrenamiento {
        private List<Ejercicio> calentamiento;
        private List<Ejercicio> entrenamiento;
        private List<String> musculos;
    }

    @Data
    public static class Ejercicio {
        private String nombre;
        private String duracion;
        private Integer repeticiones;
        private Integer series;
    }
}


