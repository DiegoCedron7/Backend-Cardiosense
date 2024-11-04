package com.cardiosense.cardiosense.model.Diet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DietRequest {

    // Todas estas las obtendré de la Información de Usuario
    private int pesoInicial;
    private String pesoActualizado;
    private int altura;
    private int edad;
    private String sexo;
    private String tipoCuerpo;
    private String residencia;

    private String nivelActividad;
    private int porcentajeGrasa;
    private String condicionesMedicas;

    private int numeroComidas;
    private String alergias;
    private String objetivo;
    private List<String> alimentosPreferidos;
    private List<String> alimentosEvitar;
    private List<String> intolerancias;
    private String preferenciaAlimentaria;
    private String distribucionMacronutrientes;
    private List<String> suplementos;
    private int disponibilidadCocinar;
    private List<String> preferenciasCoccion;
}
