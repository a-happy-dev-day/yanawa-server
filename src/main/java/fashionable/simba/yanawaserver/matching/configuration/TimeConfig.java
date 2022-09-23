package fashionable.simba.yanawaserver.matching.configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class TimeConfig {
    // 디폴트로 운영체제에 설정된 타임존과 시간을 기준으로 Clock을 반환한다.
    private static java.time.Clock clock = java.time.Clock.systemDefaultZone();

    // Clock을 모킹할 때 쓸 timezone인데 이 포스트에서는 timezone을 뛰어넘은 모킹은 다루지 않으므로 운영체제에 설정된 UTC 타임존을 사용하겠다.
    private static ZoneOffset zoneOffset = ZoneOffset.UTC;

    // 내가 모킹한, 혹은 현재 시간을 가진 Clock 인스턴스를 가지고 현재 시간을 구하게 된다.
    public static LocalDateTime dateTimeOfNow() {
        return LocalDateTime.now(clock);
    }

    public static LocalTime timeOfNow() {
        return LocalTime.now(clock);
    }

    public static LocalDate dateOfNow() {
        return LocalDate.now(clock);
    }

    // 지정한 날짜/시간으로 현재 시간을 고정시킨다.
    public static void timeFixed(LocalDateTime dateTime) {
        clock = java.time.Clock.fixed(dateTime.atOffset(zoneOffset).toInstant(), zoneOffset);
    }

    public static void timeFixed(LocalTime time) {
        // 여기서 중요한 건 시간이고 날짜는 중요치 않다.
        clock = java.time.Clock.fixed(time.atDate(LocalDate.now()).atOffset(zoneOffset).toInstant(), zoneOffset);
    }

    public static void timeFixed(LocalDate date) {
        // 여기서 중요한 건 날짜고 시간은 중요치 않다.
        clock = java.time.Clock.fixed(date.atStartOfDay().atOffset(zoneOffset).toInstant(), zoneOffset);
    }

    // 모킹한 현재 시간을 다시 원래 현재 시간으로 되돌리는 메소드이다.
    public static void reset() {
        clock = java.time.Clock.systemDefaultZone();
    }
}
