package com.cardiosense.cardiosense.service.training;

import com.cardiosense.cardiosense.model.training.*;
import com.cardiosense.cardiosense.repository.training.EquivalencesRepository;
import com.cardiosense.cardiosense.repository.training.RecommendationsRepository;
import com.cardiosense.cardiosense.repository.training.TrainingDataRepository;
import com.cardiosense.cardiosense.repository.training.TrainingPlanRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Slf4j
public class TrainingService {
    private static final String GENERATE_PLAN_URL = "http://127.0.0.1:5000/generar_plan";

    private final EquivalencesRepository equivalencesRepository;
    private final RecommendationsRepository recommendationsRepository;
    private final TrainingDataRepository trainingDataRepository;
    private final TrainingPlanRepository trainingPlanRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final UserInfoService userInfoService;

    public void generateCompleteTrainingPlan(String userId) {
        try {
            UserInfoEntity userInfo = userInfoService.getUserInfo(userId);

            String response = null;
            try {
                response = restTemplate.postForObject(GENERATE_PLAN_URL, userInfo, String.class);
            } catch (Exception e) {
                log.error("Error al conectarse con el servidor de entrenamiento: {}", e.getMessage(), e);
            }

            if (response != null) {
                JsonNode root = objectMapper.readTree(response);
                saveEquivalences(root.get("tabla_equivalencias"), userInfo);
                saveRecommendations(root.get("recomendaciones"), userInfo);
                saveTrainingData(root.get("peso_perfecto_y_estado_actual"), root.get("tipo_entrenamiento"), userInfo);
                saveTrainingPlan(root.get("plan_entrenamiento"), userInfo);
            } else {
                log.warn("La respuesta del servidor es nula para el usuario {}", userId);
            }
        } catch (Exception e) {
            log.error("Error inesperado al generar el plan de entrenamiento para el usuario {}: {}", userId, e.getMessage(), e);
        }
    }

    public EquivalencesEntity getEquivalences(String userId) {
        return equivalencesRepository.findByUser_Id(userId);
    }

    public RecommendationsEntity getRecommendations(String userId) {
        return recommendationsRepository.findByUser_Id(userId);
    }

    public TrainingPlanEntity getTrainingPlan(String userId) {
        return trainingPlanRepository.findByUser_Id(userId);
    }

    public TrainingDataEntity getTrainingData(String userId) {
        return trainingDataRepository.findByUser_Id(userId);
    }

    private void saveEquivalences(JsonNode equivalencesNode, UserInfoEntity userInfo) {
        if (equivalencesNode != null) {
            EquivalencesEntity equivalence = new EquivalencesEntity();
            equivalence.setUser(userInfo.getUser());
            equivalence.setCreatedAt(new Date());
            Map<String, List<String>> equivalences = objectMapper.convertValue(equivalencesNode, new TypeReference<>() {
            });
            equivalence.setExerciseEquivalences(equivalences);
            equivalencesRepository.save(equivalence);
        } else {
            log.warn("EquivalencesNode es nulo para el usuario {}", userInfo.getUser().getId());
        }
    }

    private void saveRecommendations(JsonNode recommendationsNode, UserInfoEntity userInfo) {
        if (recommendationsNode != null) {
            RecommendationsEntity recommendations = new RecommendationsEntity();
            recommendations.setUser(userInfo.getUser());
            recommendations.setCreatedAt(new Date());
            List<String> recommendationsList = objectMapper.convertValue(recommendationsNode, new TypeReference<>() {
            });
            recommendations.setRecommendations(recommendationsList);
            recommendationsRepository.save(recommendations);
        } else {
            log.warn("RecommendationsNode es nulo para el usuario {}", userInfo.getUser().getId());
        }
    }

    private void saveTrainingData(JsonNode stateNode, JsonNode typeNode, UserInfoEntity userInfo) {
        if (stateNode != null && typeNode != null) {
            TrainingDataEntity trainingData = new TrainingDataEntity();
            trainingData.setUser(userInfo.getUser());
            trainingData.setCreatedAt(new Date());
            trainingData.setEstadoActual(stateNode.get("estado_actual").asText());
            trainingData.setPesoActual(stateNode.get("peso_actual").asDouble());
            trainingData.setPesoPerfecto(stateNode.get("peso_perfecto").asDouble());
            trainingData.setProgresionGradual(stateNode.get("progresion_gradual").asBoolean());
            trainingData.setSexo(stateNode.get("sexo").asText());
            trainingData.setTipoEntrenamiento(typeNode.asText());
            trainingDataRepository.save(trainingData);
        } else {
            log.warn("StateNode o TypeNode es nulo para el usuario {}", userInfo.getUser().getId());
        }
    }

    private void saveTrainingPlan(JsonNode planNode, UserInfoEntity userInfo) {
        if (planNode != null) {
            TrainingPlanEntity trainingPlan = new TrainingPlanEntity();
            trainingPlan.setUser(userInfo.getUser());
            trainingPlan.setCreatedAt(new Date());
            Map<String, TrainingPlanEntity.DayPlan> planEntrenamiento = objectMapper.convertValue(planNode, new TypeReference<>() {
            });
            trainingPlan.setPlanEntrenamiento(planEntrenamiento);
            trainingPlanRepository.save(trainingPlan);
        } else {
            log.warn("PlanNode es nulo para el usuario {}", userInfo.getUser().getId());
        }
    }

    public String generateIMC(String userId) {
        UserInfoEntity userInfo = userInfoService.getUserInfo(userId);
        double altura = userInfo.getAltura() / 100.0;
        double peso;

        if (userInfo.getPeso_actualizado() != 0) {
            peso = userInfo.getPeso_actualizado();
        } else {
            peso = userInfo.getPeso_inicial();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("cbtn", "1");
        body.add("ta", String.valueOf(altura));
        body.add("pe", String.valueOf(peso));

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        String url = "https://alimentacionsaludable.ins.gob.pe/imc-adulto/result";

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return convertResponseToString(response);
        } catch (Exception e) {
            log.error("Error al calcular el IMC para el usuario {}: {}", userId, e.getMessage(), e);
            return "Error calculating IMC";
        }
    }

    private String convertResponseToString(ResponseEntity<String> response) {
        String jsonResponse = response.getBody();

        String decodedHtml = Objects.requireNonNull(jsonResponse).replace("\\u003C", "<").replace("\\u003E", ">").replace("\\u0022", "\"").replace("\\/", "/").replace("\\u00f3", "ó");

        Document doc = Jsoup.parse(decodedHtml);

        Element imcElement = doc.selectFirst("li:contains(IMC)");
        String imc = "N/A";
        if (imcElement != null) {
            String imcText = imcElement.text();
            imc = imcText.replaceAll("[^0-9.]", "").trim();
        }

        Elements listItems = doc.select("li");
        String clasificacion = "N/A";
        for (Element li : listItems) {
            if (li.text().contains("Clasificación Nutricional:")) {
                clasificacion = li.text().replace("Clasificación Nutricional:", "").trim();
                break;
            }
        }

        return "IMC: " + imc + ", Clasificación Nutricional: " + clasificacion;
    }
}
