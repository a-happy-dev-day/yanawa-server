package fashionable.simba.yanawaserver.court.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Court {
    @JsonProperty("AREANM")
    private String areaName;
    @JsonProperty("PLACENM")
    private String placeName;
    @JsonProperty("IMGURL")
    private String imageUrl;

    public Court() {
    }

    public String getAreaName() {
        return areaName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Court{" +
            "areaName='" + areaName + '\'' +
            ", placeName='" + placeName + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            '}';
    }
}
