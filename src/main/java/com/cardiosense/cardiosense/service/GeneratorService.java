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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;
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

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private boolean isPlanForCurrentWeek(String planDateString) {
        LocalDate planDate = LocalDate.parse(planDateString, dateFormatter);
        LocalDate currentDate = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int currentWeek = currentDate.get(weekFields.weekOfWeekBasedYear());
        int planWeek = planDate.get(weekFields.weekOfWeekBasedYear());

        return currentDate.getYear() == planDate.getYear() && currentWeek == planWeek;
    }

    public String generateTraining(String userId) {
        Optional<UserEntity> userEntity = userService.getUserById(userId);
        if (userEntity.isPresent()) {
            TrainingPlan existingPlan = trainingPlanRepository.findByUser(userEntity.get());
            if (existingPlan != null && isPlanForCurrentWeek(existingPlan.getDatetime())) {
                return "Ya existe un plan de entrenamiento para la semana actual.";
            }

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
                    trainingPlan.setUser(userEntity.get());
                    if (existingPlan != null) {
                        trainingPlan.setId(existingPlan.getId());
                    }
                    trainingPlanRepository.save(trainingPlan);
                    return "Plan de entrenamiento generado y guardado correctamente.";
                } else {
                    return "No se encontró el usuario con ID: " + userId;
                }
            } else {
                return "No se encontraron datos para el usuario con ID: " + userId;
            }
        }
        return "Usuario no encontrado.";
    }


    public String generateDiet(String userId) {
        Optional<UserEntity> userEntity = userService.getUserById(userId);
        if (userEntity.isPresent()) {
            DietPlan existingDietPlan = dietPlanRepository.findByUser(userEntity.get());
            if (existingDietPlan != null && isPlanForCurrentWeek(existingDietPlan.getDatetime())) {
                return "Ya existe un plan de dieta para la semana actual.";
            }

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
                    dietPlan.setUser(userEntity.get());
                    if (existingDietPlan != null) {
                        dietPlan.setId(existingDietPlan.getId()); // Actualizar el plan existente
                    }
                    dietPlanRepository.save(dietPlan);
                    return "Plan de dieta generado y guardado correctamente.";
                } else {
                    return "No se pudo generar el plan de dieta para el usuario con ID: " + userId;
                }
            } else {
                return "No se encontraron datos para el usuario con ID: " + userId;
            }
        }
        return "Usuario no encontrado.";
    }


    public DietPlan getDietPlan(String userId) {
        Optional<UserEntity> userEntity = userService.getUserById(userId);
        return userEntity.map(dietPlanRepository::findByUser).orElse(null);
    }

    public TrainingPlan getTrainingPlan(String userId) {
        Optional<UserEntity> userEntity = userService.getUserById(userId);
        return userEntity.map(trainingPlanRepository::findByUser).orElse(null);
    }
}
