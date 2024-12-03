package com.cardiosense.cardiosense.model.User;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String email;
    private String image;
    private boolean firstLogin;
    private String createdAt;

    @JsonProperty("user_id")
    private String userId;

    private String objetivo;
    private List<String> suplementos;
    private String estiloVida;
    private Integer pesoInicial;
    private Integer pesoActualizado;
    private Integer altura;
    private Integer edad;
    private String sexo;

    private boolean subscription;


}
