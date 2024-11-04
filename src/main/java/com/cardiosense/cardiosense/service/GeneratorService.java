package com.cardiosense.cardiosense.service;

import com.cardiosense.cardiosense.DTO.User.UserInfoDTO.FullDataDTO;
import com.cardiosense.cardiosense.model.Diet.DietRequest;
import com.cardiosense.cardiosense.model.Training.TrainingPlan;
import com.cardiosense.cardiosense.model.Training.TrainingRequest;
import com.cardiosense.cardiosense.model.User.UserEntity;
import com.cardiosense.cardiosense.repository.Diet.DietPlanRepository;
import com.cardiosense.cardiosense.repository.Training.TrainingPlanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GeneratorService {

    private final RestTemplate restTemplate;
    private final String TRAINING_URL = "http://localhost:8080/training";
    private final String DIET_URL = "http://localhost:8080/diet";

    private final UserService userService;

    private final DietPlanRepository dietPlanRepository;
    private final TrainingPlanRepository trainingPlanRepository;

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

            try {
                TrainingPlan trainingPlan = restTemplate.postForObject(TRAINING_URL, training, TrainingPlan.class);

                if (trainingPlan != null) {
                    Optional<UserEntity> userEntity = userService.getUserById(userId);

                    if (userEntity.isPresent()) {
                        trainingPlan.setUser(userEntity.get());
                        trainingPlanRepository.save(trainingPlan);
                    } else {
                        System.out.println("No se encontró el usuario con ID: " + userId);
                    }
                }

            } catch (Exception e) {
                System.out.println("Error al generar y guardar el TrainingPlan para el usuario con ID: " + userId);
                e.printStackTrace();
            }
        } else {
            System.out.println("No se encontraron datos para el usuario con ID: " + userId);
        }
    }


    public void generateDiet(String userId) {
        Optional<FullDataDTO> data = userService.getData(userId);
        if (data.isPresent()) {
            DietRequest dieta = DietRequest.builder()
                    /*
                    .altura(data.get().getAltura())
                    .pesoInicial(data.get().getPesoInicial())
                    .pesoActualizado(data.get().getPesoActualizado())
                    .edad(data.get().getEdad())
                    .objetivo(data.get().getObjetivo())
                    .ejercicioDiario(data.get().getEjercicioDiario())
                    .cantidadEjerciciosPorDia(data.get().getCantidadEjerciciosPorDia())
                    .frecuenciaEntrenamientoSemanal(data.get().getFrecuenciaEntrenamientoSemanal())
                    .tiempoDisponiblePorDia(data.get().getTiempoDisponiblePorDia())
                    .nivelExperiencia(data.get().getNivelExperiencia())
                    .preferenciaEjercicios(data.get().getPreferenciaEjercicios())
                    .equipamientoDisponible(data.get().getEquipamientoDisponible())
                    .areasMejorar(data.get().getAreasMejorar())
                    .lesionesLimitaciones(data.get().getLesionesLimitaciones())
                    .tipoCuerpo(data.get().getTipoCuerpo())
                    .preferenciaIndoorOutdoor(data.get().getPreferenciaIndoorOutdoor())
                    .condicionesMedicas(data.get().getCondicionesMedicas())
                    .suplementos(data.get().getSuplementos())
                    .estiloVida(data.get().getEstiloVida())
                    .preferenciaEstiloEntrenamiento(data.get().getPreferenciaEstiloEntrenamiento())
                    .horasSueno(data.get().getHorasSueno())
                    .sexo(data.get().getSexo())
                    */
                    .build();
            try {
                restTemplate.postForObject(DIET_URL, dieta, DietRequest.class);
            } catch (Exception e) {
                System.out.println("No se encontraron datos para el usuario con ID: " + userId);
            }
        } else {

            System.out.println("No se encontraron datos para el usuario con ID: " + userId);
        }
    }


}
