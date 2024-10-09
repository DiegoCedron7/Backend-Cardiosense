package com.cardiosense.cardiosense.model.training;

import com.cardiosense.cardiosense.model.UserEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "training_user_info")
@Data
public class UserInfoEntity {

    @Id
    private String id;

    @DBRef
    private UserEntity user;

    private String objetivo;

    private String ejercicio_diario;

    private int cantidad_ejercicios_por_dia;

    private int frecuencia_entrenamiento_semanal;

    private String tiempo_disponible_por_dia;

    private String nivel_experiencia;

    private String preferencia_ejercicios;

    private String equipamiento_disponible;

    private String areas_mejorar;

    private String lesiones_limitaciones;

    private String tipo_cuerpo;

    private String preferencia_indoor_outdoor;

    private String condiciones_medicas;

    private String suplementos;

    private String estilo_vida;

    private String preferencia_estilo_entrenamiento;

    private String horas_sueno;

    private int peso_inicial;

    private int peso_actualizado;

    private int altura;

    private int edad;

    private String sexo;

    private Date createdAt;
    private Date updatedAt;
}
