package fashionable.simba.yanawaserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

@Configuration
public class TimeConfig {
    private java.time.Clock clock = java.time.Clock.systemDefaultZone();
    private ZoneOffset zoneOffset = ZoneOffset.of("+09:00");

    @Bean
    public LocalDateTime dateTimeOfNow() {
        return LocalDateTime.now(clock);
    }

    @Bean
    public LocalTime timeOfNow() {
        return LocalTime.now(clock);
    }

    @Bean
    public LocalDate dateOfNow() {
        return LocalDate.now(clock);
    }

    @Bean
    public void timeFixed(LocalDateTime dateTime) {
        clock = java.time.Clock.fixed(dateTime.atOffset(zoneOffset).toInstant(), zoneOffset);
    }

    @Bean
    public void reset() {
        clock = java.time.Clock.systemDefaultZone();
    }
}
