package com.cardiosense.cardiosense.service.training;

import com.cardiosense.cardiosense.model.training.UserInfoEntity;
import com.cardiosense.cardiosense.repository.training.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;


    public UserInfoEntity saveUserInfo(UserInfoEntity userInfo) {
        return userInfoRepository.save(userInfo);
    }

    public UserInfoEntity getUserInfo(String userId) {
        return userInfoRepository.findByUser_Id(userId).orElse(null);
    }

    public UserInfoEntity updateUserInfo(String userId, UserInfoEntity userInfo) {

        UserInfoEntity user = userInfoRepository.findByUser_Id(userId).orElseThrow(() -> new IllegalArgumentException("User not found for id: " + userId));

        if (userInfo.getAltura() != 0) {
            user.setAltura(userInfo.getAltura());
        }
        if (userInfo.getEdad() != 0) {
            user.setEdad(userInfo.getEdad());
        }
        if (userInfo.getAreas_mejorar() != null) {
            user.setAreas_mejorar(userInfo.getAreas_mejorar());
        }
        if (userInfo.getObjetivo() != null) {
            user.setObjetivo(userInfo.getObjetivo());
        }
        if (userInfo.getEjercicio_diario() != null) {
            user.setEjercicio_diario(userInfo.getEjercicio_diario());
        }
        if (userInfo.getCantidad_ejercicios_por_dia() != 0) {
            user.setCantidad_ejercicios_por_dia(userInfo.getCantidad_ejercicios_por_dia());
        }
        if (userInfo.getFrecuencia_entrenamiento_semanal() != 0) {
            user.setFrecuencia_entrenamiento_semanal(userInfo.getFrecuencia_entrenamiento_semanal());
        }
        if (userInfo.getTiempo_disponible_por_dia() != null) {
            user.setTiempo_disponible_por_dia(userInfo.getTiempo_disponible_por_dia());
        }
        if (userInfo.getNivel_experiencia() != null) {
            user.setNivel_experiencia(userInfo.getNivel_experiencia());
        }
        if (userInfo.getPreferencia_ejercicios() != null) {
            user.setPreferencia_ejercicios(userInfo.getPreferencia_ejercicios());
        }
        if (userInfo.getEquipamiento_disponible() != null) {
            user.setEquipamiento_disponible(userInfo.getEquipamiento_disponible());
        }
        if (userInfo.getLesiones_limitaciones() != null) {
            user.setLesiones_limitaciones(userInfo.getLesiones_limitaciones());
        }
        if (userInfo.getTipo_cuerpo() != null) {
            user.setTipo_cuerpo(userInfo.getTipo_cuerpo());
        }
        if (userInfo.getPreferencia_indoor_outdoor() != null) {
            user.setPreferencia_indoor_outdoor(userInfo.getPreferencia_indoor_outdoor());
        }
        if (userInfo.getCondiciones_medicas() != null) {
            user.setCondiciones_medicas(userInfo.getCondiciones_medicas());
        }
        if (userInfo.getSuplementos() != null) {
            user.setSuplementos(userInfo.getSuplementos());
        }
        if (userInfo.getEstilo_vida() != null) {
            user.setEstilo_vida(userInfo.getEstilo_vida());
        }
        if (userInfo.getPreferencia_estilo_entrenamiento() != null) {
            user.setPreferencia_estilo_entrenamiento(userInfo.getPreferencia_estilo_entrenamiento());
        }
        if (userInfo.getHoras_sueno() != null) {
            user.setHoras_sueno(userInfo.getHoras_sueno());
        }
        if (userInfo.getPeso_inicial() != 0) {
            user.setPeso_inicial(userInfo.getPeso_inicial());
        }
        if (userInfo.getPeso_actualizado() != 0) {
            user.setPeso_actualizado(userInfo.getPeso_actualizado());
        }
        if (userInfo.getSexo() != null) {
            user.setSexo(userInfo.getSexo());
        }
        userInfoRepository.save(user);
        return user;
    }
}
