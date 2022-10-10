package fashionable.simba.yanawaserver.court.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Court {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String areaName;
    private String placeName;
    private String imagePath;

    protected Court() {/*no-op*/}

    public Court(UUID id, String areaName, String placeName, String imagePath) {
        this.id = id;
        this.areaName = areaName;
        this.placeName = placeName;
        this.imagePath = imagePath;
    }

    public UUID getId() {
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
