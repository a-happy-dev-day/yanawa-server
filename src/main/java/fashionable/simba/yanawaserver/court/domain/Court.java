package fashionable.simba.yanawaserver.court.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String areaName;
    private String placeName;
    private String imagePath;

    protected Court() {/*no-op*/}

    public Court(Long id, String areaName, String placeName, String imagePath) {
        this.id = id;
        this.areaName = areaName;
        this.placeName = placeName;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
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
}
