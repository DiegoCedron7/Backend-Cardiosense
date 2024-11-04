package com.cardiosense.cardiosense.DTO.User;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
    private String objetivo;
    private List<String> suplementos;
    private String estiloVida;
    private int pesoInicial;
    private int pesoActualizado;
    private int altura;
    private int edad;
    private String sexo;
}
