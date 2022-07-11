package fashionable.simba.yanawaserver.domain;

import fashionable.simba.yanawaserver.error.InvaildCourtNameException;

import java.util.UUID;

public class Court {
    private final UUID id;
    private final String name;
    private final String location;

    public Court(UUID id, String name, String location) {
        if (name.isEmpty() || name.length() > 15) {
            throw new InvaildCourtNameException();
        }
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

}
