package com.cardiosense.cardiosense.model.User;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
@Builder
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String lastname;
    private String documentType;
    private Number phone;
    private String address;
    private String email;
    private String password;
    private String image = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png";
    private String authStrategy = "local";
    private String role = "user";
    private String createdAt = String.valueOf(System.currentTimeMillis());
    private boolean firstLogin = true;

    // Datos generales del usuario.
    private String objetivo;
    private List<String> suplementos;
    private String estiloVida;
    private Integer pesoInicial;
    private Integer pesoActualizado;
    private Integer altura;
    private Integer edad;
    private String sexo;


}


