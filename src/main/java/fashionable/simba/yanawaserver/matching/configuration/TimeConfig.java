package fashionable.simba.yanawaserver.matching.configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class TimeConfig {
    private static java.time.Clock clock = java.time.Clock.systemDefaultZone();

    private static ZoneOffset zoneOffset = ZoneOffset.UTC;

    public static LocalDateTime dateTimeOfNow() {
        return LocalDateTime.now(clock);
    }

    public static LocalTime timeOfNow() {
        return LocalTime.now(clock);
    }

    public static LocalDate dateOfNow() {
        return LocalDate.now(clock);
    }

    public static void timeFixed(LocalDateTime dateTime) {
        clock = java.time.Clock.fixed(dateTime.atOffset(zoneOffset).toInstant(), zoneOffset);
    }

    public static void reset() {
        clock = java.time.Clock.systemDefaultZone();
    }
}
