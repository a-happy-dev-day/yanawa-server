package fashionable.simba.yanawaserver.court.dto;

public class CourtResponse {
    private Long id;
    private String name;
    private String imagePath;

    public CourtResponse(Long id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }
}
