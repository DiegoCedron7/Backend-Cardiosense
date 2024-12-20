package com.cardiosense.cardiosense.model.User.Info;

import com.cardiosense.cardiosense.model.User.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "diet_info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Diet {

    @Id
    private String id;

    @DBRef
    private UserEntity user;

    private int numeroComidas;
    private String alergias;
    private List<String> alimentosPreferidos;
    private List<String> alimentosEvitar;
    private List<String> intolerancias;
    private String preferenciaAlimentaria;
    private String distribucionMacronutrientes;
    private int disponibilidadCocinar;
    private List<String> preferenciasCoccion;

}
