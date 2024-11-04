package com.cardiosense.cardiosense.DTO.User.UserInfoDTO;

import lombok.Data;

@Data
public class TrainingDTO {
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
