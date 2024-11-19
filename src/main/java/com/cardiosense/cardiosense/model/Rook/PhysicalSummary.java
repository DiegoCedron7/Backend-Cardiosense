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
@Document(collection = "physical_summary")
public class PhysicalSummary {

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

    @JsonProperty("physical_health")
    private PhysicalHealth physicalHealth;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PhysicalHealth {
        @JsonProperty("summary")
        private Summary summary;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Summary {
        @JsonProperty("physical_summary")
        private PhysicalDetails physicalSummary;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PhysicalDetails {

        @JsonProperty("distance")
        private Distance distance;

        @JsonProperty("oxygenation")
        private Oxygenation oxygenation;

        @JsonProperty("activity")
        private Activity activity;

        @JsonProperty("calories")
        private Calories calories;

        @JsonProperty("heart_rate")
        private HeartRate heartRate;

        @JsonProperty("metadata")
        private Metadata metadata;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Distance {
        @JsonProperty("steps_int")
        private Integer steps;

        @JsonProperty("active_steps_int")
        private Integer activeSteps;

        @JsonProperty("walked_distance_meters_float")
        private Float walkedDistance;

        @JsonProperty("traveled_distance_meters_float")
        private Float traveledDistance;

        @JsonProperty("floors_climbed_float")
        private Float floorsClimbed;

        @JsonProperty("elevation_avg_altitude_meters_float")
        private Float elevationAvgAltitude;

        @JsonProperty("elevation_minimum_altitude_meters_float")
        private Float elevationMinAltitude;

        @JsonProperty("elevation_maximum_altitude_meters_float")
        private Float elevationMaxAltitude;

        @JsonProperty("elevation_loss_actual_altitude_meters_float")
        private Float elevationLoss;

        @JsonProperty("elevation_gain_actual_altitude_meters_float")
        private Float elevationGain;

        @JsonProperty("elevation_planned_gain_meters_float")
        private Float elevationPlannedGain;

        @JsonProperty("swimming_num_strokes_float")
        private Float swimmingNumStrokes;

        @JsonProperty("swimming_num_laps_int")
        private Integer swimmingNumLaps;

        @JsonProperty("swimming_pool_length_meters_float")
        private Float swimmingPoolLength;

        @JsonProperty("swimming_total_distance_meters_float")
        private Float swimmingTotalDistance;
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
    public static class Activity {
        @JsonProperty("active_seconds_int")
        private Integer activeSeconds;

        @JsonProperty("rest_seconds_int")
        private Integer restSeconds;

        @JsonProperty("low_intensity_seconds_int")
        private Integer lowIntensitySeconds;

        @JsonProperty("moderate_intensity_seconds_int")
        private Integer moderateIntensitySeconds;

        @JsonProperty("vigorous_intensity_seconds_int")
        private Integer vigorousIntensitySeconds;

        @JsonProperty("inactivity_seconds_int")
        private Integer inactivitySeconds;

        @JsonProperty("continuous_inactive_periods_int")
        private Integer continuousInactivePeriods;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Calories {
        @JsonProperty("calories_net_intake_kcal_float")
        private Float netIntakeCalories;

        @JsonProperty("calories_expenditure_kcal_float")
        private Float expenditureCalories;

        @JsonProperty("calories_net_active_kcal_float")
        private Float netActiveCalories;

        @JsonProperty("calories_basal_metabolic_rate_kcal_float")
        private Float basalMetabolicRateCalories;
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
    public static class Metadata {
        @JsonProperty("datetime_string")
        private String datetime;

        @JsonProperty("user_id_string")
        private String userId;

        @JsonProperty("sources_of_data_array")
        private List<String> sourcesOfData;
    }
}
