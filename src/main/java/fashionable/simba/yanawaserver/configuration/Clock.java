package fashionable.simba.yanawaserver.configuration;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class Clock {

    private java.time.Clock clock = java.time.Clock.systemDefaultZone();
    private final ZoneOffset zoneOffset = ZoneOffset.of("+09:00");

    public LocalDateTime dateTimeOfNow() {
        return LocalDateTime.now(clock);
    }

    public void timeFixed(LocalDateTime dateTime) {
        clock = java.time.Clock.fixed(dateTime.atOffset(zoneOffset).toInstant(), zoneOffset);
    }

    public void reset() {
        clock = java.time.Clock.systemDefaultZone();
    }
}
