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

@Service
@AllArgsConstructor
public class GeneratorService {

    private static final String TRAINING_URL = "http://127.0.0.1:5000/generar_plan";
    private static final String DIET_URL = "http://127.0.0.1:5000/generar_dieta";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final RestTemplate restTemplate;
    private final UserService userService;
    private final DietPlanRepository dietPlanRepository;
    private final TrainingPlanRepository trainingPlanRepository;

    private boolean isPlanForCurrentWeek(String planDateString) {
        LocalDate planDate = LocalDate.parse(planDateString, DATE_FORMATTER);
        LocalDate currentDate = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return currentDate.getYear() == planDate.getYear() &&
                currentDate.get(weekFields.weekOfWeekBasedYear()) == planDate.get(weekFields.weekOfWeekBasedYear());
    }

    public String generateTraining(String userId) {
        return userService.getUserById(userId)
                .map(this::handleTrainingGeneration)
                .orElse("Usuario no encontrado.");
    }

    private String handleTrainingGeneration(UserEntity userEntity) {
        TrainingPlan existingPlan = trainingPlanRepository.findByUser(userEntity);

        if (existingPlan != null && isPlanForCurrentWeek(existingPlan.getDatetime())) {
            return "Ya existe un plan de entrenamiento para la semana actual.";
        }

        return userService.getData(userEntity.getId())
                .map(data -> createAndSaveTrainingPlan(userEntity, data, existingPlan))
                .orElse("No se encontraron datos para el usuario con ID: " + userEntity.getId());
    }

    private String createAndSaveTrainingPlan(UserEntity userEntity, FullDataDTO data, TrainingPlan existingPlan) {
        TrainingRequest trainingRequest = buildTrainingRequest(data);

        TrainingPlan trainingPlan = attemptToGeneratePlan(trainingRequest, TRAINING_URL, TrainingPlan.class);
        if (trainingPlan == null) {
            return "No se pudo generar el plan de entrenamiento para el usuario con ID: " + userEntity.getId();
        }

        trainingPlan.setUser(userEntity);
        if (existingPlan != null) {
            trainingPlan.setId(existingPlan.getId());
        }
        trainingPlanRepository.save(trainingPlan);
        return "Plan de entrenamiento generado y guardado correctamente.";
    }

    private TrainingRequest buildTrainingRequest(FullDataDTO data) {
        return TrainingRequest.builder()
                .altura(data.getUser().getAltura())
                .pesoInicial(data.getUser().getPesoInicial())
                .pesoActualizado(data.getUser().getPesoActualizado())
                .edad(data.getUser().getEdad())
                .objetivo(data.getUser().getObjetivo())
                .ejercicioDiario(data.getTraining().getEjercicioDiario())
                .cantidadEjerciciosPorDia(data.getTraining().getCantidadEjerciciosPorDia())
                .frecuenciaEntrenamientoSemanal(data.getTraining().getFrecuenciaEntrenamientoSemanal())
                .tiempoDisponiblePorDia(data.getTraining().getTiempoDisponiblePorDia())
                .nivelExperiencia(data.getTraining().getNivelExperiencia())
                .preferenciaEjercicios(data.getTraining().getPreferenciaEjercicios())
                .equipamientoDisponible(data.getTraining().getEquipamientoDisponible())
                .areasMejorar(data.getTraining().getAreasMejorar())
                .lesionesLimitaciones(data.getTraining().getLesionesLimitaciones())
                .tipoCuerpo(data.getTraining().getTipoCuerpo())
                .preferenciaIndoorOutdoor(data.getTraining().getPreferenciaIndoorOutdoor())
                .condicionesMedicas(data.getTraining().getCondicionesMedicas())
                .preferenciaEstiloEntrenamiento(data.getTraining().getPreferenciaEstiloEntrenamiento())
                .build();
    }

    public String generateDiet(String userId) {
        return userService.getUserById(userId)
                .map(this::handleDietGeneration)
                .orElse("Usuario no encontrado.");
    }

    private String handleDietGeneration(UserEntity userEntity) {
        DietPlan existingDietPlan = dietPlanRepository.findByUser(userEntity);

        if (existingDietPlan != null && isPlanForCurrentWeek(existingDietPlan.getDatetime())) {
            return "Ya existe un plan de dieta para la semana actual.";
        }

        return userService.getData(userEntity.getId())
                .map(data -> createAndSaveDietPlan(userEntity, data, existingDietPlan))
                .orElse("No se encontraron datos para el usuario con ID: " + userEntity.getId());
    }

    private String createAndSaveDietPlan(UserEntity userEntity, FullDataDTO data, DietPlan existingDietPlan) {
        DietRequest dietRequest = buildDietRequest(data);

        DietPlan dietPlan = attemptToGeneratePlan(dietRequest, DIET_URL, DietPlan.class);
        if (dietPlan == null) {
            return "No se pudo generar el plan de dieta para el usuario con ID: " + userEntity.getId();
        }

        dietPlan.setUser(userEntity);
        if (existingDietPlan != null) {
            dietPlan.setId(existingDietPlan.getId());
        }
        dietPlanRepository.save(dietPlan);
        return "Plan de dieta generado y guardado correctamente.";
    }

    private DietRequest buildDietRequest(FullDataDTO data) {
        return DietRequest.builder()
                .altura(data.getUser().getAltura())
                .pesoInicial(data.getUser().getPesoInicial())
                .pesoActualizado(String.valueOf(data.getUser().getPesoActualizado()))
                .edad(data.getUser().getEdad())
                .sexo(data.getUser().getSexo())
                .tipoCuerpo(data.getTraining().getTipoCuerpo())
                .numeroComidas(data.getDiet().getNumeroComidas())
                .alergias(data.getDiet().getAlergias())
                .objetivo(data.getUser().getObjetivo())
                .alimentosPreferidos(data.getDiet().getAlimentosPreferidos())
                .alimentosEvitar(data.getDiet().getAlimentosEvitar())
                .intolerancias(data.getDiet().getIntolerancias())
                .preferenciaAlimentaria(data.getDiet().getPreferenciaAlimentaria())
                .distribucionMacronutrientes(data.getDiet().getDistribucionMacronutrientes())
                .suplementos(data.getUser().getSuplementos())
                .disponibilidadCocinar(data.getDiet().getDisponibilidadCocinar())
                .preferenciasCoccion(data.getDiet().getPreferenciasCoccion())
                .residencia(data.getDiet().getResidencia())
                .nivelActividad(data.getDiet().getNivelActividad())
                .porcentajeGrasa(data.getDiet().getPorcentajeGrasa())
                .condicionesMedicas(data.getDiet().getCondicionesMedicas())
                .build();
    }

    private <T> T attemptToGeneratePlan(Object request, String url, Class<T> responseType) {
        int attempts = 0;
        int maxAttempts = 2;
        while (attempts < maxAttempts) {
            try {
                return restTemplate.postForObject(url, request, responseType);
            } catch (Exception e) {
                System.err.println("Error al generar plan en el intento " + (attempts + 1));
                e.printStackTrace();
            }
            attempts++;
        }
        return null;
    }

    public DietPlan getDietPlan(String userId) {
        return userService.getUserById(userId)
                .map(dietPlanRepository::findByUser)
                .orElse(null);
    }

    public TrainingPlan getTrainingPlan(String userId) {
        return userService.getUserById(userId)
                .map(trainingPlanRepository::findByUser)
                .orElse(null);
    }
}
