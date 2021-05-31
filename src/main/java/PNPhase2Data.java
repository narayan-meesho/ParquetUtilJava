import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PNPhase2Data {

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("catalogs")
    private List<CatalogData> catalogDataList;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CatalogData {
        @JsonProperty("catalog_id")
        private int catalogId;

        @JsonProperty("algo_scores")
        private List<AlgoScore> scores;

        @JsonProperty("rank")
        private int rank;

        //Timestamp in ISO format : 2020-29-01T12:13:14+0530
        @JsonProperty("timestamp")
        private String notificationTimestamp;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AlgoScore {
        @JsonProperty("algo_name")
        private String algo;

        @JsonProperty("score")
        private double scoreValue;
    }
}
