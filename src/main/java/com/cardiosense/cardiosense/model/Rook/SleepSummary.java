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
@Document(collection = "sleep_summary")
public class SleepSummary {

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

    @JsonProperty("sleep_health")
    private SleepHealth sleepHealth;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SleepHealth {
        @JsonProperty("summary")
        private Summary summary;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Summary {
        @JsonProperty("sleep_summary")
        private SleepDetails sleepSummary;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SleepDetails {

        @JsonProperty("metadata")
        private Metadata metadata;

        @JsonProperty("duration")
        private Duration duration;

        @JsonProperty("scores")
        private Scores scores;

        @JsonProperty("heart_rate")
        private HeartRate heartRate;

        @JsonProperty("temperature")
        private Temperature temperature;

        @JsonProperty("breathing")
        private Breathing breathing;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Metadata {
        @JsonProperty("datetime_string")
        private String datetimeString;

        @JsonProperty("user_id_string")
        private String userIdString;

        @JsonProperty("sources_of_data_array")
        private List<String> sourcesOfDataArray;

        @JsonProperty("sleep_health_score")
        private Float sleepHealthScore;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Duration {
        @JsonProperty("sleep_start_datetime_string")
        private String sleepStartDatetimeString;

        @JsonProperty("sleep_end_datetime_string")
        private String sleepEndDatetimeString;

        @JsonProperty("sleep_date_string")
        private String sleepDateString;

        @JsonProperty("sleep_duration_seconds_int")
        private Integer sleepDurationSecondsInt;

        @JsonProperty("time_in_bed_seconds_int")
        private Integer timeInBedSecondsInt;

        @JsonProperty("light_sleep_duration_seconds_int")
        private Integer lightSleepDurationSecondsInt;

        @JsonProperty("rem_sleep_duration_seconds_int")
        private Integer remSleepDurationSecondsInt;

        @JsonProperty("deep_sleep_duration_seconds_int")
        private Integer deepSleepDurationSecondsInt;

        @JsonProperty("time_to_fall_asleep_seconds_int")
        private Integer timeToFallAsleepSecondsInt;

        @JsonProperty("time_awake_during_sleep_seconds_int")
        private Integer timeAwakeDuringSleepSecondsInt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Scores {
        @JsonProperty("sleep_quality_rating_1_5_score_int")
        private Integer sleepQualityRating1To5ScoreInt;

        @JsonProperty("sleep_efficiency_1_100_score_int")
        private Integer sleepEfficiency1To100ScoreInt;

        @JsonProperty("sleep_goal_seconds_int")
        private Integer sleepGoalSecondsInt;

        @JsonProperty("sleep_continuity_1_5_score_int")
        private Integer sleepContinuity1To5ScoreInt;

        @JsonProperty("sleep_continuity_1_5_rating_int")
        private Integer sleepContinuity1To5RatingInt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HeartRate {
        @JsonProperty("hr_maximum_bpm_int")
        private Integer hrMaximumBpmInt;

        @JsonProperty("hr_minimum_bpm_int")
        private Integer hrMinimumBpmInt;

        @JsonProperty("hr_avg_bpm_int")
        private Integer hrAvgBpmInt;

        @JsonProperty("hr_resting_bpm_int")
        private Integer hrRestingBpmInt;

        @JsonProperty("hr_basal_bpm_int")
        private Integer hrBasalBpmInt;

        @JsonProperty("hrv_avg_rmssd_float")
        private Float hrvAvgRmssdFloat;

        @JsonProperty("hrv_avg_sdnn_float")
        private Float hrvAvgSdnnFloat;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Temperature {
        @JsonProperty("temperature_minimum_object")
        private TemperatureObject temperatureMinimumObject;

        @JsonProperty("temperature_avg_object")
        private TemperatureObject temperatureAvgObject;

        @JsonProperty("temperature_maximum_object")
        private TemperatureObject temperatureMaximumObject;

        @JsonProperty("temperature_delta_object")
        private TemperatureObject temperatureDeltaObject;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TemperatureObject {
        @JsonProperty("temperature_celsius_float")
        private Float temperatureCelsiusFloat;

        @JsonProperty("measurement_type_string")
        private String measurementTypeString;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Breathing {
        @JsonProperty("breaths_minimum_per_min_int")
        private Integer breathsMinimumPerMinInt;

        @JsonProperty("breaths_avg_per_min_int")
        private Integer breathsAvgPerMinInt;

        @JsonProperty("breaths_maximum_per_min_int")
        private Integer breathsMaximumPerMinInt;

        @JsonProperty("snoring_events_count_int")
        private Integer snoringEventsCountInt;

        @JsonProperty("snoring_duration_total_seconds_int")
        private Integer snoringDurationTotalSecondsInt;

        @JsonProperty("saturation_avg_percentage_int")
        private Integer saturationAvgPercentageInt;

        @JsonProperty("saturation_minimum_percentage_int")
        private Integer saturationMinimumPercentageInt;

        @JsonProperty("saturation_maximum_percentage_int")
        private Integer saturationMaximumPercentageInt;
    }
}
