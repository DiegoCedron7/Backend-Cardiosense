package com.cardiosense.cardiosense.service;

import com.cardiosense.cardiosense.DTO.User.UserInfoDTO.FullDataDTO;
import com.cardiosense.cardiosense.model.Diet.DietPlan;
import com.cardiosense.cardiosense.model.Diet.DietRequest;
import com.cardiosense.cardiosense.model.Training.TrainingPlan;
import com.cardiosense.cardiosense.model.Training.TrainingRequest;
import com.cardiosense.cardiosense.model.User.UserEntity;
import com.cardiosense.cardiosense.repository.Diet.DietPlanRepository;
import com.cardiosense.cardiosense.repository.Training.TrainingPlanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GeneratorService {

    private final RestTemplate restTemplate;
    private final String TRAINING_URL = "http://127.0.0.1:5000/generar_plan";
    private final String DIET_URL = "http://127.0.0.1:5000/generar_dieta";

    private final UserService userService;

    private final DietPlanRepository dietPlanRepository;
    private final TrainingPlanRepository trainingPlanRepository;

    public TrainingPlan getTrainingPlan(String userId) {
        Optional<UserEntity> userEntity = userService.getUserById(userId);
        return userEntity.map(trainingPlanRepository::findByUser).orElse(null);
    }

    public void generateTraining(String userId) {
        Optional<FullDataDTO> data = userService.getData(userId);
        if (data.isPresent()) {
            TrainingRequest training = TrainingRequest.builder()
                    .altura(data.get().getUser().getAltura())
                    .pesoInicial(data.get().getUser().getPesoInicial())
                    .pesoActualizado(data.get().getUser().getPesoActualizado())
                    .edad(data.get().getUser().getEdad())
                    .objetivo(data.get().getUser().getObjetivo())
                    .ejercicioDiario(data.get().getTraining().getEjercicioDiario())
                    .cantidadEjerciciosPorDia(data.get().getTraining().getCantidadEjerciciosPorDia())
                    .frecuenciaEntrenamientoSemanal(data.get().getTraining().getFrecuenciaEntrenamientoSemanal())
                    .tiempoDisponiblePorDia(data.get().getTraining().getTiempoDisponiblePorDia())
                    .nivelExperiencia(data.get().getTraining().getNivelExperiencia())
                    .preferenciaEjercicios(data.get().getTraining().getPreferenciaEjercicios())
                    .equipamientoDisponible(data.get().getTraining().getEquipamientoDisponible())
                    .areasMejorar(data.get().getTraining().getAreasMejorar())
                    .lesionesLimitaciones(data.get().getTraining().getLesionesLimitaciones())
                    .tipoCuerpo(data.get().getTraining().getTipoCuerpo())
                    .preferenciaIndoorOutdoor(data.get().getTraining().getPreferenciaIndoorOutdoor())
                    .condicionesMedicas(data.get().getTraining().getCondicionesMedicas())
                    .preferenciaEstiloEntrenamiento(data.get().getTraining().getPreferenciaEstiloEntrenamiento())
                    .build();

            TrainingPlan trainingPlan = null;
            int attempts = 0;
            int maxAttempts = 2;
            while (trainingPlan == null && attempts < maxAttempts) {
                try {
                    trainingPlan = restTemplate.postForObject(TRAINING_URL, training, TrainingPlan.class);
                } catch (Exception e) {
                    System.out.println("Error al generar y guardar el TrainingPlan para el usuario con ID: " + userId);
                    e.printStackTrace();
                }
                attempts++;
            }
            if (trainingPlan != null) {
                Optional<UserEntity> userEntity = userService.getUserById(userId);

                if (userEntity.isPresent()) {
                    trainingPlan.setUser(userEntity.get());
                    trainingPlanRepository.save(trainingPlan);
                } else {
                    System.out.println("No se encontró el usuario con ID: " + userId);
                }
            }


        } else {
            System.out.println("No se encontraron datos para el usuario con ID: " + userId);
        }
    }


    public DietPlan getDietPlan(String userId) {
        Optional<UserEntity> userEntity = userService.getUserById(userId);
        return userEntity.map(dietPlanRepository::findByUser).orElse(null);
    }

    public void generateDiet(String userId) {
        Optional<FullDataDTO> data = userService.getData(userId);
        if (data.isPresent()) {
            DietRequest diet = DietRequest.builder()
                    .altura(data.get().getUser().getAltura())
                    .pesoInicial(data.get().getUser().getPesoInicial())
                    .pesoActualizado(String.valueOf(data.get().getUser().getPesoActualizado()))
                    .edad(data.get().getUser().getEdad())
                    .sexo(data.get().getUser().getSexo())
                    .tipoCuerpo(data.get().getTraining().getTipoCuerpo())
                    .numeroComidas(data.get().getDiet().getNumeroComidas())
                    .alergias(data.get().getDiet().getAlergias())
                    .objetivo(data.get().getUser().getObjetivo())
                    .alimentosPreferidos(data.get().getDiet().getAlimentosPreferidos())
                    .alimentosEvitar(data.get().getDiet().getAlimentosEvitar())
                    .intolerancias(data.get().getDiet().getIntolerancias())
                    .preferenciaAlimentaria(data.get().getDiet().getPreferenciaAlimentaria())
                    .distribucionMacronutrientes(data.get().getDiet().getDistribucionMacronutrientes())
                    .suplementos(data.get().getUser().getSuplementos())
                    .disponibilidadCocinar(data.get().getDiet().getDisponibilidadCocinar())
                    .preferenciasCoccion(data.get().getDiet().getPreferenciasCoccion())
                    .residencia(data.get().getDiet().getResidencia())
                    .nivelActividad(data.get().getDiet().getNivelActividad())
                    .porcentajeGrasa(data.get().getDiet().getPorcentajeGrasa())
                    .condicionesMedicas(data.get().getDiet().getCondicionesMedicas())
                    .build();

            DietPlan dietPlan = null;

            int attempts = 0;
            int maxAttempts = 2;
            while (dietPlan == null && attempts < maxAttempts) {
                try {
                    dietPlan = restTemplate.postForObject(DIET_URL, diet, DietPlan.class);
                } catch (Exception e) {
                    System.out.println("Error al generar y guardar el DietPlan para el usuario con ID: " + userId);
                    e.printStackTrace();
                }
                attempts++;
            }

            if (dietPlan != null) {
                Optional<UserEntity> userEntity = userService.getUserById(userId);

                if (userEntity.isPresent()) {
                    dietPlan.setUser(userEntity.get());
                    dietPlanRepository.save(dietPlan);
                } else {
                    System.out.println("No se encontró el usuario con ID: " + userId);
                }
            }
        } else {
            System.out.println("No se encontraron datos para el usuario con ID: " + userId);

        }
    }
}
