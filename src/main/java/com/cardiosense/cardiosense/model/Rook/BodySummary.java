package com.cardiosense.cardiosense.model.Rook;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "body_summary")
public class BodySummary {

    @Id
    private String id;

    @JsonProperty("version")
    private Integer version;

    @JsonProperty("data_structure")
    private String dataStructure;

    @JsonProperty("client_uuid")
    private String clientUuid;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("document_version")
    private Integer documentVersion;

    @JsonProperty("body_health")
    private BodyHealth bodyHealth;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BodyHealth {
        @JsonProperty("summary")
        private Summary summary;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Summary {
        @JsonProperty("body_summary")
        private BodyDetails bodySummary;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BodyDetails {

        @JsonProperty("body_metrics")
        private BodyMetrics bodyMetrics;

        @JsonProperty("blood_glucose")
        private BloodGlucose bloodGlucose;

        @JsonProperty("blood_pressure")
        private BloodPressure bloodPressure;

        @JsonProperty("hydration")
        private Hydration hydration;

        @JsonProperty("heart_rate")
        private HeartRate heartRate;

        @JsonProperty("mood")
        private Mood mood;

        @JsonProperty("nutrition")
        private Nutrition nutrition;

        @JsonProperty("oxygenation")
        private Oxygenation oxygenation;

        @JsonProperty("temperature")
        private Temperature temperature;

        @JsonProperty("menstruation")
        private Menstruation menstruation;

        @JsonProperty("metadata")
        private Metadata metadata;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BodyMetrics {
        @JsonProperty("waist_circumference_cm_int")
        private Integer waistCircumference;

        @JsonProperty("hip_circumference_cm_int")
        private Integer hipCircumference;

        @JsonProperty("chest_circumference_cm_int")
        private Integer chestCircumference;

        @JsonProperty("weight_kg_float")
        private Float weight;

        @JsonProperty("height_cm_int")
        private Integer height;

        @JsonProperty("bmi_float")
        private Float bmi;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BloodGlucose {
        @JsonProperty("blood_glucose_avg_mg_per_dL_int")
        private Integer avgBloodGlucose;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BloodPressure {
        @JsonProperty("blood_pressure_avg_object")
        private Object avgBloodPressure;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Hydration {
        @JsonProperty("water_total_consumption_mL_int")
        private Integer totalWaterConsumption;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HeartRate {
        @JsonProperty("hr_maximum_bpm_int")
        private Integer maxHeartRate;

        @JsonProperty("hr_minimum_bpm_int")
        private Integer minHeartRate;

        @JsonProperty("hr_avg_bpm_int")
        private Integer avgHeartRate;

        @JsonProperty("hr_resting_bpm_int")
        private Integer restingHeartRate;

        @JsonProperty("hrv_avg_rmssd_float")
        private Float hrvAvgRmssd;

        @JsonProperty("hrv_avg_sdnn_float")
        private Float hrvAvgSdnn;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Mood {
        @JsonProperty("mood_minimum_scale_int")
        private Integer moodMinimumScale;

        @JsonProperty("mood_avg_scale_int")
        private Integer moodAvgScale;

        @JsonProperty("mood_maximum_scale_int")
        private Integer moodMaximumScale;

        @JsonProperty("mood_delta_scale_int")
        private Integer moodDeltaScale;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Nutrition {
        @JsonProperty("food_intake_float")
        private Float foodIntake;

        @JsonProperty("calories_intake_kcal_float")
        private Float caloriesIntake;

        @JsonProperty("protein_intake_g_float")
        private Float proteinIntake;

        @JsonProperty("sugar_intake_g_float")
        private Float sugarIntake;

        @JsonProperty("fat_intake_g_float")
        private Float fatIntake;

        @JsonProperty("trans_fat_intake_g_float")
        private Float transFatIntake;

        @JsonProperty("carbohydrates_intake_g_float")
        private Float carbohydratesIntake;

        @JsonProperty("fiber_intake_g_float")
        private Float fiberIntake;

        @JsonProperty("alcohol_intake_g_float")
        private Float alcoholIntake;

        @JsonProperty("sodium_intake_mg_float")
        private Float sodiumIntake;

        @JsonProperty("cholesterol_intake_mg_float")
        private Float cholesterolIntake;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Oxygenation {
        @JsonProperty("saturation_avg_percentage_int")
        private Integer saturationAvgPercentage;

        @JsonProperty("vo2max_mL_per_min_per_kg_int")
        private Integer vo2max;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Temperature {
        @JsonProperty("temperature_avg_object")
        private Object temperatureAvg;

        @JsonProperty("temperature_maximum_object")
        private Object temperatureMaximum;

        @JsonProperty("temperature_minimum_object")
        private Object temperatureMinimum;

        @JsonProperty("temperature_delta_object")
        private Object temperatureDelta;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Menstruation {
        @JsonProperty("last_updated_datetime_string")
        private String lastUpdatedDatetime;

        @JsonProperty("period_start_date_string")
        private String periodStartDate;

        @JsonProperty("cycle_day_int")
        private Integer cycleDay;

        @JsonProperty("predicted_cycle_length_days_int")
        private Integer predictedCycleLength;

        @JsonProperty("cycle_length_days_int")
        private Integer cycleLength;

        @JsonProperty("current_phase_string")
        private String currentPhase;

        @JsonProperty("length_of_current_phase_days_int")
        private Integer currentPhaseLength;

        @JsonProperty("days_until_next_phase_int")
        private Integer daysUntilNextPhase;

        @JsonProperty("is_a_predicted_cycle_bool")
        private Boolean isPredictedCycle;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Metadata {
        @JsonProperty("datetime_string")
        private String datetime;

        @JsonProperty("user_id_string")
        private String userId;

        @JsonProperty("sources_of_data_array")
        private List<String> sourcesOfData;
    }
}
