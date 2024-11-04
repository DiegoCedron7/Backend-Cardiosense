package com.cardiosense.cardiosense.model.Training;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRequest {

    String objetivo;
    String ejercicioDiario;
    int cantidadEjerciciosPorDia;
    int frecuenciaEntrenamientoSemanal;
    String tiempoDisponiblePorDia;
    String nivelExperiencia;
    String preferenciaEjercicios;
    String equipamientoDisponible;
    String areasMejorar;
    String lesionesLimitaciones;
    String tipoCuerpo;
    String preferenciaIndoorOutdoor;
    String condicionesMedicas;
    String suplementos;
    String estiloVida;
    String preferenciaEstiloEntrenamiento;
    String horasSueno;
    int pesoInicial;
    int pesoActualizado;
    int altura;
    int edad;
    String sexo;
}
