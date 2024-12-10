package com.cardiosense.cardiosense.model.User;

import com.cardiosense.cardiosense.model.MercadoPago.EOrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    private EOrderStatus status;
    private String preferenceId;
    private String paymentId;
    private String role;


}
