package fashionable.simba.yanawaserver.court.infra;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    @JsonProperty("AREANM")
    private String areaName;
    @JsonProperty("PLACENM")
    private String placeName;
    @JsonProperty("IMGURL")
    private String imagePath;

    public ApiResponse() {

    }

    public ApiResponse(String areaName, String placeName, String imagePath) {
        this.areaName = areaName;
        this.placeName = placeName;
        this.imagePath = imagePath;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiResponse that = (ApiResponse) o;
        return Objects.equals(getAreaName(), that.getAreaName()) && Objects.equals(getPlaceName(), that.getPlaceName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAreaName(), getPlaceName());
    }
}
