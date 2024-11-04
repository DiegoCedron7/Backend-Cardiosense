package com.cardiosense.cardiosense.model.Diet;

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

@Document(collection = "diet_plan")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DietPlan {

    @Id
    private String id;

    @DBRef
    private UserEntity user;


    private String datetime;
    private NutricionDiaria nutricionDiaria;
    private PesoPerfectoYEstadoActual pesoPerfectoYEstadoActual;
    private Map<String, Dia> planSemanal;
    private List<String> recomendaciones;
    private Map<String, List<String>> tablaEquivalencias;


    @Data
    public static class NutricionDiaria {
        private String aguaMl;
        private String calorias;
        private String carbohidratos;
        private String grasas;
        private String proteinas;
    }

    @Data
    public static class PesoPerfectoYEstadoActual {
        private String estadoActual;
        private int pesoActual;
        private int pesoPerfecto;
        private boolean progresionGradual;
        private String sexo;
    }

    @Data
    public static class Dia {
        private String desayuno;
        private String almuerzo;
        private String cena;
    }
}


