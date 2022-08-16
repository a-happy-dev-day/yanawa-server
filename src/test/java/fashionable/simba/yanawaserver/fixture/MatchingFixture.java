package fashionable.simba.yanawaserver.fixture;

import fashionable.simba.yanawaserver.matching.constant.*;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.Matching;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchingFixture {
    public static final Long FIXTURE_COURT_ID = 1L;
    public static final Long FIXTURE_MATCHING_ID = 1L;
    public static final Long FIXTURE_HOST_ID = 1L;

    public static final LocalDate FIXTURE_DATE = LocalDate.of(2022, 7, 29);
    public static final LocalTime FIXTURE_START_TIME = LocalTime.of(19, 0);
    public static final LocalTime FIXTURE_END_TIME = LocalTime.of(21, 0);
    public static final AnnualType FIXTURE_ANNUAL_TYPE = AnnualType.TWO_YEARS_LESS;
    public static final AgeGroupType FIXTURE_AGE_GROUP_TYPE = AgeGroupType.TWENTIES;
    public static final GenderType FIXTURE_GENDER_TYPE = GenderType.MALE;
    public static final PreferenceType FIXTURE_PREFERENCE_TYPE = PreferenceType.MATCHING;
    public static final Integer FIXTURE_NUMBER_OF_RECRUITMENT = 4;
    public static final Double FIXTURE_COST_OF_COURT_PER_PERSON = 2.5;
    public static final MatchingStatusType FIXTURE_MATCHING_STATUS_TYPE = MatchingStatusType.ONGOING;

    public static final Level FIXTURE_MAXIMUM_LEVEL = new Level(5.0);
    public static final Level FIXTURE_MINIMUM_LEVEL = new Level(2.5);
    public static final String FIXTURE_DETAILS = "안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.";

    public static final Matching fixtureMatching = new Matching.MatchingBuilder()
            .setMatchingId(FIXTURE_MATCHING_ID)
            .setCourtId(FIXTURE_COURT_ID)
            .setHostId(FIXTURE_HOST_ID)
            .setDate(FIXTURE_DATE)
            .setStartTime(FIXTURE_START_TIME)
            .setEndTime(FIXTURE_END_TIME)
            .setAnnual(FIXTURE_ANNUAL_TYPE)
            .setMaximumLevel(FIXTURE_MAXIMUM_LEVEL)
            .setMinimumLevel(FIXTURE_MINIMUM_LEVEL)
            .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
            .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
            .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
            .setNumberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
            .setCostOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
            .setDetails(FIXTURE_DETAILS)
            .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
            .build();
}
