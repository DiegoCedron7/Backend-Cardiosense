package com.cardiosense.cardiosense.model.User.Info;

import com.cardiosense.cardiosense.model.User.UserEntity;
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

    private String ejercicioDiario;
    private int cantidadEjerciciosPorDia;
    private int frecuenciaEntrenamientoSemanal;
    private String tiempoDisponiblePorDia;
    private String nivelExperiencia;
    private String preferenciaEjercicios;
    private String equipamientoDisponible;
    private String areasMejorar;
    private String lesionesLimitaciones;
    private String tipoCuerpo;
    private String preferenciaIndoorOutdoor;
    private String condicionesMedicas;
    private String preferenciaEstiloEntrenamiento;
    private String horasSueno;
}
