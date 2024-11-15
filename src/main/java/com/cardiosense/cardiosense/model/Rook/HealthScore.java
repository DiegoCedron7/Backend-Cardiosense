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
@Document(collection = "health_score")
public class HealthScore {

    @Id
    private String id;

    @JsonProperty("data_structure")
    private String dataStructure;

    @JsonProperty("version")
    private Integer version;

    @JsonProperty("document_version")
    private Integer documentVersion;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("client_uuid")
    private String clientUuid;

    @JsonProperty("health_score_data")
    private HealthScoreData healthScoreData;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HealthScoreData {

        @JsonProperty("metadata")
        private Metadata metadata;

        @JsonProperty("overall_scores")
        private OverallScores overallScores;

        @JsonProperty("physical_health_score")
        private PhysicalHealthScore physicalHealthScore;

        @JsonProperty("sleep_health_score")
        private SleepHealthScore sleepHealthScore;

        @JsonProperty("body_health_score")
        private BodyHealthScore bodyHealthScore;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Metadata {

        @JsonProperty("datetime_string")
        private String datetimeString;

        @JsonProperty("sources_of_data_array")
        private List<String> sourcesOfDataArray;

        @JsonProperty("user_id_string")
        private String userIdString;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OverallScores {

        @JsonProperty("global_score_0_100_int")
        private Integer globalScore0To100Int;

        @JsonProperty("seven_days_avg_score_0_100_int")
        private Integer sevenDaysAvgScore0To100Int;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PhysicalHealthScore {

        @JsonProperty("score_0_100_int")
        private Integer score0To100Int;

        @JsonProperty("calories_score")
        private SubScore caloriesScore;

        @JsonProperty("activity_score")
        private SubScore activityScore;

        @JsonProperty("steps_score")
        private SubScore stepsScore;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SleepHealthScore {

        @JsonProperty("score_0_100_int")
        private Integer score0To100Int;

        @JsonProperty("sleep_duration_score")
        private SubScore sleepDurationScore;

        @JsonProperty("sleep_quality_score")
        private SubScore sleepQualityScore;

        @JsonProperty("readiness_score")
        private SubScore readinessScore;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BodyHealthScore {

        @JsonProperty("score_0_100_int")
        private Integer score0To100Int;

        @JsonProperty("bmi_score")
        private SubScore bmiScore;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SubScore {

        @JsonProperty("datetime_string")
        private String datetimeString;

        @JsonProperty("created_at_string")
        private String createdAtString;

        @JsonProperty("updated_at_string")
        private String updatedAtString;

        @JsonProperty("score_0_100_int")
        private Integer score0To100Int;

        @JsonProperty("calculated_with_missing_user_info_bool")
        private Boolean calculatedWithMissingUserInfoBool;

        @JsonProperty("missing_user_info_array")
        private List<String> missingUserInfoArray;
    }
}
