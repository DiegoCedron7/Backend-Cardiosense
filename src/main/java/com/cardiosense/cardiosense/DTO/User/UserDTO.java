package com.cardiosense.cardiosense.DTO.User;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
    private String _id;
    private String objetivo;
    private List<String> suplementos;
    private String estiloVida;
    private Integer pesoInicial;
    private Integer pesoActualizado;
    private Integer altura;
    private Integer edad;
    private String sexo;

    // Datos generales del usuario.
    private String name;
    private String email;
    private String image;
    private boolean firstLogin;
    private String createdAt;
}
