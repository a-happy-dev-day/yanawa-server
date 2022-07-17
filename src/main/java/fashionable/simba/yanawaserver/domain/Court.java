package fashionable.simba.yanawaserver.domain;

import fashionable.simba.yanawaserver.error.InvaildCourtLocation;
import fashionable.simba.yanawaserver.error.InvaildCourtNameException;
import fashionable.simba.yanawaserver.error.NoCourtDataException;

import java.util.UUID;

public class Court {
    private final UUID id;
    private String name;
    private String location;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Court(CourtBuilder builder) {
        if (builder.id == null) {
            throw new NoCourtDataException();
        }
        if (builder.name == null || builder.name.trim().isEmpty() || builder.name.length() > 15) {
            throw new InvaildCourtNameException();
        }
        if (builder.location == null || builder.location.trim().isEmpty()){
            throw new InvaildCourtLocation();
        }

        this.id = builder.id;
        this.name = builder.name;
        this.location = builder.location;
    }

    public static class CourtBuilder {
        private final UUID id;
        private String name;
        private String location;

        public CourtBuilder(UUID id) {
            this.id = id;
        }

        public CourtBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CourtBuilder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Court build() {
            return new Court(this);
        }
    }
}
