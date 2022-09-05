package fashionable.simba.yanawaserver.fixture;

import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.domain.Matching;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchingFixture {
    public static final LocalDate FIXTURE_DATE = LocalDate.of(2022, 7, 29);
    public static final LocalTime FIXTURE_START_TIME = LocalTime.of(19, 0);
    public static final LocalTime FIXTURE_END_TIME = LocalTime.of(21, 0);



    public static final Matching 진행중인매칭 = new Matching.Builder()
            .id(1L)
            .courtId(1L)
            .hostId(1L)
            .date(FIXTURE_DATE)
            .startTime(FIXTURE_START_TIME)
            .endTime(FIXTURE_END_TIME)
            .status(MatchingStatusType.WAITING)
            .build();

    public static final Matching 끝난매칭 = new Matching.Builder()
            .id(2L)
            .courtId(2L)
            .hostId(2L)
            .date(FIXTURE_DATE)
            .startTime(FIXTURE_START_TIME)
            .endTime(FIXTURE_END_TIME)
            .status(MatchingStatusType.FINISHED)
            .build();
}
