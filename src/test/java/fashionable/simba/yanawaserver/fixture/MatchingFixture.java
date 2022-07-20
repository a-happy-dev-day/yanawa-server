package fashionable.simba.yanawaserver.fixture;

import fashionable.simba.yanawaserver.domain.Level;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class MatchingFixture {
    public static final UUID COURT_ID = UUID.randomUUID();
    public static final UUID MATCHING_ID = UUID.randomUUID();
    public static final LocalDate DATE = LocalDate.of(2022, 7, 29);
    public static final LocalTime START_TIME = LocalTime.of(19, 0);
    public static final LocalTime END_TIME = LocalTime.of(21, 0);
    public static final Level LEVEL = new Level(5.0, 2.5);
    public static final String DETAILS = "안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.";
    public static final UUID HOST_ID = UUID.randomUUID();

}
