package com.cardiosense.cardiosense.DTO.User.UserInfoDTO;

import lombok.Data;

import java.util.List;

@Data
public class DietDTO {
    private int numeroComidas;
    private String alergias;
    private List<String> alimentosPreferidos;
    private List<String> alimentosEvitar;
    private List<String> intolerancias;
    private String preferenciaAlimentaria;
    private String distribucionMacronutrientes;
    private int disponibilidadCocinar;
    private List<String> preferenciasCoccion;
    private String residencia;
    private String nivelActividad;
    private int porcentajeGrasa;
    private List<String> condicionesMedicas;
}
