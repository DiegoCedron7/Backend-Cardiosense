package com.cardiosense.cardiosense.model.Rook;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "physical_activity")
public class PhysicalActivity {

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

    @JsonProperty("auto_detected")
    private Boolean autoDetected;

    @JsonProperty("physical_health")
    private PhysicalHealth physicalHealth;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PhysicalHealth {

        @JsonProperty("events")
        private Events events;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Events {

        @JsonProperty("activity_event")
        private List<ActivityEventData> activityEvent;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ActivityEventData {

        @JsonProperty("metadata")
        private Metadata metadata;

        @JsonProperty("activity")
        private Activity activity;

        @JsonProperty("calories")
        private Calories calories;

        @JsonProperty("distance")
        private Distance distance;

        @JsonProperty("heart_rate")
        private HeartRate heartRate;

        @JsonProperty("movement")
        private Movement movement;

        @JsonProperty("oxygenation")
        private Oxygenation oxygenation;
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
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Activity {

        @JsonProperty("activity_start_datetime_string")
        private String activityStartDatetimeString;

        @JsonProperty("activity_end_datetime_string")
        private String activityEndDatetimeString;

        @JsonProperty("activity_duration_seconds_int")
        private Integer activityDurationSecondsInt;

        @JsonProperty("activity_type_name_string")
        private String activityTypeNameString;

        @JsonProperty("active_seconds_int")
        private Integer activeSecondsInt;

        @JsonProperty("rest_seconds_int")
        private Integer restSecondsInt;

        @JsonProperty("low_intensity_seconds_int")
        private Integer lowIntensitySecondsInt;

        @JsonProperty("moderate_intensity_seconds_int")
        private Integer moderateIntensitySecondsInt;

        @JsonProperty("vigorous_intensity_seconds_int")
        private Integer vigorousIntensitySecondsInt;

        @JsonProperty("inactivity_seconds_int")
        private Integer inactivitySecondsInt;

        @JsonProperty("continuous_inactive_periods_int")
        private Integer continuousInactivePeriodsInt;

        @JsonProperty("activity_strain_level_float")
        private Float activityStrainLevelFloat;

        @JsonProperty("activity_work_kilojoules_float")
        private Float activityWorkKilojoulesFloat;

        @JsonProperty("activity_energy_kilojoules_float")
        private Float activityEnergyKilojoulesFloat;

        @JsonProperty("activity_energy_planned_kilojoules_float")
        private Float activityEnergyPlannedKilojoulesFloat;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Calories {

        @JsonProperty("calories_net_intake_kcal_float")
        private Float caloriesNetIntakeKcalFloat;

        @JsonProperty("calories_expenditure_kcal_float")
        private Float caloriesExpenditureKcalFloat;

        @JsonProperty("calories_net_active_kcal_float")
        private Float caloriesNetActiveKcalFloat;

        @JsonProperty("calories_basal_metabolic_rate_kcal_float")
        private Float caloriesBasalMetabolicRateKcalFloat;

        @JsonProperty("fat_percentage_of_calories_int")
        private Integer fatPercentageOfCaloriesInt;

        @JsonProperty("carbohydrate_percentage_of_calories_int")
        private Integer carbohydratePercentageOfCaloriesInt;

        @JsonProperty("protein_percentage_of_calories_int")
        private Integer proteinPercentageOfCaloriesInt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Distance {

        @JsonProperty("steps_int")
        private Integer stepsInt;

        @JsonProperty("walked_distance_meters_float")
        private Float walkedDistanceMetersFloat;

        @JsonProperty("traveled_distance_meters_float")
        private Float traveledDistanceMetersFloat;

        @JsonProperty("floors_climbed_float")
        private Float floorsClimbedFloat;

        @JsonProperty("elevation_avg_altitude_meters_float")
        private Float elevationAvgAltitudeMetersFloat;

        @JsonProperty("elevation_minimum_altitude_meters_float")
        private Float elevationMinimumAltitudeMetersFloat;

        @JsonProperty("elevation_maximum_altitude_meters_float")
        private Float elevationMaximumAltitudeMetersFloat;

        @JsonProperty("elevation_loss_actual_altitude_meters_float")
        private Float elevationLossActualAltitudeMetersFloat;

        @JsonProperty("elevation_gain_actual_altitude_meters_float")
        private Float elevationGainActualAltitudeMetersFloat;

        @JsonProperty("elevation_planned_gain_meters_float")
        private Float elevationPlannedGainMetersFloat;

        @JsonProperty("swimming_num_strokes_float")
        private Float swimmingNumStrokesFloat;

        @JsonProperty("swimming_num_laps_int")
        private Integer swimmingNumLapsInt;

        @JsonProperty("swimming_pool_length_meters_float")
        private Float swimmingPoolLengthMetersFloat;

        @JsonProperty("swimming_total_distance_meters_float")
        private Float swimmingTotalDistanceMetersFloat;
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

        @JsonProperty("hrv_avg_rmssd_float")
        private Float hrvAvgRmssdFloat;

        @JsonProperty("hrv_avg_sdnn_float")
        private Float hrvAvgSdnnFloat;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Movement {

        @JsonProperty("speed_normalized_meters_per_second_float")
        private Float speedNormalizedMetersPerSecondFloat;

        @JsonProperty("speed_avg_meters_per_second_float")
        private Float speedAvgMetersPerSecondFloat;

        @JsonProperty("speed_maximum_meters_per_second_float")
        private Float speedMaximumMetersPerSecondFloat;

        @JsonProperty("velocity_avg_object")
        private Object velocityAvgObject;

        @JsonProperty("velocity_maximum_object")
        private Object velocityMaximumObject;

        @JsonProperty("pace_avg_min_per_km_float")
        private Float paceAvgMinPerKmFloat;

        @JsonProperty("pace_maximum_min_per_km_float")
        private Float paceMaximumMinPerKmFloat;

        @JsonProperty("cadence_avg_rpm_float")
        private Float cadenceAvgRpmFloat;

        @JsonProperty("cadence_maximum_rpm_float")
        private Float cadenceMaximumRpmFloat;

        @JsonProperty("torque_avg_newton_meters_float")
        private Float torqueAvgNewtonMetersFloat;

        @JsonProperty("torque_maximum_newton_meters_float")
        private Float torqueMaximumNewtonMetersFloat;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Oxygenation {

        @JsonProperty("saturation_avg_percentage_int")
        private Integer saturationAvgPercentageInt;

        @JsonProperty("vo2max_mL_per_min_per_kg_int")
        private Integer vo2maxMLPerMinPerKgInt;
    }
}
