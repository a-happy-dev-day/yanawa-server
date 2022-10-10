package fashionable.simba.yanawaserver.court.dto;

import java.util.UUID;

public class CourtResponse {
    private UUID id;
    private String name;
    private String imagePath;

    public CourtResponse(UUID id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }
}
