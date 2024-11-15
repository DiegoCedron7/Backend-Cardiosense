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

    // Datos generales del usuario.
    private String sexo;
    private String name;
    private String lastname;
    private String documentType;
    private Integer documentNumber;
    private Number phone;
    private String address;
    private String email;
}
